package persistence;

import model.Cryptocurrency;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;
import model.Account;

//Represents a reader that reads account information from JSON data stored in the target file
public class JsonReader {
    private String source;

    //EFFECTS: Constructs a JSON reader to read data from the target file
    //         throws FileNotFoundException if file doesn't exist or isn't a file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: Reads in the information of user's holdings from JSON data and returns it
    //         throws IOException if an error occurred reading the file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    private Account parseAccount(JSONObject jsonObject) {
        Account account = new Account();
        parseHoldingsList(account, jsonObject);
        return account;
    }

    //EFFECTS: Reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //MODIFIES: tempList
    //EFFECTS: Adds a cryptocurrency from the JSON object to the tempList
    private void addCryptoFromJson(Account account, JSONObject jsonObject) {
        String cryptoName = jsonObject.getString("Crypto Name");
        int amount = jsonObject.getInt("Amount");
        Cryptocurrency someCrypto = new Cryptocurrency(amount, cryptoName);
        account.getCryptoTypes().add(someCrypto);
    }

    //MODIFIES: tempList
    //EFFECTS: Iterates through the list from JSON and adds each element to the holdings list
    private void parseHoldingsList(Account account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Your Holdings");
        for (Object json : jsonArray) {
            JSONObject nextObject = (JSONObject) json;
            addCryptoFromJson(account, nextObject);
        }
    }

}
