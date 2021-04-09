public class Data {
    private String cipherMessage;
    private String cipherNoiseMessage;

    public Data(String cipherMessage, String cipherNoiseMessage) {
        this.cipherMessage = cipherMessage;
        this.cipherNoiseMessage = cipherNoiseMessage;
    }

    public String getCipherMessage() {
        return cipherMessage;
    }

    public String getCipherNoiseMessage() {
        return cipherNoiseMessage;
    }
}
