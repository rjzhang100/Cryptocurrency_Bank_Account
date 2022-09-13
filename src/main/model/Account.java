package model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

//Class that to hold account information, handle conversion to JSON and hold the holdings list
public class Account {
    private static List<Cryptocurrency> cryptoTypes;

    //MODIFIES: this
    //EFFECTS: Construct new account with empty holdings list
    public Account() {
        cryptoTypes = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Changes the current holdings list to be a new one
    public void setCryptoTypes(List<Cryptocurrency> newList) {
        cryptoTypes = new ArrayList<>(newList);
    }

    //EFFECTS: Returns the holdings list
    public List<Cryptocurrency> getCryptoTypes() {
        return cryptoTypes;
    }

    //EFFECTS: Converts the array to a JSON object and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Account", "Your Account");
        json.put("Your Holdings", arrayToJson(cryptoTypes));
        return json;
    }

    //EFFECTS: Adds cryptocurrencies as JSON objects to a JSON array and returns it
    private JSONArray arrayToJson(List<Cryptocurrency> cryptoTypes) {
        JSONArray jsonArray = new JSONArray();
        for (Cryptocurrency c : cryptoTypes) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
