package Everything.Server;

import Everything.GameRunnable;
import Everything.Server.MoveObjects.*;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Client {

    private static final String TOURNAMENT_PASSWORD = "heygang";

    static String alphabet = "ABCDEFGHIGKLMNOPQRSTUV";
    static Random random = new Random();
    static String userPass = "" + alphabet.charAt(random.nextInt(20));
    private static final String USER_NAME = userPass;
    private static final String USER_PASS = userPass;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
              Socket MyClient = new Socket(hostName, portNumber);

                  BufferedReader input = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
//                BufferedReader input = new BufferedReader(new FileReader("input.txt"));
                PrintWriter output = new PrintWriter(MyClient.getOutputStream(), true); //REMOVE THE 1 SEC DELAY AT THE BOTTOM
//                PrintWriter output = new PrintWriter("file.txt");
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

            final String ourPlayerID = tip.waitForTournyToBeginAndGetPlayerId(input.readLine());                   //Grab our PlayerID for later comparisons

            while (true) {         //Keep going as long as we're in tournament

                fromServer = input.readLine();
                if (fromServer != null) {
                    if (fromServer.contains("THANK YOU FOR PLAYING!")) {
                        break;
                    }

                    System.out.println("Server: " + fromServer);

                    if (fromServer.contains("GAME")) {
                        String gameID = tip.parseGameID(fromServer);

                        if (!runningThreadsMap.containsKey(gameID)) {
                            System.out.println("Spinning up a new thread");
                            runningThreadsMap.put(gameID, new ConcurrentLinkedQueue<>());
                            GameRunnable gameRunnable = new GameRunnable(runningThreadsMap.get(gameID),
                                    gameID,
                                    ourPlayerID);
                            Thread thread = new Thread(gameRunnable);
                            thread.start();
                        }

                        if (fromServer.contains("OVER PLAYER") ||
                                fromServer.contains("FORFEITED:") ||
                                fromServer.contains("LOST")) {

                            System.out.println("Client: server said game is over");
                            ConcurrentLinkedQueue<MoveData> threadBuffer = runningThreadsMap.get(gameID);
                            threadBuffer.add(new MoveData(true, null, MoveData.Consumer.THREAD));
                        }


                        if (fromServer.contains("WITHIN"))           //Wait for server prompt for move
                        {
                            System.out.println("Client: Server asked us to place a tile");
                            MakeMoveInstruction ourMoveInstruction = tip.getMoveInstruction(fromServer);

                            ConcurrentLinkedQueue<MoveData> threadQueue = runningThreadsMap.get(gameID);

                            threadQueue.add(new MoveData(false, ourMoveInstruction, MoveData.Consumer.THREAD));
                        }

                        if (fromServer.contains("PLACED") && !tip.getPlayerIdFromGameCommand(fromServer).equals(ourPlayerID))       //if it contains placement details, and doesn't contain our PlayerID
                        {
                            System.out.println("Client: server asked us to update map with enemy move");
                            EnemyMove enemyMove = tip.parseOpponentMove(fromServer);

                            ConcurrentLinkedQueue<MoveData> threadDataQueue = runningThreadsMap.get(enemyMove.getGameid());

                            threadDataQueue.add(new MoveData(false, enemyMove, MoveData.Consumer.THREAD));
                        }
//                        try {
//                            Thread.sleep(500);
//                        }catch (Exception e){} // FIXME: 4/11/2017 REMOVE THIS

                        for (ConcurrentLinkedQueue<MoveData> threadsQueue : runningThreadsMap.values()) {

                            synchronized (threadsQueue) {
                                if (!threadsQueue.isEmpty()) {
                                    System.out.println("Queue is not empty");
                                    System.out.println(threadsQueue.size());
                                    System.out.println(threadsQueue.peek().consumer);
                                    if (threadsQueue.peek().consumer == MoveData.Consumer.CLIENT) {

                                        MoveData moveData = threadsQueue.poll();

                                        if (moveData.move instanceof WeJustDidThisMove) {

                                            String friendlyMoveMessageToBeSent = tip.createFriendlyMoveMessageToBeSent((WeJustDidThisMove) moveData.move, gameID);
                                            System.out.println("Sending this message to the server " + friendlyMoveMessageToBeSent);
                                            output.println(friendlyMoveMessageToBeSent);
                                        } else {
                                            System.out.println("Reached invalid location in, instance of not working");
                                        }
                                    }
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
