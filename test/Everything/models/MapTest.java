package Everything.models;

import Everything.game.action.MapUpdater.Updater;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;


public class MapTest {

    final private Random random = new Random();

    @Test
    public void getMapSizeTest() {
        assertEquals(200, Map.size());
    }

    @Test
    public void printMap() {
        Map map = new Map();
        Updater updater = new Updater(map, Team.ENEMY);
        updater.setFirstTile();
        map.printMap();

        MapSpot mapSpot = new MapSpot(0, 0, 0);

        MapSpot mapSpot1 = mapSpot.right();

        int col = mapSpot1.getXForServer() + (mapSpot1.getZForServer() - (mapSpot1.getZForServer()&1)) / 2;
        int row = mapSpot1.getZForServer();
    }

    @Test
    public void getMapTest() {
        final Map map = new Map();

        final Hexagon centerHexagon = RandomGenerator.generateRandomHexagon();
        final Hexagon leftHexagon = RandomGenerator.generateRandomHexagon();
        final Hexagon topLeftHexagon = RandomGenerator.generateRandomHexagon();
        final Hexagon topRightHexagon = RandomGenerator.generateRandomHexagon();
        final Hexagon righHexagon = RandomGenerator.generateRandomHexagon();
        final Hexagon bottomRightHexagon = RandomGenerator.generateRandomHexagon();
        final Hexagon bottomLeftHexagon = RandomGenerator.generateRandomHexagon();

        map.setHexagon(map.getMiddleHexagonMapSpot(), centerHexagon);
        map.setHexagon(map.getMiddleHexagonMapSpot().left(), leftHexagon);
        map.setHexagon(map.getMiddleHexagonMapSpot().topLeft(), topLeftHexagon);
        map.setHexagon(map.getMiddleHexagonMapSpot().topRight(), topRightHexagon);
        map.setHexagon(map.getMiddleHexagonMapSpot().right(), righHexagon);
        map.setHexagon(map.getMiddleHexagonMapSpot().bottomRight(), bottomRightHexagon);
        map.setHexagon(map.getMiddleHexagonMapSpot().bottomLeft(), bottomLeftHexagon);

        final Hexagon[][][] hexagonArray = map.getHexagonArray();

        assertEquals(Map.size(), hexagonArray.length);
        assertEquals(Map.size(), hexagonArray[0].length);
        assertEquals(Map.size(), hexagonArray[0][0].length);

        final Hexagon expectedCenterHex = hexagonArray[Map.size() / 2][Map.size() / 2][Map.size() / 2];
        final Hexagon expectedLeftHex = hexagonArray[Map.size() / 2 - 1][Map.size() / 2 + 1][Map.size() / 2];
        final Hexagon expectedTopLeftHex = hexagonArray[Map.size() / 2][Map.size() / 2 + 1][Map.size() / 2 - 1];
        final Hexagon expectedTopRightHex = hexagonArray[Map.size() / 2 + 1][Map.size() / 2][Map.size() / 2 - 1];
        final Hexagon expectedRightHex = hexagonArray[Map.size() / 2 + 1][Map.size() / 2 - 1][Map.size() / 2];
        final Hexagon expectedBottomRightHex = hexagonArray[Map.size() / 2][Map.size() / 2 - 1][Map.size() / 2 + 1];
        final Hexagon expectedBottomLeftHex = hexagonArray[Map.size() / 2 - 1][Map.size() / 2][Map.size() / 2 + 1];

        assertEquals(expectedCenterHex, centerHexagon);
        assertEquals(expectedLeftHex, leftHexagon);
        assertEquals(expectedTopLeftHex, topLeftHexagon);
        assertEquals(expectedTopRightHex, topRightHexagon);
        assertEquals(expectedRightHex, righHexagon);
        assertEquals(expectedBottomRightHex, bottomRightHexagon);
        assertEquals(expectedBottomLeftHex, bottomLeftHexagon);

        // The below code is redundant and unnecessary, but I wrote it already so might as well keep it
        assertEquals(centerHexagon.getLevel(), expectedCenterHex.getLevel());
        assertEquals(centerHexagon.getNumberOfMeeples(), expectedCenterHex.getNumberOfMeeples());
        assertEquals(centerHexagon.getTerrainType(), expectedCenterHex.getTerrainType());
        assertEquals(centerHexagon.isHasTotoro(), expectedCenterHex.isHasTotoro());
        assertEquals(centerHexagon.isEmpty(), expectedCenterHex.isEmpty());

        assertEquals(leftHexagon.getLevel(), expectedLeftHex.getLevel());
        assertEquals(leftHexagon.getNumberOfMeeples(), expectedLeftHex.getNumberOfMeeples());
        assertEquals(leftHexagon.getTerrainType(), expectedLeftHex.getTerrainType());
        assertEquals(leftHexagon.isHasTotoro(), expectedLeftHex.isHasTotoro());
        assertEquals(leftHexagon.isEmpty(), expectedLeftHex.isEmpty());

        assertEquals(topLeftHexagon.getLevel(), expectedTopLeftHex.getLevel());
        assertEquals(topLeftHexagon.getNumberOfMeeples(), expectedTopLeftHex.getNumberOfMeeples());
        assertEquals(topLeftHexagon.getTerrainType(), expectedTopLeftHex.getTerrainType());
        assertEquals(topLeftHexagon.isHasTotoro(), expectedTopLeftHex.isHasTotoro());
        assertEquals(topLeftHexagon.isEmpty(), expectedTopLeftHex.isEmpty());

        assertEquals(topRightHexagon.getLevel(), expectedTopRightHex.getLevel());
        assertEquals(topRightHexagon.getNumberOfMeeples(), expectedTopRightHex.getNumberOfMeeples());
        assertEquals(topRightHexagon.getTerrainType(), expectedTopRightHex.getTerrainType());
        assertEquals(topRightHexagon.isHasTotoro(), expectedTopRightHex.isHasTotoro());
        assertEquals(topRightHexagon.isEmpty(), expectedTopRightHex.isEmpty());

        assertEquals(righHexagon.getLevel(), expectedRightHex.getLevel());
        assertEquals(righHexagon.getNumberOfMeeples(), expectedRightHex.getNumberOfMeeples());
        assertEquals(righHexagon.getTerrainType(), expectedRightHex.getTerrainType());
        assertEquals(righHexagon.isHasTotoro(), expectedRightHex.isHasTotoro());
        assertEquals(righHexagon.isEmpty(), expectedRightHex.isEmpty());

        assertEquals(bottomRightHexagon.getLevel(), expectedBottomRightHex.getLevel());
        assertEquals(bottomRightHexagon.getNumberOfMeeples(), expectedBottomRightHex.getNumberOfMeeples());
        assertEquals(bottomRightHexagon.getTerrainType(), expectedBottomRightHex.getTerrainType());
        assertEquals(bottomRightHexagon.isHasTotoro(), expectedBottomRightHex.isHasTotoro());
        assertEquals(bottomRightHexagon.isEmpty(), expectedBottomRightHex.isEmpty());

        assertEquals(bottomLeftHexagon.getLevel(), expectedBottomLeftHex.getLevel());
        assertEquals(bottomLeftHexagon.getNumberOfMeeples(), expectedBottomLeftHex.getNumberOfMeeples());
        assertEquals(bottomLeftHexagon.getTerrainType(), expectedBottomLeftHex.getTerrainType());
        assertEquals(bottomLeftHexagon.isHasTotoro(), expectedBottomLeftHex.isHasTotoro());
        assertEquals(bottomLeftHexagon.isEmpty(), expectedBottomLeftHex.isEmpty());
    }

    @Test
    public void setHexagonTest() {
        final Map map = new Map();

        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();
        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        map.setHexagon(mapSpot, hexagon);
        assertEquals(map.getHexagon(mapSpot), hexagon);
    }

    @Test
    public void getMiddleHexagonTest() {
        final Map map = new Map();
        final MapSpot mapSpot = map.getMiddleHexagonMapSpot();

        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        map.setHexagon(mapSpot, hexagon);
        assertEquals(map.getMiddleHexagon(), hexagon);
    }

}


//    @Test
//    public void PrintMapTest(){
//        final Map map = new Map();
//
//        MapSpot mapSpot = map.getMiddleHexagonMapSpot();
//        Hexagon hexagon = RandomGenerator.generateRandomHexagon();
//        map.setHexagon(mapSpot, hexagon);
//        hexagon.addMeeplesAccordingToLevel(Team.FRIENDLY);
//
//        MapSpot Spot2 = mapSpot.topLeft();
//        hexagon = RandomGenerator.generateRandomHexagon();
//        map.setHexagon(Spot2, hexagon);
//        hexagon.addTotoro(Team.ENEMY);
//
//
//        MapSpot Spot3 = mapSpot.topRight();
//        hexagon = RandomGenerator.generateRandomHexagon();
//        map.setHexagon(Spot3, hexagon);
//
//        map.printMap();
//
//    }
