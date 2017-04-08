package game.action.utils;

import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettlementsFactoryTest {

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