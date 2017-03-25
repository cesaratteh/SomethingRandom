package models;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MapSpotTest {

    final Random random = new Random();

    @Test
    public void getXTest() {
        final int x = random.nextInt(Map.mapSize);
        final MapSpot mapSpot = new MapSpot(x, (x % 2 == 0) ? 0 : 1);

        assertEquals(x, mapSpot.getX());
    }

    @Test
    public void getYTest() {
        final int y = random.nextInt(Map.mapSize);
        final MapSpot mapSpot = new MapSpot((y % 2 == 0) ? 0 : 1, y);

        assertEquals(y, mapSpot.getY());
    }

    @Test(expected = RuntimeException.class)
    public void illegalSpotTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 == x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
    }

}
