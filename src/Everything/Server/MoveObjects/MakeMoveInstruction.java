package Everything.Server.MoveObjects;

public class MakeMoveInstruction implements Move{

    //-----------
    // Attributes

    private String gameId;
    private int moveNumber;
    private String tile;

    //-----------
    // Constructors

    public MakeMoveInstruction(String gameId, int moveNumber, String tile){
        this.gameId = gameId;
        this.moveNumber = moveNumber;
        this.tile = tile;
    }

    //-----------
    // Getters

    public String getGameID(){
        return gameId;
    }

    public int getMoveNumber(){
        return moveNumber;
    }

    public String getTile(){
        return tile;
    }
}
