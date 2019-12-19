package TCP_Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        // Creating a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Starting Server on port 4321
        ServerSocket server = new ServerSocket(4321);
        Socket client;

        // Running loop for accepting and sending messages to client
        while( (client = server.accept()) != null) {
            // Creating a buffered reader for client
            InputStreamReader clientStreamReader = new InputStreamReader(client.getInputStream());
            BufferedReader clientMessageReader = new BufferedReader(clientStreamReader);

            // Reading and printing message from client
            String clientMessage =  clientMessageReader.readLine();
            System.out.println("Client: " + clientMessage);

            // Reading and sending message
            String serverMessage = scanner.next();
            PrintWriter sender = new PrintWriter(client.getOutputStream(), true);
            sender.println(serverMessage);
        }
    }
}
