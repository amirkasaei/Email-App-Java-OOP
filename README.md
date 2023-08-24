# Email App with Java

<div align="center"><img src="https://github.com/amirkasaei/Email-App-Java-OOP/blob/main/img/app.png?raw=true"width="60%"/></div> </br >

#### In this project, we want to implement a simple messaging program similar to Gmail through multi-threaded programming and network programming.
## Description

### Project has two main parts:
### 1. Server:

- The task of the server is to communicate between the clients and send their messages and look at all the information of the users. After running, the server must read and load the available data of the users (such as users, their messages, etc.).
- After connecting to the server, each client receives data related to the mentioned user based on their login (username and password) and this way the main program works. Any changes in the user are sent to the server and in the object. It is applied. For example, if user A sends a letter to user B, the letter is sent to the server and stored in A's sent mails and B's received mails.
	- **Letter:**
		- You can implement the letter according to your taste. For example, it should be only a text (String) or implement it for that class or... but the implementation should be 				such that the sender and receiver can be contacted through it. And specified the time and date of sending.
	- **User:**
		- Implement a class for the user. The minimum expected features include username, password, first name and last name.
- Note: To save user information on the file, the user object itself is directly copied to the file.

  
### 2. Email App:
- User can register or login with correct username and password.
- After logging in, he enters his profile. The profile includes options for sending messages, received mails, sent mails, change profile and log out.
	- **Send message:** The user can enter a number of usernames and send them a message.
 	- **Received mails:** the user sees a list of received mails and by selecting it, he can see its content (sender, recipient, text, time and date of sending).
	- **Sent letters:** the user sees a list of sent letters and by selecting it, he can see its content (sender, recipient, text, time and date of sending).
 	- **Profile change:** The user can edit his profile.
	- **Exit:** the user exits his profile and the program returns to the initial page (registration and login).
 


### GUI:
- the Graphic UI is implemented using **Java Swing**
  
## How to Run:
- first run server and then run menu file.
- first sign up and enter the app
- add "," between usernames you wanna send an email to

