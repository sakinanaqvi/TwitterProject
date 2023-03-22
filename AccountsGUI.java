import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountsGUI extends JPanel {
    //reader and writer
    private BufferedReader reader;
    private PrintWriter writer;

    private String user;
    private String pass;
    static JButton login;
    static JButton createAccount;
    static JPasswordField password;
    static JTextField username;
    static JPanel panel;
    static JLabel signIn;
    static JLabel passwordLabel;
    static JLabel usernameLabel;
    static JTextArea textArea1;
    static JLabel label1;
    static JFrame frame;
    static JLabel label2;
    static JButton forgot;
    static JFrame edit;
    static JButton delete;
    static JButton edits;
    static JButton wrongButton;
    static JTextField userN;
    static JPasswordField passW;

    public AccountsGUI(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                user = username.getText();
                pass = password.getText();
                User user1 = new User(user, pass);
                if (username.getText().equals("") || password.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    writer.println("LoginUser;;" + user1);
                    writer.flush();
                    try {
                        String message = reader.readLine();

                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Login Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                            PostsGUI posts = new PostsGUI(reader, writer, username.getText());
                            posts.run();
                            frame.dispose();


                        } else if (message.equals("error")) {
                            JOptionPane.showMessageDialog(null, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getSource() == createAccount) {
                user = username.getText();
                pass = password.getText();
                User user1 = new User(user, pass);
                if (username.getText().equals("") || password.getPassword().length == 0||username.getText().contains(" ")||password.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Username and Password.\nEnsure that there are no spaces in the username or password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    writer.println("CreateUser;;" + user1);
                    writer.flush();
                    try {
                        String message = reader.readLine();

                        if (message.equals("success")) {
                            JOptionPane.showMessageDialog(null, "Account created", "Success", JOptionPane.INFORMATION_MESSAGE);
                            PostsGUI posts = new PostsGUI(reader, writer, username.getText());
                            posts.run();
                            frame.dispose();
                        } else if (message.equals("error")) {
                            JOptionPane.showMessageDialog(null, "Account already exists", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }
            } else if (e.getSource() == forgot) {
                ActionListener act = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == edits) {
                            user = userN.getText();
                            pass = passW.getText();
                            User user1 = new User(user, pass);
                            if (userN.getText().equals("") || passW.getPassword().length == 0) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                writer.println("LoginUser;;" + user1);
                                writer.flush();
                                try {
                                    String message = reader.readLine();

                                    if (message.equals("success")) {
                                        String usernameChange = null;
                                        String passwordChange = null;
                                        while (true) {
                                            usernameChange = JOptionPane.showInputDialog("Please Enter a new Username");
                                            if (usernameChange == null)
                                            {
                                                break;

                                            }
                                            if (usernameChange.length() != 0) {
                                                if (!usernameChange.contains(" ")) {
                                                    break;
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Username cannot contain spaces");
                                                }
                                            }

                                        }
                                        while (true) {
                                            passwordChange = JOptionPane.showInputDialog("Please Enter a new Password");
                                            if(passwordChange == null)
                                            {
                                                break;
                                            }
                                            if (passwordChange.length() != 0) {
                                                if (!passwordChange.contains(" ")) {
                                                    break;
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Password cannot contain spaces");
                                                }
                                            }

                                        }
                                        if (usernameChange == null || passwordChange == null) {
                                            edit.dispose();
                                            AccountsGUI acc = new AccountsGUI(reader, writer);
                                            acc.run();
                                        } else {
                                            writer.println("EditAccount;;" + user1 + ";;" + usernameChange + ";;" + passwordChange);
                                            writer.flush();
                                            message = reader.readLine();
                                            if (message.equals("success")) {
                                                JOptionPane.showMessageDialog(null, "Success");
                                                edit.dispose();
                                                AccountsGUI acc = new AccountsGUI(reader, writer);
                                                acc.run();
                                            }

                                        }
                                    } else if (message.equals("error")) {
                                        JOptionPane.showMessageDialog(null, "Account doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                } catch (IOException a) {
                                    a.printStackTrace();
                                }
                            }
                        }
                        if (e.getSource() == delete) {
                            user = userN.getText();
                            pass = passW.getText();
                            User user1 = new User(user, pass);
                            if (userN.getText().equals("") || passW.getPassword().length == 0) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                writer.println("LoginUser;;" + user1);
                                writer.flush();
                                try {
                                    String message = reader.readLine();
                                    if (message.equals("success")) {
                                        int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account", "Delete Account", JOptionPane.YES_NO_OPTION);
                                        if (n == JOptionPane.YES_OPTION) {
                                            writer.println("DeleteAccount;;" + user1);
                                            writer.flush();
                                            message = reader.readLine();
                                            if (message.equals("success")) {
                                                JOptionPane.showMessageDialog(null, "account deleted");
                                                edit.dispose();
                                                AccountsGUI acc = new AccountsGUI(reader, writer);
                                                acc.run();
                                            }
                                        }
                                    } else if (message.equals("error")) {
                                        JOptionPane.showMessageDialog(null, "Account doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                ;
                            }

                        }
                        if (e.getSource() == wrongButton) {
                            edit.dispose();
                            frame.setVisible(true);
                        }

                    }
                };
                frame.dispose();
                edit = new JFrame("Edit Account info");
                panel = new JPanel();
                panel.setLayout(null);
                signIn = new JLabel();
                userN = new JTextField();
                usernameLabel = new JLabel();
                passwordLabel = new JLabel();
                passW = new JPasswordField();
                delete = new JButton();
                label1 = new JLabel();
                label2 = new JLabel();
                edits = new JButton();
                wrongButton = new JButton();
                Container content = edit.getContentPane();
                panel.setBackground(new Color(102, 102, 255));
                signIn.setText("Edit Info");
                signIn.setHorizontalAlignment(SwingConstants.CENTER);
                signIn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
                signIn.setBackground(new Color(255, 204, 204));
                signIn.setForeground(Color.white);
                signIn.setBounds(115, 20, 175, 40);
                panel.add(signIn);
                usernameLabel.setText("Username:");
                usernameLabel.setForeground(Color.white);
                usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
                usernameLabel.setBounds(45, 80, 150, 40);
                panel.add(usernameLabel);
                panel.add(userN);
                userN.setBounds(205, 80, 150, 40);
                panel.add(passW);
                passwordLabel.setText("Password:");
                passwordLabel.setForeground(Color.white);
                passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
                panel.add(passwordLabel);
                passwordLabel.setBounds(45, 150, 150, 40);
                passW.setBounds(205, 150, 150, 45);
                panel.add(passW);
                edits.setText("Change pass");
                edits.setBounds(130, 210, 150, 40);
                panel.add(edits);
                edits.addActionListener(act);
                label1.setBackground(new Color(102, 102, 255));
                label1.setForeground(Color.white);
                label1.setText("Don't want an account");
                label1.setBounds(50, 265, 150, 40);
                panel.add(label1);
                delete.setText("Delete Account");
                panel.add(delete);
                delete.setBounds(215, 265, 150, 40);
                delete.addActionListener(act);
                label2.setBackground(new Color(102, 102, 255));
                label2.setForeground(Color.white);
                label2.setText("Wrong Button?");
                label2.setBounds(50, 320, 150, 40);
                panel.add(label2);
                wrongButton.setText("Go Back");
                wrongButton.setBounds(215, 320, 150, 40);
                wrongButton.addActionListener(act);
                panel.add(wrongButton);
                content.add(panel);
                edit.setSize(420, 420);
                edit.setLocationRelativeTo(null);
                edit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                edit.setVisible(true);

            }
        }
    };

    public void run() {
        frame = new JFrame("Login");
        panel = new JPanel();
        panel.setLayout(null);
        signIn = new JLabel();
        username = new JTextField();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        password = new JPasswordField();
        login = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        forgot = new JButton();
        createAccount = new JButton();
        Container content = frame.getContentPane();
        panel.setBackground(new Color(102, 102, 255));
        signIn.setText("Sign in");
        signIn.setHorizontalAlignment(SwingConstants.CENTER);
        signIn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
        signIn.setBackground(new Color(255, 204, 204));
        signIn.setForeground(Color.white);
        signIn.setBounds(115, 20, 175, 40);
        panel.add(signIn);
        usernameLabel.setText("Username:");
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        usernameLabel.setBounds(45, 80, 150, 40);
        panel.add(usernameLabel);
        panel.add(username);
        username.setBounds(205, 80, 150, 40);
        panel.add(password);
        passwordLabel.setText("Password:");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        panel.add(passwordLabel);
        passwordLabel.setBounds(45, 150, 150, 40);
        password.setBounds(205, 150, 150, 45);
        panel.add(password);
        login.setText("Sign In");
        login.setBounds(155, 210, 93, 40);
        panel.add(login);
        login.addActionListener(actionListener);
        label1.setBackground(new Color(102, 102, 255));
        label1.setForeground(Color.white);
        label1.setText("Don't have an account");
        label1.setBounds(50, 265, 150, 40);
        panel.add(label1);
        createAccount.setText("Create Account");
        panel.add(createAccount);
        createAccount.setBounds(215, 265, 150, 40);
        createAccount.addActionListener(actionListener);
        label2.setBackground(new Color(102, 102, 255));
        label2.setForeground(Color.white);
        label2.setText("Edit Account");
        label2.setBounds(50, 320, 150, 40);
        panel.add(label2);
        forgot.setText("Edit Account");
        forgot.setBounds(215, 320, 150, 40);
        forgot.addActionListener(actionListener);
        panel.add(forgot);
        content.add(panel);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
