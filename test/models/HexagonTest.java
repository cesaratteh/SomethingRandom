package models;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HexagonTest {

    final Random random = new Random();
    Player player;

    @Before
    public void initializePlayer(){
        player = new Player(RandomGenerator.generateRandomTeam());
    }

    @Test
    public void getTeamTest() {
        final Team team = RandomGenerator.generateRandomTeam();

        final Hexagon hexagon = new Hexagon(RandomGenerator.generateRandomTerrainType(),
                0,
                1);

        hexagon.addMeeples(1, team, player);
        assertEquals(team, hexagon.getOccupiedBy());
    }

    @Test
    public void terrainTypeTest() {
        final Terrain terrain = RandomGenerator.generateRandomTerrainType();

        final Hexagon hexagon =
                new Hexagon(terrain,
                        RandomGenerator.generateRandomLevel(),
                        RandomGenerator.generateRandomTileId());

        assertEquals(terrain, hexagon.getTerrainType());
    }

    @Test
    public void levelTest() {
        final int level = random.nextInt(Integer.MAX_VALUE);

        final Hexagon hexagon =
                new Hexagon(RandomGenerator.generateRandomTerrainType(),
                        level,
                        RandomGenerator.generateRandomTileId());

        assertEquals(level, hexagon.getLevel());
    }

    @Test
    public void tiledIdTest()
    {
        final int tiledId = random.nextInt(Integer.MAX_VALUE);

        final Hexagon hexagon =
                new Hexagon(RandomGenerator.generateRandomTerrainType(),
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
        final int numberOfMeeplesToAdd = random.nextInt(10);

        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addMeeples(numberOfMeeplesToAdd, RandomGenerator.generateRandomTeam(), player);
        assertEquals(numberOfMeeplesToAdd, hexagon.getNumberOfMeeples());
    }

    @Test(expected = RuntimeException.class)
    public void addingMeeplesTwiceException() {
        final int numberOfMeeplesToAddFirst = random.nextInt(100);
        final int numberOfMeeplesToAddSecond = random.nextInt(100);

        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addMeeples(numberOfMeeplesToAddFirst, RandomGenerator.generateRandomTeam(), player);
        hexagon.addMeeples(numberOfMeeplesToAddSecond, RandomGenerator.generateRandomTeam(), player);
    }

    @Test
    public void addingTotoroTest() {
        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addTotoro(RandomGenerator.generateRandomTeam(), player);
        assertEquals(true, hexagon.isHasTotoro());
    }

    @Test
    public void addingTigerTest(){
        final Hexagon hexagon = RandomGenerator.generateRandomHexagon();

        hexagon.addTiger(RandomGenerator.generateRandomTeam(), player);
        assertEquals(true, hexagon.isHasTiger());
    }

    @Test
    public void ConvertTerrainToCharTest(){

        final Hexagon hexagon = new Hexagon(Terrain.VOLCANO,
                                            RandomGenerator.generateRandomLevel(),
                                            RandomGenerator.generateRandomTileId());
        char K = hexagon.ConvertTerrainToCharacter();
        assertEquals('V', K );


    }
}
