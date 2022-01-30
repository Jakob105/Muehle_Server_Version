import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMouseListener implements MouseListener {

    private LogIn_SignIn_Screen logIn_signIn_screen;
    private ResultSet resultSet;

    LoginMouseListener(LogIn_SignIn_Screen logIn_signIn_screen) {
        this.logIn_signIn_screen = logIn_signIn_screen;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        if(logIn_signIn_screen.getUsernameTextField().getText().isEmpty() ||
            logIn_signIn_screen.getPasswordField1().getPassword().length == 0) {
            logIn_signIn_screen.add(logIn_signIn_screen.getNameOrPasswordNull());
            logIn_signIn_screen.repaint();
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
