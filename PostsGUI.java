import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.io.*;

/**
 * PostsGUI.java
 * <p>
 * The GUI that displays posts and sends requests to sever to handle operations requested by the user on posts.
 *
 * @author Group 3, CS 18000
 * @version 5/2/2021
 */
public class PostsGUI extends JComponent {
    private JLabel commenting;
    private JLabel post;
    private String username;
    private JLabel timestamp;
    private JLabel title;
    private int postNum;
    private JButton name;
    private Posts posts1;
    private static final String bigSmile = ("( ͡° ͜ʖ ͡°)");
    private static final String smile = ("(¬‿¬)");
    private static final String cuteSmile = ("（‐＾▽＾‐）");
    private static final String frown = ":(";
    private static final String angry = "凸ಠ益ಠ)凸";
    private static final String superAngry = ("(╬⓪益⓪)");
    private static final String crying = ("ᕕ༼ ͠ຈ Ĺ̯ ͠ຈ ༽┌∩┐");
    private BufferedReader reader;
    private PrintWriter writer;

    public PostsGUI(BufferedReader reader, PrintWriter writer, String username) {
        this.reader = reader;
        this.writer = writer;
        this.username = username;
    }

    public static String emoji(String text) {
        if (text.contains("<bigSmile>"))
            text = text.replaceAll("<bigSmile>", bigSmile);
        if (text.contains("<smile>"))
            text = text.replaceAll("<smile>", smile);
        if (text.contains("<cuteSmile>"))
            text = text.replaceAll("<cuteSmile>", cuteSmile);
        if (text.contains("<frown>"))
            text = text.replaceAll("<frown>", frown);
        if (text.contains("<angry>"))
            text = text.replaceAll("<angry>", angry);
        if (text.contains("<superAngry>"))
            text = text.replaceAll("<superAngry>", superAngry);
        if (text.contains("<crying>"))
            text = text.replaceAll("<crying>", crying);
        return text;
    }

    public void run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(102, 102, 255));
        JPanel generalView = new JPanel(); //creates the panel for everything to display
        generalView.setLayout(new BoxLayout(generalView, BoxLayout.PAGE_AXIS));
        generalView.setBackground(new Color(102, 102, 255));
        JFrame mainFrame = new JFrame("Post Something!"); //setting up the frame
        postNum = 1;
        writer.println("ViewPosts;;");
        writer.flush();
        try {
            String message = reader.readLine();
            if (!message.equals("")) {
                String[] allMessageDivided = message.split("`");
                for (int i = 0; i < allMessageDivided.length; i++) {
                    JPanel viewPanel = new JPanel();
                    viewPanel.setLayout(null);
                    viewPanel.setBackground((new Color(102, 102, 255)));
                    viewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                    viewPanel.setPreferredSize(new Dimension(400, 200));
                    String[] lineDivided = allMessageDivided[i].split(",");
                    JLabel title = new JLabel(lineDivided[1]);
                    title.setBounds(0, 35, 175, 20);
                    title.setFont(new Font("Kai", Font.BOLD, 15));
                    title.setForeground(Color.WHITE);
                    title.setOpaque(false);
                    JButton name = new JButton(lineDivided[2]);
                    name.setBounds(0, 10, 175, 20);
                    JFrame individualPosts = new JFrame("View " + name.getText() + " Posts and Comments!");
                    name.addActionListener(new ActionListener() { //edits and changes a post
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            writer.println("ViewPostsUser;;" + name.getText());
                            writer.flush();
                            try {
                                String message = reader.readLine();
                                JPanel allPosts = new JPanel();
                                allPosts.setLayout(new BoxLayout(allPosts, BoxLayout.PAGE_AXIS));
                                allPosts.setBackground((new Color(102, 102, 255)));
                                JLabel viewPostsMessage = new JLabel("View " + name.getText() + "'s Posts and Comments!");
                                viewPostsMessage.setFont(new Font("Kai", Font.BOLD, 17));
                                viewPostsMessage.setBounds(300, 0, 400, 20);
                                viewPostsMessage.setForeground(Color.WHITE);
                                viewPostsMessage.setOpaque(false);
                                viewPostsMessage.setOpaque(false);
                                allPosts.add(viewPostsMessage, 0);
                                String[] messageDivided = message.split("`");
                                for (int i = 0; i < messageDivided.length; i++) {
                                    JPanel multiplePanels = new JPanel();
                                    multiplePanels.setLayout(null);
                                    multiplePanels.setBackground((new Color(102, 102, 255)));
                                    multiplePanels.setBorder(BorderFactory.createLineBorder(Color.black));
                                    multiplePanels.setPreferredSize(new Dimension(400, 200));
                                    String[] lineDivided = messageDivided[i].split(",");
                                    JLabel title2 = new JLabel(lineDivided[1]);
                                    title2.setBounds(0, 15, 175, 20);
                                    title2.setFont(new Font("Kai", Font.BOLD, 15));
                                    title2.setForeground(Color.WHITE);
                                    title2.setOpaque(false);
                                    JLabel content = new JLabel(lineDivided[3]);
                                    content.setBounds(30, 50, 175, 20);
                                    content.setFont(new Font("Kai", Font.PLAIN, 15));
                                    content.setForeground(Color.WHITE);
                                    content.setOpaque(false);
                                    JLabel times = new JLabel(lineDivided[4]);
                                    times.setBounds(125, 15, 175, 20);
                                    times.setFont(new Font("Kai", Font.ITALIC, 15));
                                    times.setForeground(Color.WHITE);
                                    times.setOpaque(false);
                                    multiplePanels.add(title);
                                    multiplePanels.add(content);
                                    multiplePanels.add(times);
                                    allPosts.add(multiplePanels);
                                }
                                JScrollPane individualScrolls = new JScrollPane(allPosts);
                                individualScrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                                individualPosts.add(individualScrolls);
                                individualPosts.setSize(600, 400);
                                individualPosts.setDefaultCloseOperation(individualPosts.DISPOSE_ON_CLOSE);
                                individualPosts.setLocationRelativeTo(null);
                                individualPosts.setVisible(true);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    JLabel content = new JLabel(lineDivided[3]);
                    content.setBounds(50, 50, 175, 20);
                    content.setFont(new Font("Kai", Font.PLAIN, 15));
                    content.setForeground(Color.WHITE);
                    content.setOpaque(false);
                    JLabel times = new JLabel(lineDivided[4]);
                    times.setBounds(300, 15, 175, 20);
                    times.setFont(new Font("Kai", Font.ITALIC, 15));
                    times.setForeground(Color.WHITE);
                    times.setOpaque(false);
                    JFrame editFrame = new JFrame("Edit Post");
                    JButton editPost = new JButton("Edit Post");
                    editPost.setBounds(115, 100, 100, 25);
                    editPost.addActionListener(new ActionListener() { //edits and changes a post
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JPanel editPanel = new JPanel();
                            editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
                            editPanel.setBackground((new Color(102, 102, 255)));
                            JLabel editPost = new JLabel("Edit your post!");
                            editPost.setBounds(1000, 10, 100, 25);
                            editPost.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 20));
                            editPost.setForeground(Color.WHITE);
                            editPanel.add(editPost);
                            JTextField editText = new JTextField(30);
                            JButton postEdit = new JButton("Edit Post");
                            postEdit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String context1 = "";
                                    String editTimeChange = times.getText().replace(" ", "/");
                                    if (editText.getText().contains(","))
                                        context1 = editText.getText().replaceAll(",", "@@");
                                    context1 = emoji(context1);
                                    Posts editPosts = new Posts(postNum, title.getText(), name.getText(), context1, editTimeChange);
                                    writer.println("EditPost;;" + editPosts);
                                    writer.flush();
                                    try {
                                        String message = reader.readLine();
                                        if (message.equals("error") || editText.getText().equals("") || editText.getText().equals(" ") || !username.equals(name.getText())) {
                                            JOptionPane.showMessageDialog(null, "Please enter a valid edit", "Error", JOptionPane.ERROR_MESSAGE);
                                        } else if (message.equals("success") && username.equals(name.getText())) {
                                            content.setText(editText.getText());
                                            content.setOpaque(false);
                                            content.setBounds(50, 50, 175, 20);
                                            content.setFont(new Font("Kai", Font.ITALIC, 15));
                                            content.setForeground(Color.WHITE);
                                            mainPanel.revalidate();
                                            mainPanel.repaint();
                                            JOptionPane.showMessageDialog(null, "Successfully Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                            editFrame.dispose();
                                        }
                                    } catch (IOException exception) {
                                        exception.printStackTrace();
                                    }
                                }
                            });
                            JPanel bottomPanel = new JPanel();
                            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
                            bottomPanel.add(editText);
                            bottomPanel.add(postEdit);
                            editFrame.add(new JScrollPane(editPanel));
                            editFrame.add(bottomPanel, BorderLayout.PAGE_END);
                            editFrame.setDefaultCloseOperation(editFrame.DISPOSE_ON_CLOSE);
                            editFrame.setLocationRelativeTo(null);
                            editFrame.setSize(400, 300);
                            editFrame.setVisible(true);
                        }
                    });
                    String conTime = times.getText().replace(" ", "/");
                    Posts posts = new Posts(postNum, title.getText(), name.getText(), content.getText(), conTime);
                    JButton deletePost = new JButton("Delete Post"); //deletes a post
                    deletePost.setBounds(235, 100, 100, 25);
                    deletePost.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            writer.println("DeletePost;;" + posts);
                            writer.flush();
                            try {
                                String message = reader.readLine();
                                if ((message.equals("error") || !username.equals(name.getText())) && (!username.equals("Moderator1")) && !username.equals("Moderator2")) {
                                    JOptionPane.showMessageDialog(null, "Unable to delete post", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if (message.equals("success")) {
                                    JOptionPane.showMessageDialog(null, "Successfully deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    generalView.remove(viewPanel);
                                    generalView.revalidate();
                                    generalView.repaint();
                                }
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    JPanel maincPanel = new JPanel();
                    maincPanel.setLayout(new BorderLayout());
                    maincPanel.setBackground(new Color(102, 102, 255));
                    JPanel commentGeneralView = new JPanel(); //creates the panel for everything to display
                    commentGeneralView.setLayout(new BoxLayout(commentGeneralView, BoxLayout.PAGE_AXIS));
                    commentGeneralView.setBackground(new Color(102, 102, 255));
                    JButton comment = new JButton("Comment!"); //comment button is set up
                    comment.setBounds(0, 100, 100, 25);
                    comment.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {//work on commenting
                            writer.println("ViewComments;;" + posts); //viewing all comments and implementing action listeners fpr each
                            writer.flush();
                            try {
                                String commentMessage = reader.readLine();
                                if (!commentMessage.equals("")) {
                                    String[] allMessageDivided = commentMessage.split("`");
                                    for (int i = 0; i < allMessageDivided.length; i++) {
                                        String[] lineDivided = allMessageDivided[i].split(",");
                                        commenting = new JLabel(lineDivided[1]);
                                        JPanel commentPanel = new JPanel();
                                        commentPanel.setLayout(null);
                                        commentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                                        JLabel commentName = new JLabel(lineDivided[0]); //set this as the name of the account so that it can be seen
                                        commentName.setBounds(0, 20, 175, 20);
                                        commentName.setFont(new Font("Kai", Font.ITALIC, 15));
                                        commentName.setForeground(Color.WHITE);
                                        commenting.setBounds(50, 50, 175, 20);
                                        commenting.setFont(new Font("Kai", Font.PLAIN, 15));
                                        commenting.setForeground(Color.WHITE);
                                        commenting.setOpaque(false);
                                        JLabel commentTime = new JLabel(lineDivided[3]);
                                        commentTime.setBounds(300, 20, 175, 20);
                                        commentTime.setFont(new Font("Kai", Font.ITALIC, 15));
                                        commentTime.setForeground(Color.WHITE);
                                        JFrame editCFrame = new JFrame("Edit Comment");
                                        JButton editCPost = new JButton("Edit Comment");
                                        editCPost.setBounds(0, 80, 125, 25);
                                        editCPost.addActionListener(new ActionListener() { //edits and changes a post
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                JPanel commentEditPanel = new JPanel();
                                                commentEditPanel.setLayout(new BoxLayout(commentEditPanel, BoxLayout.PAGE_AXIS));
                                                commentEditPanel.setBackground((new Color(102, 102, 255)));
                                                JLabel editComment = new JLabel("Edit your comment!");
                                                editComment.setBounds(1000, 10, 100, 25);
                                                editComment.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 20));
                                                editComment.setForeground(Color.WHITE);
                                                commentEditPanel.add(editComment);
                                                JTextField editCommentText = new JTextField(30);
                                                JButton postCommentEdit = new JButton("Edit Comment");
                                                postCommentEdit.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        String convertedTime = times.getText().replace(" ", "/");
                                                        String commentCTime = commentTime.getText().replace(" ", "/");
                                                        Comment comment1 = new Comment(name.getText(), commenting.getText(), convertedTime, commentCTime);
                                                        String commentEdit = editCommentText.getText();
                                                        if (commentEdit.contains(","))
                                                            commentEdit = commentEdit.replaceAll(",", "@@");
                                                        commentEdit = emoji(commentEdit);
                                                        writer.println("EditComment;;" + comment1 + ";;" + convertedTime + ";;" + commentEdit);
                                                        writer.flush();
                                                        try {
                                                            String message = reader.readLine();
                                                            if (message.equals("error") || editCommentText.getText().equals("") || editCommentText.getText().equals(" ") || !username.equals(commentName.getText())) { //checks for post validity
                                                                JOptionPane.showMessageDialog(null, "Please enter a valid edit", "Error", JOptionPane.ERROR_MESSAGE);
                                                            } else if (message.equals("success") && username.equals(commentName.getText())) {
                                                                commenting.setText(editCommentText.getText());
                                                                commenting.setOpaque(false);
                                                                commenting.setBounds(50, 50, 175, 20);
                                                                commenting.setFont(new Font("Kai", Font.ITALIC, 15));
                                                                commenting.setForeground(Color.WHITE);
                                                                maincPanel.revalidate();
                                                                maincPanel.repaint();
                                                                JOptionPane.showMessageDialog(null, "Successfully Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                                editCFrame.dispose();
                                                            }
                                                        } catch (IOException exception) {
                                                            exception.printStackTrace();
                                                        }
                                                    }
                                                });
                                                JPanel bottomPanel = new JPanel();
                                                bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
                                                bottomPanel.add(editCommentText);
                                                bottomPanel.add(postCommentEdit);
                                                editCFrame.add(new JScrollPane(commentEditPanel));
                                                editCFrame.add(bottomPanel, BorderLayout.PAGE_END);
                                                editCFrame.setDefaultCloseOperation(editCFrame.DISPOSE_ON_CLOSE);
                                                editCFrame.setLocationRelativeTo(null);
                                                editCFrame.setSize(400, 300);
                                                editCFrame.setVisible(true);
                                            }
                                        });
                                        JButton deleteCPost = new JButton("Delete Comment"); //deletes a post
                                        deleteCPost.setBounds(235, 80, 130, 25);
                                        deleteCPost.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                String convertedTime = times.getText().replace(" ", "/");
                                                String commentChange = commentTime.getText().replace(" ", "/");
                                                Comment comment1 = new Comment(name.getText(), commenting.getText(), convertedTime, commentChange);
                                                writer.println("DeleteComment;;" + comment1 + ";;" + convertedTime);
                                                writer.flush();
                                                try {
                                                    String message = reader.readLine();
                                                    if (message.equals("error")) {
                                                        JOptionPane.showMessageDialog(null, "Unable to delete comment", "Error", JOptionPane.ERROR_MESSAGE);
                                                    } else if (message.equals("success")) {
                                                        commentGeneralView.remove(commentPanel);
                                                        maincPanel.revalidate();
                                                        maincPanel.repaint();
                                                    }
                                                } catch (IOException exception) {
                                                    exception.printStackTrace();
                                                }
                                            }
                                        });
                                        commentPanel.add(commentName);
                                        commentPanel.add(commenting);
                                        commentPanel.add(editCPost);
                                        commentPanel.add(deleteCPost);
                                        commentPanel.add(commentTime);
                                        commentPanel.setPreferredSize(new Dimension(400, 200));
                                        commentPanel.setBackground(new Color(102, 102, 255));
                                        commentGeneralView.add(commentPanel, 0);
                                        maincPanel.add(commentGeneralView);
                                        commentGeneralView.revalidate();
                                    }
                                }
                                JTextField commentText = new JTextField(20);
                                JButton commentButton = new JButton("Comment!");
                                commentButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int x = 0;
                                        int y = 20;
                                        int height = 20;
                                        int postX = 50;
                                        int postY = 50;
                                        int postHeight = 20;
                                        int commentY = 80;
                                        Date date1 = new Date();
                                        Timestamp timestamp = new Timestamp(date1.getTime());
                                        commenting = new JLabel(commentText.getText());
                                        JPanel commentPanel = new JPanel();
                                        commentPanel.setLayout(null);
                                        commentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                                        JLabel commentName = new JLabel(username); //set this as the name of the account so that it can be seen
                                        commentName.setBounds(x, y, 175, height);
                                        commentName.setFont(new Font("Kai", Font.ITALIC, 15));
                                        commentName.setForeground(Color.WHITE);
                                        commenting.setBounds(postX, postY, 175, postHeight);
                                        commenting.setFont(new Font("Kai", Font.PLAIN, 15));
                                        commenting.setForeground(Color.WHITE);
                                        commenting.setOpaque(false);
                                        JLabel commentTime = new JLabel(timestamp.toString());
                                        commentTime.setBounds(300, y, 175, height);
                                        commentTime.setFont(new Font("Kai", Font.ITALIC, 15));
                                        commentTime.setForeground(Color.WHITE);
                                        String viewChangedTime = times.getText().replace(" ", "/");
                                        String commentChangedTime = commentTime.getText().replace(" ", "/");
                                        String commentBody = commenting.getText();
                                        if (commentBody.contains(",")) {
                                            commentBody = commentBody.replaceAll(",", "@@");
                                        }
                                        commentBody = emoji(commentBody);
                                        Comment comment1 = new Comment(username, commentBody, viewChangedTime, commentChangedTime);
                                        writer.println("AddComment;;" + comment1 + ";;" + viewChangedTime);
                                        writer.flush();
                                        try {
                                            String message = reader.readLine();
                                            if (message.equals("error")) {
                                                JOptionPane.showMessageDialog(null, "Please enter a valid comment", "Error", JOptionPane.ERROR_MESSAGE);
                                            } else if (message.equals("success")) {
                                                JFrame editCFrame = new JFrame("Edit Comment");
                                                JButton editCPost = new JButton("Edit Comment");
                                                editCPost.setBounds(x, commentY, 125, 25);
                                                editCPost.addActionListener(new ActionListener() { //edits and changes a post
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        JPanel commentEditPanel = new JPanel();
                                                        commentEditPanel.setLayout(new BoxLayout(commentEditPanel, BoxLayout.PAGE_AXIS));
                                                        commentEditPanel.setBackground((new Color(102, 102, 255)));
                                                        JLabel editComment = new JLabel("Edit your comment!");
                                                        editComment.setBounds(1000, 10, 100, 25);
                                                        editComment.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 20));
                                                        editComment.setForeground(Color.WHITE);
                                                        commentEditPanel.add(editComment);
                                                        JTextField editCommentText = new JTextField(30);
                                                        JButton postCommentEdit = new JButton("Edit Comment");
                                                        postCommentEdit.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                String commentEdit = editCommentText.getText();
                                                                if (commentEdit.contains(","))
                                                                    commentEdit = commentEdit.replaceAll(",", "@@");
                                                                commentEdit = emoji(commentEdit);
                                                                writer.println("EditComment;;" + comment1 + ";;" + viewChangedTime + ";;" + commentEdit);
                                                                writer.flush();
                                                                try {
                                                                    String message = reader.readLine();
                                                                    if (message.equals("error") || editCommentText.getText().equals("") || editCommentText.getText().equals(" ") || !username.equals(commentName.getText())) { //checks for post validity
                                                                        JOptionPane.showMessageDialog(null, "Please enter a valid edit", "Error", JOptionPane.ERROR_MESSAGE);
                                                                    } else if (message.equals("success") && username.equals(commentName.getText())) {
                                                                        commenting.setText(editCommentText.getText());
                                                                        commenting.setOpaque(false);
                                                                        commenting.setBounds(postX, postY, 175, postHeight);
                                                                        commenting.setFont(new Font("Kai", Font.ITALIC, 15));
                                                                        commenting.setForeground(Color.WHITE);
                                                                        maincPanel.revalidate();
                                                                        maincPanel.repaint();
                                                                        JOptionPane.showMessageDialog(null, "Successfully Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                                        editCFrame.dispose();
                                                                    }
                                                                } catch (IOException exception) {
                                                                    exception.printStackTrace();
                                                                }
                                                            }
                                                        });
                                                        JPanel bottomPanel = new JPanel();
                                                        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
                                                        bottomPanel.add(editCommentText);
                                                        bottomPanel.add(postCommentEdit);
                                                        editCFrame.add(new JScrollPane(commentEditPanel));
                                                        editCFrame.add(bottomPanel, BorderLayout.PAGE_END);
                                                        editCFrame.setDefaultCloseOperation(editCFrame.DISPOSE_ON_CLOSE);
                                                        editCFrame.setLocationRelativeTo(null);
                                                        editCFrame.setSize(400, 300);
                                                        editCFrame.setVisible(true);
                                                    }
                                                });
                                                JButton deleteCPost = new JButton("Delete Comment"); //deletes a post
                                                deleteCPost.setBounds(x + 235, commentY, 130, 25);
                                                deleteCPost.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        String changedTime = times.getText().replace(" ", "/");
                                                        writer.println("DeleteComment;;" + comment1 + ";;" + changedTime);
                                                        writer.flush();
                                                        try {
                                                            String message = reader.readLine();
                                                            if (message.equals("error")) {
                                                                JOptionPane.showMessageDialog(null, "Unable to delete comment", "Error", JOptionPane.ERROR_MESSAGE);
                                                            } else if (message.equals("success")) {
                                                                commentGeneralView.remove(commentPanel);
                                                                maincPanel.revalidate();
                                                                maincPanel.repaint();
                                                            }
                                                        } catch (IOException exception) {
                                                            exception.printStackTrace();
                                                        }
                                                    }
                                                });
                                                if (commenting.getText().equals("") || commenting.getText().equals(" ")) { //checks for post validity
                                                    JOptionPane.showMessageDialog(null, "Please enter a valid comment", "Error", JOptionPane.ERROR_MESSAGE);
                                                } else {
                                                    commentPanel.add(commentName);
                                                    commentPanel.add(commenting);
                                                    commentPanel.add(editCPost);
                                                    commentPanel.add(deleteCPost);
                                                    commentPanel.add(commentTime);
                                                    commentPanel.setPreferredSize(new Dimension(400, 200));
                                                    commentPanel.setBackground(new Color(102, 102, 255));
                                                    commentGeneralView.add(commentPanel, 0);
                                                    maincPanel.add(commentGeneralView, 0);
                                                    commentText.setText("");
                                                    commentGeneralView.revalidate();
                                                }
                                            }
                                        } catch (IOException exception) {
                                            exception.printStackTrace();
                                        }
                                    }
                                });
                                JPanel postingPanel = new JPanel();
                                postingPanel.setLayout(new BoxLayout(postingPanel, BoxLayout.LINE_AXIS));
                                postingPanel.add(commentText);
                                postingPanel.add(commentButton);
                                JFrame commentFrame = new JFrame("Comment Something!"); //setting up the frame
                                JScrollPane scroller = new JScrollPane(maincPanel);
                                scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                                commentFrame.add(scroller);

                                JScrollPane anotherScroller = new JScrollPane(postingPanel);
                                commentFrame.add(anotherScroller, BorderLayout.PAGE_END);
                                commentFrame.setSize(600, 400);
                                commentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                commentFrame.setLocationRelativeTo(null);
                                commentFrame.setVisible(true);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    viewPanel.add(title);
                    content.setText(content.getText().replaceAll("@@", ","));
                    content.setText(emoji(content.getText()));
                    viewPanel.add(content);
                    viewPanel.add(times);
                    viewPanel.add(name);
                    viewPanel.add(editPost);
                    viewPanel.add(deletePost);
                    viewPanel.add(comment);
                    generalView.add(viewPanel, 0);
                    mainPanel.add(generalView);
                }
            }
            JTextField newTitle = new JTextField("Enter Title Here", 20);
            JTextField newPost = new JTextField("Enter Post Here", 50); // text field and post for the bottom panel
            JButton postButton = new JButton("Post!");
            postButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int x = 0;
                    int y = 20;
                    int height = 20;
                    int postX = 50;
                    int postY = 50;
                    int postHeight = 20;
                    int commentY = 80;
                    Date date = new Date();
                    Timestamp ts = new Timestamp(date.getTime());
                    post = new JLabel(newPost.getText()); //posts name and post on this panel
                    title = new JLabel(newTitle.getText());
                    JPanel anotherPanel = new JPanel();
                    anotherPanel.setLayout(null);
                    anotherPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                    name = new JButton(username); //set this as the name of the account so that it can be seen
                    JFrame individualPosts = new JFrame("View " + name.getText() + " Posts and Comments");
                    name.setBounds(x, 10, 100, 25);
                    name.addActionListener(new ActionListener() { //edits and changes a post
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            writer.println("ViewPostsUser;;" + name.getText());
                            writer.flush();
                            try {
                                String message = reader.readLine();
                                JPanel allPosts = new JPanel();
                                allPosts.setLayout(new BoxLayout(allPosts, BoxLayout.PAGE_AXIS));
                                allPosts.setBackground((new Color(102, 102, 255)));
                                JLabel viewPostsMessage = new JLabel("View " + username + "'s Posts and Comments!");
                                viewPostsMessage.setFont(new Font("Kai", Font.BOLD, 17));
                                viewPostsMessage.setBounds(300, 0, 400, postHeight);
                                viewPostsMessage.setForeground(Color.WHITE);
                                viewPostsMessage.setOpaque(false);
                                allPosts.add(viewPostsMessage, 0);
                                String[] messageDivided = message.split("`");
                                for (int i = 0; i < messageDivided.length; i++) {
                                    JPanel multiplePanels = new JPanel();
                                    multiplePanels.setLayout(null);
                                    multiplePanels.setBackground((new Color(102, 102, 255)));
                                    multiplePanels.setBorder(BorderFactory.createLineBorder(Color.black));
                                    multiplePanels.setPreferredSize(new Dimension(400, 200));
                                    String[] lineDivided = messageDivided[i].split(",");
                                    JLabel title = new JLabel(lineDivided[1]);
                                    title.setBounds(0, 15, 175, postHeight);
                                    title.setFont(new Font("Kai", Font.BOLD, 15));
                                    title.setForeground(Color.WHITE);
                                    title.setOpaque(false);
                                    JLabel content = new JLabel(lineDivided[3]);
                                    content.setBounds(30, 50, 175, postHeight);
                                    content.setFont(new Font("Kai", Font.PLAIN, 15));
                                    content.setForeground(Color.WHITE);
                                    content.setOpaque(false);
                                    JLabel times = new JLabel(lineDivided[4]);
                                    times.setBounds(125, 15, 175, postHeight);
                                    times.setFont(new Font("Kai", Font.ITALIC, 15));
                                    times.setForeground(Color.WHITE);
                                    times.setOpaque(false);
                                    times.setOpaque(false);
                                    multiplePanels.add(title);
                                    multiplePanels.add(content);
                                    multiplePanels.add(times);
                                    allPosts.add(multiplePanels);
                                }
                                JScrollPane individualScrolls = new JScrollPane(allPosts);
                                individualScrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                                individualPosts.add(individualScrolls);
                                individualPosts.setSize(600, 400);
                                individualPosts.setDefaultCloseOperation(individualPosts.DISPOSE_ON_CLOSE);
                                individualPosts.setLocationRelativeTo(null);
                                individualPosts.setVisible(true);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    title.setBounds(0, 35, 175, postHeight);
                    title.setFont(new Font("Kai", Font.BOLD, 15));
                    title.setForeground(Color.WHITE);
                    title.setOpaque(false);
                    post.setBounds(postX, postY, 175, postHeight);
                    post.setFont(new Font("Kai", Font.PLAIN, 15));
                    post.setForeground(Color.WHITE);
                    post.setOpaque(false);
                    timestamp = new JLabel(ts.toString());
                    timestamp.setBounds(300, 15, 175, height);
                    timestamp.setFont(new Font("Kai", Font.ITALIC, 15));
                    timestamp.setForeground(Color.WHITE);
                    String cTime = timestamp.getText().replace(" ", "/");
                    String postSend = post.getText();
                    if (postSend.contains(","))
                        postSend = postSend.replaceAll(",", "@@");
                    postSend = emoji(postSend);
                    posts1 = new Posts(postNum, title.getText(), name.getText(), postSend, cTime);
                    writer.println("CreatePost;;" + posts1);
                    writer.flush();
                    try {
                        String message = reader.readLine();
                        if (message.equals("error")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid post", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals("success")) {
                            JFrame editFrame = new JFrame("Edit Post");
                            JButton editPost = new JButton("Edit Post");
                            editPost.setBounds(x + 115, commentY, 100, 25);
                            editPost.addActionListener(new ActionListener() { //edits and changes a post
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JPanel editPanel = new JPanel();
                                    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
                                    editPanel.setBackground((new Color(102, 102, 255)));
                                    JLabel editPost = new JLabel("Edit your post!");
                                    editPost.setBounds(1000, 10, 100, 25);
                                    editPost.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 20));
                                    editPost.setForeground(Color.WHITE);
                                    editPanel.add(editPost);
                                    JTextField editText = new JTextField(30);
                                    JButton postEdit = new JButton("Edit Post");
                                    postEdit.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //post.setText(editText.getText());
                                            String convertedTime = ts.toString().replace(" ", "/");
                                            String text1 = editText.getText();
                                            if (text1.contains(","))
                                                text1 = text1.replaceAll(",", "@@");
                                            text1 = emoji(text1);
                                            Posts editPosts = new Posts(postNum, title.getText(), name.getText(), text1, convertedTime);
                                            writer.println("EditPost;;" + editPosts);
                                            writer.flush();
                                            try {
                                                String message = reader.readLine();
                                                if (message.equals("error") || editText.getText().equals("") || editText.getText().equals(" ") || !username.equals(name.getText())) {
                                                    JOptionPane.showMessageDialog(null, "Please enter a valid edit", "Error", JOptionPane.ERROR_MESSAGE);
                                                } else if (message.equals("success") && username.equals(name.getText())) {
                                                    post.setText(editText.getText());
                                                    post.setOpaque(false);
                                                    post.setBounds(postX, postY, 175, postHeight);
                                                    post.setFont(new Font("Kai", Font.ITALIC, 15));
                                                    post.setForeground(Color.WHITE);
                                                    mainPanel.revalidate();
                                                    mainPanel.repaint();
                                                    JOptionPane.showMessageDialog(null, "Successfully Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                    editFrame.dispose();
                                                }
                                            } catch (IOException exception) {
                                                exception.printStackTrace();
                                            }
                                        }
                                    });
                                    JPanel bottomPanel = new JPanel();
                                    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
                                    bottomPanel.add(editText);
                                    bottomPanel.add(postEdit);
                                    editFrame.add(new JScrollPane(editPanel));
                                    editFrame.add(bottomPanel, BorderLayout.PAGE_END);
                                    editFrame.setDefaultCloseOperation(editFrame.DISPOSE_ON_CLOSE);
                                    editFrame.setLocationRelativeTo(null);
                                    editFrame.setSize(400, 300);
                                    editFrame.setVisible(true);
                                }
                            });
                            JButton deletePost = new JButton("Delete Post"); //deletes a post
                            deletePost.setBounds(x + 235, commentY, 100, 25);
                            deletePost.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    writer.println("DeletePost;;" + posts1);
                                    writer.flush();
                                    try {
                                        String message = reader.readLine();
                                        if (message.equals("error") || !username.equals(name.getText())) {
                                            JOptionPane.showMessageDialog(null, "Unable to delete post", "Error", JOptionPane.ERROR_MESSAGE);
                                        } else if (message.equals("success")) {
                                            generalView.remove(anotherPanel);
                                            mainPanel.revalidate();
                                            mainPanel.repaint();
                                        }
                                    } catch (IOException exception) {
                                        exception.printStackTrace();
                                    }
                                }
                            });
                            JPanel maincPanel = new JPanel();
                            maincPanel.setLayout(new BorderLayout());
                            maincPanel.setBackground(new Color(102, 102, 255));
                            JPanel commentGeneralView = new JPanel(); //creates the panel for everything to display
                            commentGeneralView.setLayout(new BoxLayout(commentGeneralView, BoxLayout.PAGE_AXIS));
                            commentGeneralView.setBackground(new Color(102, 102, 255));
                            JButton comment = new JButton("Comment!"); //comment button is set up
                            comment.setBounds(0, commentY, 100, 25);
                            comment.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {//work on commenting
                                    writer.println("ViewComments;;" + posts1); //viewing all comments and implementing action listeners fpr each
                                    writer.flush();
                                    try {
                                        String commentMessage = reader.readLine();
                                        if (!commentMessage.equals("")) {
                                            String[] allMessageDivided = commentMessage.split("`");
                                            for (int i = 0; i < allMessageDivided.length; i++) {
                                                String[] lineDivided = allMessageDivided[i].split(",");
                                                commenting = new JLabel(lineDivided[1]);
                                                JPanel commentPanel = new JPanel();
                                                commentPanel.setLayout(null);
                                                commentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                                                JLabel commentName = new JLabel(lineDivided[0]); //set this as the name of the account so that it can be seen
                                                commentName.setBounds(0, 20, 175, 20);
                                                commentName.setFont(new Font("Kai", Font.ITALIC, 15));
                                                commentName.setForeground(Color.WHITE);
                                                commenting.setBounds(50, 50, 175, 20);
                                                commenting.setFont(new Font("Kai", Font.PLAIN, 15));
                                                commenting.setForeground(Color.WHITE);
                                                commenting.setOpaque(false);
                                                JLabel commentTime = new JLabel(lineDivided[3]);
                                                commentTime.setBounds(300, 20, 175, 20);
                                                commentTime.setFont(new Font("Kai", Font.ITALIC, 15));
                                                commentTime.setForeground(Color.WHITE);
                                                JFrame editCFrame = new JFrame("Edit Comment");
                                                JButton editCPost = new JButton("Edit Comment");
                                                editCPost.setBounds(0, 80, 125, 25);
                                                editCPost.addActionListener(new ActionListener() { //edits and changes a post
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        JPanel commentEditPanel = new JPanel();
                                                        commentEditPanel.setLayout(new BoxLayout(commentEditPanel, BoxLayout.PAGE_AXIS));
                                                        commentEditPanel.setBackground((new Color(102, 102, 255)));
                                                        JLabel editComment = new JLabel("Edit your comment!");
                                                        editComment.setBounds(1000, 10, 100, 25);
                                                        editComment.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 20));
                                                        editComment.setForeground(Color.WHITE);
                                                        commentEditPanel.add(editComment);
                                                        JTextField editCommentText = new JTextField(30);
                                                        JButton postCommentEdit = new JButton("Edit Comment");
                                                        postCommentEdit.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                String convertedTime = ts.toString().replace(" ", "/");
                                                                String commentChangeTime = commentTime.getText().replace(" ", "/");
                                                                Comment comment1 = new Comment(name.getText(), commenting.getText(), convertedTime, commentChangeTime);
                                                                String commentEdit = editCommentText.getText();
                                                                if (commentEdit.contains(","))
                                                                    commentEdit = commentEdit.replaceAll(",", "@@");
                                                                commentEdit = emoji(commentEdit);
                                                                writer.println("EditComment;;" + comment1 + ";;" + convertedTime + ";;" + commentEdit);
                                                                writer.flush();
                                                                try {
                                                                    String message = reader.readLine();
                                                                    if (message.equals("error") || editCommentText.getText().equals("") || editCommentText.getText().equals(" ") || !username.equals(commentName.getText())) { //checks for post validity
                                                                        JOptionPane.showMessageDialog(null, "Please enter a valid edit", "Error", JOptionPane.ERROR_MESSAGE);
                                                                    } else if (message.equals("success") && username.equals(commentName.getText())) {
                                                                        commenting.setText(editCommentText.getText());
                                                                        commenting.setOpaque(false);
                                                                        commenting.setBounds(50, 50, 175, 20);
                                                                        commenting.setFont(new Font("Kai", Font.ITALIC, 15));
                                                                        commenting.setForeground(Color.WHITE);
                                                                        maincPanel.revalidate();
                                                                        maincPanel.repaint();
                                                                        JOptionPane.showMessageDialog(null, "Successfully Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                                        editCFrame.dispose();
                                                                    }
                                                                } catch (IOException exception) {
                                                                    exception.printStackTrace();
                                                                }
                                                            }
                                                        });
                                                        JPanel bottomPanel = new JPanel();
                                                        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
                                                        bottomPanel.add(editCommentText);
                                                        bottomPanel.add(postCommentEdit);
                                                        editCFrame.add(new JScrollPane(commentEditPanel));
                                                        editCFrame.add(bottomPanel, BorderLayout.PAGE_END);
                                                        editCFrame.setDefaultCloseOperation(editCFrame.DISPOSE_ON_CLOSE);
                                                        editCFrame.setLocationRelativeTo(null);
                                                        editCFrame.setSize(400, 300);
                                                        editCFrame.setVisible(true);
                                                    }
                                                });
                                                JButton deleteCPost = new JButton("Delete Comment"); //deletes a post
                                                deleteCPost.setBounds(235, 80, 130, 25);
                                                deleteCPost.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        String convertedTime = ts.toString().replace(" ", "/");
                                                        String commentChangeTime = commentTime.getText().replace(" ", "/");
                                                        Comment comment1 = new Comment(name.getText(), commenting.getText(), convertedTime, commentChangeTime);
                                                        writer.println("DeleteComment;;" + comment1 + ";;" + convertedTime);
                                                        writer.flush();
                                                        try {
                                                            String message = reader.readLine();
                                                            if (message.equals("error")) {
                                                                JOptionPane.showMessageDialog(null, "Unable to delete comment", "Error", JOptionPane.ERROR_MESSAGE);
                                                            } else if (message.equals("success") && username.equals(commentName.getText())) {
                                                                commentGeneralView.remove(commentPanel);
                                                                maincPanel.revalidate();
                                                                maincPanel.repaint();
                                                            }
                                                        } catch (IOException exception) {
                                                            exception.printStackTrace();
                                                        }
                                                    }
                                                });
                                                commentPanel.add(commentName);
                                                commentPanel.add(commenting);
                                                commentPanel.add(editCPost);
                                                commentPanel.add(deleteCPost);
                                                commentPanel.add(commentTime);
                                                commentPanel.setPreferredSize(new Dimension(400, 200));
                                                commentPanel.setBackground(new Color(102, 102, 255));
                                                commentGeneralView.add(commentPanel, 0);
                                                maincPanel.add(commentGeneralView);
                                                commentGeneralView.revalidate();
                                            }
                                        }
                                        JTextField commentText = new JTextField(20);
                                        JButton commentButton = new JButton("Comment!");
                                        commentButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                int x = 0;
                                                int y = 20;
                                                int height = 20;
                                                int postX = 50;
                                                int postY = 50;
                                                int postHeight = 20;
                                                int commentY = 80;
                                                Date date1 = new Date();
                                                Timestamp timestamp = new Timestamp(date1.getTime());
                                                commenting = new JLabel(commentText.getText());
                                                JPanel commentPanel = new JPanel();
                                                commentPanel.setLayout(null);
                                                commentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                                                JLabel commentName = new JLabel(username); //set this as the name of the account so that it can be seen
                                                commentName.setBounds(x, y, 175, height);
                                                commentName.setFont(new Font("Kai", Font.ITALIC, 15));
                                                commentName.setForeground(Color.WHITE);
                                                commenting.setBounds(postX, postY, 175, postHeight);
                                                commenting.setFont(new Font("Kai", Font.PLAIN, 15));
                                                commenting.setForeground(Color.WHITE);
                                                commenting.setOpaque(false);
                                                JLabel commentTime = new JLabel(timestamp.toString());
                                                commentTime.setBounds(300, y, 175, height);
                                                commentTime.setFont(new Font("Kai", Font.ITALIC, 15));
                                                commentTime.setForeground(Color.WHITE);
                                                String viewChangedTime = ts.toString().replace(" ", "/");
                                                String commentChangeTime = commentTime.getText().replace(" ", "/");
                                                String commentBody = commenting.getText();
                                                if (commentBody.contains(",")) {
                                                    commentBody = commentBody.replaceAll(",", "@@");
                                                }
                                                commentBody = emoji(commentBody);
                                                Comment comment1 = new Comment(username, commentBody, viewChangedTime, commentChangeTime);
                                                writer.println("AddComment;;" + comment1 + ";;" + viewChangedTime);
                                                writer.flush();
                                                try {
                                                    String message = reader.readLine();
                                                    if (message.equals("error")) {
                                                        JOptionPane.showMessageDialog(null, "Please enter a valid comment", "Error", JOptionPane.ERROR_MESSAGE);
                                                    } else if (message.equals("success")) {
                                                        JFrame editCFrame = new JFrame("Edit Comment");
                                                        JButton editCPost = new JButton("Edit Comment");
                                                        editCPost.setBounds(x, commentY, 125, 25);
                                                        editCPost.addActionListener(new ActionListener() { //edits and changes a post
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                JPanel commentEditPanel = new JPanel();
                                                                commentEditPanel.setLayout(new BoxLayout(commentEditPanel, BoxLayout.PAGE_AXIS));
                                                                commentEditPanel.setBackground((new Color(102, 102, 255)));
                                                                JLabel editComment = new JLabel("Edit your comment!");
                                                                editComment.setBounds(1000, 10, 100, 25);
                                                                editComment.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 20));
                                                                editComment.setForeground(Color.WHITE);
                                                                commentEditPanel.add(editComment);
                                                                JTextField editCommentText = new JTextField(30);
                                                                JButton postCommentEdit = new JButton("Edit Comment");
                                                                postCommentEdit.addActionListener(new ActionListener() {
                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        String convertedTime = ts.toString().replace(" ", "/");
                                                                        String commentEdit = editCommentText.getText();
                                                                        if (commentEdit.contains(","))
                                                                            commentEdit = commentEdit.replaceAll(",", "@@");
                                                                        commentEdit = emoji(commentEdit);
                                                                        writer.println("EditComment;;" + comment1 + ";;" + convertedTime + ";;" + commentEdit);
                                                                        writer.flush();
                                                                        try {
                                                                            String message = reader.readLine();
                                                                            if (message.equals("error") || editCommentText.getText().equals("") || editCommentText.getText().equals(" ") || !username.equals(commentName.getText())) { //checks for post validity
                                                                                JOptionPane.showMessageDialog(null, "Please enter a valid edit", "Error", JOptionPane.ERROR_MESSAGE);
                                                                            } else if (message.equals("success") && username.equals(commentName.getText())) {
                                                                                commenting.setText(editCommentText.getText());
                                                                                commenting.setOpaque(false);
                                                                                commenting.setBounds(postX, postY, 175, postHeight);
                                                                                commenting.setFont(new Font("Kai", Font.ITALIC, 15));
                                                                                commenting.setForeground(Color.WHITE);
                                                                                maincPanel.revalidate();
                                                                                maincPanel.repaint();
                                                                                JOptionPane.showMessageDialog(null, "Successfully Edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                                                editCFrame.dispose();
                                                                            }
                                                                        } catch (IOException exception) {
                                                                            exception.printStackTrace();
                                                                        }
                                                                    }
                                                                });
                                                                JPanel bottomPanel = new JPanel();
                                                                bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
                                                                bottomPanel.add(editCommentText);
                                                                bottomPanel.add(postCommentEdit);
                                                                editCFrame.add(new JScrollPane(commentEditPanel));
                                                                editCFrame.add(bottomPanel, BorderLayout.PAGE_END);
                                                                editCFrame.setDefaultCloseOperation(editCFrame.DISPOSE_ON_CLOSE);
                                                                editCFrame.setLocationRelativeTo(null);
                                                                editCFrame.setSize(400, 300);
                                                                editCFrame.setVisible(true);
                                                            }
                                                        });
                                                        JButton deleteCPost = new JButton("Delete Comment"); //deletes a post
                                                        deleteCPost.setBounds(x + 235, commentY, 130, 25);
                                                        deleteCPost.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                String convertedTime = ts.toString().replace(" ", "/");
                                                                writer.println("DeleteComment;;" + comment1 + ";;" + convertedTime);
                                                                writer.flush();
                                                                try {
                                                                    String message = reader.readLine();
                                                                    if (message.equals("error")) {
                                                                        JOptionPane.showMessageDialog(null, "Unable to delete comment", "Error", JOptionPane.ERROR_MESSAGE);
                                                                    } else if (message.equals("success") && username.equals(commentName.getText())) {
                                                                        commentGeneralView.remove(commentPanel);
                                                                        maincPanel.revalidate();
                                                                        maincPanel.repaint();
                                                                    }
                                                                } catch (IOException exception) {
                                                                    exception.printStackTrace();
                                                                }
                                                            }
                                                        });
                                                        if (commenting.getText().equals("") || commenting.getText().equals(" ")) { //checks for post validity
                                                            JOptionPane.showMessageDialog(null, "Please enter a valid post", "Error", JOptionPane.ERROR_MESSAGE);
                                                        } else {
                                                            commentPanel.add(commentName);
                                                            commentPanel.add(commenting);
                                                            commentPanel.add(editCPost);
                                                            commentPanel.add(deleteCPost);
                                                            commentPanel.add(commentTime);
                                                            commentPanel.setPreferredSize(new Dimension(400, 200));
                                                            commentPanel.setBackground(new Color(102, 102, 255));
                                                            commentGeneralView.add(commentPanel);
                                                            maincPanel.add(commentGeneralView);
                                                            commentText.setText("");
                                                            commentGeneralView.revalidate();
                                                        }
                                                    }
                                                } catch (IOException exception) {
                                                    exception.printStackTrace();
                                                }
                                            }
                                        });
                                        JPanel postingPanel = new JPanel();
                                        postingPanel.setLayout(new BoxLayout(postingPanel, BoxLayout.LINE_AXIS));
                                        postingPanel.add(commentText);
                                        postingPanel.add(commentButton);
                                        JFrame commentFrame = new JFrame("Comment Something!"); //setting up the frame
                                        JScrollPane scroller = new JScrollPane(maincPanel);
                                        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                                        commentFrame.add(scroller);
                                        JScrollPane anotherScroller = new JScrollPane(postingPanel);
                                        commentFrame.add(anotherScroller, BorderLayout.PAGE_END);
                                        commentFrame.setSize(600, 400);
                                        commentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                        commentFrame.setLocationRelativeTo(null);
                                        commentFrame.setVisible(true);
                                    } catch (IOException exception) {
                                        exception.printStackTrace();
                                    }
                                }
                            });
                            if (post.getText().equals("") || post.getText().equals(" ")) { //checks for post validity
                                JOptionPane.showMessageDialog(null, "Please enter a valid post", "Error", JOptionPane.ERROR_MESSAGE);
                            } else if (title.getText().equals("") || title.getText().equals(" ")) { //checks for post validity
                                JOptionPane.showMessageDialog(null, "Please enter a valid title", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                anotherPanel.add(name);
                                anotherPanel.add(title);
                                anotherPanel.add(post);
                                anotherPanel.add(editPost);
                                anotherPanel.add(deletePost);
                                anotherPanel.add(comment);
                                anotherPanel.add(timestamp);
                                anotherPanel.setPreferredSize(new Dimension(400, 200));
                                anotherPanel.setBackground(new Color(102, 102, 255));
                                generalView.add(anotherPanel, 0);
                                mainPanel.add(generalView, 0);
                                postNum = postNum++;
                                newPost.setText("Enter Post Here");
                                newTitle.setText("Enter Title Here");
                                generalView.revalidate();
                            }
                        }
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                }
            });
            JButton importPost = new JButton("Import Post"); //have some kind of joption to pop up
            importPost.setBounds(315, 100, 100, 25);
            importPost.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame importFile = new JFrame("Import File");
                    JPanel importPanel = new JPanel();
                    importPanel.setLayout(null);
                    importPanel.setBackground(new Color(102, 102, 255));
                    JLabel importMessage = new JLabel("Import a Post! Enter file name below");
                    importMessage.setBounds(25, 15, 175, 20);
                    importMessage.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 12));
                    importMessage.setForeground(Color.WHITE);
                    JTextField fileName = new JTextField("", 100);
                    importMessage.setBounds(0, 20, 175, 20);
                    JButton importing = new JButton("Import Now!");
                    importing.setBounds(250, 50, 125, 20);
                    fileName.setBounds(25, 50, 100, 20);
                    Date date2 = new Date();
                    Timestamp importTime = new Timestamp(date2.getTime());
                    importing.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String timeChange = importTime.toString().replace(" ", "/");
                            writer.println("ImportCSV;;" + fileName.getText() + ";;" + timeChange);
                            writer.flush();
                            try {
                                String imported = reader.readLine();
                                if (imported.equals("error")) {
                                    JOptionPane.showMessageDialog(null, "Could not import file", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if (imported.equals("success")) {
                                    JOptionPane.showMessageDialog(null, "Successfully imported!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    mainFrame.dispose();
                                    run();
                                    importFile.dispose();
                                }
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    importPanel.add(importMessage);
                    importPanel.add(importing);
                    importPanel.add(fileName);
                    importFile.add(importPanel);
                    importFile.setSize(400, 300);
                    importFile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    importFile.setLocationRelativeTo(null);
                    importFile.setVisible(true);
                }
            });
            JFrame exportFrame = new JFrame("Export Posts");
            JButton exportPost = new JButton("Export Posts");
            exportPost.setBounds(435, 100, 100, 25);
            exportPost.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel exportPanel = new JPanel();
                    exportPanel.setLayout(null);
                    exportPanel.setBackground(new Color(102, 102, 255));
                    JLabel exportMessage = new JLabel("Enter file name below");
                    exportMessage.setBounds(25, 15, 175, 20);
                    exportMessage.setFont(new Font("Kai", Font.BOLD | Font.ITALIC, 12));
                    exportMessage.setForeground(Color.WHITE);
                    exportMessage.setBounds(0, 20, 175, 20);
                    JTextField exportfileName = new JTextField("", 100);
                    JButton exporting = new JButton("Export Now!");
                    exportfileName.setBounds(25, 50, 100, 20);
                    exporting.setBounds(250, 50, 125, 20);
                    exporting.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            writer.println("ExportCSV;;" + username + ";;" + exportfileName.getText());
                            writer.flush();
                            try {
                                String imported = reader.readLine();
                                if (imported.equals("error")) {
                                    JOptionPane.showMessageDialog(null, "Could not export file", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if (imported.equals("success")) {
                                    JOptionPane.showMessageDialog(null, "Successfully exported! Go check " + exportfileName.getText() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    exportFrame.dispose();
                                }
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    exportPanel.add(exportMessage);
                    exportPanel.add(exporting);
                    exportPanel.add(exportfileName);
                    exportFrame.add(exportPanel);
                    exportFrame.setSize(400, 300);
                    exportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    exportFrame.setLocationRelativeTo(null);
                    exportFrame.setVisible(true);
                }
            });
            JPanel postingPanel = new JPanel();
            postingPanel.setLayout(new BoxLayout(postingPanel, BoxLayout.LINE_AXIS));
            postingPanel.add(newTitle);
            postingPanel.add(newPost);
            postingPanel.add(postButton);
            postingPanel.add(importPost);
            postingPanel.add(exportPost);
            JScrollPane scroller = new JScrollPane(mainPanel);
            scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainFrame.add(scroller);
            JScrollPane anotherScroller = new JScrollPane(postingPanel);
            mainFrame.add(anotherScroller, BorderLayout.PAGE_END);
            mainFrame.setSize(1000, 800);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
