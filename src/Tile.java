
import java.lang.Math;

public class Tile {
    private Hexagon hex1;
    private Hexagon hex2;
    private Hexagon volcanoHex;
    private int level;
    private long ID;
    //TODO: figure out how tf to do position
    //private <type> position;

    Tile(int tileCount){
        constructHexes();
        ID = tileCount;

    }

    private void constructHexes(){
        //TODO: find a way to create a unique tile that has not been created yet
        volcanoHex = new Hexagon(this, true);
        hex1 = new Hexagon(this, false);
        hex2 = new Hexagon(this,false);
    }

    public int getLevel() {
        return level;
    }


}
