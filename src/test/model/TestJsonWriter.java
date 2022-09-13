package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter {

    @Test
    public void testFileNotExist() {
        try {
            JsonWriter testWriter = new JsonWriter("./data/\0bad::fileName1818.json");
            testWriter.openFile();
            fail("Exception should have been thrown");
        } catch (FileNotFoundException e) {
        }
    }

    @Test
    public void testWriteToEmptyFile() {
        try {
            Account temp;
            JsonWriter testWriter = new JsonWriter("./data/testWriterEmptyAccount.json");
            testWriter.openFile();
            testWriter.writeToFile(new Account());
            testWriter.close();
            JsonReader testReader = new JsonReader("./data/testWriterEmptyAccount.json");
            temp = testReader.read();
            assertEquals(0, temp.getCryptoTypes().size());
        } catch (IOException e) {
            fail("No exception should have been thrown here");
        }
    }

    @Test
    public void testWriteToOccupiedFile() {
        try {
            Account account = new Account();
            Account temp;
            JsonWriter testWriter = new JsonWriter("./data/testWriterGeneralAccount.json");
            account.getCryptoTypes().add(new Cryptocurrency(10, "Test Coin 1"));
            account.getCryptoTypes().add(new Cryptocurrency(10, "Test Coin 2"));
            testWriter.openFile();
            testWriter.writeToFile(account);
            testWriter.close();
            JsonReader testReader = new JsonReader("./data/testWriterGeneralAccount.json");
            temp = testReader.read();
            assertEquals(2, temp.getCryptoTypes().size());
        } catch (IOException e) {
            fail("No exception should have been thrown here");
        }
    }
}
