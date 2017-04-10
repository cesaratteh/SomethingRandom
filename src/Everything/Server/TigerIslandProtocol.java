package Everything.Server;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.Move;
import Everything.models.*;



public class TigerIslandProtocol {
    private String gameID;              //Dave said can be alphanumeric, so has to be a string
    private int moveNumber;
    private String tile;
    private int timeLimit;


    public String authenticateTournament(String input){
        String output = null;
        if(input.equals("WELCOME TO ANOTHER EDITION OF THUNDERDOME!")){
            output = "ENTER THUNDERDOME " + "tournament password";             //FIXME: replace with actual tournament password
            }
            else if(input.equals("TWO SHALL ENTER, ONE SHALL LEAVE")) {
            output = "I AM " + "<username> " + "<password>";                  //FIXME: replace with actual user/pass
            }
            return output;
    }

    public String getPlayerID(String input){
        String PlayerID = null;
        if(input.contains("WAIT FOR THE TOURNAMENT TO BEGIN")){
            String[] info = input.split(" ");
            PlayerID = info[6];
        }
        return PlayerID;
    }

    public String parseMoveInput(String input) {
        String[] tokens = input.split(" ");
        gameID = tokens[5];
        moveNumber = Integer.parseInt(tokens[10]);
        tile = tokens[12];
        timeLimit = Integer.parseInt(tokens[7]);
        MakeMoveInstruction instruction = new MakeMoveInstruction(gameID,moveNumber,tile);
        writeToBuffer(instruction);
        WeJustDidThisMove ourmove = readFromBuffer();
        int tileX = ourmove.getTileSpot().getXForServer();
        int tileY = ourmove.getTileSpot().getyForServer();
        int tileZ = ourmove.getTileSpot().getZForServer();
        int buildX = ourmove.getBuildSpot().getXForServer();
        int buildY = ourmove.getBuildSpot().getyForServer();
        int buildZ = ourmove.getBuildSpot().getZForServer();
        int orientation = ourmove.getOrientation();
        Terrain terrain = ourmove.getTerrain();

        String move = "GAME " + gameID + " MOVE " + moveNumber + " AT " + tileX + " " + tileY + " " + tileZ + " " + orientation + " ";      //FIXME: Need to get orientation
        switch (ourmove.getBuildType()) {
            case (1):
                move.concat("FOUND SETTLEMENT AT " + buildX + " " + buildY + " " + buildZ);
                break;
            case (2):
                move.concat("EXPAND SETTLEMENT AT " + buildX + " " + buildY + " " + buildZ + " " + terrain) ;
                break;
            case (3):
                move.concat("BUILD TOTORO SANCTUARY AT " + buildX + " " + buildY + " " + buildZ);
                break;
            case(4):
                move.concat("BUILD TIGER PLAYGROUND AT " + buildX + " " + buildY + " " + buildZ);
                break;
            case(5):
                move.concat("UNABLE TO BUILD" + buildX + " " + buildY + " " + buildZ);
                break;
        }
        return move;
    }

    public EnemyMove parseOpponentMove(String input){
        String[] tokens = input.split(" ");
        String gameid = tokens[1];
        int movenumber = Integer.parseInt(tokens[3]);
        String playerID = tokens[5];
        int tileX = Integer.parseInt(tokens[9]);
        int tileY = Integer.parseInt(tokens[10]);
        int tileZ = Integer.parseInt(tokens[11]);
        int orientation = Integer.parseInt(tokens[12]);
        int buildX;
        int buildY;
        int buildZ;
        Terrain terrain = null;
        if(tokens[13] == "FOUNDED")
        {
            buildX = Integer.parseInt(tokens[16]);
            buildY = Integer.parseInt(tokens[17]);
            buildZ = Integer.parseInt(tokens[18]);
        }
        else if(tokens[13] == "EXPANDED")
        {
            buildX = Integer.parseInt(tokens[16]);
            buildY = Integer.parseInt(tokens[17]);
            buildZ = Integer.parseInt(tokens[18]);
            terrain = Terrain.valueOf(tokens[19]);
        }
        else
        {
            buildX = Integer.parseInt(tokens[17]);
            buildY = Integer.parseInt(tokens[18]);
            buildZ = Integer.parseInt(tokens[19]);
        }

        EnemyMove theirMove = new EnemyMove(gameid, movenumber, playerID, tileX, tileY, tileZ, orientation, buildX, buildY, buildZ, terrain);
        return theirMove;

    }


    public void writeToBuffer(Move move){
        //FIXME: Write to buffer queue when buffer is set up
    }

    public WeJustDidThisMove readFromBuffer(){
        //FIXME: Read from buffer queue when buffer is set up
        WeJustDidThisMove ourmove;
        return ourmove;
    }

}
