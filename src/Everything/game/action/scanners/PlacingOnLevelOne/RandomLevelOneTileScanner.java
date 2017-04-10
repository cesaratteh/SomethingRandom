package Everything.game.action.scanners.PlacingOnLevelOne;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.TileMapSpot;

public class RandomLevelOneTileScanner {

    //---------------
    // Public Methods

    public TileMapSpot scan(final Map map) throws NoValidActionException{

        final boolean[][][] visited = new boolean[Map.size()][Map.size()][Map.size()];

        return visit(map.getMiddleHexagonMapSpot(), visited, map);
    }


    private TileMapSpot visit(final MapSpot currentMapSpot,
                              final boolean visited[][][],
                              final Map map)  throws NoValidActionException{

        visited[currentMapSpot.getX()][currentMapSpot.getY()][currentMapSpot.getZ()] = true;

        try{
            return getEmptyTilePlace(currentMapSpot, map);
        }catch (NoValidActionException e){}

        for (final MapSpot adjMapSpots : currentMapSpot.getAdjacentMapSpots()) {
            if (!visited[adjMapSpots.getX()][adjMapSpots.getY()][adjMapSpots.getZ()]) {
                visit(adjMapSpots, visited, map);
            }
        }

        throw new NoValidActionException("Cannot place tile anywhere in RandomLevelOneTileScanner");
    }

    private TileMapSpot getEmptyTilePlace(final MapSpot mapSpot, final Map map) throws NoValidActionException {
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
