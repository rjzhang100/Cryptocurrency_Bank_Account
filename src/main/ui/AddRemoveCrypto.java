package ui;

import model.Cryptocurrency;
import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the submenu which can add or remove a cryptocurrency holding
public class AddRemoveCrypto extends Submenus implements ActionListener {
    private String cryptoQuantity;
    private String cryptoName;
    private Submenus submenu;
    private int quantity;

    //EFFECTS: Constructor which uses Submenus' empty constructor
    public AddRemoveCrypto() {
        submenu = new Submenus();
    }

    //EFFECTS: Action listener for either add or remove holdings buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addHoldingButton) {
            handleAdd();
        } else if (e.getSource() == removeHoldingButton) {
            handleRemove();
        }
    }

    //EFFECTS: Receives user information about the new holding, creates it and adds it to the list
    public void handleAdd() {
        cryptoName = JOptionPane.showInputDialog("Please enter the name of the holding you'd like to add.");
        cryptoQuantity = JOptionPane.showInputDialog("Please enter the quantity of the holding.");
        if (isValidInput(cryptoQuantity,submenuFrame)) {
            quantity = Integer.parseInt(cryptoQuantity);
            submenu.getAccountState().getCryptoTypes().add(new Cryptocurrency(quantity, cryptoName));
            EventLog.getInstance().logEvent(new Event("Added " + quantity + " units of " + cryptoName + "!"));
            JOptionPane.showMessageDialog(submenuFrame, "Okay, " + cryptoName + " has been added!");
            setHoldingsList(makeList());
            SwingUtilities.updateComponentTreeUI(submenuFrame);
        }
    }

    //EFFECTS: Receive user input as a string for which crypto to remove, checks that it is in the list and removes it
    public void handleRemove() {
        Cryptocurrency cryptoToRemove = null;
        cryptoName = JOptionPane.showInputDialog("Please enter the name of the holding you'd like to remove");
        for (int i = 0; i < submenu.getAccountState().getCryptoTypes().size(); i++) {
            if (cryptoName.equals(submenu.getAccountState().getCryptoTypes().get(i).getCryptoName())) {
                cryptoToRemove = submenu.getAccountState().getCryptoTypes().get(i);
            }
        }
        if (cryptoToRemove == null) {
            JOptionPane.showMessageDialog(submenuFrame, "You do not have this holding.",
                    "Selection not Found Error", JOptionPane.ERROR_MESSAGE);
        } else {
            submenu.getAccountState().getCryptoTypes().remove(cryptoToRemove);
            EventLog.getInstance().logEvent(new Event("Removed " + cryptoName + " holding."));
            JOptionPane.showMessageDialog(submenuFrame, "Okay, " + cryptoName + " has been removed!");
            makeList();
            subPanel.repaint();
            subPanel.revalidate();
        }
    }
}
