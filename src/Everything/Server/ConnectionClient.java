package Everything.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionClient {

    private BufferedReader input;
    private PrintWriter output;

    public ConnectionClient(String hostName, int portNumber) throws Exception {
        final Socket socket = new Socket(hostName, portNumber);

        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    public synchronized boolean ready() throws IOException {
        return input.ready();
    }

    public synchronized String readLine() throws IOException {
        String message = input.readLine();
        System.out.println("SERVER: " + message);
        return message;
    }

    public synchronized void println(String message) throws IOException{
        System.out.println("CLIENT: " + message);
        output.println(message);
    }
}
