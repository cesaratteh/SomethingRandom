
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

    public Hexagon getHex1() {
        return hex1;
    }

    public Hexagon getHex2() {
        return hex2;
    }

    public Hexagon getVolcanoHex() {
        return volcanoHex;
    }

    public void setLevel(int lvl) {
        this.level = lvl;
    }


    public long getID(){return ID;}


    public boolean hasNeighbor() {
        /* Check the sides of the tile, see if it has at least one neighbor */
        return false;
    }
}
