package models;

import org.junit.Test;

import java.util.Random;
import java.util.logging.Logger;

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

    @Test
    public void leftTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 != x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
        final MapSpot left = mapSpot.left();

        if (x < 0) {
            assertEquals(left, null);
        } else {
            assertEquals(x - 2, left.getX());
            assertEquals(y, left.getY());
        }
    }

    @Test
    public void topLeftTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 != x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
        final MapSpot topLeft = mapSpot.topLeft();

        if (x < 0 | y < 0) {
            assertEquals(topLeft, null);
        } else {
            assertEquals(x - 1, topLeft.getX());
            assertEquals(y - 1, topLeft.getY());
        }
    }

    @Test
    public void topRightTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 != x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
        final MapSpot topRight = mapSpot.topRight();

        if (x >= Map.mapSize | y <0) {
            assertEquals(topRight, null);
        } else {
            assertEquals(x + 1, topRight.getX());
            assertEquals(y - 1, topRight.getY());
        }
    }

    @Test
    public void rightTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 != x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
        final MapSpot right = mapSpot.right();

        if (x >= Map.mapSize) {
            assertEquals(right, null);
        } else {
            assertEquals(x + 2, right.getX());
            assertEquals(y, right.getY());
        }
    }

    @Test
    public void bottomRightTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 != x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
        final MapSpot bottomRight = mapSpot.bottomRight();

        if (x >= Map.mapSize | y >= Map.mapSize) {
            assertEquals(bottomRight, null);
        } else {
            assertEquals(x + 1, bottomRight.getX());
            assertEquals(y + 1, bottomRight.getY());
        }
    }

    @Test
    public void bottomLeftTest() {
        final int x = random.nextInt(Map.mapSize);
        int y;
        while ((y = random.nextInt(Map.mapSize)) % 2 != x % 2) ;

        final MapSpot mapSpot = new MapSpot(x, y);
        final MapSpot bottomLeft = mapSpot.bottomLeft();

        if (x < 0 | y >= Map.mapSize) {
            assertEquals(bottomLeft, null);
        } else {
            assertEquals(x - 1, bottomLeft.getX());
            assertEquals(y + 1, bottomLeft.getY());
        }
    }
}
