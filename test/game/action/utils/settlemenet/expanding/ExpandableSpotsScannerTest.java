package game.action.utils.settlemenet.expanding;

import game.action.utils.NoValidActionException;
import game.action.utils.SettlementsFactory;
import models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExpandableSpotsScannerTest {
    @Test
    public void testTest() throws NoValidActionException {
        final Map map = new Map();

        Hexagon hexagon = new Hexagon(Terrain.GRASSLAND, 1, 1);
        hexagon.addMeeplesAccordingToLevel(Team.FRIENDLY);

        map.setHexagon(new MapSpot(0, 0, 0), hexagon);
        map.setHexagon(new MapSpot(1, -1, 0), new Hexagon(Terrain.GRASSLAND, 1, 1));

        ExpandableSpotsScanner expandableSpotsScanner = new ExpandableSpotsScanner();

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(Team.FRIENDLY);


        ArrayList<MapSpot> expandableSpots = expandableSpotsScanner.scan(settlements.get(0), map);

        for (MapSpot mapSpot : expandableSpots) {
            System.out.println(expandableSpots);
        }
    }
}