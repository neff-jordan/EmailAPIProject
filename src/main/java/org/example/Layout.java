package org.example;

import javax.security.auth.RefreshFailedException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Layout extends JPanel implements ActionListener {

    protected CardLayout cardLayout;
    protected JPanel cardPanel;

    public Layout(CardLayout cardLayout, JPanel cardPanel) {

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        //setLayout(new BorderLayout());
    }

    // Method to switch cards and repaint them
    protected void switchToCard(String cardName) throws RefreshFailedException {
        cardLayout.show(cardPanel, cardName);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)  {

    }

}
