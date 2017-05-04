import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * A client
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String server = "127.0.0.1";
        Socket sock = new Socket(server, 3000);

        BufferedReader dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintStream dat = new PrintStream(sock.getOutputStream());

        while (!sock.isClosed()) {
            dat.println("PING");

            System.out.println(dis.readLine());
        }
    }
}
