package org.example;

import java.sql.*;

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

    public static synchronized MySQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

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



}
