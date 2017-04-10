package Everything.game.action.scanners.PlacingOnLevelOne;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;
import Everything.models.TileMapSpot;

/**
 * Scans for empty **TILE** spots that are touching a settlement
 *
 * When using this to grow a settlement, put the matching terrain in TileMapSpot.m1
 */
public class SettlementLevelOneTilePlacementScanner {

    //---------------
    // Public Methods

    public TileMapSpot findTileMapSpotToPlaceTileAroundSettlment(final Map map, final Settlement settlement) throws NoValidActionException {

        for (final MapSpot mapSpot : settlement.getMapSpots()) {

            for (MapSpot adjacentMapSpot : mapSpot.getAdjacentMapSpots()) {

                if (map.getHexagon(adjacentMapSpot) == null) {
                    try{
                        return getEmptyTilePlace(adjacentMapSpot, map);
                    }catch (NoValidActionException e){}
                }
            }
        }

        throw new NoValidActionException("Cannot place a new tile on level 1 next to this settlement");
    }

    //----------------
    // Private Methods

    private TileMapSpot getEmptyTilePlace(final MapSpot mapSpot, final Map map) throws NoValidActionException{
        MapSpot[] orderedAdjacentMapSpots = new MapSpot[7];

        orderedAdjacentMapSpots[0] = mapSpot.left();
        orderedAdjacentMapSpots[1] = mapSpot.topLeft();
        orderedAdjacentMapSpots[2] = mapSpot.topRight();
        orderedAdjacentMapSpots[3] = mapSpot.right();
        orderedAdjacentMapSpots[4] = mapSpot.bottomRight();
        orderedAdjacentMapSpots[5] = mapSpot.bottomLeft();
        orderedAdjacentMapSpots[6] = mapSpot.left();

        for(int i = 0; i < 6; i++) {
            if (map.getHexagon(orderedAdjacentMapSpots[i]) == null &&
                    map.getHexagon(orderedAdjacentMapSpots[i + 1]) == null) {

                return new TileMapSpot(mapSpot, orderedAdjacentMapSpots[i], orderedAdjacentMapSpots[i + 1]);
            }
        }

        throw new NoValidActionException("No Valid Spot Found");
    }
}
