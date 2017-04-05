package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FirstLevelTileAdditionHandlerTest {

    private Map map;
    private Hexagon h1;
    private Hexagon h2;
    private Hexagon h3;
    private Hexagon h4;
    private Hexagon h5;

    private MapSpot m1;
    private MapSpot m2;
    private MapSpot m3;
    private MapSpot m4;
    private MapSpot m5;

    private FirstLevelTileAdditionHandler handler;

    @Before
    public void initializeBeforeFirstTileTests(){
        map = new Map();
        h1 = new Hexagon(Terrain.LAKE, 1, 0);
        h2 = new Hexagon(Terrain.ROCKY, 1, 0);
        h3 = new Hexagon(Terrain.VOLCANO, 1, 0);
        h4 = new Hexagon(Terrain.GRASSLAND, 1, 0);
        h5 = new Hexagon(Terrain.JUNGLE, 1, 0);

        m3 = map.getMiddleHexagonMapSpot();
        m1 = m3.topLeft();
        m2 = m3.topRight();
        m4 = m3.bottomLeft();
        m5 = m3.bottomRight();

        handler = new FirstLevelTileAdditionHandler(map);
    }

    public void placeFirstTile(){
        initializeBeforeFirstTileTests();
        handler.addFirstTileToMap(h1,h2,h3,h4,h5,m1,m2,m3,m4,m5);
    }

    @Test
    public void testAddValidFirstTileToMap(){

        handler.addFirstTileToMap(h1,h2,h3,h4,h5,m1,m2,m3,m4,m5);

        Assert.assertTrue(map.getHexagon(map.getMiddleHexagonMapSpot().topLeft()).isEqual(h1)
                && map.getHexagon(map.getMiddleHexagonMapSpot().topRight()).isEqual(h2)
                && map.getHexagon(map.getMiddleHexagonMapSpot()).isEqual(h3)
                && map.getHexagon(map.getMiddleHexagonMapSpot().bottomLeft()).isEqual(h4)
                && map.getHexagon(map.getMiddleHexagonMapSpot().bottomRight()).isEqual(h5));

    }

    @Test
    public void testAddDuplicateTerrainFirstTileToMap(){

        h2 = new Hexagon(Terrain.LAKE,1,0);

        try {
            handler.addFirstTileToMap(h1, h2, h3, h4, h5, m1, m2, m3, m4, m5);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad First Tile to be Placed");
        }

    }

    @Test
    public void testAddBadPlacementFirstTileToMap(){

        m1 = m3.left();

        try {
            handler.addFirstTileToMap(h1, h2, h3, h4, h5, m1, m2, m3, m4, m5);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad First Tile Placement");
        }

    }

    public void initTile(){
        placeFirstTile();
        MapSpot middle = map.getMiddleHexagonMapSpot();
        m1 = middle.left();
        m2 = m1.topLeft();
        m3 = m1.left();
        h1 = new Hexagon(Terrain.JUNGLE, 1, 1);
        h2 = new Hexagon(Terrain.VOLCANO, 1, 1);
        h3 = new Hexagon(Terrain.LAKE, 1, 1);
    }

    @Test
    public void testValidTilePlacement(){
        initTile();

        handler.addTileToMap(h1,h2,h3,m1,m2,m3);

        Assert.assertTrue(map.getHexagon(m1).isEqual(h1)
                && map.getHexagon(m2).isEqual(h2)
                && map.getHexagon(m3).isEqual(h3));

    }

    @Test
    public void testInvalidTilePlacement(){
        initTile();

        m3 = m1.bottomLeft();

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad Tile Placement");
        }
    }

    @Test
    public void testTooManyVolcanos(){
        initTile();

        h1 = new Hexagon(Terrain.VOLCANO, 1, 1);

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad Tile to be placed");
        }
    }

    @Test
    public void testNoVolcanos(){
        initTile();

        h2 = new Hexagon(Terrain.ROCKY, 1, 1);

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad Tile to be placed");
        }
    }

    @Test
    public void testDifferentTileID(){
        initTile();

        h1 = new Hexagon(Terrain.GRASSLAND, 1, 2);
        h3 = new Hexagon(Terrain.LAKE, 1, 3);

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad Tile to be placed");
        }
    }

    @Test
    public void testPlaceOnExistingHexagon(){
        initTile();

        m3 = m1.topRight();

        try {
            handler.addTileToMap(h1, h2, h3, m1, m2, m3);
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad Tile Placement");
        }
    }

}