package Everything.Server;

import Everything.Server.MoveObjects.*;
import Everything.models.*;

import java.util.ArrayList;


public class TigerIslandProtocol {

    public String authenticateTournament(String input, String tournamentPassword, String userName, String userPassword) {
        String output = null;
        if (input.contains("WELCOME TO ANOTHER EDITION OF THUNDERDOME!")) {

            output = "ENTER THUNDERDOME " + tournamentPassword;
        } else {
            if (input.contains("TWO SHALL ENTER, ONE SHALL LEAVE")) {
                output = "I AM " + userName + " " + userPassword;
            }
        }
        return output;
    }

    public ArrayList<Terrain> parseTileTerrain(String tile) {
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

    public EnemyMove parseOpponentMove(String input) {
        String[] tokens = input.split(" ");
        String gameid = tokens[1];
        int movenumber = Integer.parseInt(tokens[3]);
        String playerID = tokens[5];
        String buildTileTerrain = tokens[7];
        int tileX = Integer.parseInt(tokens[9]);
        int tileY = Integer.parseInt(tokens[10]);
        int tileZ = Integer.parseInt(tokens[11]);
        int orientation = Integer.parseInt(tokens[12]);
        int buildX;
        int buildY;
        int buildZ;
        int buildType;
        Terrain expansionTerrain = null;

        if (tokens[13].equals("FOUNDED")) {

            buildX = Integer.parseInt(tokens[16]);
            buildY = Integer.parseInt(tokens[17]);
            buildZ = Integer.parseInt(tokens[18]);
            buildType = 1;
        } else if (tokens[13].equals("EXPANDED")) {
            buildX = Integer.parseInt(tokens[16]);
            buildY = Integer.parseInt(tokens[17]);
            buildZ = Integer.parseInt(tokens[18]);
            expansionTerrain = Terrain.valueOf(tokens[19]);
            buildType = 2;
        } else if (tokens[14].equals("TOTORO")) {
            buildX = Integer.parseInt(tokens[17]);
            buildY = Integer.parseInt(tokens[18]);
            buildZ = Integer.parseInt(tokens[19]);
            buildType = 3;

        } else {
            buildX = Integer.parseInt(tokens[17]);
            buildY = Integer.parseInt(tokens[18]);
            buildZ = Integer.parseInt(tokens[19]);
            buildType = 4;
        }

        ArrayList<Terrain> tileTerrain = parseTileTerrain(buildTileTerrain);
        EnemyMove enemyMove = new EnemyMove(gameid, movenumber, playerID, tileX, tileY, tileZ, orientation, buildX, buildY, buildZ, buildType, tileTerrain.get(0), tileTerrain.get(1), expansionTerrain);
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
