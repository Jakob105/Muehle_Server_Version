/*
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogoutMouseListener implements MouseListener {
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
            String sql = "UPDATE login_data " + "SET signed_in = "+0+" WHERE signed_in="+1+"";
            logIn_signIn_screen.getStatement().executeUpdate(sql);
            //LoginMouseListener.getUsername();
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
/*
 */