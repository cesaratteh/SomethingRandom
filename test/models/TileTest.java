package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileTest {

    @Test
    public void getHxTest() {
        final Hexagon h1 = new Hexagon(RandomGenerator.generateRandomTerrainType(),
                RandomGenerator.generateRandomLevel(),
                RandomGenerator.generateRandomTileId());

        final Hexagon h2 = new Hexagon(RandomGenerator.generateRandomTerrainType(),
                RandomGenerator.generateRandomLevel(),
                RandomGenerator.generateRandomTileId());

        final Hexagon h3 = new Hexagon(RandomGenerator.generateRandomTerrainType(),
                RandomGenerator.generateRandomLevel(),
                RandomGenerator.generateRandomTileId());

        final Tile tile = new Tile(h1, h2, h3);

        assertEquals(h1, tile.getH1());
        assertEquals(h2, tile.getH2());
        assertEquals(h3, tile.getH3());
    }
}
