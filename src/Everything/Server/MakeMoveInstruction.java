package Everything.Server;

public class MakeMoveInstruction implements Move{

    //-----------
    // Attributes

    private String gameid;
    private int movenumber;
    private String tile;

    //-----------
    // Constructors

    public MakeMoveInstruction(String gameid, int movenumber, String tile){
        this.gameid = gameid;
        this.movenumber = movenumber;
        this.tile = tile;

    }

    //-----------
    // Getters

    public String getGameid(){return gameid;}
    public int getMovenumber(){return movenumber;}
    public String getTile(){return tile;}
}
