package ui;

import model.Account;
import model.Cryptocurrency;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Iterator;

//Represents the main UI and processes initial user inputs
public class CryptoAccount extends JFrame implements ActionListener {
    public static final String JSON_LOCATION = "./data/account.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    protected static Account account = new Account();
    private JButton updateInfoButton;
    private JButton seePredictionsButton;
    private JButton seeDataButton;
    private JButton saveButton;
    private JButton loadButton;
    protected JFrame mainFrame;
    protected GridBagConstraints constraints;
    private JPanel panel;
    private ShowData dataShower;

    //EFFECTS: Constructs a new JFrame with the buttons for the main menu
    public CryptoAccount() {
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        setupButtons();
        setupJFrame();
        setupWindowListener();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
    }

    //EFFECTS: Helper method that creates new buttons
    private void setupButtons() {
        updateInfoButton = makeButton("Edit or view my holdings",0,2,2, panel, this);
        seePredictionsButton = makeButton("See predictions about my holdings", 0,3,2, panel, this);
        seeDataButton = makeButton("See historical data about my holdings", 0,4,2, panel, this);
        saveButton = makeButton("Save my account", 0,5,2, panel, this);
        loadButton = makeButton("Load my account", 0,6,2, panel, this);
    }

    //EFFECTS: Method that creates a new button based on specified parameters and adds it to the declared JPanel
    protected JButton makeButton(String buttonName, int gridx, int gridy, int gridwidth, JPanel panel,
                                 ActionListener actionListener) {
        JButton temp = new JButton(buttonName);
        temp.addActionListener(actionListener);
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        panel.add(temp, constraints);
        return temp;
    }

    //EFFECTS: Helper method that creates the empty JFrame for the main menu
    private void setupJFrame() {
        mainFrame = new JFrame("Welcome to your Cryptocurrency Account!");
        mainFrame.setContentPane(panel);
        mainFrame.setBounds(0,0,1000,1000);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //EFFECTS: Action listener for all buttons in the main menu, handling event based on what button was pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setVisible(false);
        if (e.getSource() == updateInfoButton) {
            Submenus submenu = new Submenus("View and Edit",1000,1000,500,500);
            mainFrame.setVisible(true);
        } else if (e.getSource() == seePredictionsButton) {
            JOptionPane.showMessageDialog(mainFrame, "Sorry, this feature is still in work. Come back later!");
            mainFrame.setVisible(true);
        } else if (e.getSource() == seeDataButton) {
            JOptionPane.showMessageDialog(mainFrame, "Satisfying graphical component for Phase 3, "
                    + "only Bitcoin and ugly");
            dataShower = new ShowData();
            mainFrame.setVisible(true);
        } else if (e.getSource() == saveButton) {
            handleSaving();
        } else if (e.getSource() == loadButton) {
            handleLoading();
        }
    }

    //EFFECTS: Method to handle saving the account information to JSON
    private void handleSaving() {
        try {
            jsonWriter.openFile();
            jsonWriter.writeToFile(account);
            jsonWriter.close();
            JOptionPane.showMessageDialog(mainFrame, "Your data has been saved to" + JSON_LOCATION + "!");
            logSaving();
            mainFrame.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainFrame, "An error occurred saving your file.",
                    "Saving Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: Iterates through holding list and logs saved cryptocurrencies
    // To only be used immediately after saving
    private void logSaving() {
        for (int i = 0; i < account.getCryptoTypes().size(); i++) {
            Cryptocurrency indexedCrypto = account.getCryptoTypes().get(i);
            EventLog.getInstance().logEvent(new Event("Saved " + indexedCrypto.getQuantity()
                    + " units of " + indexedCrypto.getCryptoName() + " to " + JSON_LOCATION + "!"));
        }
    }

    //EFFECTS: Iterates through holding list and logs loaded cryptocurrencies
    // To only be used immediately after loading
    private void logLoading() {
        for (int i = 0; i < account.getCryptoTypes().size(); i++) {
            Cryptocurrency indexedCrypto = account.getCryptoTypes().get(i);
            EventLog.getInstance().logEvent(new Event("Loaded " + indexedCrypto.getQuantity()
                    + " units of " + indexedCrypto.getCryptoName() + " from " + JSON_LOCATION + "!"));
        }
    }

    //EFFECTS: Method that loads the account information from JSON
    private void handleLoading() {
        try {
            account = jsonReader.read();
            JOptionPane.showMessageDialog(mainFrame, "Your data has been loaded from" + JSON_LOCATION + "!");
            logLoading();
            mainFrame.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainFrame, "An error occurred loading your file.",
                    "Loading Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //EFFECTS: Getter method to return the current account instance
    public Account getAccountState() {
        return account;
    }

    //EFFECTS: Adds a WindowListener to handle closing event, prints log to the console on exit
    private void setupWindowListener() {
        //Window listener that overrides the windowClosing method to print log to console when exit button clicked
        class WindowListener extends WindowAdapter {
            Iterator<Event> itr;

            //EFFECTS: Overrides windowClosing to print log when exit button pressed
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                itr = EventLog.getInstance().iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
                System.exit(0);
            }
        }

        mainFrame.addWindowListener(new WindowListener());
    }
}
