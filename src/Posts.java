import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Posts.java
 *
 * Represents a post and provides methods to handles modifying a given post.
 *
 * @version 5/2/2021
 */

public class Posts {
    private int numPost;          //number index of current comment
    private String title;
    private String name;
    private String context;
    private final String postTime;
    private ArrayList<Comment> comment;

    public Posts(int numPost, String title, String name, String context, String postTime, ArrayList<Comment> comment) { // add Comment comment in constructor
        this.numPost = numPost;
        this.title = title;
        this.name = name;
        this.context = context;
        this.postTime = postTime;
        this.comment = comment;
    }

    public Posts(int numPost, String title, String name, String context, String postTime) { // add Comment comment in constructor
        this.numPost = numPost;
        this.title = title;
        this.name = name;
        this.context = context;
        this.postTime = postTime;
        this.comment = new ArrayList<>();
    }

    //updated
    public Posts(String post) {
        String[] arr = post.split(",");
        comment = new ArrayList<>();

        this.numPost = Integer.parseInt(arr[0]);
        this.title = arr[1];
        this.name = arr[2];
        this.context = arr[3];
        this.postTime = arr[4];

        if (arr.length > 5) {
            for (int i = 5; i < arr.length; i += 4) {
                comment.add(new Comment(arr[i] + "," + arr[i + 1] + "," + arr[i + 2] + "," + arr[i + 3]));
            }
        }
    }


    //for deleting post
    public boolean equals(Posts a) {
        return (a.getPostTime().equals(this.postTime));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s,%s,%s,%s,%s,", this.numPost, this.title, this.name, this.context, this.postTime));
        for (Comment com: comment)
            res.append(com.toString());
        return res.toString();
    }

    public int getNumPost() {
        return this.numPost;
    }

    //returns post title
    public String getTitle() {
        return this.title;
    }

    //returns post name
    public String getName() {
        return this.name;
    }

    //return post time
    public String getPostTime() {
        return this.postTime;
    }

    //return post context
    public String getContext() {
        return this.context;
    }

    //return comment object
    public ArrayList<Comment> getComment() {
        return this.comment;
    }

    //set new text context
    public void setContext(String newContext) {
        this.context = newContext;
    }

    public void addComment (Comment comment) {
        this.comment.add(comment);
    }

    public boolean removeComment (Comment comment) {
        for (Comment comment1: this.comment) {
            if (comment1.equals(comment))
                return this.comment.remove(comment1);
        }
        return false;
    }

    public boolean editComment (Comment comment, String newText) {
        for (Comment comment1: this.comment) {
            if (comment1.equals(comment)) {
                comment1.setCommentText(newText);
                return true;
            }
        }
        return false;
    }

}
