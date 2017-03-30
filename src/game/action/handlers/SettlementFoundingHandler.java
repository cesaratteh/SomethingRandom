package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;

import java.util.*;

/**
 * Shows available options for settlement expansion
 * Expands while checking the game rules
 */
public class SettlementFoundingHandler {

    private final Map map;

    public ArrayList<MapSpot> generateValidMapSpotsForSettlementFounding(){
        final ArrayList<MapSpot> validMapSpotsForSettlementFounding = new ArrayList<>();
        final LinkedList<MapSpot> notVisitedHexagons = new LinkedList<>();

        final boolean visited[][] = new boolean[map.size()][map.size()];

        visited[map.getMiddleHexagonMapSpot().getX()][map.getMiddleHexagonMapSpot().getY()] = true;
        notVisitedHexagons.add(map.getMiddleHexagonMapSpot());

        while(!notVisitedHexagons.isEmpty()){

            MapSpot currentMapSpot = notVisitedHexagons.getFirst();
            Hexagon currentHex = map.getHexagon(currentMapSpot);

            if(currentHex != null){
                //adding the MapSpot to the ArrayList
                if(currentHex.isEmpty() &&
                        currentHex.getTerrainType() != Terrain.VOLCANO &&
                        currentHex.getLevel() == 1){
                    validMapSpotsForSettlementFounding.add(currentMapSpot);
                }

                for(int i = 0; i<currentMapSpot.getAdjacentMapSpots().size(); i++){
                    MapSpot adjacentSpot = currentMapSpot.getAdjacentMapSpots().get(i);

                    if(adjacentSpot != null){
                        if(!visited[adjacentSpot.getX()][adjacentSpot.getY()] && map.getHexagon(adjacentSpot) != null){
                            notVisitedHexagons.add(adjacentSpot);
                            visited[adjacentSpot.getX()][adjacentSpot.getY()] = true;
                        }
                    }
                }

            }
            notVisitedHexagons.removeFirst();
        }

        return validMapSpotsForSettlementFounding;
    }



    public SettlementFoundingHandler(final Map map){
        this.map = map;
    }

}