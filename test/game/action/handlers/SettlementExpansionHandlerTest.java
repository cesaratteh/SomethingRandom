package game.action.handlers;

import org.junit.Before;
import models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class SettlementExpansionHandlerTest {

    private Map map;
    private Player player;
    private Settlement settlement;
    private SettlementExpansionHandler handler;

    @Before
    public void generateMapForTesting(){
        this.map = new Map();
        this.player = new Player(Team.FRIENDLY);
        this.settlement = new Settlement(Team.FRIENDLY);
        this.handler = new SettlementExpansionHandler(map,settlement);

        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.setHexagon(curr, new Hexagon( Terrain.GRASSLAND, 1, 1));
        map.setHexagon(curr.left(), new Hexagon(Terrain.ROCKY, 1, 1));
        map.setHexagon(curr.topLeft(), new Hexagon(Terrain.VOLCANO, 1, 1));

        map.setHexagon(curr.topRight(), new Hexagon(Terrain.VOLCANO, 1, 2));
        map.setHexagon(curr.topRight().topRight(), new Hexagon(Terrain.GRASSLAND, 1, 2));
        map.setHexagon(curr.topRight().topLeft(), new Hexagon(Terrain.LAKE, 1, 2));

        map.setHexagon(curr.left().left(), new Hexagon(Terrain.VOLCANO, 1, 3));
        map.setHexagon(curr.topLeft().left(), new Hexagon(Terrain.ROCKY, 1, 3));
        map.setHexagon(curr.left().left().topLeft(), new Hexagon(Terrain.ROCKY, 1, 3));

    }

    @Test
    public void testGenerateExpandableSettlementAreaFromStart(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        ArrayList<MapSpot> validSpots = handler.generateExpandableSettlementArea();

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());

        Assert.assertTrue(listsAreEqual(actualSpots,validSpots));

    }

    @Test
    public void testGenerateExpandableSettlementAreaFromDifferentSpot(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        Hexagon hex = map.getHexagon(middle.left().topLeft());
        
        hex.addMeeples(settlement.getTeam());
        settlement.add(middle.left().topLeft(), hex);

        ArrayList<MapSpot> validSpots = handler.generateExpandableSettlementArea();

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());
        actualSpots.add(middle.left().topLeft().left());

        Assert.assertTrue(listsAreEqual(actualSpots,validSpots));
    }

    @Test
    public void testGenerateChainedSpots(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        ArrayList<MapSpot> validSpots = handler.generateChainedSpots(middle.left());

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());
        actualSpots.add(middle.left().topLeft());
        actualSpots.add(middle.left().topLeft().left());

        Assert.assertTrue(listsAreEqual(actualSpots,validSpots));
    }

    @Test
    public void testGenerateAllChainedSpots(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        ArrayList<ArrayList<MapSpot>> chains = handler.generateAllChainedSpots();

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());
        actualSpots.add(middle.left().topLeft());
        actualSpots.add(middle.left().topLeft().left());

        Assert.assertTrue(listsAreEqual(actualSpots,chains.get(0)));
    }

    @Test // might want to expand this test
    public void testGetValidTigerSpots(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        ArrayList<MapSpot> validTigerSpots = handler.generateAllTigerSpots();

        Assert.assertTrue(validTigerSpots.isEmpty());
    }

    @Test // need to expand this one as well
    public void testGetValidTotoroSpots(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        ArrayList<MapSpot> validTotoroSpots = handler.generateAllTotoroSpots();

        Assert.assertTrue(validTotoroSpots.isEmpty());
    }

    @Test
    public void testExpandWithMeeples(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle,map.getHexagon(middle));
        handler.expandWithMeeples(middle.left());

        Assert.assertTrue(settlement.size() == 4);
    }

    @Test
    public void testExpandWithTotoros(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        try{
            handler.expandWithTotoro(middle.left());
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad expansion with Totoro");
        }
    }

    @Test
    public void testExpandWithTigers() {
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle, map.getHexagon(middle));

        try{
            handler.expandWithTiger(middle.left());
        }
        catch(RuntimeException e){
            Assert.assertTrue(e.getMessage() == "Bad expansion with Tiger");
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

