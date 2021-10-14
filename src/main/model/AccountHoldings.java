package model;

import model.exceptions.NegativeAmountException;
import model.exceptions.NotInListException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class AccountHoldings {
    private ArrayList<Cryptocurrency> cryptoTypes;

    public AccountHoldings() {
        cryptoTypes = new ArrayList<Cryptocurrency>();
    }

    public void addCrypto(int quantity, String name) {
        Cryptocurrency someCrypto = new Cryptocurrency(quantity, name);
        cryptoTypes.add(someCrypto);
    }

    public void removeCryptoType(String crypto) throws NotInListException {
        boolean inList = false;
        for (int i = 0; i < cryptoTypes.size(); i++) {
            if (Objects.equals(crypto, cryptoTypes.get(i).getCryptoName())) {
                cryptoTypes.remove(i);
                inList = true;
                break;
            }
        }
        if (!inList) {
            throw new NotInListException();
        }
    }
}
