package Everything.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileMapSpotTest {

    @Test
    public void getMxTest() {
        MapSpot m1 = new MapSpot(RandomGenerator.generateRandomMapSpotIndex(),
                RandomGenerator.generateRandomMapSpotIndex(),
                RandomGenerator.generateRandomMapSpotIndex());

        MapSpot m2 = new MapSpot(RandomGenerator.generateRandomMapSpotIndex(),
                RandomGenerator.generateRandomMapSpotIndex(),
                RandomGenerator.generateRandomMapSpotIndex());

        MapSpot m3 = new MapSpot(RandomGenerator.generateRandomMapSpotIndex(),
                RandomGenerator.generateRandomMapSpotIndex(),
                RandomGenerator.generateRandomMapSpotIndex());

        TileMapSpot tileMapSpot = new TileMapSpot(m1, m2, m3);

        assertEquals(m1, tileMapSpot.getM1());
        assertEquals(m2, tileMapSpot.getM2());
        assertEquals(m3, tileMapSpot.getM3());

        for (MapSpot m : tileMapSpot.getAllMapSpots()) {
            assertEquals(true, m == m1 ^ m == m2 ^ m == m3);
        }
    }
}