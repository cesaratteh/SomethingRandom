package Everything.game.action.scanners.PlacingOnLevelOne;

import Everything.game.action.scanners.MapGenerator;
import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.models.Map;
import Everything.models.Team;
import Everything.models.Terrain;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomLevelOneTileScannerTest {

    @Test
    public void circleTest() throws NoValidActionException{

        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.GRASSLAND,
                Terrain.GRASSLAND,
                Terrain.GRASSLAND,
                Team.FRIENDLY,
                Team.FRIENDLY,
                Team.FRIENDLY);

        RandomLevelOneTileScanner randomLevelOneTileScanner = new RandomLevelOneTileScanner();

        randomLevelOneTileScanner.scan(map);


        RandomSettlementFoundingScanner randomSettlementFoundingScanner = new RandomSettlementFoundingScanner();

//        System.out.println(randomLevelOneTileScanner.scan(map));
    }
}