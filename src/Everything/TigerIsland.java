package Everything;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
import Everything.game.action.MapUpdater.Updater;
import Everything.game.action.ai.AIBot;
import Everything.game.action.ai.StupiedBot;
import Everything.game.action.handlers.FirstLevelTileAdditionHandler;
import Everything.game.action.handlers.NukingAndStackingHandler;
import Everything.game.action.handlers.SettlementExpansionHandler;
import Everything.game.action.handlers.SettlementFoundingHandler;
import Everything.game.action.scanners.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.game.action.scanners.Nuking.SettlementAdjacentVolcanoesScanner;
import Everything.game.action.scanners.Nuking.SettlementLevelOneTwoSpotsNukingScanner;
import Everything.game.action.scanners.PlacingOnLevelOne.RandomLevelOneTileScanner;
import Everything.game.action.scanners.PlacingOnLevelOne.SettlementLevelOneTilePlacementScanner;
import Everything.game.action.scanners.SettlementFounding.FoundingNextToSettlementScanner;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.game.action.scanners.settlemenet.expanding.*;
import Everything.models.*;

import java.util.ArrayList;

public class TigerIsland {

    //-----------
    // Attributes

    private String gameId;
    private String friendlyPID;

    private Map map;

    private Player friendly;
    private Player enemy;

    private StupiedBot stupiedBot;
    private AIBot smartBot;
    private Updater enemyMoveUpdater;

    //-------------
    // Constructors

    public TigerIsland(final String gameId, final String friendlyPID) {
        this.gameId = gameId;
        this.friendlyPID = friendlyPID;

        this.map = new Map();

        friendly = new Player(Team.FRIENDLY);
        enemy = new Player(Team.ENEMY);

        this.stupiedBot = new StupiedBot(new OrientationAndVolcanoLocationCalculator(),
                new FirstLevelTileAdditionHandler(),
                new SettlementFoundingHandler(),
                new RandomLevelOneTileScanner(),
                new RandomSettlementFoundingScanner());

        this.smartBot = new AIBot(new SettlementsFactory(),
                new FirstLevelTileAdditionHandler(),
                new NukingAndStackingHandler(),
                new SettlementExpansionHandler(new SettlementExpansionMeeplesCost()),
                new SettlementFoundingHandler(),
                new SettlementAdjacentMapSpotsScanner(),
                new SettlementAdjacentVolcanoesScanner(new SettlementAdjacentMapSpotsScanner()),
                new SettlementLevelOneTwoSpotsNukingScanner(new SettlementAdjacentVolcanoesScanner(new SettlementAdjacentMapSpotsScanner())),
                new RandomLevelOneTileScanner(),
                new SettlementLevelOneTilePlacementScanner(),
                new ExpansionToSpecificTerrainScanner(),
                new SettlementExpansionMeeplesCost(),
                new TigerSpotScanner(new SettlementTouchingExpansionScanner(new SettlementAdjacentMapSpotsScanner())),
                new TotoroSpotScanner(new SettlementTouchingExpansionScanner(new SettlementAdjacentMapSpotsScanner())),
                new FoundingNextToSettlementScanner(new SettlementTouchingExpansionScanner(new SettlementAdjacentMapSpotsScanner())),
                new RandomSettlementFoundingScanner());

        this.enemyMoveUpdater = new Updater(map, Team.ENEMY);

        enemyMoveUpdater.setFirstTile();
    }

    //--------
    // Methods

    public WeJustDidThisMove doFriendlyMoveAndUpdateMap(final MakeMoveInstruction makeMoveInstruction) {
        System.out.println("TigerIsland: Game runnable asked me to play a move");
        WeJustDidThisMove weJustDidThisMove = smartBot.playTurn(makeMoveInstruction, map, friendly);
        if (weJustDidThisMove.getBuildType() == 3) {
            SettlementsFactory settlementsFactory = new SettlementsFactory();
            ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(map, friendly.getTeam());

            MapSpot buildSpot = weJustDidThisMove.getBuildSpot();

            for (Settlement s : settlements) {
                if (s.size() >= 5) {
                    for (MapSpot m : s.getMapSpots()) {
                        for (MapSpot a : m.getAdjacentMapSpots()) {
                            if (a.isEqual(buildSpot)) {
                                System.out.println("IT EQUALS");
                            }
                        }
                    }
                }
            }

        }

        System.out.println(weJustDidThisMove.getTileSpot());
        return weJustDidThisMove;
    }

    public void updateMapWithEnemyMove(final EnemyMove enemyMove) {
        System.out.println("TigerIsland: Game runnable asked me update map with enemy move");
        try {
            System.out.println("enemy move " + enemyMove);
            enemyMoveUpdater.updateMap(enemyMove);
        } catch (Exception e) {
            System.out.println("enemy move updater throws exception");
            e.printStackTrace(System.out);
        }
    }
}
