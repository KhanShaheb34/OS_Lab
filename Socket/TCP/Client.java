package com.khanshaheb;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Host IP: ");
        String host = scanner.nextLine();
        System.out.print("Port: ");
        int port = scanner.nextInt();

        Socket socket = null;
        DataOutputStream out = null;
        DataInputStream in = null;

        try {
            socket = new Socket(host, port);
            System.out.println("Connected on " + host + ":" + port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        String message = "";
        String rec = "";

        while (!message.toLowerCase().equals("bye") && !rec.toLowerCase().equals("bye")) {
            try {
                message = scanner.nextLine();
                out.writeUTF(message);
                rec = in.readUTF();
                System.out.println(rec);
            } catch (IOException e) {
                System.out.println("Message sending failed");
                e.printStackTrace();
            }
        }

        try {
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Closing failed");
            e.printStackTrace();
        }
    }
}
