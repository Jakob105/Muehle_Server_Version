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
    private GameHandler opponent;

    public String getPlayerName() {return playerName;}
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
        frame = new Frame();
    }

    public GameHandler(Socket socket, boolean playerColour, GameHandler opponent) throws IOException {
        this.socket = socket;
        this.playerColour = playerColour;
        this.opponent = opponent;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);
        frame = new Frame();
    }

    public GameHandler(Socket socket, boolean playerColour) throws IOException {
        this.socket = socket;
        this.playerColour = playerColour;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);
        frame = new Frame();
    }

    @Override
    public void run() {
        frame = new Frame(playerColour, opponent, playerName);
    }
}
