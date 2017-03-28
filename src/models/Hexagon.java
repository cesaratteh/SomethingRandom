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
    private boolean hasTiger;

    //-------------
    // Constructors

    public Hexagon(final Terrain terrainType, final int level, final int tileId) {
        this.occupiedBy = Team.UNKNOWN;
        this.terrainType = terrainType;
        this.level = level;
        this.tileId = tileId;
        numberOfMeeples = 0;
        hasTotoro = false;
        hasTiger = false;
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

    public void addTiger(final Team team){
        if(!isEmpty())
            throw new RuntimeException("Adding tiger when models.Hexagon is not empty");

        this.occupiedBy = team;
        hasTiger = true;
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

    public boolean isHasTiger() {
        return hasTiger;
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

    public void setLevel(int level){ this.level = level; }

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

    public boolean isEqual(Hexagon hexagon){
        //not totally the best way I can think of to compare hexagons, but works right now
        //if anyone can think of a better way go for it
        return this.getLevel() == hexagon.getLevel()
                && this.getTileId() == hexagon.getTileId()
                && this.getTerrainType() == hexagon.getTerrainType()
                && this.getOccupiedBy() == hexagon.getOccupiedBy()
                && this.getNumberOfMeeples() == hexagon.getNumberOfMeeples()
                && this.isHasTotoro() == hexagon.isHasTotoro()
                && this.isHasTiger() == hexagon.isHasTiger();
    }
}
