package Everything.Server;
import Everything.TigerIsland;

import java.util.concurrent.ConcurrentLinkedQueue;

public class GameThread extends Thread {

        ConcurrentLinkedQueue<FriendlyMove> friendlyMoves1;
        ConcurrentLinkedQueue<EnemyMove> enemyMoves1;
        ConcurrentLinkedQueue<FriendlyMove> friendlyMoves2;
        ConcurrentLinkedQueue<EnemyMove> enemyMoves2;
        String gameID;
        String playerID;


        public GameThread(ConcurrentLinkedQueue<EnemyMove> p , ConcurrentLinkedQueue<FriendlyMove> q,ConcurrentLinkedQueue<EnemyMove> r ,ConcurrentLinkedQueue<EnemyMove> s,  String gameID, String playerID) {
            this.friendlyMoves1 = q;
            this.enemyMoves1 = p;
            this.friendlyMoves2 = r;
            this.enemyMoves2 = s;
            this.gameID = gameID;
            this.playerID = playerID;
        }

        @Override
        public void run() {
            TigerIsland tigerIsland = new TigerIsland(gameID,playerID);

            while(true) {                                           //while still in game
             if()   tigerIsland.doFriendlyMoveAndUpdateMap();          //if client pushed something on the queue
                tigerIsland.getEnemyMoveAndUpdateMap();
            }
            super.run();
        }

    }
}
