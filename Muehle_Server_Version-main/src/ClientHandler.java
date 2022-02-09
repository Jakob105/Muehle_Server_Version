import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Objects;

public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private String playerName;
    private boolean playerColour;
    private boolean signedIn = false;
    private GameHandler gameHandler;
    private GameHandler opponentGameHandler;
    private ClientHandler clientHandler;
    private ClientHandler opponentClientHandler;

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    public GameHandler getOpponentGameHandler() {
        return opponentGameHandler;
    }

    public void setOpponentGameHandler(GameHandler opponentGameHandler) {
        this.opponentGameHandler = opponentGameHandler;
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public ClientHandler getOpponentClientHandler() {
        return opponentClientHandler;
    }

    public void setOpponentClientHandler(ClientHandler opponentClientHandler) {
        this.opponentClientHandler = opponentClientHandler;
    }

    public String getPlayerName() {
        return playerName;
    }


    public void setPlayerName(String playerName) {this.playerName = playerName;}


    public ClientHandler (Socket clientSocket) throws IOException {
        this.client = clientSocket;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(),true);

    }

    @Override
    public void run(){
        try{
            LogIn_SignIn_Screen logIn_signIn_screen = new LogIn_SignIn_Screen(gameHandler);

            String message = input.readLine();

            while(!Objects.equals(message, "quit") && !Objects.equals(message, null)) {
                output.println("["+playerName+"]: "+message);
                message = input.readLine();
            }
        } catch (IOException e){
            System.err.println("IOException in client handler");
            System.err.println(e.getStackTrace());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(Server.getAvailablePlayers().size() > 1) {
                output.println(">"+this.playerName+" has left the game.<");
            }
            output.close();
            Server.getAvailablePlayers().remove(this.getPlayerName());
            System.out.println(Server.getAvailablePlayers());
            try {
                input.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
