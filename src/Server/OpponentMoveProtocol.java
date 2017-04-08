/*
package Server;

import models.*;

public class OpponentMoveProtocol {

    // Example Input: MAKE YOUR MOVE IN GAME <GID> WITHIN <TIME> SECONDS: MOVE <MOVEID> PLAYER <PID> PLACED <TILE> AT <X> <Y> <Z> <ORIENTATION> FOUNDED SETTLEMENT AT <X> <Y> <Z>

    private int placedVolcanoXCoordinate;
    private int placedVolcanoYCoordinate;
    private int placedVolcanoZCoordinate;

    public void parseOpponentInput(String input) {
        String[] tokens = input.split(" ");

        placedTile = tokens[14];
        String[] terainTokens = placedTile.split("+");
        placedTerrainA = terrainTokens[0];
        placedTerrainB = terrainTokens[1];

        placedVolcanoXCoordinate = Integer.parseInt(tokens[16]);
        placedVolcanoYCoordinate = Integer.parseInt(tokens[17]);
        placedVolacnoZCoordinate = Integer.parseInt(tokens[18]);

        placedOrientation = Interger.parseInt(tokens[19]);

        buildType = tokens[20] + tokens[21];

        if (buildType.equals("FoundedSettlement")) {
            builtXCoordinate = Integer.parseInt(tokens[23]);
            builtYCoordinate = Integer.parseInt(tokens[24]);
            builtZCoordinate = Integer.parseInt(tokens[25]);
        }
        else if (buildType.equals("ExpandedSettlement")) {
            builtXCoordinate = Integer.parseInt(tokens[23]);
            builtYCoordinate = Integer.parseInt(tokens[24]);
            builtZCoordinate = Integer.parseInt(tokens[25]);
            builtTerrainType = tokens[26]
        }
        else if (buildType.equals("BuiltTotoro")) {
            builtXCoordinate = Integer.parseInt(tokens[24]);
            builtYCoordinate = Integer.parseInt(tokens[25]);
            builtZCoordinate = Integer.parseInt(tokens[26]);
        }
        else if (buildType.equals("BuiltTiger")) {
            builtXCoordinate = Integer.parseInt(tokens[24]);
            builtYCoordinate = Integer.parseInt(tokens[25]);
            builtZCoordinate = Integer.parseInt(tokens[26]);
        }
        else {
            // buildType out of bounds
            builtXCoordinate = Integer.parseInt(tokens[24]);
        }
    }

    public void deriveTileCoordinates(int placedOrientation) {
        placedTerrainAXCoordinate = placedVolcanoXCoordinate;
        placedTerrainAYCoordinate = placedVolcanoXCoordinate;
        placedTerrainAZCoordinate = placedVolcanoXCoordinate;

        placedTerrainBXCoordinate = placedVolcanoXCoordinate;
        placedTerrainBYCoordinate = placedVolcanoXCoordinate;
        placedTerrainBZCoordinate = placedVolcanoXCoordinate;

        if (placedOrientation == 1) {
            placedTerrainAXCoordinate = placedVolcanoXCoordinate;
            placedTerrainAYCoordinate = placedVolcanoYCoordinate + 1;
            placedTerrainAZCoordinate = placedVolcanoZCoordinate - 1;

            placedTerrainBXCoordinate = placedVolcanoXCoordinate + 1;
            placedTerrainBYCoordinate = placedVolcanoYCoordinate;
            placedTerrainBZCoordinate = placedVolcanoZCoordinate - 1;
        }
        else if (placedOrientation == 2) {
            placedTerrainAXCoordinate = placedVolcanoXCoordinate + 1;
            placedTerrainAYCoordinate = placedVolcanoYCoordinate;
            placedTerrainAZCoordinate = placedVolcanoZCoordinate - 1;

            placedTerrainBXCoordinate = placedVolcanoXCoordinate + 1;
            placedTerrainBYCoordinate = placedVolcanoYCoordinate - 1;
            placedTerrainBZCoordinate = placedVolcanoZCoordinate;
        }
        else if (placedOrientation == 3) {
            placedTerrainAXCoordinate = placedVolcanoXCoordinate + 1;
            placedTerrainAYCoordinate = placedVolcanoYCoordinate - 1;
            placedTerrainAZCoordinate = placedVolcanoZCoordinate;

            placedTerrainBXCoordinate = placedVolcanoXCoordinate;
            placedTerrainBYCoordinate = placedVolcanoYCoordinate - 1;
            placedTerrainBZCoordinate = placedVolcanoZCoordinate + 1;
        }
        else if (placedOrientation == 4) {
            placedTerrainAXCoordinate = placedVolcanoXCoordinate;
            placedTerrainAYCoordinate = placedVolcanoYCoordinate - 1;
            placedTerrainAZCoordinate = placedVolcanoZCoordinate + 1;

            placedTerrainBXCoordinate = placedVolcanoXCoordinate - 1;
            placedTerrainBYCoordinate = placedVolcanoYCoordinate;
            placedTerrainBZCoordinate = placedVolcanoZCoordinate + 1;
        }
        else if (placedOrientation == 5) {
            placedTerrainAXCoordinate = placedVolcanoXCoordinate - 1;
            placedTerrainAYCoordinate = placedVolcanoYCoordinate;
            placedTerrainAZCoordinate = placedVolcanoZCoordinate + 1;

            placedTerrainBXCoordinate = placedVolcanoXCoordinate - 1;
            placedTerrainBYCoordinate = placedVolcanoYCoordinate + 1;
            placedTerrainBZCoordinate = placedVolcanoZCoordinate;
        }
        else if (placedOrientation == 6) {
            placedTerrainAXCoordinate = placedVolcanoXCoordinate - 1;
            placedTerrainAYCoordinate = placedVolcanoYCoordinate + 1;
            placedTerrainAZCoordinate = placedVolcanoZCoordinate;

            placedTerrainBXCoordinate = placedVolcanoXCoordinate;
            placedTerrainBYCoordinate = placedVolcanoYCoordinate + 1;
            placedTerrainBZCoordinate = placedVolcanoZCoordinate - 1;
        }
        else {
            // Orientation Input Out of Bounds
            placedTerrainAXCoordinate = placedVolcanoXCoordinate;
        }
    }
}
*/
