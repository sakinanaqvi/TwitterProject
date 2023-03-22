import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /* --user-- */
        User user = new User("hello", "test");
        System.out.println(user.getUserID());
        System.out.println(user.getPassword());
        user.setUserID("hello1");
        user.setPassword("test1");
        System.out.println(user.getUserID());
        System.out.println(user.getPassword());
        User user1 = new User(user.toString());

        /* --userBank-- */
        UserBank userBank = new UserBank("userText.csv");
        System.out.println(userBank.createAccount(user)); //pass
        System.out.println(userBank.createAccount(user)); //fail
        System.out.println(userBank.loginAccount(user)); //pass
        System.out.println(userBank.loginAccount(user1)); //fail
        System.out.println(userBank.editAccount(user, "hi")); //pass
        System.out.println(userBank.editAccount(user1, "hi")); //fail
        System.out.println(userBank.deleteAccount(user)); //pass
        System.out.println(userBank.deleteAccount(user)); //fail
        //test saveCurrentUsers
        System.out.println(userBank.createAccount(user));
        System.out.println(userBank.createAccount(user1));
        userBank.saveCurrentUserAccount();

        /* --posts-- */
        Posts posts = new Posts(1, "tittle", "ayush", "hello", "today");
        Posts posts1 = new Posts(posts.toString());
        System.out.println(posts.equals(posts1)); //pass
        posts1.setContext("goodbye");
        System.out.println(posts.equals(posts1)); //fail
        System.out.println(posts.getNumPost());
        System.out.println(posts.getTitle());
        System.out.println(posts.getName());
        System.out.println(posts.getPostTime());
        System.out.println(posts.getContext());

        /* --comment-- */
        Comment comment = new Comment("name", "hello", "today", "tomorrow");
        Comment comment1 = new Comment(comment.toString());
        System.out.println(comment.equals(comment1));
        System.out.println(comment.getUserName());
        System.out.println(comment.getCommentText());
        System.out.println(comment.getPostTime());
        System.out.println(comment);
    }
}
