package Everything.game.action.scanners;

import Everything.models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettlementsFactoryTest {


    @Test
    public void scanCircleTest1() {
        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.GRASS, Terrain.GRASS, Terrain.GRASS,
                Team.FRIENDLY, Team.ENEMY, Team.ENEMY);

        SettlementsFactory settlementsFactory = new SettlementsFactory();

        ArrayList<Settlement> friendlySettlements  = settlementsFactory.generateSettlements(map, Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(map, Team.ENEMY);


        assertEquals(6, friendlySettlements.get(0).size());
        assertEquals(1, enemySettlements.get(0).size());
        assertEquals(2, enemySettlements.get(1).size());
    }

    @Test
    public void scanCircleTest2() {
        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.GRASS, Terrain.GRASS, Terrain.GRASS,
                Team.FRIENDLY, Team.ENEMY, Team.FRIENDLY);

        SettlementsFactory settlementsFactory = new SettlementsFactory();


        ArrayList<Settlement> friendlySettlements  = settlementsFactory.generateSettlements(map, Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(map, Team.ENEMY);


        assertEquals(7, friendlySettlements.get(0).size());
        assertEquals(2, enemySettlements.get(0).size());
    }

    @Test
    public void scanCircleTest3() {
        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.GRASS, Terrain.ROCKY, Terrain.GRASS,
                Team.FRIENDLY, Team.FRIENDLY, Team.FRIENDLY);

        SettlementsFactory settlementsFactory = new SettlementsFactory();


        ArrayList<Settlement> friendlySettlements  = settlementsFactory.generateSettlements(map, Team.FRIENDLY);

        assertEquals(9, friendlySettlements.get(0).size());
    }

    @Test
    public void generateSettlementsTest() {
        final Map map = new Map();

        Hexagon temp;

        temp = new Hexagon(Terrain.GRASS, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addTotoro(Team.FRIENDLY);
        map.setHexagon(new MapSpot(0, 0, 0), temp);

        temp = new Hexagon(Terrain.GRASS, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addTiger(Team.FRIENDLY);
        map.setHexagon(new MapSpot(1, -1, 0), temp);

        temp = new Hexagon(Terrain.GRASS, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addMeeplesAccordingToLevel(Team.FRIENDLY);
        map.setHexagon(new MapSpot(2, -2, 0), temp);

        temp = new Hexagon(Terrain.JUNGLE, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addMeeplesAccordingToLevel(Team.FRIENDLY);
        map.setHexagon(new MapSpot(3, -3, 0), temp);

        temp = new Hexagon(Terrain.GRASS, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(new MapSpot(4, -4, 0), temp);


        SettlementsFactory settlementsFactory = new SettlementsFactory();

        assertEquals(1, settlementsFactory.generateSettlements(map, Team.FRIENDLY).size());
        assertEquals(4, settlementsFactory.generateSettlements(map, Team.FRIENDLY).get(0).size());

        assertEquals(1, settlementsFactory.generateSettlements(map, Team.ENEMY).size());
        assertEquals(1, settlementsFactory.generateSettlements(map, Team.ENEMY).get(0).size());
    }

//    @Test
//    public void SettlementTest1(){
//
//        final Map map = new Map();
//        Updater EnemyTeamUpdater = new Updater(map);
//        EnemyTeamUpdater.SetFirstTile();
//
//        MapSpot mapspot = new MapSpot(3,0,-3);
//        MapSpot mapspotExpand = new MapSpot(1,0,-1);
//
//        EnemyTeamUpdater.updateWithEnemyMove(1,Terrain.GRASS,Terrain.GRASS, mapspot,4,1,mapspotExpand );
//
//        MapSpot Test = new MapSpot(2,0,-2);
//        mapspot = new MapSpot(0,-2,2);
//
//        EnemyTeamUpdater.updateWithEnemyMove(1,Terrain.GRASS,Terrain.GRASS, mapspot,4,1,Test);
//
//        MapSpot Test2 = new MapSpot(3,-1,-2);
//
//        //mapSpot scanner should have mapspots test 1,2 as expansion locations when expanding grasslands
//        SettlementsFactory settlementsFactory = new SettlementsFactory();
//
//        assertEquals(1, settlementsFactory.generateSettlements(map, Team.ENEMY).size());
//    }
}