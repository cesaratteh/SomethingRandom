package Everything;

import Everything.Server.MoveObjects.*;
import Everything.TigerIsland;

import java.util.concurrent.ConcurrentLinkedQueue;

public class GameRunnable implements Runnable {

    //-----------
    // Attributes

    private ConcurrentLinkedQueue<MoveData> threadQueue;
    private String gameID;
    private String playerID;

    //-------------
    // Constructors

    public GameRunnable(final ConcurrentLinkedQueue<MoveData> threadQueue,
                      final String gameID,
                      final String playerID) {

        this.threadQueue = threadQueue;
        this.gameID = gameID;
        this.playerID = playerID;
    }

    //--------
    // Methods

    @Override
    public void run() {

        final TigerIsland tigerIsland = new TigerIsland(gameID, playerID);

        while (true) {

            if (!threadQueue.isEmpty() && threadQueue.peek().consumer == MoveData.Consumer.THREAD) {

                final MoveData moveData = threadQueue.poll();

                if (moveData.gameOver) {
                    break;
                }

                if (moveData.move instanceof EnemyMove) {
                    tigerIsland.updateMapWithEnemyMove( (EnemyMove) moveData.move);
                } else {
                    final WeJustDidThisMove weJustDidThisMove
                            = tigerIsland.doFriendlyMoveAndUpdateMap((MakeMoveInstruction) moveData.move);

                    threadQueue.add(new MoveData(false, weJustDidThisMove, MoveData.Consumer.CLIENT));
                }
            }
        }
    }
}
