/**
 * Manages the connection to the MySQL database and provides methods for performing various database operations.
 * This class follows the Singleton pattern to ensure that only one instance of the database connection exists throughout
 * the application lifecycle. It provides functionalities for user management, including checking username existence,
 * retrieving passwords, adding users, and managing subscriptions.
 *
 * Key Responsibilities:
 * - Establishing and maintaining a connection to the MySQL database.
 * - Checking if a username exists in the database.
 * - Retrieving passwords for a given username.
 * - Adding new users to the database.
 * - Subscribing and unsubscribing users to/from email notifications.
 * - Retrieving a list of all subscribed users' email addresses.
 *
 * Usage:
 * - Obtain the single instance of this class using the `getInstance()` method.
 * - Use the provided methods to interact with the `users` table in the database.
 */

package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection {

    private static MySQLConnection instance;
    private Connection connection;


    private MySQLConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EmailAPIProject",
                    "root",
                    "Dd1013393"
            );

            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Singleton pattern to ensure only one instance of MySQLConnection
    public static synchronized MySQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    // Return the current database connection
    public Connection getConnection() {
        return connection;
    }


    public boolean containsUsername(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            // If resultSet has any rows, the username exists
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Failed to check if username exists.");
            e.printStackTrace();
        }
        return false;
    }

    public String getPassword(String username) {
        String sql = "SELECT password FROM users WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            // If resultSet has any rows, retrieve the password
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get password.");
            e.printStackTrace();
        }
        return null; // Return null if the username does not exist or an error occurred
    }


    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        String sql = "SELECT username FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get all users.");
            e.printStackTrace();
        }
        return users;
    }


    public void addUser(User user) {
        String sql = "INSERT INTO users(name, username, password, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add user.");
            e.printStackTrace();
        }
    }

    public void subscribeUser(String loggedInUser) {
        String sql = "UPDATE users SET `subscribed?` = ? WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, true); // Set the subscribed? field to true
            statement.setString(2, loggedInUser); // Bind the username to the SQL query

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User subscribed successfully!");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to subscribe user");
            e.printStackTrace();
        }
    }


    public void unsubscribeUser(String loggedInUser) {
        String sql = "UPDATE users SET `subscribed?` = ? WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false); // Set the subscribed? field to true
            statement.setString(2, loggedInUser); // Bind the username to the SQL query

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User unsubscribed successfully!");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to unsubscribe user");
            e.printStackTrace();
        }
    }

    public List<String> getAllSubscribedUsersEmails() throws SQLException {
        List<String> subscribedUsers = new ArrayList<>();

        String sql = "SELECT email FROM users WHERE subscribed = 1";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                subscribedUsers.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve subscribed users");
            e.printStackTrace();
            throw e;
        }

        return subscribedUsers;
    }

}
