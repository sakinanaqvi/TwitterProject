/**
 * This class tests the postBank class by passing in one "pass" condition and one "fail" condition.
 * The testing result will be displayed in terminal.
 *
 * <p>Purdue University -- CS18000 -- Spring 2021 -- Project 5
 *
 * @version May 3, 2021
 */

public class Test {
    public static void main(String args[]) {

        /* --PostBank-- */
        //construct a new postBank object bu passing in a main csv file called "testPost.csv"
        PostsBank postsBank = new PostsBank("testPost.csv");

        //call readPost method
        //testing not required(Please see readme file
        postsBank.readPosts();

        //create new post object for testing use
        Posts newPost1 = new Posts(1, "tittle",
                "ayush", "hello", "2021-05-01/20:24:22.389");
        Posts newPost2 = new Posts(2, "Hello",
                "Tao", "good bye", "2021-05-01/21:41:38.068");
        Posts newPost3 = new Posts(3, "Hello",
                "Tao", "good bye", "2021-05-01/21:41:39.068");


        //create new post
        //testing not required(Please see readme file
        postsBank.createPost(newPost1);                          //pass
        postsBank.createPost(newPost2);                          //pass
        postsBank.createPost(newPost3);                          //pass


        //deletePost test
        //pass
        System.out.println("1: ");
        if (postsBank.deletePost(newPost1)) {
            System.out.println("Post was successfully deleted ");
        } else {
            System.out.println("Post could not be deleted");
        }

        //fail because the post does not exist
        System.out.println("2: ");
        if (postsBank.deletePost(new Posts(
                2, "dddddd", "Tao",
                "good bye", "2021-05-05/21:41:38.068"))) {
            System.out.println("Post was successfully deleted ");
        } else {
            System.out.println("Post could not be deleted");
        }


        //editPost test
        //pass because post time matches
        System.out.println("3: ");
        if (postsBank.editPost(
                new Posts(3, "Hi",
                        "Tao", "good afternoon",
                        "2021-05-01/21:41:39.068"))) {
            System.out.println("Post was successfully edited");
        } else {
            System.out.println("Post could not be edited");
        }

        //fail because the post time does not exist
        System.out.println("4: ");
        if (postsBank.editPost(
                new Posts(3, "Hi",
                        "Tao", "good afternoon",
                        "2021-05-03/21:41:39.068"))) {
            System.out.println("Post was successfully edited ");
        } else {
            System.out.println("Post could not be edited");
        }


        //create comment object for test use
        Comment newComment1 = new Comment("Tao", "You are awesome",
                "2021-05-01/21:41:39.068", "2021-05-01/21:42:39.068");
        Comment newComment2 = new Comment("Tao", "You are beautiful",
                "2021-05-01/21:42:39.068", "2021-05-01/21:42:39.068");
        Comment newComment3 = new Comment("Jake", "You are fantastic",
                "2021-05-01/21:45:39.068", "2021-05-01/21:49:39.068");

        //addComment Test
        //pass
        System.out.println("5: ");
        if (postsBank.addComment(newComment1,
                "2021-05-01/21:41:39.068")) {
            System.out.println("Comment was successfully added ");
        } else {
            System.out.println("Comment could not be added");
        }

        //fail
        //Time stamp did not match
        System.out.println("6: ");
        if (postsBank.addComment(newComment1,
                "2021-05-05/21:41:39.068")) {
            System.out.println("Comment was successfully added ");
        } else {
            System.out.println("Comment could not be added");
        }


        //deleteComment Test
        //pass
        System.out.println("7: ");
        if ((postsBank.deleteComment(newComment1,
                "2021-05-01/21:41:39.068"))) {
            System.out.println("Comment was successfully deleted");
        } else {
            System.out.println("Comment could not be deleted");
        }

        //fail
        System.out.println("8: ");
        if (postsBank.deleteComment(newComment1,
                "2021-05-03/21:41:39.068")) {
            System.out.println("Comment was successfully added ");
        } else {
            System.out.println("Comment could not be added");
        }


        //editComment Test
        //pass -> when the Post time matches
        System.out.println("9: ");
        if (postsBank.editComment(newComment3,
                "2021-05-01/21:41:39.068",
                "He is handsome")) {
            System.out.println("Comment was successfully edited ");
        } else {
            System.out.println("Comment could not be edited");
        }


        //fail -> when the Post time does not match
        System.out.println("10: ");
        if (postsBank.editComment(newComment3,
                "2021-05-03/21:41:40.068",
                "He is handsome")) {
            System.out.println("Comment was successfully edited ");
        } else {
            System.out.println("Comment could not be edited");
        }



        //importCSV test
        //pass
        System.out.println("11: ");
        if (postsBank.importCSV("testImportCSV.csv", "2021-05-04/21:41:40.068")) {
            System.out.println("File was successfully imported ");
        } else {
            System.out.println("File could not be imported");
        }

        //fail
        System.out.println("12: ");
        if (postsBank.importCSV("testImportInvalid.csv", "2021-05-04/21:41:40.068")) {
            System.out.println("File was successfully imported ");
        } else {
            System.out.println("File could not be imported");
        }


        //exportCSV test
        //always valid
        postsBank.exportCSV("tao", "testImportInvalid.csv");
    }
}
