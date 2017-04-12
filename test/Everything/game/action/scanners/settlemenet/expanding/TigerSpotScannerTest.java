package Everything.game.action.scanners.settlemenet.expanding;

import Everything.game.action.scanners.MapGenerator;
import Everything.game.action.scanners.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TigerSpotScannerTest {
    @Test
    public void scanCircleTest() {
        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.ROCK, Terrain.ROCK, Terrain.ROCK,
                Team.FRIENDLY, Team.ENEMY, Team.FRIENDLY);

        SettlementsFactory settlementsFactory = new SettlementsFactory();


        ArrayList<Settlement> friendlySettlements  = settlementsFactory.generateSettlements(map, Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(map, Team.ENEMY);


        for (MapSpot s : friendlySettlements.get(0).getMapSpots()) {
            System.out.println(s);
            System.out.println(map.getHexagon(s));
        }

        //assertEquals(2, friendlySettlements.get(0).size());
        //assertEquals(1, enemySettlements.size());

        SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner
                = new SettlementAdjacentMapSpotsScanner();


//        assertEquals(6, settlementAdjacentMapSpotsScanner.generate(friendlySettlements.get(0), map).size());
        //assertEquals(10, settlementAdjacentMapSpotsScanner.generate(friendlySettlements.get(1), map).size());
    }
}
