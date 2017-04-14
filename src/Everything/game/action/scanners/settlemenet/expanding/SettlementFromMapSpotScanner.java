package Everything.game.action.scanners.settlemenet.expanding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.*;

public class SettlementFromMapSpotScanner {

    //---------------
    // Public Methods

    public Settlement scan(Map map, MapSpot mapSpot) throws NoValidActionException {

        if (map.getHexagon(mapSpot) == null && !map.getHexagon(mapSpot).isEmpty()) {
            throw new NoValidActionException("You passed a bad mapSpot that doesn't belong to any settlement in SettlementFromMapSpotScanner");
        }

        boolean[][][] visited = new boolean[Map.size()][Map.size()][Map.size()];
        Settlement settlement = new Settlement(map.getHexagon(mapSpot).getOccupiedBy());
        settlement.add(mapSpot, map.getHexagon(mapSpot));
        visit(mapSpot, settlement, map, visited, map.getHexagon(mapSpot).getOccupiedBy());

        if(settlement.size() <= 0)
            throw new NoValidActionException("Error in SettlementFromMapSpotScanner");

        return settlement;
    }

    //----------------
    // Private Methods

    private void visit(MapSpot currentMapSpot, Settlement settlement, Map map, boolean[][][] visited, Team team) {

        if (visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()])
            return;

        visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] = true;


        for (MapSpot adj : currentMapSpot.getAdjacentMapSpots()) {
            if (satisfiesVisitingAndAddingRequirements(adj, map, team)) {
                settlement.add(adj, map.getHexagon(adj));
                visit(adj, settlement, map, visited, team);
            }
        }
    }

    private boolean satisfiesVisitingAndAddingRequirements(final MapSpot mapSpot, final Map map, final Team team) {
        return map.getHexagon(mapSpot) != null &&
                !map.getHexagon(mapSpot).isEmpty()&&
                map.getHexagon(mapSpot).getOccupiedBy() == team;
    }
}
