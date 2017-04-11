package Everything.Server.MoveObjects;


import Everything.models.MapSpot;
import Everything.models.Terrain;

public class WeJustDidThisMove implements Move {

    //-----------
    // Attributes

    private MapSpot tileSpot;
    private MapSpot buildSpot;
    private int orientation;
    private Terrain terrain;
    private int buildType;
    private int moveNumber; // MUST BE SET

    //-----------
    // Getters

    public MapSpot getTileSpot(){return this.tileSpot;}
    public MapSpot getBuildSpot(){return this.buildSpot;}
    public int getOrientation(){return this.orientation;}
    public Terrain getTerrain(){return this.terrain;}
    public int getBuildType(){return this.buildType;}
    public int getMoveNumber() {
        return moveNumber;
    }

    //--------
    // Setters

    public void setTileSpot(MapSpot tileSpot) {
        this.tileSpot = tileSpot;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setBuildSpot(MapSpot buildSpot) {
        this.buildSpot = buildSpot;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setBuildType(int buildType) {
        this.buildType = buildType;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }
}
