package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * A simple TCP client
 */
public class TCPClient {
    private static final String SERVER = "127.0.0.1";
    private static final int PORT = TCPServer.PORT;
    private static Scanner scanner;
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        socket = new Socket(SERVER, PORT);
        scanner = new Scanner(System.in);

        System.out.println("Connecting to " + SERVER + " " + PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream output = new PrintStream(socket.getOutputStream());

        while (true) {
            output.println(scanner.nextLine());

            String nextLine = input.readLine();
            if (nextLine != null) {
                System.out.println("SERVER: " + nextLine);
            }
        }
    }
}
