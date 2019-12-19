package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {

    public static Socket client;
    public static ServerSocket serverSocket;

    @Override
    public void run() {
        try {
            client = serverSocket.accept();
            while(true) {
                // Receiving and printing client messages
                InputStreamReader streamReader = new InputStreamReader(client.getInputStream());
                BufferedReader messageReader = new BufferedReader(streamReader);
                String serverMessage = messageReader.readLine();
                System.out.println("Client: " + serverMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // Creating a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Starting Server on port 4321
        serverSocket = new ServerSocket(4321);

        // Starting message receiving service
        Server server = new Server();
        server.start();

        // Running loop for sending messages to client
        while(true) {
            String serverMessage = scanner.nextLine();
            PrintWriter sender = new PrintWriter(client.getOutputStream(), true);
            sender.println(serverMessage);
        }
    }
}
