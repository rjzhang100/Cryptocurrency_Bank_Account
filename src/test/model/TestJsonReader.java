package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader {

    @Test
    public void testFileNotExist() {
        try {
            JsonReader testReader = new JsonReader("./data/nonexistent.json");
            testReader.read();
            fail("Exception should have been thrown");
        } catch (IOException ex) {
        }
    }

    @Test
    public void testReadEmptyAccount() {
        JsonReader testReader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account temp = testReader.read();
            assertEquals(0, temp.getCryptoTypes().size());
        } catch (IOException ex) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testReadGeneralAccount() {
        try {
            JsonReader testReader = new JsonReader("./data/testReaderGeneralAccount.json");
            Account temp = testReader.read();
            List<Cryptocurrency> testArray = temp.getCryptoTypes();
            assertEquals(2, testArray.size());
            assertEquals("Bitcoin", testArray.get(0).getCryptoName());
            assertEquals("Dogecoin", testArray.get(1).getCryptoName());
            assertEquals(10, testArray.get(0).getQuantity(), testArray.get(1).getQuantity());
        } catch (FileNotFoundException e) {
            fail("This file exists, but was not found. Error.");
        } catch (IOException e) {
            fail("Exception was thrown when it shouldn't be");
        }
    }
}
