package models;



public class Hexagon {

    //-----------
    // Attributes

    private Team occupiedBy;
    private int tileId;
    private int level;
    private Terrain terrainType;
    private int numberOfMeeples;
    private boolean hasTotoro;

    //-------------
    // Constructors

    public Hexagon(final Terrain terrainType, final int level, final int tileId) {
        this.occupiedBy = Team.UNKNOWN;
        this.terrainType = terrainType;
        this.level = level;
        this.tileId = tileId;
        numberOfMeeples = 0;
        hasTotoro = false;
    }

    //--------
    // Methods

    public void addMeeples(final int numberOfMeeples, final Team team)
    {
        if(!isEmpty())
            throw new RuntimeException("Hexagon is not empty, can't add units"); // TODO: 3/19/2017 Replace with LOGGING

        this.occupiedBy = team;
        this.numberOfMeeples = numberOfMeeples;
    }

    public void addTotoro(final Team team) {
        if(!isEmpty())
            throw new RuntimeException("Adding totoro when models.Hexagon is not empty");

        this.occupiedBy = team;
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

    public Team getOccupiedBy() {
        return occupiedBy;
    }

    public int getTileId() {
        return tileId;
    }

    public int getLevel() {
        return level;
    }

    public int getNumberOfMeeples() {
        return numberOfMeeples;
    }

    public Terrain getTerrainType() {
        return terrainType;
    }

    public char ConvertTerrainToCharacter(){
        char TerrainChar = ' ';
        switch (terrainType){
            case VOLCANO:    TerrainChar = 'V';
            break;
            case GRASSLAND:  TerrainChar = 'G';
            break;
            case JUNGLE:     TerrainChar = 'J';
            break;
            case LAKE:       TerrainChar = 'L';
            break;
            case ROCKY:      TerrainChar = 'R';
            break;
        }
       return(TerrainChar);
    }
    public char ConvertTeamToChar(){
        char TeamChar = ' ';
        switch (occupiedBy){
            case FRIENDLY:    TeamChar = 'F';
                break;
            case ENEMY:       TeamChar = 'E';
                break;
            case UNKNOWN:     TeamChar = 'U';
                break;

        }
        return(TeamChar);
    }
}
