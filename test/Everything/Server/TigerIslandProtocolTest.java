package Everything.Server;

import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.models.Terrain;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class TigerIslandProtocolTest {

    @Test
    public void authenticateTournamentTest() {
        TigerIslandProtocol tigerIslandProtocol = new TigerIslandProtocol();

        String welcome = "WELCOME TO ANOTHER EDITION OF THUNDERDOME!";
        String welcome2 = "TWO SHALL ENTER, ONE SHALL LEAVE";

        String response1 = tigerIslandProtocol.authenticateTournament(
                welcome, "heygang", "T", "T");

        String response2 = tigerIslandProtocol.authenticateTournament(
                welcome2, "heygang", "T", "T");

        assertEquals("ENTER THUNDERDOME heygang", response1);
        assertEquals("I AM T T", response2);
    }

    @Test
    public void parseTileTerrainTest() {
        TigerIslandProtocol tigerIslandProtocol = new TigerIslandProtocol();

        String[] tiles = {"GRASS", "LAKE", "ROCK", "JUNGLE"};

        Random random = new Random();

        int index1 = random.nextInt(4);
        int index2 = random.nextInt(4);

        ArrayList<Terrain> terrains = tigerIslandProtocol.parseTileTerrain(tiles[index1] + "+" + tiles[index2]);
        assertEquals(Terrain.valueOf(tiles[index1]), terrains.get(0));
        assertEquals(Terrain.valueOf(tiles[index2]), terrains.get(1));
    }

    @Test
    public void getPlayerIdTest() {
        TigerIslandProtocol tigerIslandProtocol = new TigerIslandProtocol();

        String playerId = UUID.randomUUID().toString();
        String message = "WAIT FOR THE TOURNAMENT TO BEGIN " + playerId;

        assertEquals(playerId, tigerIslandProtocol.waitForTournyToBeginAndGetPlayerId(message));
    }

    @Test
    public void getMoveInstructionTest() {
        TigerIslandProtocol tigerIslandProtocol = new TigerIslandProtocol();
        Random random = new Random();

        String gameId = UUID.randomUUID().toString();
        int moveNumber = random.nextInt(Integer.MAX_VALUE);

        String[] tiles = {"GRASS", "LAKE", "ROCK", "JUNGLE"};

        int index1 = random.nextInt(4);
        int index2 = random.nextInt(4);

        String message = "MAKE YOUR MOVE IN GAME " + gameId + " WITHIN 1.5 SECONDS: MOVE " + moveNumber + " PLACE " + tiles[index1] + "+" + tiles[index2];

        MakeMoveInstruction moveInstruction = tigerIslandProtocol.getMoveInstruction(message);

        assertEquals(gameId, moveInstruction.getGameID());
        assertEquals(moveNumber, moveInstruction.getMoveNumber());
        assertEquals(Terrain.valueOf(tiles[index1]), moveInstruction.getTerrainA());
        assertEquals(Terrain.valueOf(tiles[index2]), moveInstruction.getTerrainB());
    }

    @Test
    public void createFriendlyMoveMessageToBeSentTest() {
        String message = "GAME <gid> MOVE <#> <place> <build> " +
                "where <place> := PLACE <tile> AT <x> <y> <z> <orientation> " +
                "where <build> := FOUND SETTLEMENT AT <x> <y> <z> or EXPAND SETTLEMENT AT <x> <y> <z> <terrain> or BUILD TOTORO SANCTUARY AT <x> <y> <z> or BUILD TIGER PLAYGROUND AT <x> <y> <z> or UNABLE TO BUILD";
    }
}