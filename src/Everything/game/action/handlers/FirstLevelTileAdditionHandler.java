package Everything.game.action.handlers;

import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.models.Hexagon;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Terrain;

import java.util.ArrayList;

/**
 * Add a tile - while checking game rules
 */
public class FirstLevelTileAdditionHandler {


    //Doesnt actually count as a move, the map should have the first tile placed at the start of the game.
    public void addFirstTileToMap(final Map map){
        MapSpot mapspot = new MapSpot(0,0,0);
        Hexagon hex1 = new Hexagon(Terrain.VOLCANO,1,0);
        map.setHexagon(mapspot, hex1);

        hex1 = new Hexagon(Terrain.JUNGLE,1,0);
        map.setHexagon(mapspot.topLeft(),hex1);

        hex1 = new Hexagon(Terrain.LAKE,1,0);
        map.setHexagon(mapspot.topRight(),hex1);

        hex1 = new Hexagon(Terrain.ROCK,1,0);
        map.setHexagon(mapspot.bottomLeft(),hex1);

        hex1 = new Hexagon(Terrain.GRASS,1,0);
        map.setHexagon(mapspot.bottomRight(),hex1);
    }

    public WeJustDidThisMove addTileToMap(Hexagon h1, Hexagon h2, Hexagon h3, MapSpot m1, MapSpot m2, MapSpot m3, final Map map) throws CannotPerformActionException {

        if (!onlyOneVolcano(h1, h2, h3) || !matchingTileID(h1, h2, h3))
            throw new RuntimeException("Bad Tile to be placed");

        if (!goodMapSpots(m1, m2, m3, map)) {
            throw new RuntimeException("Bad Map Spots for placement");
        }

        if (!isValidConfigurationForTile(m1, m2, m3))
            throw new RuntimeException("Bad Tile Placement");

        if (map.getHexagon(m1) == null)
            map.setHexagon(m1, h1);
        else
            throw new RuntimeException("Bad Tile Placement");

        if (map.getHexagon(m2) == null)
            map.setHexagon(m2, h2);
        else
            throw new RuntimeException("Bad Tile Placement");

        if (map.getHexagon(m3) == null)
            map.setHexagon(m3, h3);
        else
            throw new RuntimeException("Bad Tile Placement");

        h1.setLevel(1);
        h2.setLevel(1);
        h3.setLevel(1);

        WeJustDidThisMove move = new WeJustDidThisMove();

        if (h1.getTerrainType() == Terrain.VOLCANO){
            move.setBuildSpot(m1);
            move.setOrientation(findOrientation(m1,m2,m3));
        }
        else if (h2.getTerrainType() == Terrain.VOLCANO){
            move.setBuildSpot(m2);
            move.setOrientation(findOrientation(m2,m1,m3));
        }
        else {
            move.setBuildSpot(m3);
            move.setOrientation(findOrientation(m3,m1,m2));
        }
        return move;
    }

    private int findOrientation(MapSpot m1, MapSpot m2, MapSpot m3) throws CannotPerformActionException {
        if((m1.topLeft().isEqual(m2) && m1.topRight().isEqual(m3))
                ||( m1.topLeft().isEqual(m3) && m1.topRight().isEqual(m2)))
            return 1;
        else if ((m1.topRight().isEqual(m2)&& m1.right().isEqual(m3))
                ||(m1.topRight().isEqual(m3) && m1.right().isEqual(m2)))
            return 2;
        else if ((m1.right().isEqual(m2) && m1.bottomRight().isEqual(m3))
                || (m1.right().isEqual(m3) && m1.bottomRight().isEqual(m2)))
            return 3;
        else if ((m1.bottomRight().isEqual(m2) && m1.bottomLeft().isEqual(m3))
                || (m1.bottomRight().isEqual(m3) && m1.bottomLeft().isEqual(m2)))
            return 4;
        else if ((m1.bottomLeft().isEqual(m2) && m1.left().isEqual(m3))
                || (m1.bottomLeft().isEqual(m3) && m1.left().isEqual(m2)))
            return 5;
        else if ((m1.left().isEqual(m2) && m1.topLeft().isEqual(m3))
                || (m1.left().isEqual(m3) && m1.topLeft().isEqual(m2)))
            return 6;
        else
            throw new CannotPerformActionException("Cannot find orientation of Tile");
    }

    private boolean goodMapSpots(MapSpot m1, MapSpot m2, MapSpot m3, final Map map) {
        boolean connected = false;
        for (MapSpot adj : m1.getAdjacentMapSpots()){
            if(map.getHexagon(adj) != null)
                connected = true;
        }

        for (MapSpot adj : m2.getAdjacentMapSpots()){
            if(map.getHexagon(adj) != null)
                connected = true;
        }

        for (MapSpot adj : m3.getAdjacentMapSpots()){
            if(map.getHexagon(adj) != null)
                connected = true;
        }

        return connected;
    }

    private boolean uniqueTerrains(Hexagon h1, Hexagon h2, Hexagon h3, Hexagon h4, Hexagon h5) {
        for(Terrain t : Terrain.values()){
            if(!onlyOneTerrain(h1,h2,h3,h4,h5,t))
                return false;
        }
        return true;
    }

    private boolean onlyOneVolcano(Hexagon h1, Hexagon h2, Hexagon h3){
        return h1.getTerrainType() == Terrain.VOLCANO
                ^ h2.getTerrainType() == Terrain.VOLCANO
                ^ h3.getTerrainType() == Terrain.VOLCANO;
    }

    private boolean onlyOneTerrain(Hexagon h1, Hexagon h2, Hexagon h3, Hexagon h4, Hexagon h5, Terrain t){
        return (h1.getTerrainType() == t
                ^ h2.getTerrainType() == t
                ^ h3.getTerrainType() == t
                ^ h4.getTerrainType() == t
                ^ h5.getTerrainType() == t);
    }

    private boolean matchingTileID(Hexagon h1, Hexagon h2, Hexagon h3) {
        return h1.getTileId() == h2.getTileId() && h2.getTileId() == h3.getTileId();
    }

    private boolean matchingTileID(Hexagon h1, Hexagon h2, Hexagon h3, Hexagon h4, Hexagon h5) {
        return h1.getTileId() == h2.getTileId()
                && h2.getTileId() == h3.getTileId()
                && h3.getTileId() == h4.getTileId()
                && h4.getTileId() == h5.getTileId();
    }

    private boolean isValidConfigurationForTile(MapSpot m1, MapSpot m2, MapSpot m3) {

        return     isIn( m1.getAdjacentMapSpots(), m2 )
                && isIn( m1.getAdjacentMapSpots(), m3 )
                && isIn( m2.getAdjacentMapSpots(), m3 );
    }

    private boolean isValidConfigurationForFirstTile(MapSpot m1, MapSpot m2, MapSpot m3, MapSpot m4, MapSpot m5) {
        final ArrayList<MapSpot> validMapSpotsForFirstTile = new ArrayList<>();
        validMapSpotsForFirstTile.add(new MapSpot(0, 0, 0));
        validMapSpotsForFirstTile.add(new MapSpot(1, 0, -1));
        validMapSpotsForFirstTile.add(new MapSpot(0, 1, -1));
        validMapSpotsForFirstTile.add(new MapSpot(0, -1, 1));
        validMapSpotsForFirstTile.add(new MapSpot(-1, 0, 1));

        boolean allSpotsAreValid = true;
        for (MapSpot validSpot : validMapSpotsForFirstTile) {
            if (! (m1.isEqual(validSpot) ^ m2.isEqual(validSpot) ^ m3.isEqual(validSpot) ^ m4.isEqual(validSpot) ^ m5.isEqual(validSpot)))
                allSpotsAreValid = false;
        }

        return allSpotsAreValid;
    }

    private boolean isIn(ArrayList<MapSpot> List, MapSpot spot){
        for(MapSpot s : List){
            if(s.isEqual(spot)) return true;
        }
        return false;
    }
}
