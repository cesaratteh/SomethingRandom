package Everything.game.action.scanners.PlacingOnLevelOne;

import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettlementLevelOneTilePlacementScannerTest {

    @Test
    public void scanTest() throws NoValidActionException {
        final Map map = new Map();

        Hexagon temp = new Hexagon(Terrain.LAKE, 1, 2);
        temp.addMeeplesAccordingToLevel(Team.FRIENDLY);
        map.setHexagon(map.getMiddleHexagonMapSpot(), temp);

        SettlementLevelOneTilePlacementScanner settlementLevelOneTilePlacementScanner
                = new SettlementLevelOneTilePlacementScanner();

        SettlementsFactory settlementsFactory = new SettlementsFactory();
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(map, Team.FRIENDLY);

        System.out.println(settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, settlements.get(0)).getAllMapSpots());
    }
}