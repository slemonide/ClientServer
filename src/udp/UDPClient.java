package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static config.Configuration.PORT;
import static config.Configuration.SERVER;

/**
 * A simple UDP client
 */
public class UDPClient {
    private static Scanner scanner;
    private static DatagramSocket udpSocket;

    public static void main(String[] args) throws IOException {
        udpSocket = new DatagramSocket();
        scanner = new Scanner(System.in);

        System.out.println("Connecting to " + SERVER + " " + PORT);

        while (true) {

            String nextLine = scanner.nextLine();

            if (nextLine != null) {
                byte[] dataBuffer = nextLine.getBytes();
                DatagramPacket packet = new DatagramPacket(dataBuffer, dataBuffer.length, InetAddress.getByName(SERVER), PORT);
                udpSocket.send(packet);
            }

            //output.println(scanner.nextLine());

            //String nextLine = input.readLine();
            //if (nextLine != null) {
            //    System.out.println("SERVER: " + nextLine);
            //}
        }
    }
}
