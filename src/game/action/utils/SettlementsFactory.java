package game.action.utils;

import models.*;

import java.util.ArrayList;

public class SettlementsFactory {

    //-----------
    // Attributes

    private Map map;

    //--------
    // Methods

    /**
     * Uses something very similar to BFS (instead of doing Breadth First Search, it's doing 'Terrain & Team First Search')
     * @param team
     * @return an ArrayList of Settlements that belong to the specified team
     */
    public ArrayList<Settlement> generateSettlements(final Team team) {
        final ArrayList<Settlement> settlements = new ArrayList<>();

        final boolean visited[][][] = new boolean[Map.size()][Map.size()][Map.size()];

        visit(map.getMiddleHexagonMapSpot(), settlements, visited, true, team);

        return settlements;
    }

    private void visit(final MapSpot currentMapSpot,
                       final ArrayList<Settlement> settlements,
                       final boolean visited[][][],
                       final boolean startANewSettlement,
                       final Team team) {

        if(visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] || map.getHexagon(currentMapSpot) == null)
            return;
        else
            visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] = true;

        if (map.getHexagon(currentMapSpot).getTerrainType() != Terrain.VOLCANO && map.getHexagon(currentMapSpot).getOccupiedBy() == team) {
            if (startANewSettlement) {
                settlements.add(new Settlement(team));
            }

            settlements.get(settlements.size()-1).add(currentMapSpot, map.getHexagon(currentMapSpot));
        }

        for (final MapSpot adjacentMapSpot : currentMapSpot.getAdjacentMapSpots()) {

            if (map.getHexagon(adjacentMapSpot) != null &&
                    map.getHexagon(adjacentMapSpot).getTerrainType() == map.getHexagon(currentMapSpot).getTerrainType() &&
                    map.getHexagon(adjacentMapSpot).getOccupiedBy() == map.getHexagon(currentMapSpot).getOccupiedBy()) {

                visit(adjacentMapSpot, settlements, visited, false, team);
            }
        }

        for (final MapSpot adjacentMapSpot : currentMapSpot.getAdjacentMapSpots()) {
            visit(adjacentMapSpot, settlements, visited, true, team);
        }
    }

    //-------------
    // Constructors

    public SettlementsFactory(final Map map) {
        this.map = map;
    }
}
