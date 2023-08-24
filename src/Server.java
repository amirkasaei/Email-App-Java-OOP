import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    final static int port = 37;

    public static void main(String[] args) {
        new Server();
    }

    Server() {
        //reading data from file
        try (FileInputStream fis = new FileInputStream("users.txt"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            User.users = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        }

        System.out.println("Server started with port: " + port);

        Socket socket;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);

            while (true) {
                System.out.println("Waiting for a client ...");
                socket = server.accept();
                System.out.println("Client accepted");

                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    //thread for handling client connection
    class ClientHandler implements Runnable {

        private DataInputStream in;
        private DataOutputStream out;
        Socket socket;

        ClientHandler(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {

            // set reader and writer
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.getStackTrace();
            }
            boolean exit = false;

            //responding to client messages
            try {
                String message;
                String[] temp;
                User usr;
                Mail mail;
                do {
                    message = in.readUTF();
                    if (message.startsWith("exit")) {
                        //for closing the thread and creating user data file
                        exit = true;
                    } else if (message.startsWith("sign in: ")) {
                        //user data
                        temp = message.substring(9).split("/");

                        //sending answer to client
                        out.writeBoolean(signInCheck(temp));
                        out.flush();

                    } else if (message.startsWith("sign up: ")) {
                        //user data
                        temp = message.substring(9).split("/");

                        //sending answer to client
                        out.writeBoolean(usernameCheck(temp[2]));

                        //creating user if there is no problem
                        if (usernameCheck(temp[2])) {
                            User.users.add(new User(temp[0], temp[1], temp[2], temp[3]));
                        }
                    } else if (message.startsWith("getUserData: ")) {
                        //get user for filling the user data text fields in setting panel in email frame
                        usr = User.getUserByUsername(message.substring(13));
                        out.writeUTF(usr.getFirstName() + "/" + usr.getLastName() + "/" + usr.getUsername() + "/" + usr.getPassword());
                    } else if (message.startsWith("edit: ")) {
                        //user new data and its previous username
                        temp = message.substring(6).split("/");

                        usr = User.getUserByUsername(temp[0]);

                        //sending answer to client
                        out.writeBoolean(usernameCheck(temp[3]));

                        //editing user if there is no problem
                        if (usernameCheck(temp[3])) {
                            usr.setFirstName(temp[1]);
                            usr.setLastName(temp[2]);
                            usr.setUsername(temp[3]);
                            usr.setPassword(temp[4]);
                        }
                    } else if (message.startsWith("email: ")) {
                        //sender's and receivers' usernames and mail data
                        temp = message.substring(7).split("/");
                        usr = User.getUserByUsername(temp[0]);
                        mail = new Mail(temp[0], temp[1], temp[2], temp[3], temp[4]);
                        boolean sent = false;
                        //adding mail to receivers' inboxes
                        String[] receiverUsername = temp[1].split(",");
                        for (String u : receiverUsername) {
                            if (User.getUserByUsername(u) != null) {
                                User.getUserByUsername(u).setInbox(mail);
                                sent = true;
                            }
                        }
                        //send the result of process
                        out.writeBoolean(sent);

                        if (sent) {
                            //adding mail in senders sent box
                            usr.setSent(mail);
                        }

                    } else if (message.startsWith("inbox: ")) {
                        //getting inbox mails
                        usr = User.getUserByUsername(message.substring(7));
                        out.writeInt(usr.getInbox().size());
                        for (int i = usr.getInbox().size() - 1; i >= 0; i--) {
                            //send mail data
                            mail = usr.getInbox().get(i);
                            out.writeUTF(mail.getSender() + "/" + mail.getSubject() + "/" + mail.getText() + "/" + mail.getDate());
                        }
                    } else if (message.startsWith("sent: ")) {
                        //getting sent mails
                        usr = User.getUserByUsername(message.substring(6));
                        out.writeInt(usr.getSent().size());
                        for (int i = usr.getSent().size() - 1; i >= 0; i--) {
                            //send mail data
                            mail = usr.getSent().get(i);
                            out.writeUTF(mail.getReceivers() + "/" + mail.getSubject() + "/" + mail.getText() + "/" + mail.getDate());
                        }
                    }
                } while (!exit);
            } catch (IOException e) {
                e.getStackTrace();
            } finally {
                //write data to file
                try (FileOutputStream fos = new FileOutputStream("users.txt"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(User.users);
                } catch (FileNotFoundException e) {
                    e.getStackTrace();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }

        //check user pass for signning in
        boolean signInCheck(String[] temp) {
            for (User usr : User.users) {
                if (usr.getUsername().equals(temp[0]) && usr.getPassword().equals(temp[1])) {
                    return true;
                }
            }
            return false;
        }

        //username availability check
        boolean usernameCheck(String user) {
            for (User usr : User.users) {
                if (usr.getUsername().equals(user)) {
                    return false;
                }
            }
            return true;
        }
    }
}
