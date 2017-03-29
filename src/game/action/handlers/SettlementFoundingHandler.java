package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Shows available options for settlement expansion
 * Expands while checking the game rules
 */
public class SettlementFoundingHandler {

    private final Map map;

    public SettlementFoundingHandler(Map map) {
        this.map = map;
    }

    public ArrayList<MapSpot> generateAllPossibleSettlementMapSpots(){
        ArrayList<MapSpot> AllPossibleSettlementMapSpots = new ArrayList<>();

        Stack<Hexagon> HexStack = new Stack<Hexagon>();

        HexStack.push(map.getMiddleHexagon());

        while(!HexStack.empty()){

        }


        return AllPossibleSettlementMapSpots;
    }






}
