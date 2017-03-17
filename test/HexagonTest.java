import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nathan on 3/16/2017.
 */
public class HexagonTest {


    @Test
    public void getMeepleCount() throws Exception {
        Tile tile1 = new Tile(1);
        Hexagon Hex1 = new Hexagon(tile1,false);

        assertEquals(0, Hex1.getMeepleCount());

    }

    @Test
    public void setMeepleCount() throws Exception {
        Tile tile1 = new Tile(1);
        Hexagon Hex1 = new Hexagon(tile1,false);
        Hex1.setMeepleCount(5);
        assertEquals(5, Hex1.getMeepleCount());
    }

    @Test
    public void getHasTotoro() throws Exception {

    }

    @Test
    public void setHasTotoro() throws Exception {

    }

}