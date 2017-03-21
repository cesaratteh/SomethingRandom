package models;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HexagonTest {

    final Random random = new Random();

    private Terrain getRandomTerrainType() {
        final int randomIndex = random.nextInt(Terrain.values().length);
        final Terrain[] values = Terrain.values();

        return values[randomIndex];
    }

    @Test
    public void terrainTypeTest() {

        final Terrain terrain = getRandomTerrainType();

        final Hexagon hexagon =
                new Hexagon(terrain,
                        random.nextInt(),
                        random.nextInt());

        assertEquals(terrain, hexagon.getTerrainType());
    }

    @Test
    public void levelTest() {
        final int level = random.nextInt(Integer.MAX_VALUE);

        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(), level,random.nextInt());

        assertEquals(level, hexagon.getLevel());
    }

    @Test
    public void tiledIdTest()
    {
        final int tiledId = random.nextInt(Integer.MAX_VALUE);

        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(),
                        0,
                        tiledId);

        assertEquals(tiledId, hexagon.getTileId());
    }

    @Test
    public void hexagonStartsEmptyTest() {
        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(), random.nextInt(), random.nextInt());

        assertEquals(true, hexagon.isEmpty());
        assertEquals(0, hexagon.getNumberOfMeeples());
        assertEquals(false, hexagon.isHasTotoro());
    }

    @Test
    public void addingMeeplesTest() {
        final int numberOfMeeplesToAdd = random.nextInt(100);

        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(), random.nextInt(), random.nextInt());

        hexagon.addMeeples(numberOfMeeplesToAdd);
        assertEquals(numberOfMeeplesToAdd, hexagon.getNumberOfMeeples());
    }

    @Test(expected = RuntimeException.class)
    public void addingMeeplesTwiceException() {
        final int numberOfMeeplesToAddFirst = random.nextInt(100);
        final int numberOfMeeplesToAddSecond = random.nextInt(100);

        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(), random.nextInt(), random.nextInt());

        hexagon.addMeeples(numberOfMeeplesToAddFirst);
        hexagon.addMeeples(numberOfMeeplesToAddSecond);
    }

    @Test
    public void addingTotoroTest() {
        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(), random.nextInt(), random.nextInt());

        hexagon.addTotoro();
        assertEquals(true, hexagon.isHasTotoro());
    }

    @Test
    public void nukingHexagonTest() {
        final int numberOfMeeplesToAdd = random.nextInt(100);

        final Hexagon hexagon =
                new Hexagon(getRandomTerrainType(), random.nextInt(), random.nextInt());

        hexagon.addMeeples(numberOfMeeplesToAdd);

        assertEquals(numberOfMeeplesToAdd, hexagon.getNumberOfMeeples());
        hexagon.nukeHexagon();
        assertEquals(0, hexagon.getNumberOfMeeples());
    }
}
