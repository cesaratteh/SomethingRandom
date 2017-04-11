package Everything.game.action.scanners.Nuking;

import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettlementAdjacentMapSpotsScannerTest {

    @Test
    public void scanBlobShapeTest(){
        final Map map = new Map();

        Hexagon h1 = new Hexagon(Terrain.GRASSLAND, 1, 1);
        Hexagon h2 = new Hexagon(Terrain.GRASSLAND, 1, 1);
        Hexagon h3 = new Hexagon(Terrain.GRASSLAND, 1, 1);

        h1.addMeeplesAccordingToLevel(Team.FRIENDLY);
        h2.addMeeplesAccordingToLevel(Team.FRIENDLY);
        h3.addTotoro(Team.FRIENDLY);

        map.setHexagon(map.getMiddleHexagonMapSpot(), h1);
        map.setHexagon(map.getMiddleHexagonMapSpot().topRight(), h2);
        map.setHexagon(map.getMiddleHexagonMapSpot().topLeft(), h3);

        Hexagon temp = new Hexagon(Terrain.GRASSLAND, 1, 2);
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(map.getMiddleHexagonMapSpot().right(), temp);

        temp = new Hexagon(Terrain.GRASSLAND, 1, 2);
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(map.getMiddleHexagonMapSpot().right().right(), temp);

        SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner
                = new SettlementAdjacentMapSpotsScanner();

        SettlementsFactory settlementsFactory = new SettlementsFactory();

        ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(map, Team.FRIENDLY);

        ArrayList<Settlement> enemySettlements
                = settlementsFactory.generateSettlements(map, Team.ENEMY);

        assertEquals(9, settlementAdjacentMapSpotsScanner.generate(friendlySettlements.get(0), map).size());
        assertEquals(8, settlementAdjacentMapSpotsScanner.generate(enemySettlements.get(0), map).size());
    }
}