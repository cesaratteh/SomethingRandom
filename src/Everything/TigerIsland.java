package Everything;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
import Everything.game.action.MapUpdater.Updater;
import Everything.game.action.ai.StupiedBot;
import Everything.game.action.ai.TestingBot;
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

    private TestingBot testingBot;
    private Updater enemyMoveUpdater;

    //-------------
    // Constructors

    public TigerIsland(final String gameId, final String friendlyPID) {
        this.gameId = gameId;
        this.friendlyPID = friendlyPID;

        this.map = new Map();

        friendly = new Player(Team.FRIENDLY);
        enemy = new Player(Team.ENEMY);

        TestingBot testingBot = new TestingBot(new OrientationAndVolcanoLocationCalculator(),
                new FirstLevelTileAdditionHandler(),
                new SettlementFoundingHandler(),
                new RandomLevelOneTileScanner(),
                new RandomSettlementFoundingScanner());

        this.enemyMoveUpdater = new Updater(map);
        enemyMoveUpdater.setFirstTile();

        this.testingBot = testingBot;
    }

    //--------
    // Methods

    public WeJustDidThisMove doFriendlyMoveAndUpdateMap(final MakeMoveInstruction makeMoveInstruction) {
        System.out.println("TigerIsland: Game runnable asked me to play a move");
        WeJustDidThisMove weJustDidThisMove = testingBot.playTurn(makeMoveInstruction, map, friendly);
        return weJustDidThisMove;
    }

    public void updateMapWithEnemyMove(final EnemyMove enemyMove) {
        System.out.println("TigerIsland: Game runnable asked me update map with enemy move");
        try {
            enemyMoveUpdater.updateMap(enemyMove);
        } catch (Exception e) {
            System.out.println("enemy move updater throws exception");
        }
    }
}
