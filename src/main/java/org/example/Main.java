
/*
    Author: Jordan Neff
    Purpose: This program helps a user map out their debits/credits and helps advise on
             how good a private or public company is for an investing or career move

    Line Count Goal = 4000+
    Current = 10

*/

package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        FlatDarculaLaf.setup();

        JFrame frame = new JFrame("Space Industry News");
        frame.setSize(520, 640);
        frame.setMinimumSize(new Dimension(520, 640));
        frame.setMaximumSize(new Dimension(520, 640));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        MySQLConnection connection = MySQLConnection.getInstance();

        LoginPage loginPage = new LoginPage(cardLayout, cardPanel, connection);
        cardPanel.add(loginPage, "LoginPage");

        CreateAccountPage createAccountPage = new CreateAccountPage(cardLayout, cardPanel, connection);
        cardPanel.add(createAccountPage, "CreateAccount");

        frame.add(cardPanel);
        frame.setVisible(true);

        cardLayout.show(cardPanel,"LoginPage");

    }
}




















