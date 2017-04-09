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
            String PlayerID;

            TigerIslandProtocol tip = new TigerIslandProtocol();            //Only authenticate once, so outside while loop
            fromPlayer = tip.authenticateTournament(input.readLine());
            output.println(fromPlayer);
            fromPlayer = tip.authenticateTournament(input.readLine());
            output.println(fromPlayer);
            PlayerID = tip.getPlayerID(input.readLine());                   //Grab our PlayerID for later comparisons

            while ((fromServer = input.readLine()) != "END OF CHALLENGES"){         //Keep going as long as we're in tournament
                System.out.println("Server: " + fromServer);

                while(!(fromServer.contains("OVER PLAYER"))) {          //While game isn't over
                    if (fromServer.contains("WITHIN"))           //Wait for server prompt for move
                    {
                        fromPlayer = tip.parseMoveInput(fromServer);
                        System.out.println("Player: " + fromPlayer);
                        output.println(fromPlayer);
                    }
                    if(fromServer.contains("PLACED") && !(fromServer.contains(PlayerID)))       //if it contains placement details, and doesn't contain our PlayerID
                    {
                       EnemyMove theirMove = tip.parseOpponentMove(fromServer);
                       tip.writeToBuffer(theirMove);
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
