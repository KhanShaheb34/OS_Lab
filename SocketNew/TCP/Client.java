package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        while(true) {
            // Creating a scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Connecting to server
            Socket server = new Socket("localhost", 4321);

            // Sending message to server
            String clientMessage = scanner.next();
            PrintWriter sender = new PrintWriter(server.getOutputStream(), true);
            sender.println(clientMessage);

            // Receiving and printing server messages
            InputStreamReader streamReader = new InputStreamReader(server.getInputStream());
            BufferedReader messageReader = new BufferedReader(streamReader);
            String serverMessage = messageReader.readLine();
            System.out.println("Server: " + serverMessage);
        }
    }
}
