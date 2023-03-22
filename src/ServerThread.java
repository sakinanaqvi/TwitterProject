import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * ServerThread.java
 *
 * Accepts message requests from clients and carries them out using UserBank and PostBank objects.
 *
 * @author Group 3, CS 18000
 * @version 5/2/2021
 */

public class ServerThread implements Runnable {

    private Socket socket;
    private UserBank usrBank;
    private PostsBank postsBank;

    public ServerThread(Socket socket, UserBank usrBank, PostsBank postsBank) {
        this.socket = socket;
        this.usrBank = usrBank;
        this.postsBank = postsBank;
    }

    public void run() {
        try {
            while (true) {
                //initialize reader and writer
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                //receive message
                String message = reader.readLine();
                System.out.println("message from client received: " + message);

                //save message
                writer.println(readMessage(message));
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //check google doc for possible statements
    //returns success or error and sends to client
    //viewPost sends the posts in string format to client
    public String readMessage(String message) {
        String substring = message.substring(message.indexOf(";;") + 2);
        switch (message.substring(0, message.indexOf(";;"))) {
            case "CreateUser": {
                if (!usrBank.createAccount(new User(substring)))
                    return "error";
                return "success";
            }
            case "LoginUser": {
                if (!usrBank.loginAccount(new User(substring)))
                    return "error";
                return "success";
            }
            case "EditAccount": {
                String[] arr = message.split(";;");
                if (!usrBank.editAccount(new User(arr[1]), arr[2]))
                    return "error";
                return "success";
            }
            case "DeleteAccount": {
                if (!usrBank.deleteAccount(new User(substring)))
                    return "error";
                return "success";
            }
            case "ViewPosts":
                return postsBank.viewPost();
            case "ViewPostsUser":
                return postsBank.viewPosts(substring);
            case "CreatePost":
                postsBank.createPost(new Posts(substring));
                return "success";
            case "DeletePost": {
                if (!postsBank.deletePost(new Posts(substring)))
                    return "error";
                return "success";
            }
            case "EditPost": {
                if (!postsBank.editPost(new Posts(substring)))
                    return "error";
                return "success";
            }
            case "AddComment": {
                String[] arr = message.split(";;");
                if (!postsBank.addComment((new Comment(arr[1])), arr[2]))
                    return "error";
                return "success";
            }
            case "EditComment": {
                String[] arr = message.split(";;");
                if (!postsBank.editComment((new Comment(arr[1])), arr[2], arr[3])) {
                    return "error";
                }
                postsBank.saveCurrentPosts();
                return "success";
            }
            case "DeleteComment": {
                String[] arr = message.split(";;");
                if (!postsBank.deleteComment((new Comment(arr[1])), arr[2])) {
                    postsBank.saveCurrentPosts();
                    return "error";
                }
                return "success";
            }
            case "ImportCSV": {
                String[] arr = message.split(";;");
                if (!postsBank.importCSV(arr[1], arr[2]))
                    return "error";
                return "success";
            }
            case "ExportCSV": {
                String[] arr = message.split(";;");
                if (!postsBank.exportCSV(arr[1], arr[2]))
                    return "error";
                return "success";
            }
            case "ViewComments": {
                return postsBank.viewComments(new Posts(substring));
            }
            default:
                return "error";
        }
    }
}
