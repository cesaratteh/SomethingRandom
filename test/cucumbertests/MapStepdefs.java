//package cucumbertests;
//
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import cucumber.api.java8.En;
//import FirstLevelTileAdditionHandler;
//import Everything.models.*;
//import org.junit.Assert;
//
//public class MapStepdefs implements En {
//
//        private Map map;
//        private Player player1;
//        private Player player2;
//        private Hexagon th1;
//        private Hexagon th2;
//        private Hexagon th3;
//
//        private Hexagon fh1;
//        private Hexagon fh2;
//        private Hexagon fh3;
//        private Hexagon fh4;
//        private Hexagon fh5;
//
//        private MapSpot m1;
//        private MapSpot m2;
//        private MapSpot m3;
//        private MapSpot m4;
//        private MapSpot m5;
//
//        private FirstLevelTileAdditionHandler fhandler;
//
//
//
//    private Terrain generateRandomTerrain() {
//        int terrainCode = (int)(Math.random()*5);
//        switch(terrainCode){
//            case 0:
//                return Terrain.JUNGLE;
//            case 1:
//                return Terrain.LAKE;
//            case 2:
//                return Terrain.GRASS;
//            case 3:
//                return Terrain.ROCK;
//        }
//        return null;
//    }
//
//    @Before
//    public void beforeScenario(){
//        map = new Map();
//        player1 = new Player(Team.FRIENDLY);
//        player2 = new Player(Team.ENEMY);
//        fhandler = new FirstLevelTileAdditionHandler(map);
//    }
//
//    @Given("^There are no Tiles$")
//    public void thereAreNoTiles() throws Throwable {
//        if(map.getMiddleHexagon() != null)
//            Assert.fail("There are existing things on the board");
//        System.out.println("Passed");
//    }
//
//    @When("^A Tile is placed$")
//    public void aTileIsPlaced() throws Throwable {
//
//        map = new Map();
//        fh1 = new Hexagon(Terrain.LAKE, 1, 0);
//        fh2 = new Hexagon(Terrain.ROCK, 1, 0);
//        fh3 = new Hexagon(Terrain.VOLCANO, 1, 0);
//        fh4 = new Hexagon(Terrain.GRASS, 1, 0);
//        fh5 = new Hexagon(Terrain.JUNGLE, 1, 0);
//
//        m3 = map.getMiddleHexagonMapSpot();
//        m1 = m3.topLeft();
//        m2 = m3.topRight();
//        m4 = m3.bottomLeft();
//        m5 = m3.bottomRight();
//
//        fhandler.addFirstTileToMap(fh1, fh2, fh3, fh4, fh5, m1, m2, m3, m4, m5);
//
//    }
//
//    @And("^The Tile is a FirstTile$")
//    public void theTileIsAFirstTile() throws Throwable {
//        if(fh1.getTileId() != 0
//                ||fh2.getTileId() != 0
//                ||fh3.getTileId() != 0
//                ||fh4.getTileId() != 0
//                ||fh5.getTileId() != 0)
//            Assert.fail("Tile is not a first tile");
//    }
//
//    @Then("^The Tile starts a new board in the center$")
//    public void theTileStartsANewBoard() throws Throwable {
//
//        if(map.getMiddleHexagon() == null)
//            Assert.fail("The board is empty in the center");
//    }
//
//    @And("^The Tile is on level one")
//    public void theTileIsOnLevelOne() throws Throwable {
//        if(fh1.getTileId() != 0
//                ||fh2.getTileId() != 0
//                ||fh3.getTileId() != 0
//                ||fh4.getTileId() != 0
//                ||fh5.getTileId() != 0)
//            Assert.fail("Tile may not be on level 1");
//
//    }
//
//    @Given("^There is an existing board$")
//    public void thereIsAnExistingBoard() throws Throwable {
//        if(map.getMiddleHexagon() == null) {
//            Assert.fail("Board Doesn't Exist");
//        }
//    }
//
//    @When("^placing a Tile adjacent to another Tile$")
//    public void placingATileAdjacentToAnotherTile() throws Throwable {
//        th1 = new Hexagon(Terrain.GRASS, 1, 1);
//        th2 = new Hexagon(Terrain.LAKE, 1, 1);
//        th3 = new Hexagon(Terrain.VOLCANO, 1, 1);
//
//        m1 = map.getMiddleHexagonMapSpot().left();
//        m2 = m1.topLeft();
//        m3 = m1.left();
//
//        fhandler.addTileToMap(th1,th2,th3,  m1,m2,m3);
//    }
//
//    @Then("^The Tile is placed on level one in the correct spot$")
//    public void theTileIsPlacedOnLevelOneInTheCorrectSpot() throws Throwable {
//        MapSpot middle = map.getMiddleHexagonMapSpot();
//
//        if (map.getHexagon(middle).getLevel() != 1 ||
//                !hexIsEqual(map.getHexagon(m1),th1) ||
//                !hexIsEqual(map.getHexagon(m2),th2) ||
//                !hexIsEqual(map.getHexagon(m3),th1)) {
//            Assert.fail("Tile is not where it is expected");
//        }
//    }
//
//    private boolean hexIsEqual(Hexagon hex1, Hexagon hex2) {
//        return (hex1.getLevel() == hex2.getLevel()
//                && hex1.getOccupiedBy() == hex2.getOccupiedBy()
//                && hex1.getTerrainType() == hex2.getTerrainType()
//                && hex1.getNumberOfMeeples() == hex2.getNumberOfMeeples()
//                && hex1.isHasTiger() == hex2.isHasTiger()
//                && hex1.isHasTotoro() == hex2.isHasTotoro()
//                && hex1.getTileId() == hex2.getTileId()
//                && hex1.isEmpty() == hex2.isEmpty());
//    }
//}
