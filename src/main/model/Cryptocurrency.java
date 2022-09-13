package model;

import model.exceptions.InsufficientFundsException;
import org.json.JSONObject;

//Represents a cryptocurrency holding
public class Cryptocurrency {
    private int quantity;
    private String cryptoName;

    //MODIFIES: this
    //EFFECTS: Defines quantity and name of the cryptocurrency
    public Cryptocurrency(int quantity, String cryptoName) {
        this.quantity = quantity;
        this.cryptoName = cryptoName;
    }

    //EFFECTS: Returns quantity of given cryptocurrency
    public int getQuantity() {
        return quantity;
    }

    //EFFECTS: Returns name of given cryptocurrency
    public String getCryptoName() {
        return cryptoName;
    }

    //REQUIRES: A positive integer
    //MODIFIES: this
    //EFFECTS: Sets quantity of cryptocurrency to the desired input
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //MODIFIES: this
    //EFFECTS: Sets name of cryptocurrency to the desired input
    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    //REQUIRES: A positive integer
    //MODIFIES: this
    //EFFECTS: Adds the inputted amount to the current quantity of a given cryptocurrency
    public void add(int amount) {
        quantity = quantity + amount;
    }

    //REQUIRES: A positive integer
    //MODIFIES: this
    //EFFECTS: Deducts the inputted amount from the current quantity of a given cryptocurrency
    public void deduct(int amount) throws InsufficientFundsException {
        if (amount > quantity) {
            throw new InsufficientFundsException();
        }
        quantity = quantity - amount;
    }

    //EFFECTS: Converts the cryptocurrency to a JSON object and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Crypto Name", cryptoName);
        json.put("Amount", quantity);
        return json;
    }
}
