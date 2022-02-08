import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    //Liste, die die einzelnen Threads hält:
    private static ArrayList<ClientHandler> activeClientThreads;
    private static ArrayList<GameHandler> activeGameThreads;
    private static ClientHandler clientThread;
    private static GameHandler gameThread;
    private static Thread game;

    public static Thread getGame() {return game;}
    public static ArrayList<ClientHandler> getClients(){return activeClientThreads;}
    public static ArrayList<GameHandler> getGames() {return activeGameThreads;}

    public static void main(String[] args) throws IOException {

        activeClientThreads = new ArrayList<>();
        activeGameThreads = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {

            System.out.println("[SERVER]: Waiting for client connection...");

            Socket client = serverSocket.accept();
            System.out.println("[SERVER]: Connected to client!");

            gameThread = new GameHandler(client);
            clientThread = new ClientHandler(client, gameThread);
            activeGameThreads.add(gameThread);
            activeClientThreads.add(clientThread);
            new Thread(clientThread).start();
            game = new Thread(gameThread);
        }
    }
}
