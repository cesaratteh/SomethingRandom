package Everything.game.action.scanners.Nuking;

import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

/**
 * Scans for all map spots that are "touching" the settlement.
 * It scans for MapSpots NOT Hexagons (the MapSpot could be empty)
 */
public class SettlementAdjacentMapSpotsScanner {

    //---------------
    // Public Methods

    public ArrayList<MapSpot> generate(final Settlement settlement, final Map map) {

        final ArrayList<MapSpot> boarderLine = new ArrayList<>();

        boolean[][][] heatMap = initializeHeatMap(settlement);

        for (MapSpot settlementMapSpot : settlement.getMapSpots()) {
            for (MapSpot adjacentMapSpot : settlementMapSpot.getAdjacentMapSpots()) {

                if (!isMapSpotInHeatMap(adjacentMapSpot, heatMap)) {
                    boarderLine.add(adjacentMapSpot);
                    heatMap[adjacentMapSpot.getX()][adjacentMapSpot.getY()][adjacentMapSpot.getZ()] = true;
                }
            }
        }

        return boarderLine;
    }

    //----------------
    // Private Methods

    private boolean[][][] initializeHeatMap(final Settlement settlement) {

        boolean[][][] settlementHeatMap = new boolean[Map.size()][Map.size()][Map.size()];

        for (MapSpot settlementMapSpot : settlement.getMapSpots()) {
            settlementHeatMap[settlementMapSpot.getX()][settlementMapSpot.getY()][settlementMapSpot.getZ()] = true;
        }

        return settlementHeatMap;
    }

    private boolean isMapSpotInHeatMap(final MapSpot mapSpot, final boolean[][][] settlementHeatMap) {
        return settlementHeatMap[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()];
    }
}
