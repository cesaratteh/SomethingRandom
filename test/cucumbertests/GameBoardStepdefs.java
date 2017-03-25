package cucumbertests;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import models.*;

public class GameBoardStepdefs {

    private Map GameBoard;
    private Player player1;
    private Player player2;
    private Hexagon Hex1;
    private Hexagon Hex2;
    private Hexagon Hex3;

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
        }
        return null;
    }

    @Before
    public void beforeScenario(){
        GameBoard = new Map();
        player1 = new Player();
        player2 = new Player();
    }

    @Given("^There are no Tiles$")
    public void thereAreNoTiles() throws Throwable {
        if(GameBoard.getMiddleHexagon() == null){

        }
    }

    @When("^A Tile is placed$")
    public void aTileIsPlaced() throws Throwable {
//        Hex1 = new Hexagon(generateRandomTerrain(),0,0);
//        Hex2 = new Hexagon(generateRandomTerrain(),0,0);
//        Hex3 = new Hexagon(Terrain.VOLCANO,0,0);
//
//        GameBoard.addHexagon(new MapSpot(0,0),Hex1);
//        GameBoard.addHexagon(new MapSpot(2,0),Hex2);
//        GameBoard.addHexagon(new MapSpot(1,1),Hex3);
        // FIXME: 3/25/2017 Added new dependency to constructor of hexagon. You can probably use RandomGenerator.generateRandomHexagon()
    }

    @Then("^The Tile starts a new board$")
    public void theTileStartsANewBoard() throws Throwable {
        if(GameBoard.getMiddleHexagon() != null){
            //do stuff
        }
    }

    @And("^The Tile is on level one")
    public void theTileIsOnLevelOne() throws Throwable {
        if(0 == GameBoard.getHexagon(new MapSpot(0,0)).getLevel()){
            //do stuff
        }
    }

    @Given("^There is an existing board$")
    public void thereIsAnExistingBoard() throws Throwable {
        if(GameBoard != null) {
            //do stuff
        }
    }

    @When("^placing a Tile adjacent to another Tile$")
    public void placingATileAdjacentToAnotherTile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Tile is placed on level one in the correct spot$")
    public void theTileIsPlacedOnLevelOneInTheCorrectSpot() throws Throwable {
        if(GameBoard.getHexagon(new MapSpot(0,0)).getLevel() == 1 &&
                GameBoard.getHexagon(new MapSpot(0,0)) == Hex2 &&
                GameBoard.getHexagon(new MapSpot(2,0)) == Hex3 &&
                GameBoard.getHexagon(new MapSpot(1,1)) == Hex1){

        }
    }
}
