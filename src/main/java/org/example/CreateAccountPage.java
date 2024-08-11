/**
 * This class represents a GUI page for creating a new user account.
 * It includes form fields for the user's name, username, password,
 * and email, along with buttons to submit the form or go back to
 * the login page.
 */

package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class CreateAccountPage extends Layout implements ActionListener {

    JLabel name = new JLabel("name: ");
    JLabel username = new JLabel("username: ");
    JLabel password = new JLabel("password: ");
    JLabel email = new JLabel("email: ");

    JTextField nameInput = new JTextField();
    JTextField usernameInput = new JTextField();
    private JPasswordField pass = new JPasswordField();
    JTextField emailInput = new JTextField();

    private JButton submit = new JButton("Submit");
    private JButton back = new JButton("Back");

    MySQLConnection connection = MySQLConnection.getInstance();

    /**
     * Constructor that sets up the CreateAccountPage layout and components.
     *
     * @param cardLayout The CardLayout used for switching between different pages.
     * @param cardPanel  The main JPanel containing the different pages.
     * @param connection The MySQLConnection instance for database operations.
     * @throws SQLException if a database access error occurs.
     */
    public CreateAccountPage(CardLayout cardLayout, JPanel cardPanel, MySQLConnection connection) throws SQLException {

        super(cardLayout, cardPanel);
        this.connection = connection;

        this.setLayout(null);

        name.setBounds(98, 100, 100, 25);
        username.setBounds(98, 150, 100, 25);
        password.setBounds(98, 200, 100, 25);
        email.setBounds(98, 250, 120, 25);

        nameInput.setBounds(223, 100, 200, 25);
        usernameInput.setBounds(223, 150, 200, 25);
        pass.setBounds(223, 200, 200, 25);
        emailInput.setBounds(223, 250, 200, 25);

        submit.setBounds(210, 450, 100, 25);
        submit.addActionListener(this);
        back.setBounds(210, 400, 100, 25);
        back.addActionListener(this);

        this.add(name);
        this.add(username);
        this.add(password);
        this.add(email);

        this.add(nameInput);
        this.add(usernameInput);
        this.add(pass);
        this.add(emailInput);

        this.add(submit);
        this.add(back);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == back) {
            cardLayout.show(cardPanel, "LoginPage");
        }

        if (e.getSource() == submit) {
            // Check to see if the fields are filled
            String nameText = name.getText();
            String usernameText = usernameInput.getText();
            String passwordText = new String(pass.getPassword());
            String emailText = emailInput.getText();

            if (nameText.isEmpty() || usernameText.isEmpty() || passwordText.isEmpty() || emailText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try {

                MySQLConnection connection = MySQLConnection.getInstance();
                for (String usernames : connection.getAllUsers()) {
                    if (usernames.equals(usernameText)) {
                        JOptionPane.showMessageDialog(this, "Error! Username already taken.");
                        return;
                    }
                }

                User newUser = new User(nameText, usernameText, passwordText, emailText);
                connection.addUser(newUser);

                JOptionPane.showMessageDialog(this, "Account created successfully!");
                cardLayout.show(cardPanel, "Login");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Height, weight, and target weight must be numbers.");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
