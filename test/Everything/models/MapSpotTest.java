package Everything.models;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MapSpotTest {

    final Random random = new Random();

    @Test
    public void leftTest() {
        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();

        assertEquals(mapSpot.getX() - 1, mapSpot.left().getX());
        assertEquals(mapSpot.getY() + 1, mapSpot.left().getY());
        assertEquals(mapSpot.getZ(), mapSpot.left().getZ());
    }

    @Test
    public void topLeftTest() {
        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();

        assertEquals(mapSpot.getX(), mapSpot.topLeft().getX());
        assertEquals(mapSpot.getY() + 1, mapSpot.topLeft().getY());
        assertEquals(mapSpot.getZ() - 1, mapSpot.topLeft().getZ());
    }

    @Test
    public void topRightTest() {
        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();

        assertEquals(mapSpot.getX() + 1, mapSpot.topRight().getX());
        assertEquals(mapSpot.getY(), mapSpot.topRight().getY());
        assertEquals(mapSpot.getZ() - 1, mapSpot.topRight().getZ());
    }

    @Test
    public void rightTest() {
        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();

        assertEquals(mapSpot.getX() + 1, mapSpot.right().getX());
        assertEquals(mapSpot.getY() - 1, mapSpot.right().getY());
        assertEquals(mapSpot.getZ(), mapSpot.right().getZ());
    }

    @Test
    public void bottomRightTest() {
        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();

        assertEquals(mapSpot.getX(), mapSpot.bottomRight().getX());
        assertEquals(mapSpot.getY() - 1, mapSpot.bottomRight().getY());
        assertEquals(mapSpot.getZ() + 1, mapSpot.bottomRight().getZ());
    }

    @Test
    public void bottomLeftTest() {
        final MapSpot mapSpot = RandomGenerator.generateRandomMapSpot();

        assertEquals(mapSpot.getX() - 1, mapSpot.bottomLeft().getX());
        assertEquals(mapSpot.getY(), mapSpot.bottomLeft().getY());
        assertEquals(mapSpot.getZ() + 1, mapSpot.bottomLeft().getZ());
    }

    @Test
    public void equalsTest() {
        MapSpot m1 = new MapSpot(0, 0, 0);
        MapSpot m2 = new MapSpot(0, 0, 0);
        MapSpot m3 = new MapSpot(0, 1, 0);

        assertEquals(true, m1.isEqual(m2));
        assertEquals(false, m1.isEqual(m3));
    }
}
