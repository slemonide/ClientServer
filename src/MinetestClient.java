import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * A basic minetest client
 */
public class MinetestClient {
    private static final String HOSTNAME = "shame.ddns.net";
    private static final int PORT = 30000;

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName(HOSTNAME);
        DatagramSocket udpSocket = new DatagramSocket();

        byte[] dataBuffer = HexBin.decode(String.valueOf("4f457403".toCharArray()));
        DatagramPacket packet = new DatagramPacket(dataBuffer, dataBuffer.length, address, PORT);
        udpSocket.send(packet);
        
    }
}
