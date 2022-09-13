package model;

import model.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ChangeCryptoAmounts;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestModel {
    private Cryptocurrency emptyCrypto;
    private Cryptocurrency filledCrypto;
    private ChangeCryptoAmounts tester;
    private Account account;
    private List<Cryptocurrency> testCryptoList;

    @BeforeEach
    public void setup() {
        emptyCrypto = new Cryptocurrency(0, "No quantity");
        filledCrypto = new Cryptocurrency(100, "Has quantity");
        account = new Account();
        testCryptoList = new ArrayList<>();
    }

    @Test
    public void testSetQuantity() {
        emptyCrypto.setQuantity(50);
        filledCrypto.setQuantity(100);
        assertEquals(50, emptyCrypto.getQuantity());
        assertEquals(100, filledCrypto.getQuantity());
    }

    @Test
    public void testAddCryptoQuantity() {
        emptyCrypto.add(100);
        filledCrypto.add(100);
        assertEquals(100, emptyCrypto.getQuantity());
        assertEquals(200, filledCrypto.getQuantity());
    }

    @Test
    public void testDeductCryptoQuantity() {
        try {
            filledCrypto.deduct(50);
            assertEquals(50, filledCrypto.getQuantity());
        } catch (InsufficientFundsException ex) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testCryptoNameSet() {
        emptyCrypto.setCryptoName("Bitcoin Test");
        assertEquals("Bitcoin Test", emptyCrypto.getCryptoName());
    }


    @Test
    public void testInsufficientFundsException() {
        try {
            emptyCrypto.deduct(10000);
            fail("Exception should have been thrown here");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    @Test
    public void testAccountConstructor() {
        assertTrue(testCryptoList.isEmpty());
    }

    @Test
    public void testAccountSetCryptoTypes() {
        List<Cryptocurrency> filledList = new ArrayList<>();
        filledList.add(new Cryptocurrency(10, "Test Coin 1"));
        account.setCryptoTypes(filledList);
        assertEquals(account.getCryptoTypes().size(), 1);
        assertEquals(account.getCryptoTypes().get(0).getQuantity(), 10);
        assertEquals(account.getCryptoTypes().get(0).getCryptoName(), "Test Coin 1");
    }

//    @Test
//    public void testNegativeAmountException() {
//        try {
//            emptyCrypto.deduct(-1000);
//            fail();
//        } catch (NegativeAmountException e) {
//            return;
//        } catch (InsufficientFundsException e) {
//            fail();
//        }
//    }
}
