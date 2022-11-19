import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Email extends JFrame {
    Email(Menu menu, Client client) {
        super("Email");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        //frame logo
        ImageIcon logo = new ImageIcon("img/logo.png");
        setIconImage(logo.getImage());

        //container panel
        JPanel container = new JPanel(null);
        container.setBounds(0, 0, 800, 600);
        container.setBackground(new Color(130, 130, 130));
        add(container);

        //handling frame close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menu.setVisible(true);
            }
        });

        //---------------------------------------------------header panel-----------------------------------------------
        JPanel header = new JPanel(null);
        header.setBounds(0, 0, 800, 100);
        JLabel headerLabelLeft = new JLabel(new ImageIcon("img/headerEmail.png"));
        headerLabelLeft.setBounds(270, 30, 50, 50);
        JLabel headerLabelCenter = new JLabel(new ImageIcon("img/Email.png"));
        headerLabelCenter.setBounds(350, 0, 100, 100);
        JLabel headerLabelRight = new JLabel(new ImageIcon("img/headerEmail.png"));
        headerLabelRight.setBounds(480, 30, 50, 50);
        header.add(headerLabelLeft);
        header.add(headerLabelCenter);
        header.add(headerLabelRight);
        header.setBackground(new Color(40, 40, 40));
        container.add(header);

        //--------------------------------------------------new mail panel----------------------------------------------
        JPanel newMail = new JPanel(null);
        newMail.setBackground(new Color(230, 230, 230));
        newMail.setBounds(300, 100, 500, 461);
        //receivers text field
        JTextField receiversTextField = new JTextField();
        receiversTextField.setBounds(150, 26, 200, 20);
        receiversTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        JLabel receiversLabel = new JLabel("To: ");
        receiversLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        receiversLabel.setBounds(55, 30, 100, 17);
        newMail.add(receiversLabel);
        newMail.add(receiversTextField);

        //subject text field
        JTextField subjectTextField = new JTextField();
        subjectTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        subjectTextField.setBounds(150, 60, 200, 20);
        JLabel subjectLabel = new JLabel("Subject: ");
        subjectLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        subjectLabel.setBounds(50, 60, 100, 22);
        newMail.add(subjectTextField);
        newMail.add(subjectLabel);

        //text area
        JTextArea textArea = new JTextArea();
        textArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textArea.setBounds(50, 110, 390, 300);
        textArea.setFont(new Font("Calibri", Font.PLAIN, 14));
        newMail.add(textArea);

        //date
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        //send button
        JLabel send = new JLabel(new ImageIcon("img/send.png"));
        send.setBounds(370, 25, 60, 60);
        send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean sent = false;
                //check if any of fields is empty
                if (receiversTextField.getText().isEmpty() || subjectTextField.getText().isEmpty() || textArea.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String date = LocalDateTime.now().format(dateTimeFormatter);
                        client.out.writeUTF("email: " + client.getUsername() + "/" + receiversTextField.getText() + "/" + subjectTextField.getText() + "/" + textArea.getText() + "/" + date);
                        sent = client.in.readBoolean();
                    } catch (IOException ioException) {
                        ioException.getStackTrace();
                    }
                    if (sent) {
                        JOptionPane.showMessageDialog(null, "Mail has been sent successfully!");
                    }else {
                        JOptionPane.showMessageDialog(null, "You entered invalid username!", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        newMail.add(send);
        newMail.setVisible(false);
        container.add(newMail);


        //--------------------------------------------------setting panel-----------------------------------------------
        JPanel settingPanel = new JPanel(null);
        settingPanel.setBackground(new Color(90, 90, 90));
        settingPanel.setBounds(300, 100, 500, 461);
        JLabel editLabel = new JLabel(new ImageIcon("img/edit.png"));
        editLabel.setBounds(220, 40, 60, 60);
        settingPanel.add(editLabel);

        //first name text field
        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        JLabel firstNameLabel = new JLabel("First name:");
        firstNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        firstNameLabel.setBounds(95, 128, 70, 15);
        firstNameLabel.setForeground(Color.WHITE);
        settingPanel.add(firstNameLabel);
        firstNameTextField.setBounds(180, 120, 200, 22);
        settingPanel.add(firstNameTextField);

        //last name text field
        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(95, 173, 70, 15);
        settingPanel.add(lastNameLabel);
        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        lastNameTextField.setBounds(180, 165, 200, 22);
        settingPanel.add(lastNameTextField);

        //username text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        usernameLabel.setBounds(95, 218, 70, 15);
        usernameLabel.setForeground(Color.WHITE);
        settingPanel.add(usernameLabel);
        JTextField usernameTextField = new JTextField();
        usernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        usernameTextField.setBounds(180, 210, 200, 22);
        settingPanel.add(usernameTextField);

        //password text field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(95, 263, 70, 15);
        settingPanel.add(passLabel);
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordTextField.setBounds(180, 255, 200, 22);
        settingPanel.add(passwordTextField);

        //submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setFocusPainted(false);
        submitButton.setBounds(218, 315, 80, 25);
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean edited = false;
                //check if any of fields is empty
                if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() || String.valueOf(passwordTextField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        //send data to server
                        client.out.writeUTF("edit: " + client.getUsername() + "/" + firstNameTextField.getText() + "/" + lastNameTextField.getText() + "/" + usernameTextField.getText() + "/" + String.valueOf(passwordTextField.getPassword()));

                        //server answer
                        if (client.in.readBoolean()) {
                            edited = true;
                            JOptionPane.showMessageDialog(null, "Profile successfully edited!", "", JOptionPane.INFORMATION_MESSAGE);
                            client.setUsername(usernameTextField.getText());
                        }
                    } catch (IOException ioException) {
                        ioException.getStackTrace();
                    }
                    //unavailable user name for sign in
                    if (!edited) {
                        JOptionPane.showMessageDialog(null, "Username is not available!", "", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        settingPanel.add(submitButton);
        settingPanel.setVisible(false);
        container.add(settingPanel);

        //--------------------------------------------------mail preview panel------------------------------------------
        JPanel mailPreview = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(10, 100, 470, 100);
                g.drawLine(10, 105, 470, 105);
            }
        };
        //subject
        JLabel subjectPreview = new JLabel();
        subjectPreview.setBounds(50, 20, 60, 20);
        subjectPreview.setFont(new Font("Calibri", Font.BOLD, 18));
        //sender
        JLabel senderPreview = new JLabel();
        senderPreview.setBounds(55, 50, 200, 15);
        senderPreview.setFont(new Font("Calibri", Font.PLAIN, 14));
        //receiver
        JLabel receiverPreview = new JLabel();
        receiverPreview.setBounds(55, 50, 200, 15);
        receiverPreview.setFont(new Font("Calibri", Font.PLAIN, 14));
        //date
        JLabel datePreview = new JLabel();
        datePreview.setBounds(55, 70, 200, 15);
        datePreview.setFont(new Font("Calibri", Font.PLAIN, 14));
        //text
        JTextArea textPreview = new JTextArea();
        textPreview.setEditable(false);
        textPreview.setBounds(50, 130, 390, 300);
        textPreview.setFont(new Font("Calibri", Font.PLAIN, 14));
        mailPreview.add(subjectPreview);
        mailPreview.add(senderPreview);
        mailPreview.add(receiverPreview);
        mailPreview.add(datePreview);
        mailPreview.add(textPreview);
        mailPreview.setBackground(new Color(230, 230, 230));
        mailPreview.setBounds(300, 100, 500, 461);
        mailPreview.setVisible(false);
        container.add(mailPreview);

        //------------------------------------------------mail list container------------------------------------------
        JPanel mailList = new JPanel(null);
        mailList.setBounds(50, 100, 250, 461);
        mailList.setBackground(new Color(70, 70, 70));
        container.add(mailList);

        //--------------------------------------------inbox mail list panel----------------------------------------------
        JPanel inboxMailList = new JPanel(null);
        inboxMailList.setBackground(new Color(70, 70, 70));
        inboxMailList.setBounds(0, 0, 250, 461);
        inboxMailList.setVisible(false);
        mailList.add(inboxMailList);

        //-------------------------------------------------sent mail list panel-----------------------------------------
        JPanel sentMailList = new JPanel(null);
        sentMailList.setBackground(new Color(70, 70, 70));
        sentMailList.setBounds(0, 0, 250, 461);
        sentMailList.setVisible(false);
        mailList.add(sentMailList);

        //---------------------sidebar which contains compose, inbox, sent, setting and sign out buttons----------------
        JPanel sidebar = new JPanel(null);
        sidebar.setBackground(new Color(60, 60, 60));
        sidebar.setBounds(0, 100, 50, 461);
        container.add(sidebar);

        //----------------------------------------------compose button---------------------------------------------------
        JPanel compose = new JPanel(null);
        compose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        compose.setBackground(new Color(60, 60, 60));
        compose.setBounds(0, 30, 50, 50);
        compose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                compose.setBackground(new Color(40, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                compose.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                newMail.setVisible(true);
                mailPreview.setVisible(false);
                settingPanel.setVisible(false);
            }
        });
        JLabel composeLabel = new JLabel(new ImageIcon("img/compose.png"));
        composeLabel.setBounds(15, 15, 20, 20);
        compose.add(composeLabel);
        sidebar.add(compose);

        //-----------------------------------------------inbox button---------------------------------------------------
        JPanel inbox = new JPanel(null);
        inbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inbox.setBackground(new Color(60, 60, 60));
        inbox.setBounds(0, 120, 50, 50);
        inbox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                inbox.setBackground(new Color(40, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                inbox.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                sentMailList.setVisible(false);
                inboxMailList.removeAll();
                //getting inbox mail data from server to show on inbox mail list
                String[] temp;
                //widget panel for each mail
                try {
                    client.out.writeUTF("inbox: " + client.getUsername());
                    int numberOfMails = client.in.readInt();
                    for (int i = 0; i < numberOfMails; i++) {
                        temp = client.in.readUTF().split("/");
                        //creating mail object
                        Mail m = new Mail(client.getUsername(), temp[0], temp[1], temp[2], temp[3]);
                        //panel for each mail and its data
                        JPanel inboxMailPanel = new JPanel(null);
                        JLabel inboxMailSubLabel = new JLabel(m.getSubject());
                        inboxMailSubLabel.setFont(new Font("Calibri", Font.BOLD, 18));
                        inboxMailSubLabel.setForeground(Color.WHITE);
                        JLabel inboxMailSenderLabel = new JLabel("From: " + m.getSender());
                        inboxMailSenderLabel.setForeground(Color.WHITE);
                        inboxMailSenderLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
                        JLabel inboxMailDateLabel = new JLabel(m.getDate());
                        inboxMailDateLabel.setForeground(Color.WHITE);
                        inboxMailDateLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
                        JLabel inboxMailTextLabel = new JLabel(m.getText());
                        inboxMailTextLabel.setForeground(Color.WHITE);
                        inboxMailTextLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
                        inboxMailPanel.add(new JPanel(null));
                        inboxMailSubLabel.setBounds(15, 7, 100, 17);
                        inboxMailSenderLabel.setBounds(15, 30, 100, 15);
                        inboxMailDateLabel.setBounds(130, 30, 150, 15);
                        inboxMailTextLabel.setBounds(15, 45, 50, 15);
                        inboxMailPanel.add(inboxMailSubLabel);
                        inboxMailPanel.add(inboxMailSenderLabel);
                        inboxMailPanel.add(inboxMailDateLabel);
                        inboxMailPanel.add(inboxMailTextLabel);
                        inboxMailPanel.setBackground(new Color(170, 170, 170, 20));
                        inboxMailPanel.setBounds(0, i * 62, 250, 60);
                        inboxMailList.add(inboxMailPanel);
                        //viewing mail data
                        inboxMailPanel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                subjectPreview.setText(m.getSubject());
                                senderPreview.setText("From: " + m.getSender());
                                senderPreview.setVisible(true);
                                receiverPreview.setVisible(false);
                                datePreview.setText(m.getDate());
                                textPreview.setText(m.getText());
                                mailPreview.setVisible(true);
                                settingPanel.setVisible(false);
                                newMail.setVisible(false);
                            }
                        });
                    }
                } catch (IOException ioException) {
                    ioException.getStackTrace();
                }
                inboxMailList.setVisible(true);
            }
        });
        JLabel inboxLabel = new JLabel(new ImageIcon("img/inbox.png"));
        inboxLabel.setBounds(15, 15, 20, 20);
        inbox.add(inboxLabel);
        sidebar.add(inbox);

        //----------------------------------------------------sent button-----------------------------------------------
        JPanel sent = new JPanel(null);
        sent.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sent.setBackground(new Color(60, 60, 60));
        sent.setBounds(0, 210, 50, 50);
        sent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sent.setBackground(new Color(40, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sent.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                inboxMailList.setVisible(false);
                sentMailList.setVisible(false);
                sentMailList.removeAll();
                //getting sent mail data fromm server to show on sent mail list
                String[] temp;
                //widget panel for each mail
                try {
                    client.out.writeUTF("sent: " + client.getUsername());
                    int numberOfMails = client.in.readInt();
                    for (int i = 0; i < numberOfMails; i++) {
                        temp = client.in.readUTF().split("/");
                        //creating mail object
                        Mail m = new Mail(client.getUsername(), temp[0], temp[1], temp[2], temp[3]);
                        //panel for each mail and its data
                        JPanel sentMailPanel = new JPanel(null);
                        JLabel sentMailSubLabel = new JLabel(m.getSubject());
                        sentMailSubLabel.setFont(new Font("Calibri", Font.BOLD, 18));
                        sentMailSubLabel.setForeground(Color.WHITE);
                        JLabel sentMailReceiverLabel = new JLabel("To: " + m.getReceivers());
                        sentMailReceiverLabel.setForeground(Color.WHITE);
                        sentMailReceiverLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
                        JLabel sentMailDateLabel = new JLabel(m.getDate());
                        sentMailDateLabel.setForeground(Color.WHITE);
                        sentMailDateLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
                        JLabel sentMailTextLabel = new JLabel(m.getText());
                        sentMailTextLabel.setForeground(Color.WHITE);
                        sentMailTextLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
                        sentMailPanel.add(new JPanel(null));
                        sentMailSubLabel.setBounds(15, 7, 100, 17);
                        sentMailReceiverLabel.setBounds(15, 30, 100, 15);
                        sentMailDateLabel.setBounds(130, 30, 150, 15);
                        sentMailTextLabel.setBounds(15, 45, 50, 15);
                        sentMailPanel.add(sentMailSubLabel);
                        sentMailPanel.add(sentMailReceiverLabel);
                        sentMailPanel.add(sentMailDateLabel);
                        sentMailPanel.add(sentMailTextLabel);
                        sentMailPanel.setBackground(new Color(170, 170, 170, 20));
                        sentMailPanel.setBounds(0, i * 62, 250, 60);
                        sentMailList.add(sentMailPanel);
                        sentMailPanel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                subjectPreview.setText(m.getSubject());
                                senderPreview.setVisible(false);
                                receiverPreview.setText("To: " + m.getReceivers());
                                receiverPreview.setVisible(true);
                                datePreview.setText(m.getDate());
                                textPreview.setText(m.getText());
                                mailPreview.setVisible(true);
                                settingPanel.setVisible(false);
                                newMail.setVisible(false);
                            }
                        });
                    }
                } catch (IOException ioException) {
                    ioException.getStackTrace();
                }
                sentMailList.setVisible(true);
            }
        });
        JLabel sentLabel = new JLabel(new ImageIcon("img/sent.png"));
        sentLabel.setBounds(15, 15, 20, 20);
        sent.add(sentLabel);
        sidebar.add(sent);

        //--------------------------------------------setting button---------------------------------------------------
        JPanel setting = new JPanel(null);
        setting.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setting.setBackground(new Color(60, 60, 60));
        setting.setBounds(0, 300, 50, 50);
        setting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setting.setBackground(new Color(40, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setting.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingPanel.setVisible(true);
                newMail.setVisible(false);
                mailPreview.setVisible(false);
                //filling setting panel text fields with user data
                try {
                    client.out.writeUTF("getUserData: " + client.getUsername());
                    String[] tmp = client.in.readUTF().split("/");
                    firstNameTextField.setText(tmp[0]);
                    lastNameTextField.setText(tmp[1]);
                    usernameTextField.setText(tmp[2]);
                    passwordTextField.setText(tmp[3]);
                } catch (IOException ioException) {
                    ioException.getStackTrace();
                }
            }
        });
        JLabel settingLabel = new JLabel(new ImageIcon("img/setting.png"));
        settingLabel.setBounds(15, 15, 20, 20);
        setting.add(settingLabel);
        sidebar.add(setting);

        //--------------------------------------------------sign out button---------------------------------------------
        JPanel signOut = new JPanel(null);
        signOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signOut.setBackground(new Color(60, 60, 60));
        signOut.setBounds(0, 390, 50, 50);
        signOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signOut.setBackground(new Color(40, 40, 40));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signOut.setBackground(new Color(60, 60, 60));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "", JOptionPane.YES_NO_OPTION);
                if (ans == 0) {
                    dispose();
                }
            }
        });
        JLabel signOutLabel = new JLabel(new ImageIcon("img/signout.png"));
        signOutLabel.setBounds(15, 15, 20, 20);
        signOut.add(signOutLabel);
        sidebar.add(signOut);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}