import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Menu extends JFrame implements Runnable{

    //  exit
    private void exit() {
        System.exit(0);
    }

    //    sign in
    void signIn(Menu menu, Client client) {
        JFrame signIn = new JFrame("Sign in");
        signIn.setSize(450, 350);
        signIn.getContentPane().setBackground(new Color(40, 40, 40));
        signIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signIn.setLayout(null);
        signIn.setResizable(false);
        signIn.setLocationRelativeTo(null);

        //frame logo
        ImageIcon logo = new ImageIcon("img/logo.png");
        signIn.setIconImage(logo.getImage());

        //logo
        JLabel Logo = new JLabel(new ImageIcon("img/signin.png"));
        Logo.setBounds(170, 20, 100, 100);
        signIn.add(Logo);

        //username text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        usernameLabel.setBounds(95, 153, 70, 15);
        usernameLabel.setForeground(Color.WHITE);
        signIn.add(usernameLabel);
        JTextField usernameTextField = new JTextField();
        usernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        usernameTextField.setBounds(180, 145, 150, 22);
        signIn.add(usernameTextField);

        //password text field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(95, 193, 70, 15);
        signIn.add(passLabel);
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordTextField.setBounds(180, 185, 150, 22);
        signIn.add(passwordTextField);

        //sign in button
        JButton signInButton = new JButton("Sign in");
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInButton.setFocusPainted(false);
        signInButton.setBounds(185, 235, 80, 25);
        signInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean signedIn = false;
                //check if any of fields is empty
                if (usernameTextField.getText().isEmpty() || String.valueOf(passwordTextField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields!", "", JOptionPane.ERROR_MESSAGE);
                }else {
                    try {
                        //sending data to server
                        client.out.writeUTF("sign in: " + usernameTextField.getText() + "/" + String.valueOf(passwordTextField.getPassword()));

                        //server answer
                        if (client.in.readBoolean()) {
                            signedIn = true;
                            client.setUsername(usernameTextField.getText());
                            menu.setVisible(false);
                            signIn.dispose();
                            new Email(menu, client);
                        }
                    } catch (IOException ioException) {
                        ioException.getStackTrace();
                    }
                    //not signed in because of wrong username or password
                    if (!signedIn) {
                        JOptionPane.showMessageDialog(null, "Username or password is wrong!", "", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        signIn.add(signInButton);

        signIn.setVisible(true);
    }

    //    sign up
    void signUp(Menu menu, Client client) {
        JFrame signUp = new JFrame("Sign Up");
        signUp.setSize(450, 450);
        signUp.getContentPane().setBackground(new Color(40, 40, 40));
        signUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUp.setLayout(null);
        signUp.setResizable(false);
        signUp.setLocationRelativeTo(null);

        //frame logo
        ImageIcon logo = new ImageIcon("img/logo.png");
        signUp.setIconImage(logo.getImage());

        //logo
        JLabel Logo = new JLabel(new ImageIcon("img/signup.png"));
        Logo.setBounds(170, 20, 100, 100);
        signUp.add(Logo);

        //first name text field
        JLabel firstNameLabel = new JLabel("First name:");
        firstNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        firstNameLabel.setBounds(95, 163, 70, 15);
        firstNameLabel.setForeground(Color.WHITE);
        signUp.add(firstNameLabel);
        JTextField firstNameTextField = new JTextField();
        firstNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        firstNameTextField.setBounds(180, 155, 150, 22);
        signUp.add(firstNameTextField);

        //last name text field
        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(95, 203, 70, 15);
        signUp.add(lastNameLabel);
        JTextField lastNameTextField = new JTextField();
        lastNameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        lastNameTextField.setBounds(180, 195, 150, 22);
        signUp.add(lastNameTextField);

        //username text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        usernameLabel.setBounds(95, 243, 70, 15);
        usernameLabel.setForeground(Color.WHITE);
        signUp.add(usernameLabel);
        JTextField usernameTextField = new JTextField();
        usernameTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        usernameTextField.setBounds(180, 235, 150, 22);
        signUp.add(usernameTextField);

        //password text field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(95, 283, 70, 15);
        signUp.add(passLabel);
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordTextField.setBounds(180, 275, 150, 22);
        signUp.add(passwordTextField);

        //sign up button
        JButton signUpButton = new JButton("Sign up");
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.setFocusPainted(false);
        signUpButton.setBounds(185, 325, 80, 25);
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean signedUp = false;
                //check if any of fields is empty
                if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() || String.valueOf(passwordTextField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all of the fields!", "", JOptionPane.ERROR_MESSAGE);
                }else {

                    try {
                        //send data to server
                        client.out.writeUTF("sign up: " + firstNameTextField.getText() + "/" + lastNameTextField.getText() + "/" + usernameTextField.getText() + "/" + String.valueOf(passwordTextField.getPassword()));
                        //server answer
                        if (client.in.readBoolean()) {
                            signedUp = true;
                            client.setUsername(usernameTextField.getText());
                            signUp.dispose();
                            menu.setVisible(false);
                            new Email(menu, client);
                        }
                    } catch (IOException ioException) {
                        ioException.getStackTrace();
                    }
                    //unavailable user name for sign in
                    if (!signedUp) {
                        JOptionPane.showMessageDialog(null, "Username is not available!", "", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        signUp.add(signUpButton);

        signUp.setVisible(true);
    }

    public Menu() {
        super("Menu");
    }

    public static void main(String[] args) {
        //changing look and feel into windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.getStackTrace();
        }
       new Thread(new Menu()).start();
    }

    @Override
    public void run() {
        Client  client = new Client();

        setSize(450, 350);
        getContentPane().setBackground(new Color(40, 40, 40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //frame logo
        ImageIcon logo = new ImageIcon("img/logo.png");
        setIconImage(logo.getImage());

        //a reference for inside of action listener block
        Menu menu = this;

        //closing socket and ending its thread
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    client.out.writeUTF("exit");
                    client.socket.close();
                } catch (IOException ioException) {
                    ioException.getStackTrace();
                }
            }
        });

        //logo
        JLabel Logo = new JLabel(new ImageIcon("img/Email.png"));
        Logo.setBounds(170, 30, 100, 75);
        add(Logo);

        //creating menu buttons and their action listener and adding them to the frame
        JButton[] menuButton = new JButton[3];
        for (byte i = 0; i < 3; i++) {
            menuButton[i] = new JButton();
            menuButton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (i == 0) {
                menuButton[i].setText("Sign in");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        signIn(menu, client);
                    }
                });
            } else if (i == 1) {
                menuButton[i].setText("Sign up");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        signUp(menu, client);
                    }
                });
            } else {
                menuButton[i].setText("Exit");
                menuButton[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == 0)
                            exit();
                    }
                });
            }
            menuButton[i].setFocusPainted(false);
            menuButton[i].setBounds(175, 150 + i * 40, 90, 30);
            add(menuButton[i]);
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
