package game.action.handlers;

import models.Map;
import models.MapSpot;
import models.Settlement;
import models.Terrain;

import java.util.ArrayList;

/**
 * Shows available expansion options
 * Expands while checking the game rules
 */
public class SettlementExpansionHandler {

    private Settlement settlement;
    private Map map;

    /**
     * Returns an ArrayList of the viable MapSpots the settlement can expand to
     */

    ArrayList<MapSpot> generateExpandableSettlementArea(){
        ArrayList<MapSpot> ValidSpotsForExpansion = new ArrayList<>();
        ArrayList<MapSpot> SettlementMapSpots = settlement.getMapSpots();

        final boolean visited[][] = new boolean[map.size()][map.size()];

        for(int i = 0; i< SettlementMapSpots.size(); i++){
            visited[SettlementMapSpots.get(i).getX()][SettlementMapSpots.get(i).getY()] = true;
        }

        for(int i = 0; i< SettlementMapSpots.size(); i++){

            MapSpot curr = SettlementMapSpots.get(i);

            for(int j = 0; j < curr.getAdjacentMapSpots().size(); j++){

                MapSpot currentAdjacentSpot = curr.getAdjacentMapSpots().get(i);

                if(CanBeExpandedTo(curr, currentAdjacentSpot, visited, i)){
                    visited[currentAdjacentSpot.getX()][currentAdjacentSpot.getY()] = true;
                    ValidSpotsForExpansion.add(currentAdjacentSpot);
                }

            }
        }

        return ValidSpotsForExpansion;
    }

    private boolean CanBeExpandedTo(MapSpot curr, MapSpot currentAdjacentSpot,boolean[][] visited, int i) {
        return curr.getAdjacentMapSpots().get(i) != null
                && Math.abs(map.getHexagon(currentAdjacentSpot).getLevel() - map.getHexagon(curr).getLevel()) <= 1
                && map.getHexagon(currentAdjacentSpot).isEmpty()
                && map.getHexagon(currentAdjacentSpot).getTerrainType() != Terrain.VOLCANO
                && !visited[currentAdjacentSpot.getX()][currentAdjacentSpot.getY()];
    }

    SettlementExpansionHandler(Settlement settlement, Map map){
        this.settlement = settlement;
        this.map = map;
    }
}
