package Everything.Server;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.Move;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.TigerIsland;

import java.util.concurrent.ConcurrentLinkedQueue;

public class GameRunnable implements Runnable {

    //-----------
    // Attributes

    private ConcurrentLinkedQueue<WeJustDidThisMove> threadToClientQueue;
    private ConcurrentLinkedQueue<Move> clientToThreadQueue;
    private String gameID;
    private String playerID;

    //-------------
    // Constructors

    public GameRunnable(final ConcurrentLinkedQueue<WeJustDidThisMove> threadToClientQueue,
                      final ConcurrentLinkedQueue<Move> clientToThreadQueue,
                      final String gameID,
                      final String playerID) {

        this.threadToClientQueue = threadToClientQueue;
        this.clientToThreadQueue = clientToThreadQueue;
        this.gameID = gameID;
        this.playerID = playerID;
    }

    //--------
    // Methods

    @Override
    public void run() {

        final TigerIsland tigerIsland = new TigerIsland(gameID, playerID);

        while (true) {

            if (!clientToThreadQueue.isEmpty()) {
                final Move move = clientToThreadQueue.poll();

                if (move instanceof EnemyMove) {
                    tigerIsland.updateMapWithEnemyMove( (EnemyMove) move);
                    // TODO: 4/10/2017 if move ends the game, end it
                } else {
                    final WeJustDidThisMove weJustDidThisMove
                            = tigerIsland.doFriendlyMoveAndUpdateMap((MakeMoveInstruction) move);

                    threadToClientQueue.add(weJustDidThisMove);

                    // TODO: 4/10/2017 if Move ends game, end the game
                }
            }
        }
    }
}
