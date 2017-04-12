package Everything.game.action.scanners.SettlementFounding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Terrain;

public class RandomSettlementFoundingScanner {

    public MapSpot scan(final Map map) throws NoValidActionException {

        final boolean visited[][][] = new boolean[Map.size()][Map.size()][Map.size()];
        result = null;

        visit(map.getMiddleHexagonMapSpot(), visited, map);

        if (result == null) {
            throw new NoValidActionException("No valid spots found");
        }
        return result;
    }

    //----------------
    // Private Methods
    private MapSpot result;
    private void visit(final MapSpot currentMapSpot, boolean visited[][][], final Map map) throws NoValidActionException {

        if (result != null || visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()]) {
            return;
        }

        visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] = true;


        for (final MapSpot adjMapSpot : currentMapSpot.getAdjacentMapSpots()) {
            if (satisfiesFoundingRequirements(adjMapSpot, map)) {
                result = adjMapSpot;
            }

            if(map.getHexagon(adjMapSpot) != null && !visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()])
                visit(adjMapSpot, visited, map);
        }
    }

    private boolean satisfiesFoundingRequirements(final MapSpot mapSpot, final Map map) {
        return map.getHexagon(mapSpot) != null &&
                map.getHexagon(mapSpot).isEmpty() &&
                map.getHexagon(mapSpot).getTerrainType() != Terrain.VOLCANO &&
                map.getHexagon(mapSpot).getLevel() == 1;
    }
}
