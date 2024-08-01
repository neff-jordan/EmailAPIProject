package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;

public class LoginPage extends Layout implements ActionListener, ComponentListener {

    private JTextField userIDField = new JTextField();
    private JPasswordField userPasswordField = new JPasswordField();
    private JLabel userIDLabel = new JLabel("username:");
    private JLabel userPasswordLabel = new JLabel("password:");
    private JLabel messageLabel = new JLabel();
    private JButton loginButton = new JButton("Login");
    private JButton createButton = new JButton("Create Account");
    private JButton resetButton = new JButton("Reset");
    MySQLConnection connection = MySQLConnection.getInstance();


    public LoginPage(CardLayout cardLayout, JPanel cardPanel, MySQLConnection connection) throws SQLException {

        super(cardLayout, cardPanel);
        this.connection = connection;

        this.addComponentListener(this);

        // Set layout to null for absolute positioning
        this.setLayout(null);

        userIDLabel.setBounds(113, 170, 75, 25);
        userPasswordLabel.setBounds(113, 220, 75, 25);
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        userIDField.setBounds(188, 170, 200, 25);
        userPasswordField.setBounds(188, 220, 200, 25);
        loginButton.setBounds(65, 320, 125, 50);
        createButton.setBounds(195, 320, 125, 50);
        resetButton.setBounds(325, 320, 125, 50);

        loginButton.setFocusable(false);
        createButton.setFocusable(false);
        resetButton.setFocusable(false);

        loginButton.addActionListener(this);
        createButton.addActionListener(this);
        resetButton.addActionListener(this);

        this.add(userIDLabel);
        this.add(userPasswordLabel);
        this.add(messageLabel);
        this.add(userIDField);
        this.add(userPasswordField);
        this.add(loginButton);
        this.add(createButton);
        this.add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == createButton) {

            cardLayout.show(cardPanel, "CreateAccount");
        }

        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            MySQLConnection connect = null;
            try {
                connect = MySQLConnection.getInstance();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (connect.containsUsername(userID)) {
                if (connect.getPassword(userID).equals(password)) {
                    CurrentUser.setInstance(userID);
                    System.out.println("Login successful");

                    // Switch to HomePage
                    HomePage homePage = null;
                    try {
                        homePage = new HomePage(cardLayout, cardPanel, userID, connection);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    cardPanel.add(homePage, "HomePage");
                    cardLayout.show(cardPanel, "HomePage");
                    switchToCard("HomePage");

                } else {
                    JOptionPane.showMessageDialog(this,"Login unsuccessful. Wrong password.");

                }
            } else {
                JOptionPane.showMessageDialog(this,"Login unsuccessful. Wrong username.");
            }
        }
    }

    public void switchToCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
        cardPanel.revalidate();
        cardPanel.repaint();
    }


    @Override
    public void componentShown(ComponentEvent e) {
        resetFields();
    }
    private void resetFields() {
        userIDField.setText("");
        userPasswordField.setText("");
        messageLabel.setText("");
    }

    @Override
    public void componentResized(ComponentEvent e) {}
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}

}