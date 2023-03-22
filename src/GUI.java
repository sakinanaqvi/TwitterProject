import java.io.*;

/**
 * GUI.java
 *
 * Provides the reader and writer to the GUI and runs the initial GUI
 *
 * @version 5/2/2021
 */

public class GUI implements Runnable {
    //reader and writer
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * Creates a GUI object
     *
     * @param reader reader for reading from server
     * @param writer writer for sneding data to the server
     */
    public GUI(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Runs the GUI by starting the accounts GUI and passing in the reader and writer
     */
    public void run() {
        AccountsGUI acc = new AccountsGUI(reader, writer);
        acc.run();
    }
}
