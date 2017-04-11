package Everything;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.game.action.MapUpdater.Updater;
import Everything.game.action.ai.StupiedBot;
import Everything.models.Map;
import Everything.models.Player;
import Everything.models.Team;

public class TigerIsland {

    //-----------
    // Attributes

    private String gameId;
    private String friendlyPID;

    private Map map;

    private Player friendly;
    private Player enemy;

    private StupiedBot friendlyAI;
    private Updater enemyMoveUpdater;

    //-------------
    // Constructors

    public TigerIsland(final String gameId, final String friendlyPID) {
        this.gameId = gameId;
        this.friendlyPID = friendlyPID;

        this.map = new Map();
        enemyMoveUpdater.setFirstTile();

        friendly = new Player(Team.FRIENDLY);
        enemy = new Player(Team.ENEMY);

        StupiedBot stupiedBot = new StupiedBot();
        this.enemyMoveUpdater = new Updater();

        this.friendlyAI = stupiedBot;
    }

    //--------
    // Methods

    public WeJustDidThisMove doFriendlyMoveAndUpdateMap(final MakeMoveInstruction makeMoveInstruction) {
        // Call OUR AI
        // Get the MapObject from our AI
        // Push it to the ConnectionClient Queue
        WeJustDidThisMove weJustDidThisMove = friendlyAI.playTurn(makeMoveInstruction, map, friendly);
        return weJustDidThisMove;
    }

    public void updateMapWithEnemyMove(final EnemyMove enemyMove) {
        enemyMoveUpdater.updateMap(enemyMove);
    }
}
