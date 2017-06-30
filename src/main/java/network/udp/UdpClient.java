package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by UC206612 on 6/30/2017.
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        int serverPort = 2345;
        int clientPort = 2346;
        DatagramSocket sendSocket = new DatagramSocket();
        DatagramSocket datagramSocket = new DatagramSocket(clientPort);
        Scanner sio = new Scanner(System.in);
        while (true) {

            System.out.println("Please input:");
            int radius = sio.nextInt();
            if (radius < 0) {
                System.out.println("Invalid input.");
                continue;
            }


            DatagramPacket dataPack = new DatagramPacket(Integer.toString(
                    radius).getBytes(), Integer.toString(radius).length(),
                    InetAddress.getByName("127.0.0.1"), new Integer(serverPort));
            sendSocket.send(dataPack);
            System.out.println("Client send:\t" + radius);

            byte[] buffer = new byte[2048];
            DatagramPacket datagramPacket = new DatagramPacket(buffer,
                    buffer.length);
            datagramSocket.receive(datagramPacket);
            System.out.println("Client receive:\t"
                    + new String(datagramPacket.getData()).trim());
        }
    }
}
