import java.io.*;
import java.util.ArrayList;

/**
 * PostsBank.java
 *
 * Handles reading and writing to file along with carrying out requested operations related to posts and comments such as create, edit and delete.
 *
 * @author Group 3, CS 18000
 * @version 5/2/2021
 */

public class PostsBank {
    private final String filename;
    private ArrayList<Posts> currentPosts;

    public PostsBank(java.lang.String filename) {
        this.filename = filename;
        readPosts();
    }

    public void saveCurrentPosts() {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(filename, false));

            for (Posts post: currentPosts) {
                writer.println(post);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //adds new post to the array
    public void createPost(Posts postNew) {
        currentPosts.add(postNew);
        saveCurrentPosts();
    }

    // edit post if username and current post index matches
    public boolean editPost(Posts posts) {
        for (Posts posts1: currentPosts) {
            if (posts1.equals(posts)) {
                posts1.setContext(posts.getContext());
                saveCurrentPosts();
                return true;
            }
        }
        return false;
    }


    // delete post if
    public boolean deletePost(Posts posts) {
        for (Posts posts1: currentPosts) {
            if (posts1.equals(posts)) {
                currentPosts.remove(posts1);
                saveCurrentPosts();
                return true;
            }
        }
        return false;
    }

    //view posts
    public String viewPost() {
        readPosts();
        StringBuilder allPosts = new StringBuilder();
        for (Posts currentPost : currentPosts) {
            String post = currentPost.toString();
            allPosts.append(post).append("`");
        }
        return allPosts.toString();
    }

    public String viewPosts(String user) {
        readPosts();
        StringBuilder allPosts = new StringBuilder();
        for (Posts currentPost: currentPosts) {
            if (currentPost.getName().equals(user)) {
                allPosts.append(currentPost).append("`");
            }
        }
        return allPosts.toString();
    }

    // pass in the comment object and postNum that the comment belongs to
    public boolean addComment(Comment comment, String postTime) {
        for (Posts posts: currentPosts) {
            if (posts.getPostTime().equals(postTime)) {
                posts.addComment(comment);
                saveCurrentPosts();
                return true;
            }
        }
        return false;
    }

    public boolean deleteComment(Comment comment, String postTime) {
        for (Posts posts: currentPosts) {
            if (posts.getPostTime().equals(postTime)) {
                posts.removeComment(comment);
                saveCurrentPosts();
                return true;
            }
        }
        return false;
    }

    public boolean editComment(Comment comment, String postTime, String newText) {
        for (Posts posts: currentPosts) {
            if (posts.getPostTime().equals(postTime)) {
                posts.editComment(comment, newText);
                saveCurrentPosts();
                return true;
            }
        }
        return false;
    }

    public String viewComments(Posts posts) {
        StringBuilder allPosts = new StringBuilder();
        for (Posts posts1: currentPosts) {
            if (posts.equals(posts1)) {
                for (Comment comment: posts1.getComment()) {
                    allPosts.append(comment).append("`");
                }
            }
        }
        System.out.println(allPosts);
        return allPosts.toString();
    }

    //loads the saved file content onto the arraylist
    public void readPosts() {
        //Array List storing Posts
        currentPosts = new ArrayList<>();

        try {
            BufferedReader bfr = new BufferedReader(new FileReader(filename));
            String line = bfr.readLine();
            while (line != null) {
                currentPosts.add(new Posts(line));
                line = bfr.readLine();
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean importCSV(String importFilename, String currentTime) {

        try {
            //read the file from importFile
            BufferedReader bfr = new BufferedReader(new FileReader(importFilename));
            // append to existing file (posts.csv)
            PrintWriter writer = new PrintWriter(new FileOutputStream(this.filename, true));

            String line = bfr.readLine();

            while (line != null) {

                String[] split = line.split(",");   //split base on comma
                int lengthSize =  split.length;           //count the length of array
                if (lengthSize < 5) {
                    return false;
                }

                split[4] = currentTime;

                if (lengthSize > 5) {
                    for (int i = 8; i < lengthSize; i+=4) {
                        split[i] = currentTime;
                    }
                }

                String linePrintOut = "";                 //pop the last comma in the string

                for (String element: split) {
                    linePrintOut = linePrintOut + element + ",";
                }

                linePrintOut = linePrintOut.substring(0, linePrintOut.length() - 1);

                writer.println(linePrintOut);
                line = bfr.readLine();

            }
            bfr.close();
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public boolean exportCSV(String userName, String exportFileName) {
        try {
            // read posts.csv file
            BufferedReader bfr = new BufferedReader(new FileReader(this.filename));
            // Export to new file
            PrintWriter writer = new PrintWriter(new FileOutputStream(exportFileName, false));

            String line= bfr.readLine();

            while (line != null) {
                String[] split = line.split(",");
                // if user name matches, write line
                System.out.println(split[2]);
                if (split[2].equals(userName)) {
                    writer.println(line);
                }
                line = bfr.readLine();
            }
            bfr.close();
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
