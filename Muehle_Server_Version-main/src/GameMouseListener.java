import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMouseListener implements MouseListener {

    private Frame frame;
    private Feld field;


    public GameMouseListener(Frame frame, Feld field) {
        this.frame = frame;
        this.field = field;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (frame.isItsYourTurn()) {
            if (!frame.isGameOver()) {
                if (frame.isaMillWasCreatedInThePreviousAction()) {
                    frame.removeStone(field);
                    if (frame.allStonesAreBlocked() || frame.lessThanThreeStonesOnBoard(frame.isPlayerColour())) {
                        frame.setGameOver(true);
                        frame.getOpponent().getFrame().setGameOver(true);

                        frame.displayWinner(frame.isPlayerColour());
                        frame.getOpponent().getFrame().displayWinner(frame.getOpponent().getFrame().isPlayerColour());
                    }
                }
                else {
                    if (frame.getAmountOfUnusedStones(frame.isPlayerColour()) > 0) {
                        frame.firstPhaseMove(field);
                    }
                    else if (frame.getAmountOfStonesOutOfGame(frame.isPlayerColour()) < 6) {
                        frame.secondPhaseMove(field);
                    }
                    else {
                        frame.thirdPhaseMove(field);
                    }
                }
            }

            if (frame.isChangePlayer()) {
                frame.setItsYourTurn(false);
                frame.getOpponent().getFrame().setItsYourTurn(true);
                frame.setChangePlayer(false);
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