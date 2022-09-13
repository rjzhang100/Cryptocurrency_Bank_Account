package ui;

import model.Account;
import model.Cryptocurrency;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//Represents the first submenu which the user can enter from the main menu
public class Submenus extends CryptoAccount {
    protected JFrame submenuFrame;
    protected DefaultListModel<Cryptocurrency> model;
    private JList<Cryptocurrency> holdingsList;
    private JLabel instructionLabel;
    protected JButton addHoldingButton;
    protected JButton removeHoldingButton;
    protected JPanel subPanel;

    //EFFECTS: Empty constructor that reshows the mainframe
    public Submenus() {
        mainFrame.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Parameterized constructor which creates a new frame, with lists and buttons
    public Submenus(String frameName, int x, int y, int width, int height) {
        mainFrame.setVisible(false);
        submenuFrame = new JFrame(frameName);
        subPanel = new JPanel(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        instructionLabel = new JLabel("Double click on a holding to edit it", SwingConstants.CENTER);
        subPanel.add(instructionLabel, constraints);
        submenuFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setupJLists();
        setupButtons();
        submenuFrame.setBounds(x, y, width, height);
        submenuFrame.setContentPane(subPanel);
        submenuFrame.setVisible(true);
    }

    //EFFECTS: Helper method to setup the add/remove buttons
    private void setupButtons() {
        addHoldingButton = makeButton("Add a new holding",0,3,2, subPanel, this);
        removeHoldingButton = makeButton("Remove an existing holding", 0,4,2, subPanel, this);
    }

    //EFFECTS: Helper method to create and add a JList to the frame
    private void setupJLists() {
        holdingsList = makeList();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        subPanel.add(holdingsList, constraints);
    }

    //EFFECTS: Helper method to convert the holdings list into a JList
    protected JList<Cryptocurrency> makeList() {
        //Class that formats layout of cells in JList
        class CryptoCellRenderer extends DefaultListCellRenderer {
            //EFFECTS: Returns a list cell with the desired structure
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Cryptocurrency label = (Cryptocurrency) value;
                String cryptoName = label.getCryptoName();
                int quantity = label.getQuantity();
                String labelText = "<html>" + cryptoName + "</br>" + ":" + quantity;
                setText(labelText);
                return this;
            }
        }

        JList<Cryptocurrency> temp = new JList<>();
        model = new DefaultListModel<>();
        for (int i = 0; i < super.getAccountState().getCryptoTypes().size(); i++) {
            model.addElement(super.getAccountState().getCryptoTypes().get(i));
        }
        temp.setModel(model);
        temp.setCellRenderer(new CryptoCellRenderer());
        temp.addMouseListener(new MouseClickListener());
        return temp;
    }

    //EFFECTS: Return the current instance of the account
    public Account getAccountState() {
        return super.getAccountState();
    }

    //EFFECTS: Action listener for the buttons, handling event of button click
    @Override
    public void actionPerformed(ActionEvent e) {
        AddRemoveCrypto adderRemover = new AddRemoveCrypto();
        if (e.getSource() == addHoldingButton) {
            adderRemover.handleAdd();
        } else if (e.getSource() == removeHoldingButton) {
            adderRemover.handleRemove();
        }
    }

    //EFFECTS: Checks that the input is of valid integer
    public boolean isValidInput(String input, JFrame parentFrame) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentFrame, "Please enter an integer quantity",
                    "Invalid Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: Set the current holdings list to a new one
    public void setHoldingsList(JList<Cryptocurrency> newList) {
        holdingsList = newList;
    }
}
