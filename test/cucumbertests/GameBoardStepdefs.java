package cucumbertests;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.Map;
import models.Player;

public class GameBoardStepdefs {

    private Map GameBoard;
    private Player player1;
    private Player player2;

    @Before
    public void beforeScenario(){
        GameBoard = new Map();
        player1 = new Player();
        player2 = new Player();
    }

    @Given("^There are no Tiles$")
    public void thereAreNoTiles() throws Throwable {
       // GameBoard.getMiddleHexagon() == null;
    }

    @When("^A Tile is placed$")
    public void aTileIsPlaced() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Tile starts a new board$")
    public void theTileStartsANewBoard() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The Tile is on level (\\d+)$")
    public void theTileIsOnLevel(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There is an existing board$")
    public void thereIsAnExistingBoard() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^placing a Tile adjacent to another Tile$")
    public void placingATileAdjacentToAnotherTile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Tile is placed on level (\\d+) in the correct spot$")
    public void theTileIsPlacedOnLevelInTheCorrectSpot(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
