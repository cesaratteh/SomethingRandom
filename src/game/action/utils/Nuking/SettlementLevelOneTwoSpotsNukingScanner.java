package game.action.utils.Nuking;

import game.action.utils.NoValidActionException;
import models.Map;
import models.MapSpot;
import models.Settlement;
import models.TileMapSpot;

import java.util.ArrayList;

/**
 * Given an enemy settlement
 * returns a tile map spot: 3 regular map spots that are level 1. This tile map spot can be stacked on.
 * The volcano is placed in m1 (tileMapSpot.m1) of the tileMapSpot returned
 */
public class SettlementLevelOneTwoSpotsNukingScanner {

    //-----------
    // attributes

    private SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner;

    //-------------
    // Constructors

    public SettlementLevelOneTwoSpotsNukingScanner(final SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner) {
        this.settlementAdjacentVolcanoesScanner = settlementAdjacentVolcanoesScanner;
    }

    //---------------
    // Public Methods

    public TileMapSpot findTileMapSpotToNukeOnSettlment(final Settlement settlement, final Map map) throws NoValidActionException {

        if(settlement.size() < 3)
            throw new NoValidActionException("Cannot cover entire settlement");

        ArrayList<MapSpot> nukableMapSpots = new ArrayList<>();

        for (MapSpot settlementMapSpot : settlement.getMapSpots()) {
            if(!map.getHexagon(settlementMapSpot).isHasTotoro())
                nukableMapSpots.add(settlementMapSpot);
        }

        final ArrayList<MapSpot> adjacentVolcanoes = settlementAdjacentVolcanoesScanner.scan(settlement, map);

        boolean[][][] heatMap = initializeHeatMap(nukableMapSpots);

        for (MapSpot volcano : adjacentVolcanoes) {
            try{
                return getNukingPlace(volcano, heatMap, map);
            }catch (NoValidActionException e){}
        }

        throw new NoValidActionException("Could not find a way to nuke this settlement");
    }

    //----------------
    // Private Methods

    private TileMapSpot getNukingPlace(final MapSpot volcanicMapSpot, final boolean[][][] heatMap, final Map map) throws NoValidActionException {
        MapSpot[] orderedAdjacentMapSpots = new MapSpot[7];

        orderedAdjacentMapSpots[0] = volcanicMapSpot.left();
        orderedAdjacentMapSpots[1] = volcanicMapSpot.topLeft();
        orderedAdjacentMapSpots[2] = volcanicMapSpot.topRight();
        orderedAdjacentMapSpots[3] = volcanicMapSpot.right();
        orderedAdjacentMapSpots[4] = volcanicMapSpot.bottomRight();
        orderedAdjacentMapSpots[5] = volcanicMapSpot.bottomLeft();
        orderedAdjacentMapSpots[6] = volcanicMapSpot.left();

        for(int i = 0; i < 6; i++) {
            if (map.getHexagon(orderedAdjacentMapSpots[i]) != null &&
                    map.getHexagon(orderedAdjacentMapSpots[i + 1]) != null) {

                if (!hasMatchingTileID(volcanicMapSpot, orderedAdjacentMapSpots[i], orderedAdjacentMapSpots[i + 1], map)) {
                    if (isInHeatMap(orderedAdjacentMapSpots[i], heatMap) && isInHeatMap(orderedAdjacentMapSpots[i + 1], heatMap)) {
                        return new TileMapSpot(volcanicMapSpot, orderedAdjacentMapSpots[i], orderedAdjacentMapSpots[i + 1]);
                    }
                }
            }
        }

        throw new NoValidActionException("No Valid Spot Found");
    }

    private boolean[][][] initializeHeatMap(final ArrayList<MapSpot> mapSpots) {

        boolean[][][] heatMap = new boolean[Map.size()][Map.size()][Map.size()];

        for (MapSpot mapSpot : mapSpots) {
            heatMap[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()] = true;
        }

        return heatMap;
    }

    private boolean isInHeatMap(final MapSpot mapSpot, final boolean[][][] settlementHeatMap) {
        return settlementHeatMap[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()];
    }

    private boolean hasMatchingTileID(final MapSpot m1, final MapSpot m2, final MapSpot m3, final Map map) {
        return map.getHexagon(m1).getTileId() == map.getHexagon(m2).getTileId() && map.getHexagon(m1).getTileId() == map.getHexagon(m3).getTileId();
    }
}
