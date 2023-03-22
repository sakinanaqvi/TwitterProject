/**
 * Comment.java
 *
 * A class that represents a comment and its private fields. Includes methods to modify features of a given comment object.
 *
 * @version 5/2/2021
 */

public class Comment {
    private final String userName;
    private String commentText;
    private String postTime;
    private String commentTime;

    // Constructor
    public Comment(String userName, String commentText, String postTime, String commentTime) {
        this.userName = userName;                //current user object
        this.commentText = commentText;  //comment Text
        this.postTime = postTime;
        this.commentTime = commentTime;
    }

    public Comment(String str) {
        String[] arr = str.split(",");

        userName = arr[0];
        commentText = arr[1];
        postTime = arr[2];
        commentTime = arr[3];
    }

    public String getUserName() {
        return this.userName;
    }

    public String getCommentText() {
        return this.commentText;
    }

    public String getPostTime() {
        return this.postTime;
    }

    public String getCommentTime() {
        return this.commentTime;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,", this.userName, this.getCommentText(), this.getPostTime(), this.getCommentTime());
    }
