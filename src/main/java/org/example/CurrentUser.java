/*
 * Singleton class to manage the currently logged-in user.
 */
package org.example;

public class CurrentUser {

    // Singleton instance
    private static CurrentUser instance;
    private String loggedInUser;

    // Private constructor to prevent instantiation from other classes
    private CurrentUser(String user) {
        this.loggedInUser = user;
    }

    /**
     * Returns the singleton instance of the CurrentUser class.
     *
     * @return The singleton instance of CurrentUser.
     * @throws IllegalStateException if the instance is not initialized.
     */
    public static CurrentUser getInstance() {
        if (instance == null) {
            throw new IllegalStateException("CurrentUser is not set. Login required.");
        }
        return instance;
    }

    /**
     * Initializes the singleton instance with the given user.
     *
     * @param user The user to set as the logged-in user.
     */
    public static void setInstance(String user) {
        if (instance != null) {
            throw new IllegalStateException("CurrentUser instance already exists.");
        }
        instance = new CurrentUser(user);
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return The username of the logged-in user.
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param user The username to set as the logged-in user.
     */
    public void setLoggedInUser(String user) {
        this.loggedInUser = user;
    }
}
