# Space Industry News Application

## Overview
The Space Industry News Application is a Java-based desktop application designed to manage user accounts and provide daily updates on space industry news. Users can create accounts, log in, and subscribe to receive daily emails with space-related news and Mars photos. The application also supports a graphical user interface (GUI) using Swing, and it interacts with a MySQL database to manage user data and subscriptions.

## Features
User Authentication: Allows users to create an account, log in, and manage their subscriptions.
Subscription Management: Users can subscribe or unsubscribe from daily email updates.
Email Notifications: Sends daily emails with the latest space industry news and Mars photos.
Graphical User Interface: Provides an intuitive interface for interacting with the application.

## Technologies Used
Java: The core programming language used for the application.
Swing: For building the graphical user interface.
MySQL: For database management, including user accounts and subscription information.
FlatLaf: For a modern look and feel of the Swing components.
Timer: To schedule daily email notifications.
Components

1. User Class
   The User class represents a user in the system with attributes for name, username, password, and email. It includes getters and setters for these attributes and a toString method for debugging.

2. MySQLConnection Class
   The MySQLConnection class handles database interactions, including connecting to the database, checking for existing usernames, retrieving passwords, adding new users, and managing subscriptions. It also retrieves emails of subscribed users for sending notifications.

3. Layouts and GUI
   Layout Class: An abstract class for managing card layouts in the GUI, providing methods to switch between different screens.
   LoginPage Class: Manages user login, account creation, and resetting fields.
   CreateAccountPage Class: Allows users to create new accounts and validates input fields.
   HomePage Class: Displays a welcome message, subscription options, and handles user actions such as subscribing, unsubscribing, and logging out.
4. Email Sending
   SendEmail Class: Responsible for sending daily email notifications with space industry news and Mars photos. The Main class schedules this task to run daily at 9 AM.
   Setup and Installation
   Prerequisites
   Java Development Kit (JDK) 8 or higher
   MySQL database server
   Database Setup
   Create Database: Set up a MySQL database named EmailAPIProject.
### Things to add later: 

1) A email follow-up when you subscribe or unsubscribe from the news. 
2) customization around what type of space news to receive 
3) ability to change email addresses/user info
4) move the front end to a RESTful web service instead of a desktop GUI
5) user adjust how many news stories they want
6) add other data like mars weather and upcoming rocket launches 