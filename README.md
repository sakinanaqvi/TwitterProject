# Overview
In this project, we designed a social media where users can login to personal accounts and creating, editing, and deleting posts.

## Running the project
To run the project:
1. Start the server using the driver method in Server.java
2. Start the client using the driver method in Client.java

Once the client is connected, the GUI starts.

# Classes
## Server.java
Opens a socket for clients to connect to. For ever client that connects, the server starts a thread that handles requets made by the client.
## ServerThread.java
Reads requests made by the Client and handles them by reading the type of message and running commands from PostsBank.java and UserBank.java to handle requests.
## Client.java
Creates a connection with the server and passes on the input and output stream of the socket to the GUI.
## GUI.java
Starts the AccountsGUI.java and passes on a reader and writer for it to communicate to the server.
## AccountGUI.java
Creates a GUI window that displays a Login/Create account screen, uses reader and writer to verify logins with the server and storing new accounts. Once the user is logged in, opens up PostsGUI.java. The user can also choose to edit an account that has already been created by changing the password and deleting their own account.
## PostsGUI.java
Creates a GUI window that displays posts and allows users to create new ones or edit and delete existing ones. Uses a reader and writer to carry out these operations in the backend. The user is able to make posts and there are buttons to edit and also delete a post. There is also a button to comment on a post and a user is able to edit and delete their own comments.
## PostsBank.java
Contains various methods related to manipulating posts in the backend. Includes methods to load files, save to file, create, edit and delete posts and comments.
## UserBank.java
Contains various methods related to manipulating users in the backend. Includes methods to load files, save to file, login, create account, edit account, and delete accounts.
## User.java
Record the userID and password of a given user. The constructor takes in either the userID and password or a message form of the user and extracts the userID and password from it.
## Posts.java
Records the postNumber, tittle, name, context, postTime, and comments of a given post. The class can be constructed using the fields, all fields except comments, and a message form where it extracts the fields from a string with fields separated by a comma. There is a addComment() method that adds a comment to the list of comments for the psot given a comment object. The toString() returns a string with all fields separated by a comma.
## Comment.java
Records the username, comment text, post time, and comment number. Can constructed using the fields or a string message form where the information is extracted separated by commas. The toString() returns a string with all fields separated by commas.

## Testing
#Backend
The project includes two test classes that run tests on all the backend classes with proper and inproper input. For testing on PostsBank, run the main method of the Test.java and for every other class, run Test2.java. These classes will create all the constructors and run every method of every class.

#Frontend
In testing the frontend, first login with an invalid account, then login with a valid one. Create a valid account and then try editing the account info. Create a post, then create a comment. Edit the post. Close the client and reopen to see if the previous posts and comments are still there. Delete the post. Try clicking on the account name to view its posts. Finally, test the import and export feature by tlicking on import and export with the filenames of your choice.
