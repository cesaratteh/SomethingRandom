package models;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HexagonTest {

    final Random random = new Random();

    @Test
    public void getTeamTest() {
        final Team team = RandomGenerator.generateRandomTeam();

        final Hexagon hexagon = new Hexagon(team,
                RandomGenerator.generateRandomTerrainType(),
                0,
                1);

        assertEquals(team, hexagon.getOccupiedBy());
    }

    @Test
    public void terrainTypeTest() {
        final Terrain terrain = RandomGenerator.generateRandomTerrainType();

        final Hexagon hexagon =
                new Hexagon(RandomGenerator.generateRandomTeam(),
                        terrain,
                        RandomGenerator.generateRandomLevel(),
                        RandomGenerator.generateRandomTileId());

        assertEquals(terrain, hexagon.getTerrainType());
    }

    @Test
    public void levelTest() {
        final int level = random.nextInt(Integer.MAX_VALUE);

        final Hexagon hexagon =
                new Hexagon(RandomGenerator.generateRandomTeam(),
                        RandomGenerator.generateRandomTerrainType(),
                        level,
                        RandomGenerator.generateRandomTileId());

        assertEquals(level, hexagon.getLevel());
    }

    @Test
    public void tiledIdTest()
    {
        final int tiledId = random.nextInt(Integer.MAX_VALUE);

        final Hexagon hexagon =
                new Hexagon(RandomGenerator.generateRandomTeam(),
                        RandomGenerator.generateRandomTerrainType(),
                        RandomGenerator.generateRandomLevel(),
                        tiledId);

        assertEquals(tiledId, hexagon.getTileId());
    }

    @Test
    public void hexagonStartsEmptyTest() {
        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        assertEquals(true, hexagon.isEmpty());
        assertEquals(0, hexagon.getNumberOfMeeples());
        assertEquals(false, hexagon.isHasTotoro());
    }

    @Test
    public void addingMeeplesTest() {
        final int numberOfMeeplesToAdd = random.nextInt(100);

        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addMeeples(numberOfMeeplesToAdd);
        assertEquals(numberOfMeeplesToAdd, hexagon.getNumberOfMeeples());
    }

    @Test(expected = RuntimeException.class)
    public void addingMeeplesTwiceException() {
        final int numberOfMeeplesToAddFirst = random.nextInt(100);
        final int numberOfMeeplesToAddSecond = random.nextInt(100);

        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addMeeples(numberOfMeeplesToAddFirst);
        hexagon.addMeeples(numberOfMeeplesToAddSecond);
    }

    @Test
    public void addingTotoroTest() {
        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addTotoro();
        assertEquals(true, hexagon.isHasTotoro());
    }

    @Test
    public void nukingHexagonTest() {
        final int numberOfMeeplesToAdd = random.nextInt(100);

        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addMeeples(numberOfMeeplesToAdd);

        assertEquals(numberOfMeeplesToAdd, hexagon.getNumberOfMeeples());
        hexagon.nukeHexagon();
        assertEquals(0, hexagon.getNumberOfMeeples());
    }

    @Test
    public void ConvertTerrainToCharTest(){

        final Hexagon hexagon = new Hexagon(RandomGenerator.generateRandomTeam(), Terrain.VOLCANO,
                                            RandomGenerator.generateRandomLevel(),
                                            RandomGenerator.generateRandomTileId());
        char K = hexagon.ConvertTerrainToCharacter();
        assertEquals('V', K );


    }




}
