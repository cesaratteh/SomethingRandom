package models;

public class Hexagon {

    //-----------
    // Attributes

    private Terrain terrainType;
    private int tileId;
    private int numberOfMeeples;
    private boolean hasTotoro;

    //-------------
    // Constructors

    public Hexagon(final Terrain terrainType, final int tileId) {
        this.terrainType = terrainType;
        this.tileId = tileId;
        numberOfMeeples = 0;
        hasTotoro = false;
    }

    //--------
    // Methods

    public void addMeeples(final int numberOfMeeples)
    {
        if(this.numberOfMeeples != 0)
            throw new RuntimeException("Can't Added meeples twice"); // TODO: 3/19/2017 Replace with LOGGING

        this.numberOfMeeples = numberOfMeeples;
    }

    public void addTotoro() {
        if(!isEmpty())
            throw new RuntimeException("Adding totoro when models.Hexagon is not empty");

        hasTotoro = true;
    }

    public void nukeHexagon()
    {
        if(hasTotoro)
        {
            throw new RuntimeException("Can't nuke Totoro"); // TODO: 3/19/2017 Replace with LOGGING
        }

        this.numberOfMeeples = 0;
    }

    public boolean isEmpty(){
        return (numberOfMeeples == 0) && !(hasTotoro);
    }

    public boolean isHasTotoro() {
        return hasTotoro;
    }

    //----------
    // Getters

    public int getNumberOfMeeples() {
        return numberOfMeeples;
    }

    public Terrain getTerrainType() {
        return terrainType;
    }

    public int getTileId() {
        return tileId;
    }
}
