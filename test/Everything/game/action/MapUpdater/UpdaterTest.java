//package Everything.game.action.MapUpdater;
//
//import Everything.game.action.scanners.settlemenet.expanding.ExpansionToSpecificTerrainScanner;
//import Everything.models.Hexagon;
//import Everything.models.Map;
//import Everything.models.MapSpot;
//import Everything.models.Terrain;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class UpdaterTest {
//
//    @Test
//    public void getMapTest() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(new ExpansionToSpecificTerrainScanner());
//        EnemyTeamUpdater.SetFirstTile(map);
//        MapSpot mapspot = new MapSpot(0, 0, 0);
//
//        Hexagon hex1 = new Hexagon(Terrain.VOLCANO, 1, 0);
//        assertEquals(hex1.getTerrainType(), map.getHexagon(mapspot).getTerrainType());
//    }
//
//    @Test
//    public void getMapTest2() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(new ExpansionToSpecificTerrainScanner());
//        EnemyTeamUpdater.SetFirstTile(map);
//        MapSpot mapspot = new MapSpot(0, -1, 1);
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//        assertEquals(hex1.getTerrainType(), map.getHexagon(mapspot).getTerrainType());
//    }
//
//    @Test
//    public void getMapTestSetTile() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 0, -2);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 1, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.VOLCANO, 1, 0);
//        assertEquals(hex1.getTerrainType(), map.getHexagon(mapspot).getTerrainType());
//    }
//
//    @Test
//    public void UpdaterOrientation1() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 1, -3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 1, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//        assertEquals(hex1.getTerrainType(), map.getHexagon(mapspotExpand).getTerrainType());
//    }
//
//    @Test
//    public void UpdaterLevel() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 1, -3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 1, mapspotExpand);
//        EnemyTeamUpdater.executeEnemyMove(2, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 1, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//        assertEquals(2, map.getHexagon(mapspotExpand).getLevel());
//    }
//
//    @Test
//    public void UpdaterFoundSettlement() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 1, -3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 1, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//        assertEquals(1, map.getHexagon(mapspotExpand).getNumberOfMeeples());
//    }
//
//    @Test
//    public void UpdaterAddTotoro() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 1, -3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 3, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//        assertEquals(true, map.getHexagon(mapspotExpand).isHasTotoro());
//    }
//
//    @Test
//    public void UpdaterAddTiger() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 1, -3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 4, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//        assertEquals(true, map.getHexagon(mapspotExpand).isHasTiger());
//    }
//
//    @Test
//    public void UpdaterExpansion() {
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(2, 0, -2);
//        MapSpot mapspotExpand = new MapSpot(2, 1, -3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(1, Terrain.GRASS, Terrain.JUNGLE, mapspot, 1, 1, mapspotExpand);
//
//        mapspot = new MapSpot(-2, 0, 2);
//        mapspotExpand = new MapSpot(-3, 0, 3);
//
//
//        EnemyTeamUpdater.executeEnemyMove(2, Terrain.GRASS, Terrain.JUNGLE, mapspot, 4, 1, mapspotExpand);
//
//
//        Hexagon hex1 = new Hexagon(Terrain.GRASS, 1, 0);
//
//        EnemyTeamUpdater.EnemyMoveExpand(2, Terrain.GRASS, Terrain.JUNGLE, mapspot, 4, 1, mapspotExpand, Terrain.GRASS);
//        assertEquals(1, map.getHexagon(mapspotExpand).getNumberOfMeeples());
//    }
//}