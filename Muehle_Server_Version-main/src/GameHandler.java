import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameHandler implements Runnable {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private boolean playerColour;
    private Frame frame;
    private String playerName;
    private String opponentName;
    private GameHandler opponent;

    public String getPlayerName() {return playerName;}
    public String getOpponentName() {return opponentName;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}
    public void setOpponent(GameHandler opponent) {
        this.opponent = opponent;
    }
    public Frame getFrame() {
        return frame;
    }
    public void setPlayerColour(boolean playerColour) {
        this.playerColour = playerColour;
    }
    public boolean isPlayerColour() {return playerColour;}

    public GameHandler(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);
    }

    @Override
    public void run() {
        frame = new Frame(playerName);
    }
}
