import model.CommandInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A client
 */
public class Client {
    // Socket leading to the server
    private Socket serverConnection;

    // Input command stream from the server
    protected CommandInputStream inStream;

    private Client(String host, int port) throws IllegalArgumentException {
        try {
            System.out.println("Trying to connect to " + host + " " + port);
            serverConnection = new Socket(host, port);
            System.out.println("Made server connection.");
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Bad host name given.");
        } catch (IOException e) {
            System.out.println("Client: " + e);
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        if (args.length > 0) {
            host = args[0];
        }
        int port = 3000;
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        Client client = new Client(host, port);
        client.sendCommands();
    }

    private void sendCommands() {
        try {
            OutputStreamWriter outWriter = new OutputStreamWriter(serverConnection.getOutputStream());
            BufferedReader inReader = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));

            outWriter.write("GET cat ");
            String result = inReader.readLine();
            System.out.println("Server says: \"" + result + "\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void finalize() {
        System.out.println("Closing down SimpleClient...");
        try {
            serverConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
