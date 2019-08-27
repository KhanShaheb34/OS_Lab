package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        DatagramSocket ds = new DatagramSocket();
        DatagramPacket dpSend = null;
        DatagramPacket dpRec = null;
        InetAddress ip = InetAddress.getLocalHost();

        byte[] buf = null;
        byte[] rec = new  byte[65535];

        while (true) {
            String message = scanner.nextLine();
            buf = message.getBytes();
            dpSend = new DatagramPacket(buf, buf.length, ip, 8080);
            ds.send(dpSend);
            if (message.toLowerCase().equals("bye")) break;

            dpRec = new DatagramPacket(rec, rec.length);
            ds.receive(dpRec);
            System.out.println(data(rec));
            if (data(rec).toString().toLowerCase().equals("bye")) break;
        }
    }

    private static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
