
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogoutMouseListener implements MouseListener {

    public LogoutMouseListener() {

    }
    private LogIn_SignIn_Screen logIn_signIn_screen;
    private LoginMouseListener  loginMouseListener;
    private ResultSet resultSet;

    LogoutMouseListener(LogIn_SignIn_Screen logIn_signIn_screen ) {
        this.logIn_signIn_screen = logIn_signIn_screen;
        this.loginMouseListener = loginMouseListener;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        try {
            String sql ="update registration_table set signed_in = "+0+" WHERE Username='"+logIn_signIn_screen.getUsernameTextField().getText()+"' AND passwort='"+logIn_signIn_screen.getPasswordField1input()+"'";
            logIn_signIn_screen.getStatement().executeUpdate(sql);
            PreparedStatement preparedStatement = logIn_signIn_screen.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.exit(0);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

}
