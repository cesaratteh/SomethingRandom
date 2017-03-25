package game.action.handlers.utils;

import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettlementsFactoryTest {

    @Test
    public void generateSettlementsTest() {
        Map map = new Map();
        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.addHexagon(curr, new Hexagon(Team.ENEMY, Terrain.GRASSLAND, 1, 1));
        map.addHexagon(curr.topLeft(), new Hexagon(Team.FRIENDLY, Terrain.GRASSLAND, 1, 1));
        map.addHexagon(curr.topRight(), new Hexagon(Team.FRIENDLY, Terrain.LAKE, 1, 1));

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);

        assertEquals(1, settlementsFactory.generateSettlements(Team.ENEMY).size());
        assertEquals(2, settlementsFactory.generateSettlements(Team.FRIENDLY).size());
    }
}