package ui;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Submenus {
    private Scanner scanIn = new Scanner(System.in);
    protected int userInput;
    protected ArrayList<String> menuOptions = new ArrayList<String>();
    protected CryptoAccount mainMenu = new CryptoAccount();

    protected int displayMenuOptions(ArrayList<String> optionsList) {
        System.out.println("What would you like to do? (Enter the corresponding integer to select option)");
        for (int i = 0; i < optionsList.size(); i++) {
            System.out.println((i + 1) + " - " + optionsList.get(i));
        }
        userInput = scanIn.nextInt();
        return userInput;
    }

    protected boolean checkValidInput(String userInput) {
        int userInt;
        try {
            userInt = Integer.parseInt(userInput);
            if (userInt < 0) {
                System.out.println("You cannot have a negative amount. Please try again.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer quantity.");
            return false;
        }
        return true;
    }

    protected abstract void setupMenuOptions();

    protected abstract void runSub();
}
