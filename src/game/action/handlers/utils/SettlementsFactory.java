package game.action.handlers.utils;

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

        final boolean visited[][] = new boolean[map.getMapSize()][map.getMapSize()];

        visit(map.getMiddleHexagonMapSpot(), settlements, visited, true, team);

        return settlements;
    }

    private void visit(final MapSpot mapSpot, final ArrayList<Settlement> settlements, final boolean visited[][], final boolean startANewSettlement, final Team team) {

        if(visited[mapSpot.getX()][mapSpot.getY()] || map.getHexagon(mapSpot) == null)
            return;
        else
            visited[mapSpot.getX()][mapSpot.getY()] = true;


        if (map.getHexagon(mapSpot).getTerrainType() != Terrain.VOLCANO && map.getHexagon(mapSpot).getOccupiedBy() == team) {
            if (startANewSettlement) {
                settlements.add(new Settlement(team));
            }

            settlements.get(settlements.size()-1).add(mapSpot, map.getHexagon(mapSpot));
        }

        if ( mapSpot.left() != null &&
                map.getHexagon(mapSpot.left()) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(mapSpot.left()).getTerrainType() &&
                map.getHexagon(mapSpot).getOccupiedBy() == team) {
            visit(mapSpot.left(), settlements, visited, false, team);
        }

        if ( mapSpot.topLeft() != null &&
                map.getHexagon(mapSpot.topLeft()) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(mapSpot.topLeft()).getTerrainType() &&
                map.getHexagon(mapSpot).getOccupiedBy() == team) {
            visit(mapSpot.topLeft(), settlements, visited, false, team);
        }

        if ( mapSpot.topRight() != null &&
                map.getHexagon(mapSpot.topRight()) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(mapSpot.topRight()).getTerrainType() &&
                map.getHexagon(mapSpot).getOccupiedBy() == team) {
            visit(mapSpot.topRight(), settlements, visited, false, team);
        }

        if ( mapSpot.right() != null &&
                map.getHexagon(mapSpot.right()) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(mapSpot.right()).getTerrainType() &&
                map.getHexagon(mapSpot).getOccupiedBy() == team) {
            visit(mapSpot.right(), settlements, visited, false, team);
        }

        if ( mapSpot.bottomRight() != null &&
                map.getHexagon(mapSpot.bottomRight()) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(mapSpot.bottomRight()).getTerrainType() &&
                map.getHexagon(mapSpot).getOccupiedBy() == team) {
            visit(mapSpot.bottomRight(), settlements, visited, false, team);
        }

        if ( mapSpot.bottomLeft() != null &&
                map.getHexagon(mapSpot.bottomLeft()) != null &&
                map.getHexagon(mapSpot).getTerrainType() == map.getHexagon(mapSpot.bottomLeft()).getTerrainType() &&
                map.getHexagon(mapSpot).getOccupiedBy() == team) {
            visit(mapSpot.bottomLeft(), settlements, visited, false, team);
        }

        visit(mapSpot.left(), settlements, visited, true, team);
        visit(mapSpot.topLeft(), settlements, visited, true, team);
        visit(mapSpot.topRight(), settlements, visited, true, team);
        visit(mapSpot.right(), settlements, visited, true, team);
        visit(mapSpot.bottomRight(), settlements, visited, true, team);
        visit(mapSpot.bottomLeft(), settlements, visited, true, team);
    }

    //-------------
    // Constructors

    public SettlementsFactory(final Map map) {
        this.map = map;
    }
}
