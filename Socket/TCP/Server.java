package com.khanshaheb;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        ServerSocket server = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        System.out.println("Port: ");
        int port = scanner.nextInt();

        try {
            server = new ServerSocket(port);
            System.out.println("Server created, waiting for client...");
        } catch (IOException e) {
            System.out.println("Server Creation Failed");
            e.printStackTrace();
        }

        try {
            socket = server.accept();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client Joined");
        } catch (IOException e) {
            System.out.println("Client Connection Failed");
            e.printStackTrace();
        }

        String message = "";
        String send = "";

        while (!message.toLowerCase().equals("bye") && !send.toLowerCase().equals("bye")) {
            try {
                message = in.readUTF();
                System.out.println(message);
                send = scanner.nextLine();
                out.writeUTF(send);
            } catch (IOException e) {
                System.out.println("Message Reading or Sending Failed");
                e.printStackTrace();
            }
        }

        try {
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}