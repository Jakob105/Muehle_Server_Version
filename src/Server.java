import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    //Liste, die die einzelnen Threads hält:
    private static ArrayList<ClientHandler> clients;
    private static ArrayList<GameHandler> games;
    private static ExecutorService playerPool = Executors.newFixedThreadPool(2);
    private static ExecutorService gamePool = Executors.newFixedThreadPool(2);
    private static ClientHandler clientThread;
    private static GameHandler gameThread;


    public static ExecutorService getGamePool() {return gamePool;}
    public static ArrayList<ClientHandler> getClients(){return clients;}
    public static ArrayList<GameHandler> getGames() {return games;}

    public static void main(String[] args) throws IOException {

        clients = new ArrayList<>();
        games = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {

            System.out.println("[SERVER]: Waiting for client connection...");

            Socket client = serverSocket.accept();
            System.out.println("[SERVER]: Connected to client!");

            if (clients.size() == 0){
                gameThread = new GameHandler(client, true);
                clientThread = new ClientHandler(client,true, gameThread);
            }
            else if (clients.size() == 1){
                gameThread = new GameHandler(client, !games.get(0).isPlayerColour(), games.get(0));
                clientThread = new ClientHandler(client,gameThread.isPlayerColour(),clients.get(0),gameThread);
                games.get(0).setOpponent(gameThread);
                clients.get(0).setOpponent(clientThread);
            }
            else {
                gameThread = new GameHandler(client);
                clientThread = new ClientHandler(client,gameThread);
            }
            games.add(gameThread);
            clients.add(clientThread);
            playerPool.execute(clientThread);
        }
    }
}
