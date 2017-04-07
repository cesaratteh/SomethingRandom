package game.action.handlers.utils;

import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettlementsFactoryTest {

    @Test
    public void generateSettlementsTest() {
        Map map = new Map();
        Player player = new Player(RandomGenerator.generateRandomTeam());
        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.setHexagon(curr, new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.setHexagon(curr.topLeft(), new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.setHexagon(curr.topRight(), new Hexagon(Terrain.LAKE, 1, 1));

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);

        assertEquals(2, settlementsFactory.generateSettlements(Team.NONE).size());
    }
}