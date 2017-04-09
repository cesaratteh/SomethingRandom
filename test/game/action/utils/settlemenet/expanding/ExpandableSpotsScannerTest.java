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

        Hexagon temp;

        Hexagon hexagon = new Hexagon(Terrain.GRASSLAND, 1, 1);
        hexagon.addMeeplesAccordingToLevel(Team.FRIENDLY);

        //center
        map.setHexagon(new MapSpot(0, 0, 0), hexagon);
        //top ri
        map.setHexagon(new MapSpot(-1, 1, 0), new Hexagon(Terrain.VOLCANO, 1, 1));
        //right
        map.setHexagon(new MapSpot(0, 1, -1), new Hexagon(Terrain.JUNGLE, 1, 1));
        //bot ri
        map.setHexagon(new MapSpot(1, 0, -1), new Hexagon(Terrain.GRASSLAND, 1, 1));
        //bot le
        map.setHexagon(new MapSpot(1, -1, 0), new Hexagon(Terrain.ROCKY, 1, 1));
        //left
        map.setHexagon(new MapSpot(0, -1, 1), new Hexagon(Terrain.LAKE, 1, 1));
        //top le
        map.setHexagon(new MapSpot(-1, 0, 1), new Hexagon(Terrain.LAKE, 1, 1));
        //outside circle top right
        map.setHexagon(new MapSpot(-1, 2, -1), new Hexagon(Terrain.GRASSLAND, 1, 1));

        map.setHexagon(new MapSpot(0, -2, 2), new Hexagon(Terrain.GRASSLAND, 1, 1));
        map.setHexagon(new MapSpot(1, -2, 1), new Hexagon(Terrain.GRASSLAND, 1, 1));

        temp = new Hexagon(Terrain.GRASSLAND, 1, 1);
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(new MapSpot(1, 1, -2), temp);


        ExpandableSpotsScanner expandableSpotsScanner = new ExpandableSpotsScanner();

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(Team.FRIENDLY);


        ArrayList<MapSpot> expandableSpots = expandableSpotsScanner.scan(settlements.get(0), map);

        for (MapSpot mapSpot : expandableSpots) {
            System.out.println(expandableSpots);
        }
    }
}