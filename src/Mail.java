import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Mail implements Serializable {
    private String sender;
    private String receivers;
    private String subject;
    private String text;
    private String date;

    public String getSender() {
        return sender;
    }

    public String getReceivers() {
        return receivers;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    Mail (String sender, String receivers, String subject, String text, String date) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.text = text;
        this.date = date;
    }
}
