import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    static ArrayList<User> users = new ArrayList<>();
    private ArrayList<Mail> inbox = new ArrayList<>();
    private ArrayList<Mail> sent = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByUsername(String username) {
        for (User usr: users) {
            if (usr.getUsername().equals(username)) {
                return usr;
            }
        }
        return null;
    }

    public ArrayList<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(Mail mail) {
        this.inbox.add(mail);
    }

    public ArrayList<Mail> getSent() {
        return sent;
    }

    public void setSent(Mail mail) {
        this.sent.add(mail);
    }

    public User(String firstName, String lastName, String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
    }

}
