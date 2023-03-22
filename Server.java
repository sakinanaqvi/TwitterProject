/**
 * Server.java
 *
 * Runs the server and opens up to clients. Connects clients and provides them with a new thread.
 *
 * @author Group 3, CS 18000
 * @version 5/2/2021
 */

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {

        /* Connect to client */
        try {
            ServerSocket server = new ServerSocket(4242);
            System.out.println("server ready to accept clients");
            UserBank usrBank = new UserBank("userAccount.csv");
            PostsBank postsBank = new PostsBank("posts.csv");

            while(true) {
                Socket socket = server.accept();
                System.out.println("client connected! " + socket.getPort());
                Thread serverThread = new Thread(new ServerThread(socket, usrBank, postsBank)); //create thread for client
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
