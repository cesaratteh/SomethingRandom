package Everything.game.action.handlers;

import Everything.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SettlementFoundingHandlerTest {

    private Map map;
    private SettlementFoundingHandler foundingHandler;

    @Before
    public void generateMapForTesting() throws Exception{
        map = new Map();
        FirstLevelTileAdditionHandler tileHandler = new FirstLevelTileAdditionHandler();
        foundingHandler = new SettlementFoundingHandler();

        tileHandler.addFirstTileToMap(map);

        MapSpot curr = map.getMiddleHexagonMapSpot().topLeft().left().bottomLeft();

        tileHandler.addTileToMap(
                new Hexagon(Terrain.VOLCANO, 1, 2),
                new Hexagon(Terrain.GRASS, 1, 2),
                new Hexagon(Terrain.LAKE, 1, 2),
                curr.topRight(),
                curr.topRight().topRight(),
                curr.topRight().topLeft(),
                map);

        tileHandler.addTileToMap(
                new Hexagon(Terrain.GRASS, 1, 1),
                new Hexagon(Terrain.ROCK, 1, 1),
                new Hexagon(Terrain.VOLCANO, 1, 1),
                curr,
                curr.left(),
                curr.topLeft(),
                 map);

        tileHandler.addTileToMap(
                new Hexagon(Terrain.VOLCANO, 1, 3),
                new Hexagon(Terrain.LAKE, 1, 3),
                new Hexagon(Terrain.ROCK, 1, 3),
                curr.left().left(),
                curr.topLeft().left(),
                curr.left().left().topLeft(),
                map);
    }

    @Test
    public void testFoundSettlementWithValidMapSpot(){

        Team team = RandomGenerator.generateRandomTeam();
        try {
            foundingHandler.foundSettlement(map.getMiddleHexagonMapSpot().topLeft(),
                    map, team);
        } catch (CannotPerformActionException e) {
            Assert.fail();
        }

        Hexagon settlementHex = map.getHexagon(map.getMiddleHexagonMapSpot().topLeft());

        Assert.assertTrue(!settlementHex.isEmpty()
                && settlementHex.getOccupiedBy() == team);

    }

    @Test
    public void testFoundSettlementWithInvalidMapSpot(){

        Team team = RandomGenerator.generateRandomTeam();
        boolean caughtException = false;

        try {
            foundingHandler.foundSettlement(map.getMiddleHexagonMapSpot(), map, team);
        } catch (CannotPerformActionException e) {
            caughtException = true;
        }
        Assert.assertTrue(caughtException);
    }

}