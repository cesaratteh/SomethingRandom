
import java.lang.Math;

public class Hexagon {
    private Terrain hexTerrain;
    private boolean hexIsEmpty;
    private Tile TileContainedIn;
    private int meepleCount;
    private boolean hasTotoro;

    private Hexagon hexA; // connected to hexB in a Tile
    private Hexagon hexB; // connected to hexA in a Tile
    private Hexagon hexC;
    private Hexagon hexD;
    private Hexagon hexE;
    private Hexagon hexF;


    Hexagon(Tile containingTile, boolean isVolcano){
        TileContainedIn = containingTile;
        meepleCount = 0;
        hasTotoro = false;
        hexIsEmpty = true;
        if(isVolcano)
            hexTerrain = Terrain.Volcano;
        else
            hexTerrain = generateTerrain();

    }

    private Terrain generateTerrain() {
        int terrainCode = (int)(Math.random()*4);
        switch(terrainCode){
            case 0:
                return Terrain.Jungle;
            case 1:
                return Terrain.Lake;
            case 2:
                return Terrain.Grasslands;
            case 3:
                return Terrain.Rocky;
        }
        //TODO: put in an error here
        return null;
    }

    private void changeMeeples(int newMeepleCount, int playerMeepleCount) {

        if (playerMeepleCount >= TileContainedIn.getLevel()
                && hexIsEmpty) {
            meepleCount = newMeepleCount;
            hexIsEmpty = false;

        } else {
            //TODO: game over
        }

    }


    public boolean isEmpty(){
        return hexIsEmpty;
    }

    public Hexagon getHexA(){
        return hexA;
    }

    public Hexagon getHexB(){
        return hexB;
    }

    public Hexagon getHexC(){
        return hexC;
    }

    public Hexagon getHexD(){
        return hexD;
    }

    public Hexagon getHexE(){
        return hexE;
    }

    public Hexagon getHexF(){
        return hexF;
    }



    public void setHexA(Hexagon hexA) {
        this.hexA = hexA;
    }

    public void setHexB(Hexagon hexB) {
        this.hexB = hexB;
    }

    public void setHexC(Hexagon hexC) {
        this.hexC = hexC;
    }

    public void setHexD(Hexagon hexD) {
        this.hexD = hexD;
    }

    public void setHexE(Hexagon hexE) {
        this.hexE = hexE;
    }

    public void setHexF(Hexagon hexF) {
        this.hexF = hexF;
    }



    public int getMeepleCount() {
        return meepleCount;
    }

    public void setMeepleCount(int meepleCount) {
        this.meepleCount = meepleCount;
    }




    public boolean getHasTotoro() {
        return hasTotoro;
    }

    public void setHasTotoro(boolean hasTotoro) {
        this.hasTotoro = hasTotoro;
    }

}
