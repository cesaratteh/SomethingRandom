package game.action.utils;

import game.action.utils.Nuking.SettlementAdjacentMapSpotsScanner;
import models.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettlementsFactoryTest {


    @Test
    public void scanCircleTest() {
        final Map map = MapGenerator.generateCircleWithOutsideLine(Terrain.GRASSLAND, Terrain.GRASSLAND, Terrain.GRASSLAND,
                Team.FRIENDLY, Team.ENEMY, Team.FRIENDLY);

        SettlementsFactory settlementsFactory = new SettlementsFactory(map);


        ArrayList<Settlement> friendlySettlements  = settlementsFactory.generateSettlements(Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(Team.ENEMY);


        assertEquals(7, friendlySettlements.get(0).size());
        assertEquals(2, enemySettlements.get(0).size());
    }

    @Test
    public void generateSettlementsTest() {
        final Map map = new Map();

        Hexagon temp;

        temp = new Hexagon(Terrain.GRASSLAND, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addTotoro(Team.FRIENDLY);
        map.setHexagon(new MapSpot(0, 0, 0), temp);

        temp = new Hexagon(Terrain.GRASSLAND, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addTiger(Team.FRIENDLY);
        map.setHexagon(new MapSpot(1, -1, 0), temp);

        temp = new Hexagon(Terrain.GRASSLAND, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addMeeplesAccordingToLevel(Team.FRIENDLY);
        map.setHexagon(new MapSpot(2, -2, 0), temp);

        temp = new Hexagon(Terrain.JUNGLE, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addMeeplesAccordingToLevel(Team.FRIENDLY);
        map.setHexagon(new MapSpot(3, -3, 0), temp);

        temp = new Hexagon(Terrain.GRASSLAND, RandomGenerator.generateRandomLevel(), RandomGenerator.generateRandomTileId());
        temp.addMeeplesAccordingToLevel(Team.ENEMY);
        map.setHexagon(new MapSpot(4, -4, 0), temp);


        SettlementsFactory settlementsFactory = new SettlementsFactory(map);

        assertEquals(2, settlementsFactory.generateSettlements(Team.FRIENDLY).size());
        assertEquals(1, settlementsFactory.generateSettlements(Team.ENEMY).size());
    }
}