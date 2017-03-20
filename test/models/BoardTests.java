package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTests {
        private Map board;
        @Before
        public void setupBoard(){
            Tile initialTile = new Tile(0);
            board = new Map(initialTile);
        }
        @Test
        public void testAbilitytoConstructBoard(){
            Assert.assertTrue(board instanceof Map);
        }

        @Test
        public void testBoardStartsOnLevelOne() {
            Assert.assertEquals(1,board.getSeedTile().getLevel());
        }
        @Test
        public void testLevelOnePlacementIsAdjacent(){
            Tile newTile = new Tile(1);
            Assert.assertEquals(true,newTile.hasNeighbor());
        }
}
