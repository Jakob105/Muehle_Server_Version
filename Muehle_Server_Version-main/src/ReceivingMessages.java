import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceivingMessages implements Runnable {

    //basic stuff
    private Socket socket;
    private BufferedReader input;

    //constructor
    public ReceivingMessages(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run() {
        try {

            String serverResponse = input.readLine();
            //as soon as the client leaves, the server
            //response becomes 'null', so the
            //Receiving Messages thread should stop
            while(!(serverResponse == null)) {
                System.out.println(serverResponse);
                serverResponse = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}