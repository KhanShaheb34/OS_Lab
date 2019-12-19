package TCP_Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    // Connecting to server
    public static Socket server;

    @Override
    public void run() {
        try {
            while(true) {
                // Receiving and printing server messages
                InputStreamReader streamReader = new InputStreamReader(server.getInputStream());
                BufferedReader messageReader = new BufferedReader(streamReader);
                String serverMessage = messageReader.readLine();
                System.out.println("Server: " + serverMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        server = new Socket("localhost", 4321);
        Client client = new Client();
        client.start();

        // Creating a scanner for user input
        Scanner scanner = new Scanner(System.in);

        while(true) {
            // Sending message to server
            String clientMessage = scanner.nextLine();
            PrintWriter sender = new PrintWriter(server.getOutputStream(), true);
            sender.println(clientMessage);
        }
    }
}
