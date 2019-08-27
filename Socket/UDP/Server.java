package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket(8080);
        ds.setBroadcast(true);
        byte[] receive = new byte[65535];
        byte[] buf = null;
        DatagramPacket dpRec = null;
        DatagramPacket dpSend = null;

        InetAddress cAddress;
        int cPort;

        while (true) {
            dpRec = new DatagramPacket(receive, receive.length);
            ds.receive(dpRec);
            cAddress = dpRec.getAddress();
            cPort = dpRec.getPort();
            System.out.println(data(receive));
            if (data(receive).toString().toLowerCase().equals("bye")) break;
            receive = new byte[65535];

            String message = scanner.nextLine();
            buf = message.getBytes();
            dpSend = new DatagramPacket(buf, buf.length, cAddress, cPort);
            ds.send(dpSend);
            if (message.toLowerCase().equals("bye")) break;
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
