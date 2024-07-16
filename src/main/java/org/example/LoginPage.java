package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {

    JLabel username = new JLabel("username: ");
    JLabel password = new JLabel("password: ");
    JLabel email = new JLabel("email: ");

    JTextField usernameInput = new JTextField(3);
    JTextField passwordInput = new JTextField(3);
    JTextField emailInput = new JTextField();

    JButton login = new JButton("Login");
    JButton create = new JButton("Create Account");
    JButton forgot = new JButton("Forgot password?");

    public LoginPage(CardLayout cardLayout, JPanel cardPanel) {

        JPanel panel = new JPanel(new GridLayout(3, 1)); // Using 4 rows for username, password, email, and buttons

        // creating the row for the USERNAME label and text field
        JPanel usernameRow = new JPanel(new GridLayout(1, 2));
        usernameRow.add(username);
        usernameRow.add(usernameInput);
        panel.add(usernameRow);

        // creating the row for the PASSWORD label and text field
        JPanel passwordRow = new JPanel(new GridLayout(1, 2));
        passwordRow.add(password);
        passwordRow.add(passwordInput);
        panel.add(passwordRow);

        // add buttons
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(login);
        buttons.add(create);
        buttons.add(forgot);
        panel.add(buttons);

        // none of this works
        Dimension textFieldSize = new Dimension(100, 25); // Change the dimensions as needed
        usernameInput.setPreferredSize(textFieldSize);
        passwordInput.setPreferredSize(textFieldSize);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        login.addActionListener(this);
        create.addActionListener(this);
        forgot.addActionListener(this);

        cardPanel.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks here
    }

    public static void main(String[] args) {
        new LoginPage(new CardLayout(), new JPanel());
    }
}
