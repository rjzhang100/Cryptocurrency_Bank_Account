package ui;

import java.util.ArrayList;

public class UpdateHoldingsUI extends Submenus {

    public UpdateHoldingsUI() {
        userInput = 0;
        menuOptions.clear();
    }

    @Override
    protected void setupMenuOptions() {
        menuOptions.add("Update quantity of existing holdings");
        menuOptions.add("Add or remove a type of cryptocurrency");
    }

    @Override
    protected void runSub() {
        setupMenuOptions();
        userInput = displayMenuOptions(menuOptions);
        switch (userInput) {
            case 1:
                //stub
                break;
            case 2:
                AddRemoveCrypto adderRemover = new AddRemoveCrypto();
                adderRemover.runSub();
                break;
        }
    }
}
