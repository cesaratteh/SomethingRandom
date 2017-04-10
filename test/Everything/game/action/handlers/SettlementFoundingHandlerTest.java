package Everything.game.action.handlers;

import Everything.models.Hexagon;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Terrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SettlementFoundingHandlerTest {

    private Map map;

    @Before
    public void generateMapForTesting(){
        map = new Map();
        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.setHexagon(curr, new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.setHexagon(curr.left(), new Hexagon(Terrain.ROCKY, 1, 1));
        map.setHexagon(curr.topLeft(), new Hexagon(Terrain.VOLCANO, 1, 1));

        map.setHexagon(curr.topRight(), new Hexagon(Terrain.VOLCANO, 1, 2));
        map.setHexagon(curr.topRight().topRight(), new Hexagon(Terrain.GRASSLAND, 1, 2));
        map.setHexagon(curr.topRight().topLeft(), new Hexagon(Terrain.LAKE, 1, 2));

        map.setHexagon(curr.left().left(), new Hexagon(Terrain.VOLCANO, 1, 3));
        map.setHexagon(curr.topLeft().left(), new Hexagon(Terrain.LAKE, 1, 3));
        map.setHexagon(curr.left().left().topLeft(), new Hexagon(Terrain.ROCKY, 1, 3));

    }
}