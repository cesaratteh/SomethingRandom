package Everything;

import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
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
import Everything.models.Map;
import Everything.models.Player;
import Everything.models.Team;
import Everything.models.Terrain;
import org.junit.Test;

import java.util.Random;

public class SmartBotIntegrationTest {

    @Test
    public void runGame() {

        final Player friendly = new Player(Team.FRIENDLY);
        final Player enemy = new Player(Team.ENEMY);

        final Map map = new Map();

        FirstLevelTileAdditionHandler firstLevelTileAdditionHandler = new FirstLevelTileAdditionHandler();
        firstLevelTileAdditionHandler.addFirstTileToMap(map);

        final AIBot friendlyBot = new AIBot(new SettlementsFactory(),
                new FirstLevelTileAdditionHandler(),
                new NukingAndStackingHandler(),
                new SettlementExpansionHandler(),
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

        final AIBot enemyBot = new AIBot(new SettlementsFactory(),
                new FirstLevelTileAdditionHandler(),
                new NukingAndStackingHandler(),
                new SettlementExpansionHandler(),
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

        for(int i = 0; i < 15; i++) {
            WeJustDidThisMove friendlyMove = friendlyBot.playTurn(generateRandomInstruction(), map, friendly);
            WeJustDidThisMove enemyMove = enemyBot.playTurn(generateRandomInstruction(), map, enemy);

            System.out.println("FRIENDLY:");
            System.out.println(friendlyMove);
            System.out.println("ENEMY:");
            System.out.println(enemyMove);
            System.out.println("---------------------------------------");
            System.out.println("");
        }
    }

    private static int moveNumber = 0;
    private MakeMoveInstruction generateRandomInstruction() {
        return new MakeMoveInstruction("gameId123", moveNumber, generateRandomTerrain(), generateRandomTerrain());
    }

    private Terrain generateRandomTerrain() {


        final String[] terrainTypes = {"LAKE", "JUNGLE", "GRASS", "ROCK"};
        Random random = new Random();

        return Terrain.valueOf(terrainTypes[random.nextInt(4)]);
    }
}
