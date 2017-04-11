package Everything.game.action.scanners;

import Everything.models.*;

import java.util.ArrayList;

public class SettlementsFactory {

    //--------
    // Methods

    /**
     * Uses something very similar to BFS (instead of doing Breadth First Search, it's doing 'Terrain & Team First Search')
     * @param team
     * @return an ArrayList of Settlements that belong to the specified team
     */
    public ArrayList<Settlement> generateSettlements(final Map map, final Team team) {
        final ArrayList<Settlement> settlements = new ArrayList<>();

        final boolean visited[][][] = new boolean[Map.size()][Map.size()][Map.size()];

        visit(map.getMiddleHexagonMapSpot(), settlements, visited, true, team, map);

        return settlements;
    }

    private void visit(final MapSpot mapSpot,
                       final ArrayList<Settlement> settlements,
                       final boolean visited[][][],
                       final boolean startANewSettlement,
                       final Team team,
                       final Map map) {

        if(visited[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()] || map.getHexagon(mapSpot) == null)
            return;
        else
            visited[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()] = true;

        if (map.getHexagon(mapSpot).getTerrainType() != Terrain.VOLCANO && map.getHexagon(mapSpot).getOccupiedBy() == team) {
            if (startANewSettlement) {
                settlements.add(new Settlement(team));
            }

            settlements.get(settlements.size()-1).add(mapSpot, map.getHexagon(mapSpot));
        }

        for (final MapSpot adjacentMapSpot : mapSpot.getAdjacentMapSpots()) {

            if (map.getHexagon(adjacentMapSpot) != null &&
                    map.getHexagon(mapSpot).getOccupiedBy() == map.getHexagon(adjacentMapSpot).getOccupiedBy()) {

                visit(adjacentMapSpot, settlements, visited, false, team, map);
            }
        }

        for (final MapSpot adjacentMapSpot : mapSpot.getAdjacentMapSpots()) {
            visit(adjacentMapSpot, settlements, visited, true, team, map);
        }
    }
}
