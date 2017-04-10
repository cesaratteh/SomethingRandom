package Everything.Server;

import Everything.GameRunnable;
import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.GameOverMove;
import Everything.Server.MoveObjects.Move;
import com.sun.security.ntlm.Server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client {
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.err.println(
//                    "Usage: <host name> <port number>");
//            System.exit(1);
//        }
//
//        String hostName = args[0];
//        int portNumber = Integer.parseInt(args[1]);

        try (
               //   Socket MyClient = new Socket("localhost", 6539);  //FIXME: Change to actual server info

                //  BufferedReader input = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
                BufferedReader input = new BufferedReader(new FileReader("input.txt"));
                //PrintWriter output = new PrintWriter(MyClient.getOutputStream(), true);
                PrintWriter output = new PrintWriter("file.txt");
        ){
            String fromServer;
            String fromPlayer;
            String PlayerID;
            HashMap<String, ConcurrentLinkedQueue<Move>> ThreadtoClient = new HashMap<>();
            ArrayList<ConcurrentLinkedQueue<Move>> ClienttoThread = new ArrayList<>();


            TigerIslandProtocol tip = new TigerIslandProtocol();            //Only authenticate once, so outside while loop
            fromPlayer = tip.authenticateTournament(input.readLine());

            output.println(fromPlayer);

            System.out.println(fromPlayer);

            fromPlayer = tip.authenticateTournament(input.readLine());

            output.println(fromPlayer);

            System.out.println(fromPlayer);

            PlayerID = tip.getPlayerID(input.readLine());                   //Grab our PlayerID for later comparisons


            while (!((fromServer = input.readLine()).equals("END OF CHALLENGES"))){         //Keep going as long as we're in tournament
                System.out.println("Server: " + fromServer);


                if(fromServer.contains("GAME")) {
                    String gameID = tip.parseGameID(fromServer);

                    if(!ThreadtoClient.containsKey(gameID))
                    {
                        ThreadtoClient.put(gameID, new ConcurrentLinkedQueue<Move>());
                        ClienttoThread.add(new ConcurrentLinkedQueue<Move>());
                        GameRunnable game = new GameRunnable(ThreadtoClient.get(gameID),
                                ClienttoThread.get(ClienttoThread.size()-1),
                                gameID,
                                "laskdjfldsakj");
                        game.run();
                    }

                    while (!(fromServer.contains("OVER PLAYER"))) {          //While game isn't over
                        System.out.println("Server: " + fromServer);
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
                        fromServer = input.readLine();
                    }
                    String gameID1 = tip.parseGameID(fromServer);
                    GameOverMove gameover = new GameOverMove(gameID1);
                    tip.writeToBuffer(gameover);
                }
            }
        }catch (ConnectException e){
            System.out.println("ahhhhhhhhhhhhhhhhhhhhhh");
        }catch (UnknownHostException e) {
            System.out.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }






    }
}
