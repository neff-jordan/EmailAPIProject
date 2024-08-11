/**
 * Abstract base class for panels using CardLayout.
 * Provides functionality for switching between different cards.
 */

package org.example;

import javax.security.auth.RefreshFailedException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Layout extends JPanel implements ActionListener {

    protected CardLayout cardLayout;
    protected JPanel cardPanel;

    /**
     * Constructor for Layout.
     *
     * @param cardLayout The CardLayout used for switching between cards.
     * @param cardPanel  The JPanel containing the different cards.
     */
    public Layout(CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

    /**
     * Switches to a specified card and updates the panel.
     *
     * @param cardName The name of the card to switch to.
     * @throws IllegalArgumentException if the cardName does not match any of the cards in the cardPanel.
     */
    protected void switchToCard(String cardName) throws RefreshFailedException {
        cardLayout.show(cardPanel, cardName);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        // This method should be overridden by subclasses to handle action events.
    }

}
