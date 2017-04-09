package Everything.Server;

import Everything.models.Terrain;

public class EnemyMove implements Move{

    //-----------
    // Attributes

    private String gameid;
    private int movenumber;
    private String playerID;
    private int tileX;
    private int tileY;
    private int tileZ;
    private int orientation;
    private int buildX;
    private int buildY;
    private int buildZ;
    private Terrain terrain;

    //-----------
    // Constructors

    public EnemyMove(final String gameid, final int movenumber, final String playerID, final int tileX,
                     final int tileY, final int tileZ, final int orientation, final int buildX, final int buildY,
                     final int buildZ, final Terrain terrain){
        this.gameid = gameid;
        this.movenumber = movenumber;
        this.playerID = playerID;
        this.tileX = tileX;
        this.tileY = tileY;
        this.tileZ = tileZ;
        this.orientation = orientation;
        this.buildX = buildX;
        this.buildY = buildY;
        this.buildZ = buildZ;
        this.terrain = terrain;

    }

    //-----------
    // Getters

    public String getGameid(){return gameid;}
    public int getMovenumber(){return movenumber;}
    public String getPlayerID(){return playerID;}
    public int getTileX(){return tileX;}
    public int getTileY(){return tileY;}
    public int getTileZ(){return tileZ;}
    public int getOrientation(){return orientation;}
    public int getBuildX(){return buildX;}
    public int getBuildY(){return buildY;}
    public int getBuildZ(){return buildZ;}
    public Terrain getTerrain(){return terrain;}


}
