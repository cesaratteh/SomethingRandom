package game.action.MapUpdater;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdaterTest {

    @Test
    public void getMapTest() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();
        MapSpot mapspot = new MapSpot(0,0,0);

        Hexagon hex1 = new Hexagon(Terrain.VOLCANO,1,0);
        assertEquals(hex1.getTerrainType(),map.getHexagon(mapspot).getTerrainType());
    }

    @Test
    public void getMapTest2() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();
        MapSpot mapspot = new MapSpot(0,-1,1);

        Hexagon hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        assertEquals(hex1.getTerrainType(),map.getHexagon(mapspot).getTerrainType());
    }

    @Test
    public void getMapTestSetTile() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();

        MapSpot mapspot = new MapSpot(2,0,-2);
        MapSpot mapspotExpand = new MapSpot(2,0,-2);


        EnemyTeamUpdater.EnemyMove(1,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,1,mapspotExpand );


        Hexagon hex1 = new Hexagon(Terrain.VOLCANO,1,0);
        assertEquals(hex1.getTerrainType(),map.getHexagon(mapspot).getTerrainType());
    }

    @Test
    public void UpdaterOrientation1() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();

        MapSpot mapspot = new MapSpot(2,0,-2);
        MapSpot mapspotExpand = new MapSpot(2,1,-3);


        EnemyTeamUpdater.EnemyMove(1,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,1,mapspotExpand );


        Hexagon hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        assertEquals(hex1.getTerrainType(),map.getHexagon(mapspotExpand).getTerrainType());
    }

    @Test
    public void UpdaterLevel() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();

        MapSpot mapspot = new MapSpot(2,0,-2);
        MapSpot mapspotExpand = new MapSpot(2,1,-3);


        EnemyTeamUpdater.EnemyMove(1,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,1,mapspotExpand );
        EnemyTeamUpdater.EnemyMove(2,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,1,mapspotExpand );


        Hexagon hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        assertEquals(2,map.getHexagon(mapspotExpand).getLevel());
    }

    @Test
    public void UpdaterFoundSettlement() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();

        MapSpot mapspot = new MapSpot(2,0,-2);
        MapSpot mapspotExpand = new MapSpot(2,1,-3);


        EnemyTeamUpdater.EnemyMove(1,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,1,mapspotExpand );


        Hexagon hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        assertEquals(1,map.getHexagon(mapspotExpand).getNumberOfMeeples());
    }

    @Test
    public void UpdaterAddTotoro() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();

        MapSpot mapspot = new MapSpot(2,0,-2);
        MapSpot mapspotExpand = new MapSpot(2,1,-3);


        EnemyTeamUpdater.EnemyMove(1,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,3,mapspotExpand );


        Hexagon hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        assertEquals(true,map.getHexagon(mapspotExpand).isHasTotoro());
    }

    @Test
    public void UpdaterAddTiger() {
        final Map map = new Map();
        Updater EnemyTeamUpdater = new Updater(map);
        EnemyTeamUpdater.SetFirstTile();

        MapSpot mapspot = new MapSpot(2,0,-2);
        MapSpot mapspotExpand = new MapSpot(2,1,-3);


        EnemyTeamUpdater.EnemyMove(1,Terrain.GRASSLAND,Terrain.JUNGLE, mapspot,1,4,mapspotExpand );


        Hexagon hex1 = new Hexagon(Terrain.GRASSLAND,1,0);
        assertEquals(true,map.getHexagon(mapspotExpand).isHasTiger());
    }
}
