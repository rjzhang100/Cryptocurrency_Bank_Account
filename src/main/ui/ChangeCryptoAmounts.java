package ui;

import model.Cryptocurrency;
import model.Event;
import model.EventLog;
import model.exceptions.InsufficientFundsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a submenu which has options to change the amounts of existing holdings
public class ChangeCryptoAmounts extends Submenus implements ActionListener {
    private JButton addButton;
    private JButton deductButton;
    private JFrame addDeductFrame;
    private Cryptocurrency selectedCrypto;
    private JPanel addDeductPane;

    //EFFECTS: Creates a frame to allow user to add or deduct values of the selected cryptocurrency
    public ChangeCryptoAmounts(Cryptocurrency selectedCrypto) {
        mainFrame.setVisible(false);
        this.selectedCrypto = selectedCrypto;
        addDeductFrame = new JFrame("Editing your " + selectedCrypto.getCryptoName() + " holding.");
        addDeductPane = new JPanel(new GridBagLayout());
        addDeductFrame.setBounds(500,500,300,200);
        addButton = makeButton("Add", 0,0,1,addDeductPane, this);
        deductButton = makeButton("Deduct",1,0,1,addDeductPane,this);
        addDeductFrame.setContentPane(addDeductPane);
        addDeductFrame.setVisible(true);
    }

    //EFFECTS: Action listener for buttons that handles button click event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addQuantity();
        } else if (e.getSource() == deductButton) {
            deductQuantity();
        }
    }

    //MODIFIES: amount
    //EFFECTS: Receives, checks and adds user inputted quantity if the input is valid
    private void addQuantity() {
        int amount;
        String inputAmount = JOptionPane.showInputDialog("How much would you like to add?");
        if (isValidInput(inputAmount, addDeductFrame)) {
            amount = Integer.parseInt(inputAmount);
            if (amount < 0) {
                handleNegativeAmount();
                return;
            }
            selectedCrypto.add(amount);
            EventLog.getInstance().logEvent(new Event("Added " + amount + " units to "
                    + selectedCrypto.getCryptoName() + " holding!"));
            JOptionPane.showMessageDialog(addDeductFrame, selectedCrypto.getCryptoName() + " has been increased "
                    + "by " + amount + " !");
        }
    }

    //MODIFIES: amount
    //EFFECTS: Receives, checks and deducts user inputted quantity if the input is valid
    private void deductQuantity() {
        int amount;
        addDeductFrame.setVisible(false);
        String inputAmount = JOptionPane.showInputDialog("By how much would you like to deduct?");
        if (isValidInput(inputAmount, addDeductFrame)) {
            amount = Integer.parseInt(inputAmount);
            if (amount < 0) {
                handleNegativeAmount();
                return;
            }
            try {
                selectedCrypto.deduct(amount);
                EventLog.getInstance().logEvent(new Event("Deducted " + amount + " units from "
                        + selectedCrypto.getCryptoName() + " holding!"));
                JOptionPane.showMessageDialog(addDeductFrame, selectedCrypto.getCryptoName() + " has been decreased "
                        + "by " + amount + " !");
            } catch (InsufficientFundsException ex) {
                addDeductFrame.setVisible(false);
                JOptionPane.showMessageDialog(addDeductFrame, "You have insufficient funds to make this change.",
                        "Insufficient Funds Error", JOptionPane.WARNING_MESSAGE);
            } finally {
                addDeductFrame.setVisible(true);
            }
        }
    }

    //EFFECTS: Method to handle a negative integer input, displaying error message
    private void handleNegativeAmount() {
        addDeductFrame.setVisible(false);
        JOptionPane.showMessageDialog(addDeductFrame, "Cannot alter by a negative amount",
                "Negative Amount Error", JOptionPane.WARNING_MESSAGE);
        addDeductFrame.setVisible(true);
    }

}
