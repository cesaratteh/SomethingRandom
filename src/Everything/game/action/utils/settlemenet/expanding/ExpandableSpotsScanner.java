package Everything.game.action.utils.settlemenet.expanding;

import Everything.game.action.utils.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

/**
 * Scans for the expandable area of a settlement
 */
public class ExpandableSpotsScanner {

    //---------------
    // Public Methods

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {

        final ArrayList<MapSpot> expandableMapSpots = new ArrayList<>();

        final boolean visited[][][] = new boolean[Map.size()][Map.size()][Map.size()];

        visit(settlement.getMapSpots().get(0), expandableMapSpots, visited, map, settlement);

        if(expandableMapSpots.size() < 1)
            throw new NoValidActionException("No available expansion spots");

        return expandableMapSpots;
    }

    //----------------
    // Private Methods

    private void visit(final MapSpot currentMapSpot, final ArrayList<MapSpot> expandableMapSpots, boolean visited[][][], final Map map, final Settlement settlement) {

        if(visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()])
            return;
        else
            visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] = true;


        if (satisfiesExpansionRequirements(currentMapSpot, map, settlement)) {
            expandableMapSpots.add(currentMapSpot);
        }

        for (final MapSpot adjMapSpot : currentMapSpot.getAdjacentMapSpots()) {
            if(satisfiesVisitingRequirements(adjMapSpot, map, settlement))
                visit(adjMapSpot, expandableMapSpots, visited, map, settlement);
        }
    }

    private boolean satisfiesExpansionRequirements(final MapSpot mapSpot, final Map map, final Settlement settlement) {
        return satisfiesVisitingRequirements(mapSpot, map, settlement) &&
                map.getHexagon(mapSpot).isEmpty();
    }

    private boolean satisfiesVisitingRequirements(final MapSpot mapSpot, final Map map, final Settlement settlement) {
        return map.getHexagon(mapSpot) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(settlement.getMapSpots().get(0)).getTerrainType();

    }
}
