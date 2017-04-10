package Everything.game.action.scanners.SettlementFounding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Terrain;

public class RandomSettlementFoundingScanner {

    public MapSpot scan(final Map map) throws NoValidActionException {

        final boolean visited[][][] = new boolean[Map.size()][Map.size()][Map.size()];

        return visit(map.getMiddleHexagonMapSpot(), visited, map);
    }

    //----------------
    // Private Methods

    private MapSpot visit(final MapSpot currentMapSpot, boolean visited[][][], final Map map) throws NoValidActionException {

        visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] = true;

        if (satisfiesFoundingRequirements(currentMapSpot, map)) {
            return currentMapSpot;
        }

        for (final MapSpot adjMapSpot : currentMapSpot.getAdjacentMapSpots()) {
            if(map.getHexagon(adjMapSpot) != null && !visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()])
                visit(adjMapSpot, visited, map);
        }

        throw new NoValidActionException("No valid founding spot");
    }

    private boolean satisfiesFoundingRequirements(final MapSpot mapSpot, final Map map) {
        return map.getHexagon(mapSpot) != null &&
                map.getHexagon(mapSpot).isEmpty() &&
                map.getHexagon(mapSpot).getTerrainType() != Terrain.VOLCANO &&
                map.getHexagon(mapSpot).getLevel() == 1;
    }
}
