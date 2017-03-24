package cucumbertests;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.*;

public class TileStepdefs {

    private Terrain generateRandomTerrain() {
        int terrainCode = (int)(Math.random()*5);
        switch(terrainCode){
            case 0:
                return Terrain.JUNGLE;
            case 1:
                return Terrain.LAKE;
            case 2:
                return Terrain.GRASSLAND;
            case 3:
                return Terrain.ROCKY;
            case 4:
                return Terrain.VOLCANO;
        }
        return null;
    }

    private Player player1;
    private Player player2;

    @Before
    public void beforeScenario(){
        player1 = new Player();
        player2 = new Player();
    }

    @Given("^A Player draws a Tile$")
    public void aPlayerDrawsATile() throws Throwable {
         //player1.drawTile();
         //Tile t = player1.drawTile();

        throw new PendingException();
    }

    @When("^The Tile is chosen from the set of remaining Tiles$")
    public void theTileIsChosenFromTheSetOfRemainingTiles() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @Then("^The Tile has all (\\d+) appropriately attached Hexagons$")
    public void theTileHasAllAppropriatelyAttachedHexagons(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The Tile has (\\d+) volcano and (\\d+) other Terrain types$")
    public void theTileHasVolcanoAndOtherTerrainTypes(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^The Player looks at a Hexagon contained in the Tile$")
    public void thePlayerLooksAtAHexagonContainedInTheTile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Hexagon has a Terrain$")
    public void theHexagonHasATerrain() throws Throwable {

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The Hexagon has an empty space for Meeples or Totoros$")
    public void theHexagonHasAnEmptySpaceForMeeplesOrTotoros() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There are Tiles on level (\\d+) or higher on the board$")
    public void thereAreTilesOnLevelOrHigherOnTheBoard(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^The Player wants to nuke some Hexagons with a new Tile$")
    public void thePlayerWantsToNukeSomeHexagonsWithANewTile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^At least (\\d+) of the Hexagons are on different Tiles$")
    public void atLeastOfTheHexagonsAreOnDifferentTiles(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^All of the Hexagons are on the same level$")
    public void allOfTheHexagonsAreOnTheSameLevel() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Player nukes the Hexagons$")
    public void thePlayerNukesTheHexagons() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Places the Tile$")
    public void placesTheTile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^All of the Hexagons belong to the same Tile$")
    public void allOfTheHexagonsBelongToTheSameTile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
