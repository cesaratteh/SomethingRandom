package Everything;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
import Everything.game.action.MapUpdater.Updater;
import Everything.game.action.ai.StupiedBot;
import Everything.game.action.handlers.FirstLevelTileAdditionHandler;
import Everything.game.action.handlers.SettlementFoundingHandler;
import Everything.game.action.scanners.PlacingOnLevelOne.RandomLevelOneTileScanner;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
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

        StupiedBot stupiedBot = new StupiedBot(new OrientationAndVolcanoLocationCalculator(),
                new FirstLevelTileAdditionHandler(),
                new SettlementFoundingHandler(),
                new RandomLevelOneTileScanner(),
                new RandomSettlementFoundingScanner());

        this.enemyMoveUpdater = new Updater(map);

        this.friendlyAI = stupiedBot;
    }

    //--------
    // Methods

    public WeJustDidThisMove doFriendlyMoveAndUpdateMap(final MakeMoveInstruction makeMoveInstruction) {
        WeJustDidThisMove weJustDidThisMove = friendlyAI.playTurn(makeMoveInstruction, map, friendly);
        return weJustDidThisMove;
    }

    public void updateMapWithEnemyMove(final EnemyMove enemyMove) {
        try {
            enemyMoveUpdater.updateMap(enemyMove);
        } catch (Exception e) {
            System.out.println("enemy move updater throws exception");
        }
    }
}
