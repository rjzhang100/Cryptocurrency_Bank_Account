package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CryptoAccount {
    private Scanner scanIn = new Scanner(System.in);
    private int userInput;
    private ArrayList<String> menuOptions = new ArrayList<String>();
    private boolean badInput = true;

    public CryptoAccount() {
        menuOptions.add("Update information about my holdings");
        menuOptions.add("Get information about my holdings");
        menuOptions.add("See predictions about my holdings");
        menuOptions.add("See historical data about my holdings");
        menuOptions.add("Quit");
    }

    public void runProgram() {
        System.out.println("<<<WELCOME TO YOUR CRYPTOCURRENCY ACCOUNT!>>>");
        do {
            System.out.println("MAIN MENU");
            for (int i = 0; i < menuOptions.size(); i++) {
                System.out.println((i + 1) + " - " + menuOptions.get(i));
            }
            try {
                System.out.println("What would you like to do? (Enter the corresponding integer to select option)");
                userInput = Integer.parseInt(scanIn.nextLine());
                if ((userInput <= 0) || (userInput > menuOptions.size())) {
                    System.out.println("ERROR -- INTEGER OUT OF RANGE. Try Again!");
                } else {
                    badInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR -- NON INTEGER INPUT FOUND. Try Again!");
            }
            parseUserInput(userInput);
        } while (badInput);
    }

    private void parseUserInput(int userInput) {
        switch (userInput) {
            case 1:
                UpdateHoldingsUI updateHoldings = new UpdateHoldingsUI();
                updateHoldings.runSub();
                break;
            case 5:
                System.out.println("Thank you for visiting your account today. See you soon!");
                System.exit(0);
        }
    }
}
