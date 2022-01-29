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
    private ClientHandler opponent;

    public boolean isSignedIn() {return signedIn;}
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerColour(boolean playerColour) {
        this.playerColour = playerColour;}
    public void setOpponent(ClientHandler opponent) {
        this.opponent = opponent;
    }

    public ClientHandler (Socket clientSocket, GameHandler gameHandler) throws IOException {
        this.client = clientSocket;
        this.gameHandler = gameHandler;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(),true);

    }

    @Override
    public void run(){
        try{
            LogIn_SignIn_Screen logIn_signIn_screen = new LogIn_SignIn_Screen(Server.getActivePlayers());
            //new Thread(gameHandler).start();
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
            if(Server.getClients().size() > 2) {
                Server.getClients().get(2).setPlayerColour(this.playerColour);
                Server.getGames().get(2).setPlayerColour(this.playerColour);
            }
            if(Server.getClients().size() > 1 && signedIn) {
                opponent.output.println(">"+this.playerName+" has left the game.<");
            }
            output.close();
            Server.getClients().remove(this);
            Server.getGames().remove(this.gameHandler);

            if(Server.getClients().size() > 1) {
                Server.getClients().get(0).opponent = Server.getClients().get(1);
                Server.getClients().get(1).opponent = Server.getClients().get(0);
            }
            try {
                input.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
