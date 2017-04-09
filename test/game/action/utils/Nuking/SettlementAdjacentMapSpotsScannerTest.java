package game.action.utils.Nuking;

import game.action.utils.MapGenerator;
import game.action.utils.SettlementsFactory;
import models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettlementAdjacentMapSpotsScannerTest {

    @Test
    public void scanCircleTest() {
        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.ROCKY, Terrain.ROCKY, Terrain.ROCKY,
                Team.FRIENDLY, Team.ENEMY, Team.FRIENDLY);

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);


        ArrayList<Settlement> friendlySettlements  = settlementsFactory.generateSettlements(Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(Team.ENEMY);


        for (MapSpot s : friendlySettlements.get(0).getMapSpots()) {
            System.out.println(s);
            System.out.println(map.getHexagon(s));
        }

        assertEquals(2, friendlySettlements.get(0).size());
        assertEquals(1, enemySettlements.size());

        SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner
                = new SettlementAdjacentMapSpotsScanner();


//        assertEquals(6, settlementAdjacentMapSpotsScanner.generate(friendlySettlements.get(0), map).size());
        assertEquals(10, settlementAdjacentMapSpotsScanner.generate(friendlySettlements.get(1), map).size());
    }

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

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);

        ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(Team.FRIENDLY);

        ArrayList<Settlement> enemySettlements
                = settlementsFactory.generateSettlements(Team.ENEMY);

        assertEquals(12, settlementAdjacentMapSpotsScanner.generate(friendlySettlements.get(0), map).size());
        assertEquals(10, settlementAdjacentMapSpotsScanner.generate(enemySettlements.get(0), map).size());
    }
}