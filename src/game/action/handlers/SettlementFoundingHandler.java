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
        ArrayList<MapSpot> ValidMapSpotsForSettlementFounding = new ArrayList<>();
        LinkedList<MapSpot> NotVisitedHexagons = new LinkedList<>();

        final boolean visited[][] = new boolean[map.getMapSize()][map.getMapSize()];

        visited[map.getMiddleHexagonMapSpot().getX()][map.getMiddleHexagonMapSpot().getY()] = true;
        NotVisitedHexagons.add(map.getMiddleHexagonMapSpot());

        while(!NotVisitedHexagons.isEmpty()){

            MapSpot currentMapSpot = NotVisitedHexagons.getFirst();
            Hexagon currentHex = map.getHexagon(currentMapSpot);

            if(currentHex != null){
                //adding the MapSpot to the ArrayList
                if(currentHex.isEmpty() &&
                        currentHex.getTerrainType() != Terrain.VOLCANO &&
                        currentHex.getLevel() == 1){
                    ValidMapSpotsForSettlementFounding.add(currentMapSpot);
                }

                for(int i = 0; i<currentMapSpot.getAdjacentMapSpots().size(); i++){
                    MapSpot adjacentSpot = currentMapSpot.getAdjacentMapSpots().get(i);

                    if(adjacentSpot != null){
                        if(!visited[adjacentSpot.getX()][adjacentSpot.getY()] && map.getHexagon(adjacentSpot) != null){
                            NotVisitedHexagons.add(adjacentSpot);
                            visited[adjacentSpot.getX()][adjacentSpot.getY()] = true;
                        }
                    }
                }

            }
            NotVisitedHexagons.removeFirst();
        }

        return ValidMapSpotsForSettlementFounding;
    }



    public SettlementFoundingHandler(final Map map){
        this.map = map;
    }

}
