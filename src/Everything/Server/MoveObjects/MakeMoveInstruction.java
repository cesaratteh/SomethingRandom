package Everything.Server.MoveObjects;

import Everything.models.Terrain;

public class MakeMoveInstruction implements Move{

    //-----------
    // Attributes

    private String gameId;
    private int moveNumber;
    private Terrain terrainA;
    private Terrain terrainB;

    //-----------
    // Constructors

    public MakeMoveInstruction(String gameId, int moveNumber, Terrain terrainA, Terrain terrainB){
        this.gameId = gameId;
        this.moveNumber = moveNumber;
        this.terrainA = terrainA;
        this.terrainB = terrainB;
    }

    //-----------
    // Getters

    public String getGameID(){
        return gameId;
    }

    public int getMoveNumber(){
        return moveNumber;
    }

    public Terrain getTerrainA(){
        return terrainA;
    }

    public Terrain getTerrainB(){
        return terrainB;
    }
}
