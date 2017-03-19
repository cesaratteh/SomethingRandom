package models;

import models.Hexagon;
import models.Player;
import models.Terrain;
import models.Tile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoundingSettlementTests {
    private Tile tile;
    private Player player1;
    private Hexagon target;
    @Before
    public void setupTile(){
        tile = new Tile(1);
    }
    public void setupHexagon(){
        Hexagon target = new Hexagon(tile, false);
    }
    public void playerFound(){
        player1.foundSettlement(target);
    }
    @Test
    public void testSettlementIsOnNonVolcano(){             //foundSettlement works for both totoro and meeple
        Assert.assertNotEquals(Terrain.Volcano, target.getTerrain());
    }
    @Test
    public void testFoundingOnLevelOne(){
        Assert.assertEquals(1,target.getTileContainedIn().getLevel());
    }
    @Test
    public void testGameOver(){
        int total = player1.getMeepleCount() + player1.getTotoroCount();
        Assert.assertEquals(0,total);
    }
}
