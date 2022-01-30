import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;

public class LogIn_SignIn_Screen extends JFrame{

    private String playerName;
    private String password;
    private static ArrayList<ClientHandler> clients;
    private static  ArrayList<GameHandler> games;
    private static ArrayList<ClientHandler> activePlayers;

    //Database Connection
    private Connection connection;
    private Statement statement;

    //JLabels
    private JLabel usernameLabel;
    private JLabel passwordLabel1;
    private JLabel passwordLabel2;
    private JLabel notYetSignedIn;
    //warnings
    private JLabel nameOrPasswordNull;

    //JTextFields
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;


    //JButtons
    private JButton loginButton;
    private JButton registrateButton;
    private JButton backButton;

    //MouseListeners
    private LoginMouseListener loginMouseListener;
    private RegistrateMouseListener registrateMouseListener;
    private GOBackMouseListener goBackMouseListener;




    public LogIn_SignIn_Screen(ArrayList<ClientHandler>activePlayers) throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registered_users","jakob","vnSRCiÜfSxGF=7=Kp(W_ÖjO)6NÖ~A3");
        statement = connection.createStatement();

        this.setTitle("Login");
        this.setSize(600,600);
        this.setLayout(null);

        //Labels
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(80,70,200,30);
        passwordLabel1 = new JLabel("Password");
        passwordLabel1.setBounds(80,120,200,30);
        passwordLabel2 = new JLabel("Confirm Password");
        passwordLabel2.setBounds(80,170,200,30);
        notYetSignedIn = new JLabel("Not yet registrated?");
        notYetSignedIn.setBounds(80,220,200,30);
        nameOrPasswordNull = new JLabel("UserName or password is empty.");
        nameOrPasswordNull.setBounds(250,40,400,30);
        nameOrPasswordNull.setForeground(Color.red);



        //TextFields
        usernameTextField = new JTextField();
        usernameTextField.setBounds(250,70,200,30);
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(250,120,200,30);
        passwordField2 = new JPasswordField();
        passwordField2.setBounds(250,170,200,30);

        //JButtons
        loginButton = new JButton("Login");
        loginButton.setBounds(250,170,100,30);
        loginMouseListener = new LoginMouseListener(this);
        loginButton.addMouseListener(loginMouseListener);

        registrateButton = new JButton("Register");
        registrateButton.setBounds(250,220,100,30);
        registrateMouseListener = new RegistrateMouseListener(this);
        registrateButton.addMouseListener(registrateMouseListener);

        backButton = new JButton("Back");
        backButton.setBounds(250,300,100,30);
        goBackMouseListener = new GOBackMouseListener(this);
        backButton.addMouseListener(goBackMouseListener);

        backButton = new JButton("Back");

        this.add(usernameLabel);
        this.add(passwordLabel1);
        this.add(usernameTextField);
        this.add(passwordField1);
        //this.add(passwordField2);
        //this.add(passwordLabel2);
        this.add(notYetSignedIn);
        this.add(loginButton);
        this.add(registrateButton);
        this.add(backButton);
        this.setVisible(true);

    }

    public Statement getStatement() {
        return statement;
    }

    public static ArrayList<ClientHandler> getActivePlayers() {
        return activePlayers;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public JLabel getNameOrPasswordNull() {
        return nameOrPasswordNull;
    }
}
