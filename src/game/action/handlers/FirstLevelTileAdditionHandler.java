package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;

import java.util.ArrayList;

/**
 * Add a tile - while checking game rules
 */
public class FirstLevelTileAdditionHandler {

    private Map map;




    /**
     * h1-5 are the hexes of the FirstTile
     * m1-5 are the corresponding MapSpots
     * can handle any orientation of FirstTile
     */
    public void addFirstTileToMap(Hexagon h1, Hexagon h2, Hexagon h3, Hexagon h4, Hexagon h5, MapSpot m1, MapSpot m2, MapSpot m3, MapSpot m4, MapSpot m5){

        if(!uniqueTerrains(h1,h2,h3,h4,h5) || !matchingTileID(h1,h2,h3,h4,h5)){
            throw new RuntimeException("Bad First Tile to be Placed");
        }

        if(!isValidConfigurationForFirstTile(m1,m2,m3,m4,m5)){
            throw new RuntimeException("Bad First Tile Placement");
        }

        map.getHexagonArray()[m1.getX()][m1.getY()][m1.getZ()] = h1;
        map.getHexagonArray()[m2.getX()][m2.getY()][m1.getZ()] = h2;
        map.getHexagonArray()[m3.getX()][m3.getY()][m1.getZ()] = h3;
        map.getHexagonArray()[m4.getX()][m4.getY()][m1.getZ()] = h4;
        map.getHexagonArray()[m5.getX()][m5.getY()][m1.getZ()] = h5;

    }



    public void addTileToMap(Hexagon h1, Hexagon h2, Hexagon h3, MapSpot m1, MapSpot m2, MapSpot m3){

        if (!onlyOneVolcano(h1, h2, h3) || !matchingTileID(h1, h2, h3))
            throw new RuntimeException("Bad Tile to be placed");

        if(!goodMapSpots(m1,m2,m3)){
            throw new RuntimeException("Bad Map Spots for placement");
        }

        if(!isValidConfigurationForTile(m1,m2,m3))
            throw new RuntimeException("Bad Tile Placement");

        if(map.getHexagon(m1) == null)
            map.getHexagonArray()[m1.getX()][m1.getY()][m1.getZ()] = h1;
        else
            throw new RuntimeException("Bad Tile Placement");

        if(map.getHexagon(m2) == null)
            map.getHexagonArray()[m2.getX()][m2.getY()][m2.getZ()] = h2;
        else
            throw new RuntimeException("Bad Tile Placement");

        if(map.getHexagon(m3) == null)
            map.getHexagonArray()[m3.getX()][m3.getY()][m3.getZ()] = h3;
        else
            throw new RuntimeException("Bad Tile Placement");

        h1.setLevel(1);
        h2.setLevel(1);
        h3.setLevel(1);

    }

    private boolean goodMapSpots(MapSpot m1, MapSpot m2, MapSpot m3) {
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
                && h2.getTerrainType() != Terrain.VOLCANO
                && h3.getTerrainType() != Terrain.VOLCANO

                || h2.getTerrainType() == Terrain.VOLCANO
                && h1.getTerrainType() != Terrain.VOLCANO
                && h3.getTerrainType() != Terrain.VOLCANO

                || h3.getTerrainType() == Terrain.VOLCANO
                && h1.getTerrainType() != Terrain.VOLCANO
                && h2.getTerrainType() != Terrain.VOLCANO;
    }

    private boolean onlyOneTerrain(Hexagon h1, Hexagon h2, Hexagon h3, Hexagon h4, Hexagon h5, Terrain t){
        return ((h1.getTerrainType() == t
                && h2.getTerrainType() != t
                && h3.getTerrainType() != t
                && h4.getTerrainType() != t
                && h5.getTerrainType() != t)

                || (h2.getTerrainType() == t
                && h1.getTerrainType() != t
                && h3.getTerrainType() != t
                && h4.getTerrainType() != t
                && h5.getTerrainType() != t)

                || (h3.getTerrainType() == t
                && h1.getTerrainType() != t
                && h2.getTerrainType() != t
                && h4.getTerrainType() != t
                && h5.getTerrainType() != t)

                || (h4.getTerrainType() == t
                && h1.getTerrainType() != t
                && h2.getTerrainType() != t
                && h3.getTerrainType() != t
                && h5.getTerrainType() != t)

                || (h5.getTerrainType() == t
                && h1.getTerrainType() != t
                && h2.getTerrainType() != t
                && h3.getTerrainType() != t
                && h4.getTerrainType() != t));
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
        return(
                isIn(m1.getAdjacentMapSpots(), m2)
                &&isIn(m1.getAdjacentMapSpots(), m3)

                &&isIn(m2.getAdjacentMapSpots(), m3)

                &&isIn(m3.getAdjacentMapSpots(), m4)
                &&isIn(m3.getAdjacentMapSpots(), m5)

                &&isIn(m4.getAdjacentMapSpots(), m5)
        );
    }

    private boolean isIn(ArrayList<MapSpot> List, MapSpot spot){
        for(MapSpot s : List){
            if(isEqual(s, spot)) return true;
        }
        return false;
    }

    private boolean isEqual(MapSpot m1, MapSpot m2) {
        return m1.getX() == m2.getX() && m1.getY() == m2.getY();
    }

    public FirstLevelTileAdditionHandler(Map map){
        this.map = map;
    }

}
