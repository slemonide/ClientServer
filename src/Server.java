import model.CommandInputStream;
import model.protocol.AbstractCommand;
import model.protocol.Done;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple server
 */
public class Server {
    protected int port;
    protected ServerSocket clientConnection;

    public Server(int port) throws IllegalArgumentException {
        if (port <= 0) {
            throw new IllegalArgumentException("Bad port number.");
        }

        System.out.println("Connecting server socket to port...");
        try {
            clientConnection = new ServerSocket();
        } catch (IOException e) {
            System.out.println("Failed to connect to port: " + port);
            System.exit(1);
        }

        this.port = port;
    }

    public static void main(String[] args) {
        int port = 3000;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        Server server = new Server(port);
        System.out.println("Server running on port " + port + "...");
        server.listen();
    }

    private void listen() {
        System.out.println("Waiting for clients...");
        while (!Thread.interrupted()) {
            try {
                Socket clientRequest = clientConnection.accept();
                System.out.println("Got a client...");
                serviceClient(clientRequest);
            } catch (IOException ignored) {}
        }
    }

    private void serviceClient(Socket clientRequest) {
        try {
            CommandInputStream inStream = new CommandInputStream(clientRequest.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientRequest.getOutputStream());

            AbstractCommand command = null;
            while (command == null || !(command instanceof Done)) {
                command = inStream.readCommand();

                if (command != null) {
                    String result = command.execute();
                    outputStream.writeBytes(result);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void finalize() {
        System.out.println("Shutting down server running on port " + port);
    }
}
