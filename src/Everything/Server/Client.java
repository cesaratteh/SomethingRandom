package Everything.Server;

import Everything.GameRunnable;
import Everything.Server.MoveObjects.*;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Client {

    private static final String TOURNAMENT_PASSWORD = "";
    private static final String USER_NAME = "";
    private static final String USER_PASS = "";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
//              Socket MyClient = new Socket(hostName, portNumber);

                //  BufferedReader input = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
                BufferedReader input = new BufferedReader(new FileReader("input.txt"));
                //PrintWriter output = new PrintWriter(MyClient.getOutputStream(), true);
                PrintWriter output = new PrintWriter("file.txt");
        ){
            String fromServer;
            String fromPlayer;


            final HashMap<String, ConcurrentLinkedQueue<MoveData>> runningThreadsMap = new HashMap<>();


            TigerIslandProtocol tip = new TigerIslandProtocol();            //Only authenticate once, so outside while loop
            fromPlayer = tip.authenticateTournament(input.readLine(), TOURNAMENT_PASSWORD, USER_NAME, USER_PASS);
            output.println(fromPlayer);

            System.out.println(fromPlayer);

            fromPlayer = tip.authenticateTournament(input.readLine(), TOURNAMENT_PASSWORD, USER_NAME, USER_PASS);
            output.println(fromPlayer);

            System.out.println(fromPlayer);

            final String ourPlayerID = tip.getPlayerID(input.readLine());                   //Grab our PlayerID for later comparisons

            while (!((fromServer = input.readLine()).contains("THANK YOU FOR PLAYING!"))){         //Keep going as long as we're in tournament
                System.out.println("Server: " + fromServer);

                if(fromServer.contains("GAME")) {
                    String gameID = tip.parseGameID(fromServer);

                    if(!runningThreadsMap.containsKey(gameID))
                    {
                        runningThreadsMap.put(gameID, new ConcurrentLinkedQueue<MoveData>());
                        GameRunnable gameRunnable = new GameRunnable(runningThreadsMap.get(gameID),
                                gameID,
                                ourPlayerID);
                        gameRunnable.run();
                    }

                    if (fromServer.contains("OVER PLAYER") ||
                            fromServer.contains("FORFEITED:") ||
                            fromServer.contains("LOST")) {


                        ConcurrentLinkedQueue<MoveData> threadBuffer = runningThreadsMap.get(gameID);
                        threadBuffer.add(new MoveData(true, null, MoveData.Consumer.THREAD));
                    }


                    if (fromServer.contains("WITHIN"))           //Wait for server prompt for move
                    {
                        MakeMoveInstruction ourMoveInstruction = tip.getMoveInstruction(fromServer);

                        ConcurrentLinkedQueue<MoveData> threadQueue = runningThreadsMap.get(gameID);

                        threadQueue.add(new MoveData(false, ourMoveInstruction, MoveData.Consumer.THREAD));
                    }

                    if (fromServer.contains("PLACED") && !(fromServer.contains(ourPlayerID)))       //if it contains placement details, and doesn't contain our PlayerID
                    {
                        EnemyMove enemyMove = tip.parseOpponentMove(fromServer);

                        ConcurrentLinkedQueue<MoveData> threadDataQueue = runningThreadsMap.get(enemyMove.getGameid());

                        threadDataQueue.add(new MoveData(false, enemyMove, MoveData.Consumer.THREAD));
                    }

                    for (ConcurrentLinkedQueue<MoveData> threadsQueue : runningThreadsMap.values()) {
                        if (!threadsQueue.isEmpty()) {

                            if (threadsQueue.peek().consumer == MoveData.Consumer.CLIENT) {

                                Move friendlyMove = threadsQueue.poll().move;

                                if (friendlyMove instanceof WeJustDidThisMove) {

                                    String friendlyMoveMessageToBeSent = tip.createFriendlyMoveMessageToBeSent((WeJustDidThisMove) friendlyMove, gameID);
                                    output.println(friendlyMoveMessageToBeSent);
                                }
                            }
                        }
                    }
                }
            }
        }catch (ConnectException e){
            System.out.println("ERROR");
        }catch (UnknownHostException e) {
            System.out.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
