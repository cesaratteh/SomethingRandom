package models;



import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;



public class MapTest {

    final Random random = new Random();
    private Map map = new Map();
    private Terrain getRandomTerrainType() {
        final int randomIndex = random.nextInt(Terrain.values().length);
        final Terrain[] values = Terrain.values();

        return values[randomIndex];
    }
    final Terrain terrain = getRandomTerrainType();

    @Test
    public void addHexagonTest(){
        final int x = random.nextInt(map.calculateMaximumMapWidthAndHeight());
        final MapSpot mapSpot = new MapSpot(x, (x % 2 == 0) ? 0 : 1);
        final Hexagon hexagon =
                new Hexagon(terrain,
                        random.nextInt(),
                        random.nextInt());
        map.addHexagon(mapSpot,hexagon);
        assertEquals(map.getHexagon(mapSpot), hexagon);

    }
    @Test
    public void getMiddleHexagonTest(){
        final Terrain terrain = getRandomTerrainType();
        final MapSpot mapSpot = map.getMiddleHexagonMapSpot();
        final Hexagon hexagon =
                new Hexagon(terrain,
                        random.nextInt(),
                        random.nextInt());
        map.addHexagon(mapSpot,hexagon);
        assertEquals(map.getMiddleHexagon(), hexagon);

    }

}
