
/*
    Author: Jordan Neff
    Purpose: This program helps a user map out their debits/credits and helps advise on
             how good a private or public company is for an investing or career move

    Line Count Goal = 4000+
    Current = 10

*/

package org.example;

import backend.SendEmail;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;


public class Main {
    public static void main(String[] args) throws Exception {

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

        sendEmailTime();

        cardLayout.show(cardPanel,"LoginPage");

    }

    public static void sendEmailTime() throws Exception {

        Timer timer = new Timer();
        // Create a TimerTask for the email sending
        TimerTask emailTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    SendEmail send = new SendEmail();
                    send.sendingEmail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // Calculate the initial delay
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date scheduledTime = calendar.getTime();

        // If the current time is past 9 AM, schedule for the next day
        if (scheduledTime.before(new Date())) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            scheduledTime = calendar.getTime();
        }

        // Schedule the task to run daily at 9 AM
        timer.scheduleAtFixedRate(emailTask, scheduledTime, 24 * 60 * 60 * 1000); // 24 hours in milliseconds

    }

}




















