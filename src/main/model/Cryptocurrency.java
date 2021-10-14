package model;

public class Cryptocurrency {
    private int quantity;
    private String cryptoName;

    public Cryptocurrency(int quantity, String cryptoName) {
        this.quantity = quantity;
        this.cryptoName = cryptoName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }
}
