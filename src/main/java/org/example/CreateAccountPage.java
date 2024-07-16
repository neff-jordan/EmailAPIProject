package org.example;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountPage implements ActionListener {
    JLabel firstname = new JLabel("firstname: ");
    JLabel lastname = new JLabel("lastname: ");
    JLabel username = new JLabel("username: ");
    JLabel password = new JLabel("password: ");
    JLabel email = new JLabel("email: ");

    JTextField firstnameInput = new JTextField();
    JTextField lastnameInput = new JTextField();
    JTextField usernameInput = new JTextField();
    JTextField passwordInput = new JTextField();
    JTextField emailInput = new JTextField();

    public CreateAccountPage() { }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
