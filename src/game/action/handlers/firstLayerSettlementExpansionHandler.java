package game.action.handlers;

import models.Map;
import models.MapSpot;
import models.Settlement;
import models.Terrain;

import java.util.ArrayList;

public class firstLayerSettlementExpansionHandler {

    private Map map;

    /**
     * Returns an ArrayList of the viable MapSpots the settlement can expand to.
     * Only returns the first layer of expandable spots (expandable layer of hexagons immediately surrounding settlement)
     * Can be used for tiger/totoro expansions but not meeples.
     */

    ArrayList<MapSpot> generateExpandableSettlementArea(final Settlement settlement){
        final ArrayList<MapSpot> validSpotsForExpansion = new ArrayList<>();
        final ArrayList<MapSpot> settlementMapSpots = settlement.getMapSpots();

        final boolean visited[][] = new boolean[map.size()][map.size()];

        for (final MapSpot settlementMapSpot : settlementMapSpots) {
            visited[settlementMapSpot.getX()][settlementMapSpot.getY()] = true;
        }

        for(int i = 0; i< settlementMapSpots.size(); i++){

            MapSpot curr = settlementMapSpots.get(i);

            for(int j = 0; j < curr.getAdjacentMapSpots().size(); j++){

                MapSpot currentAdjacentSpot = curr.getAdjacentMapSpots().get(i);

                if(CanBeExpandedTo(curr, currentAdjacentSpot, visited, i)){
                    visited[currentAdjacentSpot.getX()][currentAdjacentSpot.getY()] = true;
                    validSpotsForExpansion.add(currentAdjacentSpot);
                }
            }
        }

        return validSpotsForExpansion;
    }

    private boolean CanBeExpandedTo(MapSpot curr, MapSpot currentAdjacentSpot,boolean[][] visited, int i) {
        return curr.getAdjacentMapSpots().get(i) != null
                && Math.abs(map.getHexagon(currentAdjacentSpot).getLevel() - map.getHexagon(curr).getLevel()) <= 1
                && map.getHexagon(currentAdjacentSpot).isEmpty()
                && map.getHexagon(currentAdjacentSpot).getTerrainType() != Terrain.VOLCANO
                && !visited[currentAdjacentSpot.getX()][currentAdjacentSpot.getY()];
    }

    public firstLayerSettlementExpansionHandler(final Map map){
        this.map = map;
    }
}
