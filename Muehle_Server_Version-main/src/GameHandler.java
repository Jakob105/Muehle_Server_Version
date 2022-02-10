import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameHandler implements Runnable {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Frame frame;
    private String playerName;
    private GameHandler gameHandler;
    private GameHandler opponentGameHandler;
    private ClientHandler clientHandler;
    private ClientHandler opponentClientHandler;

    public String getPlayerName() {
        return playerName;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public GameHandler getOpponentGameHandler() {
        return opponentGameHandler;
    }

    public ClientHandler getOpponentClientHandler() {
        return opponentClientHandler;
    }

    public void setPlayerName(String playerName) {this.playerName = playerName;}
    public Frame getFrame() {
        return frame;
    }
    public ClientHandler getClientHandler() {
        return clientHandler;
    }
    public void setOpponentGameHandler(GameHandler opponentGameHandler) {
        this.opponentGameHandler = opponentGameHandler;
    }
    public void setOpponentClientHandler(ClientHandler opponentClientHandler) {
        this.opponentClientHandler = opponentClientHandler;
    }
    public GameHandler(Socket socket, ClientHandler clienthandler) throws IOException {
        this.socket = socket;
        this.clientHandler = clienthandler;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);
    }

    @Override
    public void run() {

        frame = new Frame(playerName,clientHandler,this);
    }
}
