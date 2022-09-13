package ui;

import model.Cryptocurrency;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Class which serves as an action listener for mouse clicks on the list pane
public class MouseClickListener extends MouseAdapter {
    private Submenus submenu;
    private Cryptocurrency selectedCrypto;

    //EFFECTS: Constructor which uses Submenus' empty constructor
    public MouseClickListener() {
        submenu = new Submenus();
    }

    //EFFECTS: Action listener for double mouse click, showing the change crypto amount panel if double clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JList thing = (JList) e.getSource();
            int index = thing.getSelectedIndex();
            selectedCrypto = submenu.getAccountState().getCryptoTypes().get(index);
            ChangeCryptoAmounts amountChanger = new ChangeCryptoAmounts(selectedCrypto);
        }
    }
}
