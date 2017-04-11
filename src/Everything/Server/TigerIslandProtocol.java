package Everything.Server;

import Everything.Server.MoveObjects.*;
import Everything.models.*;

import java.util.ArrayList;


public class TigerIslandProtocol {

    public String authenticateTournament(String input, String tournamentPassword, String userName, String userPassword) {
        String output = null;
        if (input.equals("WELCOME TO ANOTHER EDITION OF THUNDERDOME!")) {

            output = "ENTER THUNDERDOME " + tournamentPassword;             //FIXME: replace with actual tournament password
        } else {
            if (input.equals("TWO SHALL ENTER, ONE SHALL LEAVE")) {
                output = "I AM " + userName + " " + userPassword;
            }
        }
        return output;
    }

    private ArrayList<Terrain> parseTileTerrain(String tile) {
        String[] split = tile.split("\\+");

        ArrayList<Terrain> terrains = new ArrayList<>();

        terrains.add(Terrain.valueOf(split[0]));
        terrains.add(Terrain.valueOf(split[1]));

        return terrains;
    }


    public String getPlayerID(String input) {
        String PlayerID = null;
        if (input.contains("WAIT FOR THE TOURNAMENT TO BEGIN")) {
            String[] info = input.split(" ");
            PlayerID = info[6];
        }
        return PlayerID;
    }

    public String getGameID(String input) {
        String GameID = null;
        String tokens[] = input.split(" ");
        GameID = tokens[5];
        return GameID;
    }

    public MakeMoveInstruction getMoveInstruction(final String input) {
        String[] tokens = input.split(" ");
        String gameID = tokens[5];
        int moveNumber = Integer.parseInt(tokens[10]);
        String tile = tokens[12];

        ArrayList<Terrain> terrains = parseTileTerrain(tile);
        MakeMoveInstruction instruction = new MakeMoveInstruction(gameID, moveNumber, terrains.get(0), terrains.get(1));

        return instruction;
    }

    public String createFriendlyMoveMessageToBeSent(WeJustDidThisMove ourMove, String gameID) {
        int tileX = ourMove.getTileSpot().getXForServer();
        int tileY = ourMove.getTileSpot().getYForServer();
        int tileZ = ourMove.getTileSpot().getZForServer();
        int buildX = ourMove.getBuildSpot().getXForServer();
        int buildY = ourMove.getBuildSpot().getYForServer();
        int buildZ = ourMove.getBuildSpot().getZForServer();
        int orientation = ourMove.getOrientation();
        Terrain terrain = ourMove.getTerrain();

        String move = "GAME " + gameID + " MOVE " + ourMove.getMoveNumber() + " AT " + tileX + " " + tileY + " " + tileZ + " " + orientation + " ";      //FIXME: Need to get orientation
        switch (ourMove.getBuildType()) {
            case (1):
                move += ("FOUND SETTLEMENT AT " + buildX + " " + buildY + " " + buildZ);
                break;
            case (2):
                move += ("EXPAND SETTLEMENT AT " + buildX + " " + buildY + " " + buildZ + " " + terrain);
                break;
            case (3):
                move += ("BUILD TOTORO SANCTUARY AT " + buildX + " " + buildY + " " + buildZ);
                break;
            case (4):
                move += ("BUILD TIGER PLAYGROUND AT " + buildX + " " + buildY + " " + buildZ);
                break;
            case (5):
                move += ("UNABLE TO BUILD");
                break;
        }

        return move;
    }

//    public String playFriendlyTurn(String input, ConcurrentLinkedQueue<Move> queue) {
//        String[] tokens = input.split(" ");
//        gameID = tokens[5];
//        moveNumber = Integer.parseInt(tokens[10]);
//        tile = tokens[12];
//        timeLimit = Integer.parseInt(tokens[7]);
//        MakeMoveInstruction instruction = new MakeMoveInstruction(gameID,moveNumber,tile);
//        writeToBuffer(instruction);
//        WeJustDidThisMove ourmove = readFromBuffer();
//        int tileX = ourmove.getTileSpot().getXForServer();
//        int tileY = ourmove.getTileSpot().getYForServer();
//        int tileZ = ourmove.getTileSpot().getZForServer();
//        int buildX = ourmove.getBuildSpot().getXForServer();
//        int buildY = ourmove.getBuildSpot().getYForServer();
//        int buildZ = ourmove.getBuildSpot().getZForServer();
//        int orientation = ourmove.getOrientation();
//        Terrain terrain = ourmove.getTerrain();
//
//        String move = "GAME " + gameID + " MOVE " + moveNumber + " AT " + tileX + " " + tileY + " " + tileZ + " " + orientation + " ";      //FIXME: Need to get orientation
//        switch (ourmove.getBuildType()) {
//            case (1):
//                move.concat("FOUND SETTLEMENT AT " + buildX + " " + buildY + " " + buildZ);
//                break;
//            case (2):
//                move.concat("EXPAND SETTLEMENT AT " + buildX + " " + buildY + " " + buildZ + " " + terrain) ;
//                break;
//            case (3):
//                move.concat("BUILD TOTORO SANCTUARY AT " + buildX + " " + buildY + " " + buildZ);
//                break;
//            case(4):
//                move.concat("BUILD TIGER PLAYGROUND AT " + buildX + " " + buildY + " " + buildZ);
//                break;
//            case(5):
//                move.concat("UNABLE TO BUILD");
//                break;
//        }
//        return move;
//    }

    public EnemyMove parseOpponentMove(String input) {
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
        EnemyMove.BuildType buildType;
        Terrain terrain = null;

        if (tokens[13].equals("FOUNDED")) {

            buildX = Integer.parseInt(tokens[16]);
            buildY = Integer.parseInt(tokens[17]);
            buildZ = Integer.parseInt(tokens[18]);
            buildType = EnemyMove.BuildType.MEEPLES_FOUNDING;
        } else if (tokens[13].equals("EXPANDED")) {
            buildX = Integer.parseInt(tokens[16]);
            buildY = Integer.parseInt(tokens[17]);
            buildZ = Integer.parseInt(tokens[18]);
            terrain = Terrain.valueOf(tokens[19]);
            buildType = EnemyMove.BuildType.MEEPLES_EXPANDING;
        } else if (tokens[14].equals("TOTORO")) {
            buildX = Integer.parseInt(tokens[17]);
            buildY = Integer.parseInt(tokens[18]);
            buildZ = Integer.parseInt(tokens[19]);
            buildType = EnemyMove.BuildType.TOTORO;

        } else {
            buildX = Integer.parseInt(tokens[17]);
            buildY = Integer.parseInt(tokens[18]);
            buildZ = Integer.parseInt(tokens[19]);
            buildType = EnemyMove.BuildType.TIGER;
        }

        EnemyMove enemyMove = new EnemyMove(gameid, movenumber, playerID, tileX, tileY, tileZ, orientation, buildX, buildY, buildZ, buildType, terrain);
        return enemyMove;
    }

    public String parseGameID(String input) {
        String tokens[] = input.split(" ");

        if (tokens[0].equals("GAME")) {
            return tokens[1];
        } else {
            return tokens[5];
        }
    }
}
