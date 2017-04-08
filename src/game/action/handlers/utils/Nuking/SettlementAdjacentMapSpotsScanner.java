package game.action.handlers.utils.Nuking;

import models.Map;
import models.MapSpot;
import models.Settlement;

import java.util.ArrayList;

/**
 * Scans for all map spots that are "touching" the settlement
 */
public class SettlementAdjacentMapSpotsScanner {

    //---------------
    // Public Methods

    public ArrayList<MapSpot> generate(final Settlement settlement, final Map map) {

        final ArrayList<MapSpot> boarderLine = new ArrayList<>();

        boolean[][][] settlementHeatMap = initializeHeatMap(settlement);

        for (MapSpot settlementMapSpot : settlement.getMapSpots()) {
            for (MapSpot adjacentMapSpot : settlementMapSpot.getAdjacentMapSpots()) {

                if (!isMapSpotInSettlement(adjacentMapSpot, settlementHeatMap)) {
                    boarderLine.add(adjacentMapSpot);
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

    private boolean isMapSpotInSettlement(final MapSpot mapSpot, final boolean[][][] settlementHeatMap) {
        return settlementHeatMap[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()];
    }
}
