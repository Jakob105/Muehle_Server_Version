import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

public class LogIn_SignIn_Screen extends JFrame{

    private String playerName;
    private String password;
    private  String username;
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
    private JLabel passwordNotTheSame;

    //JTextFields
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;


    //JButtons
    private JButton loginButton;
    private JButton logoutButton;
    private JButton registrateButton;
    private JButton backButton;

    //MouseListeners
    private LoginMouseListener loginMouseListener;
    private RegistrateMouseListener registrateMouseListener;
    private GOBackMouseListener goBackMouseListener;
    private LogoutMouseListener logoutMouseListener;

    public LogIn_SignIn_Screen(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                e.getWindow().dispose();

            }
        });

    }




    public LogIn_SignIn_Screen(ArrayList<ClientHandler>activePlayers) throws SQLException {

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/database_registration2","root","mypassword123");
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
        passwordLabel2.setVisible(false);
        notYetSignedIn = new JLabel("Not yet registrated?");
        notYetSignedIn.setBounds(80,220,200,30);
        nameOrPasswordNull = new JLabel("UserName or password is empty.");
        nameOrPasswordNull.setBounds(250,40,400,30);
        nameOrPasswordNull.setForeground(Color.red);
        nameOrPasswordNull.setVisible(false);


        passwordNotTheSame = new JLabel("passwords don't match.");
        passwordNotTheSame.setBounds(250,40,400,30);
        passwordNotTheSame.setForeground(Color.red);
        passwordNotTheSame.setVisible(false);


        //TextFields
        usernameTextField = new JTextField();
        usernameTextField.setBounds(250,70,200,30);
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(250,120,200,30);
        passwordField2 = new JPasswordField();
        passwordField2.setBounds(250,170,200,30);
        passwordField2.setVisible(false);

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

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(250,400,100,30);
        logoutMouseListener = new LogoutMouseListener(this);
        logoutButton.addMouseListener(logoutMouseListener);

        this.add(usernameLabel);
        this.add(passwordLabel1);
        this.add(usernameTextField);
        this.add(passwordField1);
        this.add(passwordField2);
        this.add(passwordLabel2);
        this.add(notYetSignedIn);
        this.add(loginButton);
        this.add(registrateButton);
        this.add(logoutButton);
        this.add(backButton);
        this.add(passwordNotTheSame);
        this.add(nameOrPasswordNull);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    }

    public Connection getConnection() { return connection; }
    public Statement getStatement() {
        return statement;
    }

    public String usernameinput() { return usernameTextField.getText(); }
    public String usernameinputstrg() { return String.valueOf(usernameTextField.getText()); }

    public static ArrayList<ClientHandler> getActivePlayers() {
        return activePlayers;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JButton GetLoginButton() {
        return loginButton;
    }

    public JButton logoutButton() {
        return logoutButton;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public String getPasswordField1input() {
        return String.valueOf(passwordField1.getPassword());
    }

    public JLabel getPasswordLabel2() {
        return  passwordLabel2;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public String getPasswordField2input() { return String.valueOf(passwordField2.getPassword()); }

    public JLabel NotYetRegistrated() { return  notYetSignedIn; }

    public JLabel getNameOrPasswordNull() {
        return nameOrPasswordNull;
    }

    public JLabel getPasswordNotTheSame() { return passwordNotTheSame; }
    public String viewValue(Connection con, String command) throws SQLException
    {
        String value = null;
        Statement stmt = null;

        try
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(command);

            while (rs.next())
                value = value = rs.getString(1);
        }

        catch (SQLException e )
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt !=
                    null) { stmt.close(); }
        }

        return value;

    }

}
