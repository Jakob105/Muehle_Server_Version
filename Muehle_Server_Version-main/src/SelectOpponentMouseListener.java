import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectOpponentMouseListener implements MouseListener {

    private Frame frame;

    public SelectOpponentMouseListener(Frame frame) {
        this.frame = frame;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (Server.getAvailablePlayers().size() > 0) {
            frame.cleanUp();
            if(frame.getOpponentGameHandler() != null){
                frame.getOpponentGameHandler().getFrame().cleanUp();
            }
            GameHandler opponent = Server.getAvailablePlayers().get(frame.getAvailablePlayers().getSelectedItem());
            frame.setOpponentGameHandler(opponent);
            frame.getGameHandler().setOpponentGameHandler(opponent);
            if(opponent.getOpponentGameHandler() != null &&
                    opponent.getOpponentGameHandler().equals(frame.getGameHandler())){
                frame.setPlayerColour(false);

                JOptionPane.showMessageDialog(frame, "You are player black. "+opponent.getPlayerName()+" makes the first move. Hit 'ok' to inform "+opponent.getPlayerName()+".");
                JOptionPane.showMessageDialog(opponent.getFrame(),opponent.getPlayerName()+"is ready. You can make your first move.");

                opponent.getFrame().setItsYourTurn(true);
            }
            else {
                frame.setPlayerColour(true);

                JOptionPane.showMessageDialog(frame, "Select "+opponent.getPlayerName()+" as opponent?");

            }
        }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {}
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
