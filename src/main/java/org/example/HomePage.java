package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePage extends Layout implements ActionListener {

    CurrentUser currentUser = CurrentUser.getInstance();

    JLabel user = new JLabel("Welcome " + currentUser.getLoggedInUser());

    JLabel subscriptionStatus = new JLabel("Space News Subscription Options");

    JButton unsub = new JButton("Unsubscribe");
    JButton sub = new JButton("Subscribe");

    JButton logout = new JButton("Logout");



    public HomePage(CardLayout cardLayout, JPanel cardPanel, String userID, MySQLConnection connection) throws SQLException {

        super(cardLayout, cardPanel);
        this.setLayout(null);

        user.setBounds(175, 100, 200, 40);
        subscriptionStatus.setBounds(125,250,270,40);
        sub.setBounds(150,300,110,40);
        unsub.setBounds(260,300,110,40);
        logout.setBounds(210,400,100,40);

        user.setFont(new Font("MV Boli", Font.BOLD, 20));
        subscriptionStatus.setFont(new Font("MV Boli", Font.BOLD, 15));

        unsub.addActionListener(this);
        sub.addActionListener(this);
        logout.addActionListener(this);

        this.add(user);
        this.add(subscriptionStatus);
        this.add(sub);
        this.add(unsub);
        this.add(logout);
    }

    public void actionPerformed(ActionEvent e) {

        MySQLConnection connection = null;
        try {
            connection = MySQLConnection.getInstance();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if(e.getSource() == sub) {

            // add them to the database which will then run through all the emails and send them out
            connection.subscribeUser(currentUser.getLoggedInUser());
            JOptionPane.showMessageDialog(this, "You have successfully subscribed!");

        } else if(e.getSource() == unsub) {

            // remove them from the database so they do not get an email
            connection.unsubscribeUser(currentUser.getLoggedInUser());
            JOptionPane.showMessageDialog(this, "You have successfully unsubscribed!");

        } else if (e.getSource() == logout) {
            // switch to login page
            System.out.println("Logout successful");
            cardLayout.show(cardPanel,"LoginPage");
        }
    }
}