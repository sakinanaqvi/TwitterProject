import java.io.*;
import java.util.ArrayList;

/**
 * UserBank.java
 *
 * Handles file reading and writing and carriers out requested operations related to Users such as create edit and delete.
 *
 * @author Group 3, CS 18000
 * @version 5/2/2021
 */

public class UserBank extends java.lang.Object {
    private ArrayList<User> currentUser;
    private String filename;

    public UserBank(java.lang.String filename) throws IOException {
        // an empty arraylist that stores the User object
        this.filename = filename;
        currentUser = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                String[] c = line.split(",");

                // Extract ID and password
                int firstIndex = line.indexOf(",");

                String userID = line.substring(0, firstIndex);
                String password = line.substring(firstIndex + 1);

                // create a new User object
                User user = new User(userID, password);
                // append to the currentUser arrayList
                currentUser.add(user);
                line = reader.readLine();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    // check whether the new account exist or not.
    // Return boolean
    // false -> the account ID already exist
    // true -> create a new account and append it to the arraylist.
    public boolean createAccount(User userNew) {
        for (User user: currentUser) {
            if (user.getUserID().equals(userNew.getUserID())) {
                System.out.println("Occupied");
                return false;
            }
        }
        currentUser.add(userNew);
        saveCurrentUserAccount();
        return true;
    }


    // check whether the login information is valid or not
    // if ID and password match with the existing user info, return true
    // if ID and password do not match, return false

    public boolean loginAccount(User user1) {
        for (User user: currentUser) {
            if (user.equals(user1)) {
                System.out.println("Logged in successfully");
                return true;
            }
        }
        return false;
    }


    // return true when account is deleted successfully
    public boolean deleteAccount(User user1) {
        for (User user: currentUser) {
            if (user.getUserID().equals(user1.getUserID())) {
                currentUser.remove(user);
                saveCurrentUserAccount();
                return true;
            }
        }
        return false;
    }

    // return true when account information is edited.
    public boolean editAccount(User user1, String userNewPassword) {
        for (User user: currentUser) {
            if (user.getUserID().equals(user1.getUserID())) {
                user.setPassword(userNewPassword);
                saveCurrentUserAccount();
                return true;
            }
        }
        return false;
    }


    // Write out currentUser arraylist.
    // set the filename same as the input file

    public void saveCurrentUserAccount() {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(filename, false));

            for (User user: currentUser)
                writer.println(user);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
