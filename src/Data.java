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

    public void setCipherMessage(String cipherMessage) {
        this.cipherMessage = cipherMessage;
    }

    public String getCipherNoiseMessage() {
        return cipherNoiseMessage;
    }

    public void setCipherNoiseMessage(String cipherNoiseMessage) {
        this.cipherNoiseMessage = cipherNoiseMessage;
    }
}
