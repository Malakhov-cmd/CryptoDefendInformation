import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.Exchanger;

public class User implements Runnable {
    private String password;
    private String message;

    String cipherMessage;
    String cipherNoiseMessage;

    private SecretKey key = null;

    private Exchanger<Data> exchanger;

    public User(Exchanger<Data> exchanger, String password, String message){
        this.password = password;
        this.message = message;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //1
        String hashMessage = hash(message);

        try {
            //2
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "DES");

            //3
            DesEncrypter encrypter = new DesEncrypter(secretKeySpec);
            cipherMessage = encrypter.encrypt(message);
            cipherNoiseMessage = encrypter.encrypt(hashMessage);

            //4
            exchanger.exchange(new Data(cipherMessage, cipherNoiseMessage));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Incorrect type of algorithm");
        } catch (BadPaddingException e) {
            System.out.println("The decryption key or method is incorrect, or the message may have been corrupted");
        } catch (InvalidKeyException e) {
            System.out.println("Incorrect key");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Incorrect symbol encoding? use UTF-8");
        } catch (NoSuchPaddingException e) {
            System.out.println("The decryption key or method is incorrect, and the message may have been corrupted");
        } catch (IllegalBlockSizeException e) {
            System.out.println("Incorrect size of key");
        } catch (InterruptedException e) {
            System.out.println("Error while transfer data to threads");
        }
    }

    private String hash(String str) {
        return MD2.encryptThisString(str);
    }
}
