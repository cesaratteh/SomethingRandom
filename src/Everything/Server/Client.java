//package Everything.Server;
//
//import Everything.Server.MoveObjects.EnemyMove;
//import Everything.Server.MoveObjects.MakeMoveInstruction;
//import Everything.Server.MoveObjects.MoveData;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.Random;
//
//
//public class Client {
//
//    private static final String TOURNAMENT_PASSWORD = "heygang";
//
//    static String alphabet = "ABCDEFGHIGKLMNOPQRST";
//    static Random random = new Random();
//    static String userPass = "TEAM_" + alphabet.charAt(random.nextInt(20));
//    private static final String USER_NAME = userPass;
//    private static final String USER_PASS = userPass;
//
//    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.err.println("Usage: <host name> <port number>");
//            System.exit(1);
//        }
//
//        String hostName = args[0];
//        int portNumber = Integer.parseInt(args[1]);
//
//
//        try (
//                Socket MyClient = new Socket(hostName, portNumber);
//
//
//                BufferedReader input = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
////                BufferedReader input = new BufferedReader(new FileReader("input.txt"));
//                PrintWriter output = new PrintWriter(MyClient.getOutputStream(), true); //REMOVE THE 1 SEC DELAY AT THE BOTTOM
////                PrintWriter output = new PrintWriter("file.txt");
//        ) {
//            String fromServer = null;
//            String authenticationToBeSent;
//
//
////            final HashMap<String, ConcurrentLinkedQueue<MoveData>> runningThreadsMap = new HashMap<>();
//            final CommunicationBuffer communicationBuffer = new CommunicationBuffer();
//
//            TigerIslandProtocol tip = new TigerIslandProtocol();            //Only authenticate once, so outside while loop
//            authenticationToBeSent = tip.authenticateTournament(input.readLine(), TOURNAMENT_PASSWORD, USER_NAME, USER_PASS);
//            output.println(authenticationToBeSent);
//
//            System.out.println(authenticationToBeSent);
//
//            authenticationToBeSent = tip.authenticateTournament(input.readLine(), TOURNAMENT_PASSWORD, USER_NAME, USER_PASS);
//            output.println(authenticationToBeSent);
//
//            System.out.println(authenticationToBeSent);
//
//            final String ourPlayerID = tip.waitForTournyToBeginAndGetPlayerId(input.readLine());                   //Grab our PlayerID for later comparisons
//
//            while (true) {         //Keep going as long as we're in tournament
//                if (input.ready()) {
//                    fromServer = input.readLine();
//                    System.out.println("SERVER: " + fromServer);
//
//                    if (fromServer != null) {
//                        System.out.println("SERVER: " + fromServer);
//
//
//                        if (fromServer.contains("THANK YOU FOR PLAYING!")) {
//                            break;
//                        } else if (fromServer.contains("GAME")) {
//                            String gameID = tip.parseGameId(fromServer);
//
//
//                            communicationBuffer.addNewGameIdAndCreateRunnableIfGameDoesntExist(gameID, ourPlayerID);
//                        }
//
//                        if (fromServer.contains("OVER PLAYER") ||
//                                fromServer.contains("FORFEITED:") ||
//                                fromServer.contains("LOST")) {
//
//                            String gameID = tip.parseGameId(fromServer);
//                            System.out.println("Client: server said game is over");
//                            communicationBuffer.sendData(new MoveData(true, null, MoveData.Consumer.THREAD), gameID);
//                        }
//
//                        if (fromServer.contains("WITHIN")) {           //Wait for server prompt for move
//                            String gameID = tip.parseGameId(fromServer);
//
//                            System.out.println("Client: Server asked us to place a tile");
//                            MakeMoveInstruction ourMoveInstruction = tip.getMoveInstruction(fromServer);
//
//                            communicationBuffer.sendData(new MoveData(false, ourMoveInstruction, MoveData.Consumer.THREAD), gameID);
//                        }
//
//                        if (fromServer.contains("PLACED") && !tip.getPlayerIdFromGameCommand(fromServer).equals(ourPlayerID)) {       //if it contains placement details, and doesn't contain our PlayerID
//
//                            System.out.println("Client: server asked us to update map with enemy move");
//                            EnemyMove enemyMove = tip.parseOpponentMove(fromServer);
//
//                            communicationBuffer.sendData(new MoveData(false, enemyMove, MoveData.Consumer.THREAD), enemyMove.getGameid());
//                        }
//
//                        if (fromServer.contains("END OF ROUND")) {
//                            System.out.println("----------------------------------------------- END OF ROUND");
//                            System.out.println("----------------------------------------------- END OF ROUND");
//                            System.out.println("----------------------------------------------- END OF ROUND");
//
//                            communicationBuffer.shutDownAllThreads();
//                        }
//
//                    }
//
//                }
//            }
//
////            communicationBuffer.flushQueues(output, tip);
//
//        } catch (ConnectException e) {
//            System.out.println("ERROR");
//        } catch (UnknownHostException e) {
//            System.out.println(e);
//            System.exit(1);
//        } catch (IOException e) {
//            System.out.println(e);
//            System.exit(1);
//        }
//    }
//}
