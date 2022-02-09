import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectOpponentMouseListener implements MouseListener {

    private String playerName;
    private JComboBox availablePlayers;
    private GameHandler gameHandler;
    private ClientHandler clientHandler;

    public SelectOpponentMouseListener(String playerName, JComboBox availablePlayers, GameHandler gameHandler, ClientHandler clientHandler) {
        this.playerName = playerName;
        this.availablePlayers = availablePlayers;
        this.gameHandler = gameHandler;
        this.clientHandler = clientHandler;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GameHandler opponent = Server.getAvailablePlayers().get(availablePlayers.getSelectedItem());
        gameHandler.setOpponentGameHandler(opponent);
        if(opponent.getOpponentGameHandler().equals(gameHandler)){
            //game should start
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
