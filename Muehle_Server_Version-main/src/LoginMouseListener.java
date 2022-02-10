import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginMouseListener implements MouseListener {

    private LogIn_SignIn_Screen logIn_signIn_screen;
    private ResultSet resultSet;

    private String username;


    LoginMouseListener(LogIn_SignIn_Screen logIn_signIn_screen) {
        this.logIn_signIn_screen = logIn_signIn_screen;
    }



    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if(logIn_signIn_screen.getUsernameTextField().getText().isEmpty() ||
            logIn_signIn_screen.getPasswordField1().getPassword().length == 0) {
            logIn_signIn_screen.add(logIn_signIn_screen.getNameOrPasswordNull());
            logIn_signIn_screen.repaint();
        }else {
            try {
                String query = "SELECT * FROM `registration_table` WHERE `Username` =? AND `passwort` =?";
                String query2 = "SELECT signed_in FROM `registration_table` WHERE Username='" + logIn_signIn_screen.getUsernameTextField().getText() + "' AND passwort ='" + logIn_signIn_screen.getPasswordField1input() + "'";
                String itemNo = logIn_signIn_screen.viewValue(logIn_signIn_screen.getConnection(), query2);
                PreparedStatement preparedStatement2 = logIn_signIn_screen.getConnection().prepareStatement(query);
                preparedStatement2.setString(1, logIn_signIn_screen.getUsernameTextField().getText());
                preparedStatement2.setString(2, logIn_signIn_screen.getPasswordField1input());
                ResultSet rs = preparedStatement2.executeQuery();
                int numberInt = Integer.parseInt(itemNo);
                if (numberInt == 1) {
                    logIn_signIn_screen.add(logIn_signIn_screen.getNameOrPasswordNull());
                    logIn_signIn_screen.repaint();
                } else {


                    if (rs.next()) {
                        logIn_signIn_screen.logoutButton().setVisible(true);
                        logIn_signIn_screen.GetLoginButton().setVisible(false);
                        logIn_signIn_screen.getPasswordField1().setEditable(false);
                        logIn_signIn_screen.getUsernameTextField().setEditable(false);
                        logIn_signIn_screen.repaint();
                        String sql = "update registration_table set signed_in = " + 1 + " WHERE Username='" + logIn_signIn_screen.getUsernameTextField().getText() + "' AND passwort='" + logIn_signIn_screen.getPasswordField1input() + "'";
                        logIn_signIn_screen.getStatement().executeUpdate(sql);
                        PreparedStatement preparedStatement = logIn_signIn_screen.getConnection().prepareStatement(sql);
                        preparedStatement.executeUpdate();
                    } else {
                        logIn_signIn_screen.add(logIn_signIn_screen.getNameOrPasswordNull());
                        logIn_signIn_screen.repaint();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    public String getUsername() {
        return username;
    }
}
