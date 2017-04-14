package Everything.game.action.handlers;

import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.game.action.scanners.settlemenet.expanding.SettlementExpansionMeeplesCost;
import Everything.game.action.scanners.settlemenet.expanding.SettlementTouchingExpansionScanner;
import Everything.game.action.scanners.settlemenet.expanding.TigerSpotScanner;
import Everything.game.action.scanners.settlemenet.expanding.TotoroSpotScanner;
import org.junit.Before;
import Everything.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class SettlementExpansionHandlerTest {

    private Map map;
    private SettlementExpansionHandler handler;

    @Before
    public void generateMapForTesting() throws Exception{
        map = new Map();
        FirstLevelTileAdditionHandler tileHandler = new FirstLevelTileAdditionHandler();
        handler = new SettlementExpansionHandler(new SettlementExpansionMeeplesCost());

        tileHandler.addFirstTileToMap(map);

        MapSpot curr = map.getMiddleHexagonMapSpot().topLeft().left().bottomLeft();

        try {
            tileHandler.addTileToMap(
                    new Hexagon(Terrain.VOLCANO, 1, 2),
                    new Hexagon(Terrain.GRASS, 1, 2),
                    new Hexagon(Terrain.LAKE, 1, 2),
                    curr.topRight(),
                    curr.topRight().topRight(),
                    curr.topRight().topLeft(),
                    map);
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }

        try {
            tileHandler.addTileToMap(
                    new Hexagon(Terrain.GRASS, 1, 1),
                    new Hexagon(Terrain.ROCK, 1, 1),
                    new Hexagon(Terrain.VOLCANO, 1, 1),
                    curr,
                    curr.left(),
                    curr.topLeft(),
                    map);
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }

        try {
            tileHandler.addTileToMap(
                    new Hexagon(Terrain.VOLCANO, 1, 3),
                    new Hexagon(Terrain.LAKE, 1, 3),
                    new Hexagon(Terrain.ROCK, 1, 3),
                    curr.left().left(),
                    curr.topLeft().left(),
                    curr.left().left().topLeft(),
                    map);
        } catch (CannotPerformActionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExpandWithMeeples(){
        Team team = RandomGenerator.generateRandomTeam();

        MapSpot middle = map.getMiddleHexagonMapSpot();
        SettlementFoundingHandler foundingHandler = new SettlementFoundingHandler();
        try {
            foundingHandler.foundSettlement(middle.topLeft(), map, new Player(team));
        } catch (CannotPerformActionException e) {
            Assert.fail();
        }

        SettlementAdjacentMapSpotsScanner scanner = new SettlementAdjacentMapSpotsScanner();
        SettlementsFactory settlementsFactory = new SettlementsFactory();
        Settlement currentSettlement = settlementsFactory.generateSettlements(map,team).get(0);
        ArrayList<MapSpot> adjSpots = scanner.generate(currentSettlement, map);


        try {
            handler.expandWithMeeples(adjSpots, map, new Player(team));
        } catch (CannotPerformActionException e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }

        Assert.assertTrue(currentSettlement.size() == 2);
    }

    @Test
    public void testInvalidExpandWithTotoros(){
        Team team = RandomGenerator.generateRandomTeam();

        MapSpot middle = map.getMiddleHexagonMapSpot();
        SettlementFoundingHandler foundingHandler = new SettlementFoundingHandler();
        try {
            foundingHandler.foundSettlement(middle.topLeft(), map, new Player(team));
        } catch (CannotPerformActionException e) {
            Assert.fail();
        }

        TotoroSpotScanner scanner = new TotoroSpotScanner(new SettlementTouchingExpansionScanner(new SettlementAdjacentMapSpotsScanner()));
        SettlementsFactory settlementsFactory = new SettlementsFactory();
        Settlement currentSettlement = settlementsFactory.generateSettlements(map,team).get(0);


        try {
            handler.expandWithTotoro(scanner.scan(currentSettlement, map).get(0), map, new Player(team));
        } catch (CannotPerformActionException | NoValidActionException e) {

            Assert.assertTrue(currentSettlement.size() == 2);
        }

    }

    @Test
    public void testInvalidExpandWithTigers() {
        Team team = RandomGenerator.generateRandomTeam();

        MapSpot middle = map.getMiddleHexagonMapSpot();
        SettlementFoundingHandler foundingHandler = new SettlementFoundingHandler();
        try {
            foundingHandler.foundSettlement(middle.topLeft(), map, new Player(team));
        } catch (CannotPerformActionException e) {
            Assert.fail();
        }

        TigerSpotScanner tigerSpotScanner = new TigerSpotScanner(new SettlementTouchingExpansionScanner(new SettlementAdjacentMapSpotsScanner()));
        SettlementsFactory settlementsFactory = new SettlementsFactory();
        Settlement currentSettlement = settlementsFactory.generateSettlements(map,team).get(0);


        try {
            handler.expandWithTiger(tigerSpotScanner.scan(currentSettlement, map).get(0), map, new Player(team));
        } catch (CannotPerformActionException | NoValidActionException e) {
            Assert.assertTrue(currentSettlement.size() == 1);
        }

    }

    //Helpers:
    //--------
    private boolean listsAreEqual(ArrayList<MapSpot> a, ArrayList<MapSpot> b){
        boolean equal = true;
        for(int i = 0; i< a.size(); i++){
            boolean found = false;
            for(int j =0 ; j< b.size(); j++){
                if(isEqual(a.get(i), b.get(j))){
                    found = true;
                }
            }
            if(!found){
                equal = false;
                break;
            }
        }
        return equal && a.size() == b.size();
    }

    private boolean isEqual(MapSpot m1, MapSpot m2) {
        return m1.getX() == m2.getX() && m1.getY() == m2.getY();
    }


}

