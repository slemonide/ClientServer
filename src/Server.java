import model.CommandInputStream;
import model.protocol.AbstractCommand;
import model.protocol.Done;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple server
 */
public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (!Thread.interrupted()) {
            Socket clientSocket = serverSocket.accept();

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            PrintStream output = new PrintStream(outputStream);

            System.out.println(input.readLine());
            output.println(System.nanoTime());
            System.out.println(System.nanoTime());
            clientSocket.close();
        }
    }
}
