package udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static config.Configuration.PORT;

/**
 * A simple UDP server
 */
public class UDPServer {
    private static DatagramSocket udpSocket;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        udpSocket = new DatagramSocket(PORT);
        scanner = new Scanner(System.in);

        System.out.println("Server listening on " + PORT);

        while (true) {
            DatagramPacket packet = new DatagramPacket(new byte[10], 10);
            udpSocket.receive(packet);
            System.out.println("Got a client...");

            System.out.println("ADDRESS: " + packet.getAddress());
            System.out.println("PORT: " + packet.getPort());
            System.out.println("MESSAGE: " + new String(packet.getData()));

            System.out.println("Client is disconnected.");
        }
    }
}
