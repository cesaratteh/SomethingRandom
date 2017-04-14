package Everything.game.action.scanners.SettlementFounding;

import Everything.game.action.MapUpdater.Updater;
import Everything.game.action.scanners.MapGenerator;
import Everything.models.Map;
import Everything.models.Team;
import Everything.models.Terrain;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomSettlementFoundingScannerTest {
    @Test
    public void scanTest() throws Exception {

        Map map = new Map();
        Updater updater = new Updater(map, Team.ENEMY);
        updater.setFirstTile();

        RandomSettlementFoundingScanner randomSettlementFoundingScanner = new RandomSettlementFoundingScanner();

        System.out.println(randomSettlementFoundingScanner.scan(map));
    }
}