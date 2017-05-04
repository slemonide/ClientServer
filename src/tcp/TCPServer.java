package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static config.Configuration.PORT;

/**
 * A simple TCP server
 */
public class TCPServer {
    private static ServerSocket serverSocket;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(PORT);
        scanner = new Scanner(System.in);

        System.out.println("Server listening on " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Got a client...");

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            PrintStream output = new PrintStream(outputStream);

            while (clientSocket.isConnected()) {
                try {
                    String nextLine = input.readLine();
                    if (nextLine != null) {
                        System.out.println("CLIENT: " + nextLine);
                    }
                    output.println(scanner.nextLine());
                } catch (IOException ignored) {}
            }

            System.out.println("Client is disconnected.");
        }
    }
}
