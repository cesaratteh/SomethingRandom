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

    @Before
    public void generateMapForTesting(){
        this.map = new Map();
        this.player = new Player(Team.FRIENDLY);
        this.settlement = new Settlement(Team.FRIENDLY, map, player);

        MapSpot curr = map.getMiddleHexagonMapSpot();

        map.addHexagon(curr, new Hexagon( player, Terrain.GRASSLAND, 1, 1));
        map.addHexagon(curr.left(), new Hexagon( player, Terrain.ROCKY, 1, 1));
        map.addHexagon(curr.topLeft(), new Hexagon( player, Terrain.VOLCANO, 1, 1));

        map.addHexagon(curr.topRight(), new Hexagon( player, Terrain.VOLCANO, 1, 2));
        map.addHexagon(curr.topRight().topRight(), new Hexagon( player, Terrain.GRASSLAND, 1, 2));
        map.addHexagon(curr.topRight().topLeft(), new Hexagon( player, Terrain.LAKE, 1, 2));

        map.addHexagon(curr.left().left(), new Hexagon( player, Terrain.VOLCANO, 1, 3));
        map.addHexagon(curr.topLeft().left(), new Hexagon( player, Terrain.ROCKY, 1, 3));
        map.addHexagon(curr.left().left().topLeft(), new Hexagon( player, Terrain.ROCKY, 1, 3));

    }

    @Test
    public void testGenerateExpandableSettlementAreaFromStart(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle);

        ArrayList<MapSpot> validSpots = settlement.getValidExpansionSpots();

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());

        Assert.assertTrue(listsAreEqual(actualSpots,validSpots));

    }

    @Test
    public void testGenerateExpandableSettlementAreaFromDifferentSpot(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle.left().topLeft());

        ArrayList<MapSpot> validSpots = settlement.getValidExpansionSpots();

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());
        actualSpots.add(middle.left().topLeft().left());

        Assert.assertTrue(listsAreEqual(actualSpots,validSpots));
    }

    @Test
    public void testGenerateChainedSpots(){
        MapSpot middle = map.getMiddleHexagonMapSpot();
        settlement.add(middle);

        ArrayList<MapSpot> validSpots = settlement.getChainedSpots(middle.left());

        ArrayList<MapSpot> actualSpots = new ArrayList<>();
        actualSpots.add(middle.left());
        actualSpots.add(middle.left().topLeft());
        actualSpots.add(middle.left().topLeft().left());

        Assert.assertTrue(listsAreEqual(actualSpots,validSpots));
    }

    private boolean listsAreEqual(ArrayList<MapSpot> a, ArrayList<MapSpot> b){
        boolean equal = true;
        for(int i = 0; i< a.size(); i++){
            boolean found = false;
            for(int j =0 ; j< b.size(); j++){
                if(a.get(i).isEqual(b.get(j))){
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

}

