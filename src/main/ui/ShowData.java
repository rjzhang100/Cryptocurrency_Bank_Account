package ui;

import javax.swing.*;
import java.awt.*;

//Class which displays a graph of the data (VISUAL COMPONENT)
public class ShowData {
    private JFrame displayFrame;

    //Constructor which loads and display a scaled image
    public ShowData() {
        displayFrame = new JFrame("Historical Data For Bitcoin");
        displayFrame.setBounds(1000,1000,500, 500);
        displayFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        ImageIcon logo = new ImageIcon("./data/graph.png");
        Image logoTemp = logo.getImage();
        Image newLogo = logoTemp.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        displayFrame.add(new JLabel(new ImageIcon(newLogo)));
        displayFrame.setVisible(true);
    }

}


