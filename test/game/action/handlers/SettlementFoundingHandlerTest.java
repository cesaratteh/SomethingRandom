package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SettlementFoundingHandlerTest {

    private Map map;

    @Before
    public void generateMapForTesting(){
        map = new Map();
        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.addHexagon(curr, new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.addHexagon(curr.left(), new Hexagon(Terrain.ROCKY, 1, 1));
        map.addHexagon(curr.topLeft(), new Hexagon(Terrain.VOLCANO, 1, 1));

        map.addHexagon(curr.topRight(), new Hexagon(Terrain.VOLCANO, 1, 2));
        map.addHexagon(curr.topRight().topRight(), new Hexagon(Terrain.GRASSLAND, 1, 2));
        map.addHexagon(curr.topRight().topLeft(), new Hexagon(Terrain.LAKE, 1, 2));

        map.addHexagon(curr.left().left(), new Hexagon(Terrain.VOLCANO, 1, 3));
        map.addHexagon(curr.topLeft().left(), new Hexagon(Terrain.LAKE, 1, 3));
        map.addHexagon(curr.left().left().topLeft(), new Hexagon(Terrain.ROCKY, 1, 3));

    }

    @Test
    public void testGenerateValidMapSpotsForSettlementFounding() {
        Assert.assertEquals(new SettlementFoundingHandler(map).generateValidMapSpotsForSettlementFounding().size(),6);
    }
}