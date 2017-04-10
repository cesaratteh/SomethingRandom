package Everything;

import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
import Everything.game.action.ai.StupiedBot;
import Everything.game.action.handlers.FirstLevelTileAdditionHandler;
import Everything.game.action.handlers.SettlementFoundingHandler;
import Everything.game.action.scanners.PlacingOnLevelOne.RandomLevelOneTileScanner;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.models.Map;
import Everything.models.Player;
import Everything.models.Team;
import org.junit.Test;

import java.util.Random;

public class StupiedBotIntegrationTest {

    @Test
    public void runGame() {

        final Player friendly = new Player(Team.FRIENDLY);
        final Player enemy = new Player(Team.ENEMY);

        final Map map = new Map();

        FirstLevelTileAdditionHandler firstLevelTileAdditionHandler = new FirstLevelTileAdditionHandler();
        firstLevelTileAdditionHandler.addFirstTileToMap(map);

        final StupiedBot friendlyBot = new StupiedBot(new OrientationAndVolcanoLocationCalculator(),
                new FirstLevelTileAdditionHandler(),
                new SettlementFoundingHandler(),
                new RandomLevelOneTileScanner(),
                new RandomSettlementFoundingScanner());

        final StupiedBot enemyBot = new StupiedBot(new OrientationAndVolcanoLocationCalculator(),
                new FirstLevelTileAdditionHandler(),
                new SettlementFoundingHandler(),
                new RandomLevelOneTileScanner(),
                new RandomSettlementFoundingScanner());

        for(int i = 0; i < 4; i++) {
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
        return new MakeMoveInstruction("gameId123", moveNumber, generateRandomTile());
    }

    private String generateRandomTile() {
        final String[] terrainTypes = {"LAKE", "JUNGLE", "GRASSLAND", "ROCKY"};

        final Random random = new Random();
        int firstTerrainIndex = random.nextInt(4);
        int secondTerrainIndex = random.nextInt(4);

        return terrainTypes[firstTerrainIndex] + "+" + terrainTypes[secondTerrainIndex];
    }
}
