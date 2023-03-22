import java.io.*;
import java.net.*;

/**
 * Client.java
 *
 * Runs client program to start the GUI and provide them with readers and writers to communicate to the server.
 *
 * @author Group 3
 * @version 5/2/2021
 *
 */

public class Client {
    public static void main(String[] args) {

        /* Connect to server */
        try {
            Socket socket = new Socket("localhost", 4242); //connect on localhost port 4242

            //initialize reader and writer
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            //start GUI
            Thread t = new Thread(new GUI(reader, writer));
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
