import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Exchanger;

public class Service implements Runnable {
    private String deCipherMessage;
    private String deCipherNoiseMessage;

    private String standard = "pa$$word";

    private Exchanger<Data> exchanger;

    public Service(Exchanger<Data> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Data userData = exchanger.exchange(null);

            String cipherMessage = userData.getCipherMessage();
            String cipherNoiseMessage = userData.getCipherNoiseMessage();

            SecretKeySpec secretKeySpec = new SecretKeySpec(standard.getBytes(), "DES");
            DesEncrypter encrypter = new DesEncrypter(secretKeySpec);

            deCipherMessage = encrypter.decrypt(cipherMessage);
            deCipherNoiseMessage = encrypter.decrypt(cipherNoiseMessage);

            String noise = hash(deCipherMessage);

            if (noise.equals(deCipherNoiseMessage)) {
                System.out.println("true");
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Incorrect type of algorithm");
        } catch (BadPaddingException e) {
            System.out.println("The decryption key or method is incorrect, and the message may have been corrupted");
        } catch (InvalidKeyException e) {
            System.out.println("Incorrect key");
        } catch (NoSuchPaddingException e) {
            System.out.println("The decryption key or method is incorrect, and the message may have been corrupted");
        } catch (IllegalBlockSizeException e) {
            System.out.println("Incorrect size of key");
        } catch (InterruptedException e) {
            System.out.println("Error while transfer data to threads");
        } catch (IOException e) {
            System.out.println("Thread fatal error");
        }
    }

    private String hash(String str) {
        return MD2.encryptThisString(str);
    }
}
