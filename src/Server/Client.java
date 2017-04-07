package Server;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "Usage: <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket MyClient = new Socket(hostName, portNumber);  //FIXME: Change to actual server info
            BufferedReader input = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
            PrintWriter output = new PrintWriter(MyClient.getOutputStream(), true);
        ){
            String fromServer;
            String fromPlayer;
            TigerIslandProtocol tip = new TigerIslandProtocol();
            fromPlayer = tip.authenticateTournament(input.readLine());
            output.println(fromPlayer);
            fromPlayer = tip.authenticateTournament(input.readLine());
            output.println(fromPlayer);
            while ((fromServer = input.readLine()) != null){
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("END OF CHALLENGES"))
                    break;

                while(!(fromServer.contains("OVER PLAYER"))) {
                    if (fromServer.contains("WITHIN"))           //Wait for server prompt for move
                    {
                        fromPlayer = tip.parseMoveInput(input.readLine());
                        System.out.println("Player: " + fromPlayer);
                        output.println(fromPlayer);
                    }
                }
            }
        }catch (UnknownHostException e) {
            System.out.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }





    }
}
