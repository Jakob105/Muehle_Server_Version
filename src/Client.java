import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {

        //Basic stuff -> Socket, In- and Output
        Socket socket = new Socket("127.0.0.1",8080);
        ReceivingMessages receivingMessages = new ReceivingMessages(socket);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

        //starting new Thread that's responsible for receiving messages all the time
        new Thread(receivingMessages).start();;

        //instanciating the message a client can send
        String message = "dummyString";

        //the client can leave the program by entering 'quit'
        while (!message.equals("quit")) {
            message = keyboard.readLine();
            output.println(message);
        }
        Thread.currentThread().interrupt();
    }
}
