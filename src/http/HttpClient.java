package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

/**
 *
 */
public class HttpClient {
    private static final String HOSTNAME = "www.example.com";
    private static final String PAGE = "/index.html";

    public static void main(String[] args) throws IOException {
        Socket sock = new Socket(HOSTNAME, 80);

        BufferedReader clientReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintStream serverWriter = new PrintStream(sock.getOutputStream());

        serverWriter.println("GET " + PAGE + " HTTP/1.1\r\n" +
                "Host: " + HOSTNAME + "\r\n\r\n");

        String nextLine = clientReader.readLine();

        while (nextLine != null) {
            System.out.println(clientReader.readLine());
            nextLine = clientReader.readLine();
        }
    }
}
