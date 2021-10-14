package ui;

import model.AccountHoldings;
import model.exceptions.NegativeAmountException;
import model.exceptions.NotInListException;

import java.util.Scanner;

public class AddRemoveCrypto extends Submenus {
    private Scanner scanIn = new Scanner(System.in);
    private String cryptoQuantity;
    private String cryptoName;
    private boolean isValid = false;
    private AccountHoldings account = new AccountHoldings();

    public AddRemoveCrypto() {
        userInput = 0;
        menuOptions.clear();
    }

    @Override
    protected void setupMenuOptions() {
        menuOptions.add("Add a new type of cryptocurrency holding");
        menuOptions.add("Remove an existing cryptocurrency holding");
        menuOptions.add("Return to previous menu");
        menuOptions.add("Quit Program");
    }

    @Override
    protected void runSub() {
        setupMenuOptions();
        userInput = displayMenuOptions(menuOptions);
        switch (userInput) {
            case 1:
                processCaseOne();
                break;
            case 2:
                processCaseTwo();
                break;
            case 3:
                UpdateHoldingsUI updateHoldings = new UpdateHoldingsUI();
                updateHoldings.runSub();
                break;
            case 4:
                System.out.println("Thank you for visiting your account today. See you soon!");
                System.exit(0);
        }
    }

    private void processCaseOne() {
        do {
            System.out.println("Please enter the name of the cryptocurrency you'd like to add: ");
            cryptoName = scanIn.nextLine();
            System.out.println("Please enter the quantity of the cryptocurrency you have: ");
            cryptoQuantity = scanIn.nextLine();
            isValid = checkValidInput(cryptoQuantity);
        } while (!isValid);
        account.addCrypto(Integer.parseInt(cryptoQuantity), cryptoName);
    }

    private void processCaseTwo() {
        do {
            System.out.println("Please enter the name of the cryptocurrency you'd like to remove: ");
            cryptoName = scanIn.nextLine();
            try {
                account.removeCryptoType(cryptoName);
                isValid = true;
            } catch (NotInListException e) {
                System.out.println("Sorry, you do not have this cryptocurrency. Please try again.");
            }
        } while (!isValid);
    }
}
