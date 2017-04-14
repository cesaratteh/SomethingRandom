package Everything.Server;

import Everything.GameRunnable;

import java.util.HashMap;
import java.util.Random;

public class Main {

    //----------
    // Constants

    private static final String TOURNAMENT_PASSWORD = "Iwanttobelieve";
//    private static String alphabet = "ABCDEFGHIGKLMNOPQRST";
//    private static Random random = new Random();
//    private static String userPass = "TEAM_" + alphabet.charAt(random.nextInt(20));
    private static final String USER_NAME = "Team_T";
    private static final String USER_PASS = "Team_T";

    //--------
    // Methods

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
            final ConnectionClient connectionClient = new ConnectionClient(hostName, portNumber);
            final SharedDataBuffer sharedDataBuffer = new SharedDataBuffer();
            final HashMap<String, Thread> runningThreads = new HashMap<>();


            String fromServer = null;
            String authenticationToBeSent;

            TigerIslandProtocol tip = new TigerIslandProtocol();            //Only authenticate once, so outside while loop
            authenticationToBeSent = tip.authenticateTournament(connectionClient.readLine(), TOURNAMENT_PASSWORD, USER_NAME, USER_PASS);
            connectionClient.println(authenticationToBeSent);

            authenticationToBeSent = tip.authenticateTournament(connectionClient.readLine(), TOURNAMENT_PASSWORD, USER_NAME, USER_PASS);
            connectionClient.println(authenticationToBeSent);

            final String ourPlayerID = tip.waitForTournyToBeginAndGetPlayerId(connectionClient.readLine());

            while (true) {
                if (connectionClient.ready()) {
                    fromServer = connectionClient.readLine();

                    if (fromServer != null) {

                        if (fromServer.contains("THANK YOU FOR PLAYING!")) {
                            break;
                        } else if (fromServer.contains("GAME") &&
                                (fromServer.contains("WITHIN") || fromServer.contains("PLACED"))) {

                            final String gameId = tip.parseGameId(fromServer);

                            if (!runningThreads.containsKey(gameId)) {
                                Thread t = new Thread(new GameRunnable(sharedDataBuffer, connectionClient, gameId, ourPlayerID));
                                runningThreads.put(gameId, t);
                                t.start();
                            }
                        }

                        if (fromServer.contains("OVER PLAYER") ||
                                fromServer.contains("FORFEITED:") ||
                                fromServer.contains("LOST")) {

                            String gameId = tip.parseGameId(fromServer);
                            if (runningThreads.get(gameId) != null) {
                                runningThreads.get(gameId).interrupt();
                                runningThreads.remove(gameId);
                            }
                        }

                        if (fromServer.contains("WITHIN")) {
                            sharedDataBuffer.add(fromServer);
                        }

                        if (fromServer.contains("PLACED") && !tip.getPlayerIdFromGameCommand(fromServer).equals(ourPlayerID)) {
                            sharedDataBuffer.add(fromServer);
                        }

                        if (fromServer.contains("END OF ROUND")) {
                            for (Thread t : runningThreads.values()) {
                                t.interrupt();
                            }
                            runningThreads.clear();

                            System.out.println("----------------------------------------------- END OF ROUND");
                            System.out.println("----------------------------------------------- END OF ROUND");
                            System.out.println("----------------------------------------------- END OF ROUND");

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
