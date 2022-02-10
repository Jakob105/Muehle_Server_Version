import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Server {

    //Liste, die die einzelnen Threads hält:

    private static ClientHandler clientThread;
    private static GameHandler gameThread;
    private static Thread game;
    private static Hashtable<String, GameHandler> availablePlayers;
    private static List<GameHandler> gameHandlerList;

    public static Hashtable<String, GameHandler> getAvailablePlayers() {return availablePlayers;}
    public static Thread getGame() {return game;}
    public static List<GameHandler> getGameHandlerList() {return gameHandlerList;}

    public static void main(String[] args) throws IOException {

        availablePlayers = new Hashtable<>();
        gameHandlerList = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {

            System.out.println("[SERVER]: Waiting for client connection...");

            Socket client = serverSocket.accept();
            System.out.println("[SERVER]: Connected to client!");

            clientThread = new ClientHandler(client);
            gameThread = new GameHandler(client, clientThread);
            clientThread.setGameHandler(gameThread);

            new Thread(clientThread).start();
            game = new Thread(gameThread);
        }
    }
}
