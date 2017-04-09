package Everything.Server;

import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Terrain;
import Everything.models.TileMapSpot;

public class OrientationParser {

    public int getOrientation(final Map map, final MapSpot volcanoSpot) {
        final MapSpot orderedListOfAdjacentSpots[] = new MapSpot[7];

        int orientation = 1;

        orderedListOfAdjacentSpots[0] = volcanoSpot.topLeft();
        orderedListOfAdjacentSpots[1] = volcanoSpot.topRight();
        orderedListOfAdjacentSpots[2] = volcanoSpot.right();
        orderedListOfAdjacentSpots[3] = volcanoSpot.bottomRight();
        orderedListOfAdjacentSpots[4] = volcanoSpot.bottomLeft();
        orderedListOfAdjacentSpots[5] = volcanoSpot.left();
        orderedListOfAdjacentSpots[6] = volcanoSpot.topLeft();

        for(int i = 0; i < 6; i++) {
            if (mapSpotsFormTile(orderedListOfAdjacentSpots[i], orderedListOfAdjacentSpots[i + 1], volcanoSpot, map)) {
                return orientation;
            } else {
                orientation++;
            }
        }

        return orientation;
    }

    public int getOrientation(final Map map, final TileMapSpot tileMapSpot) {

        if (map.getHexagon(tileMapSpot.getM1()).getTerrainType() == Terrain.VOLCANO) {
            return getOrientation(map, tileMapSpot.getM1());
        } else if (map.getHexagon(tileMapSpot.getM2()).getTerrainType() == Terrain.VOLCANO) {
            return getOrientation(map, tileMapSpot.getM2());
        } else {
            return getOrientation(map, tileMapSpot.getM3());
        }
    }

    private boolean mapSpotsFormTile(final MapSpot m1,
                                     final MapSpot m2,
                                     final MapSpot m3,
                                     final Map map) {

        return (map.getHexagon(m1) != null && map.getHexagon(m2) != null && map.getHexagon(m3) != null) &&
                (map.getHexagon(m1).getTileId() == map.getHexagon(m2).getTileId() && map.getHexagon(m1).getTileId() == map.getHexagon(m3).getTileId());
    }
}
