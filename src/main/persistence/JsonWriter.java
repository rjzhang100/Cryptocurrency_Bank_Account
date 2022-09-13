package persistence;

import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import model.Account;

//Represents a writer that writes JSON representation of the holdings to the target file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: Constructs a writer which writes to the destination file
    //         throws FileNotFoundException if the file doesn't exist or isn't a file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: Opens the file
    ///        throws FileNotFoundException if the file can't be opened
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: Writes the string to the file
    private void saveToFile(String json) {
        writer.print(json);
    }

    //MODIFIES: this
    //EFFECTS: Writes the holdings list to the JSON file
    public void writeToFile(Account account) {
        JSONObject json = account.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: Closes the writer
    public void close() {
        writer.close();
    }
}
