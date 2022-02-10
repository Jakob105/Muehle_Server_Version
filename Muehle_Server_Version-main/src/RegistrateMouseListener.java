import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.sql.SQLException;

public class RegistrateMouseListener implements MouseListener {

    private LogIn_SignIn_Screen logIn_signIn_screen;

    public RegistrateMouseListener(LogIn_SignIn_Screen logIn_signIn_screen) {
        this.logIn_signIn_screen = logIn_signIn_screen;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        String query2 = "SELECT Username FROM `registration_table` WHERE Username='" + logIn_signIn_screen.getUsernameTextField().getText() + "'";
        try {
            String itemNo = logIn_signIn_screen.viewValue(logIn_signIn_screen.getConnection(), query2);
            System.out.println(itemNo);


            if (logIn_signIn_screen.GetLoginButton().isVisible() == true) {
                logIn_signIn_screen.GetLoginButton().setVisible(false);
                logIn_signIn_screen.getPasswordField2().setVisible(true);
                logIn_signIn_screen.getPasswordLabel2().setVisible(true);
                logIn_signIn_screen.NotYetRegistrated().setVisible(false);
                logIn_signIn_screen.repaint();
            } else {
                if (itemNo ==null) {

                    if ((logIn_signIn_screen.getPasswordField1input().equals(logIn_signIn_screen.getPasswordField2input()) == true) && (logIn_signIn_screen.getPasswordField1input().isEmpty() == false)  &&(logIn_signIn_screen.usernameinput().isEmpty()==false)) {


                        logIn_signIn_screen.GetLoginButton().setVisible(true);
                        logIn_signIn_screen.getPasswordField2().setVisible(false);
                        logIn_signIn_screen.getPasswordLabel2().setVisible(false);
                        logIn_signIn_screen.NotYetRegistrated().setVisible(true);

                        logIn_signIn_screen.repaint();

                        try {
                            System.out.println(logIn_signIn_screen.usernameinput());
                            String sql = "INSERT INTO registration_table VALUES('" + logIn_signIn_screen.getUsernameTextField().getText() + "','" + logIn_signIn_screen.getPasswordField1input() + "'," + 0 + ")";
                            logIn_signIn_screen.getStatement().executeUpdate(sql);
                            logIn_signIn_screen.getPasswordField2().setText(null);
                            logIn_signIn_screen.getPasswordField1().setText(null);
                            logIn_signIn_screen.getUsernameTextField().setText(null);
                            logIn_signIn_screen.repaint();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        logIn_signIn_screen.getPasswordNotTheSame().setVisible(true);
                        logIn_signIn_screen.getPasswordNotTheSame().setText("password not the same");
                        logIn_signIn_screen.repaint();
                    }
                } else {
                    logIn_signIn_screen.getPasswordNotTheSame().setVisible(true);
                    logIn_signIn_screen.getPasswordNotTheSame().setText("username already given");
                    logIn_signIn_screen.repaint();
                }
            }

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }}

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
