package Everything;

import Everything.Server.ConnectionClient;
import Everything.Server.MoveObjects.*;
import Everything.Server.SharedDataBuffer;
import Everything.Server.TigerIslandProtocol;

import java.io.IOException;
import java.util.Date;

public class GameRunnable implements Runnable {

    //-----------
    // Attributes

    private SharedDataBuffer sharedDataBuffer;
    private ConnectionClient connectionClient;
    private String gameId;
    private String playerId;

    final private Date timeCreated = new Date();
    //-------------
    // Constructors

    public GameRunnable(final SharedDataBuffer sharedDataBuffer,
                        final ConnectionClient connectionClient,
                        final String gameId,
                        final String playerId) {

        this.sharedDataBuffer = sharedDataBuffer;
        this.connectionClient = connectionClient;
        this.gameId = gameId;
        this.playerId = playerId;
    }

    //--------
    // Methods

    @Override
    public void run() {

        final TigerIslandProtocol tip = new TigerIslandProtocol();
        final TigerIsland tigerIsland = new TigerIsland(gameId, playerId);
        System.out.println("Spinning up a new thread for game " + gameId);
        while (!Thread.currentThread().isInterrupted()) {

            if (sharedDataBuffer.inputReady(gameId)) {
                final String message = sharedDataBuffer.getNextMessage(gameId);
                System.out.println("Thread just poped from the shared buffer" + gameId + " id: " + timeCreated.getTime());

                if (message.contains("WITHIN") && tip.parseGameId(message).equals(gameId)) {

                    WeJustDidThisMove weJustDidThisMove = tigerIsland.doFriendlyMoveAndUpdateMap(tip.getMoveInstruction(message));
                    String moveWeMade = tip.createFriendlyMoveMessageToBeSent(weJustDidThisMove, gameId);
                    try {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        connectionClient.println(moveWeMade);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }

                } else if (message.contains("PLACED") && tip.parseGameId(message).equals(gameId) && !tip.getPlayerIdFromGameCommand(message).equals(playerId)) {
                    tigerIsland.updateMapWithEnemyMove(tip.parseOpponentMove(message));
                }
            }

        }

        System.out.println("Spinning down thread for game " + gameId);
    }
}
