package Everything.game.action.scanners.Nuking;

import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;
import org.junit.Test;

import java.util.ArrayList;

public class SettlementLevelOneTwoSpotsNukingScannerTest {

    @Test(expected = NoValidActionException.class)
    public void scanCoversEntireSettlementTest() throws NoValidActionException{
        final Map map = new Map();

        Hexagon temp = new Hexagon(Terrain.VOLCANO, 1, 1);
        map.setHexagon(map.getMiddleHexagonMapSpot(), temp);

        temp = new Hexagon(Terrain.LAKE, 1, 2);
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(map.getMiddleHexagonMapSpot().topLeft(), temp);
        map.setHexagon(map.getMiddleHexagonMapSpot().topRight(), temp);

        SettlementLevelOneTwoSpotsNukingScanner settlementLevelOneTwoSpotsNukingScanner
                = new SettlementLevelOneTwoSpotsNukingScanner(new SettlementAdjacentVolcanoesScanner(new SettlementAdjacentMapSpotsScanner()));

        SettlementsFactory settlementsFactory = new SettlementsFactory();
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(map, Team.ENEMY);

        System.out.println(settlementLevelOneTwoSpotsNukingScanner.findTileMapSpotToNukeOnSettlment(settlements.get(0),map));
    }

    @Test
    public void scanTest() throws NoValidActionException{
        final Map map = new Map();

        Hexagon temp = new Hexagon(Terrain.LAKE, 1, 1);
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(map.getMiddleHexagonMapSpot(), temp);
        map.setHexagon(map.getMiddleHexagonMapSpot().topLeft(), temp);
        map.setHexagon(map.getMiddleHexagonMapSpot().topRight(), temp);

        temp = new Hexagon(Terrain.VOLCANO, 1, 2);
        map.setHexagon(map.getMiddleHexagonMapSpot().right(), temp);

        SettlementLevelOneTwoSpotsNukingScanner settlementLevelOneTwoSpotsNukingScanner
                = new SettlementLevelOneTwoSpotsNukingScanner(new SettlementAdjacentVolcanoesScanner(new SettlementAdjacentMapSpotsScanner()));

        SettlementsFactory settlementsFactory = new SettlementsFactory();
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(map, Team.ENEMY);

        TileMapSpot result = settlementLevelOneTwoSpotsNukingScanner.findTileMapSpotToNukeOnSettlment(settlements.get(0),map);
        System.out.println(result.getAllMapSpots());
    }
}