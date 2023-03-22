/**
 * User.java
 *
 * Represents a user and provides methods to modify the fields of a given user object.
 *
 * @author Group 3, CS 18000
 * @version 5/2/2021
 */

public class User {
    private String userID;
    private String password;

    // Constructor
    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    //converts toString of user into a user object
    public User(String usr) {
        String[] arr = usr.split(",");

        userID = arr[0];
        password = arr[1];
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", userID, password);
    }

    public boolean equals(User user) {
        return (this.userID.equals(user.getUserID())) && (this.password.equals(user.getPassword()));
    }
} 
