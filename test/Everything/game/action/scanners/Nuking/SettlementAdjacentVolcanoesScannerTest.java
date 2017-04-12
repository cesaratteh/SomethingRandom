package Everything.game.action.scanners.Nuking;

import Everything.game.action.scanners.MapGenerator;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SettlementAdjacentVolcanoesScannerTest {

    @Test
    public void scanTest() {

        final Map map = MapGenerator
                .generateCircleWithOutsideLine(
                        Terrain.ROCK, Terrain.VOLCANO, Terrain.LAKE,
                        Team.FRIENDLY, Team.ENEMY, Team.FRIENDLY);

        map.setHexagon(map.getMiddleHexagonMapSpot().bottomRight().bottomRight().bottomRight(), new Hexagon(Terrain.VOLCANO, 1, 1));

        final SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner
                = new SettlementAdjacentVolcanoesScanner(new SettlementAdjacentMapSpotsScanner());

        final SettlementsFactory settlementsFactory = new SettlementsFactory();
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(map, Team.FRIENDLY);

        assertEquals(true, settlementAdjacentVolcanoesScanner.scan(settlements.get(0), map).get(0).isEqual(new MapSpot(1, -2, 1)));
    }
}