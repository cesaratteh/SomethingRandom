package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Add a tile - while checking game rules
 */
public class FirstLevelTileAdditionHandler {

    private Map map;


    /**
     *
     * @return an arraylist of sets of 3 mapspots for valid tile placement
     * i dunno if this works right now
     */
    public ArrayList<ArrayList<MapSpot>> generateAllSpotsForTilePlacement(){
        ArrayList<MapSpot> edgeSpots = generateAllEdgeSpots();

        ArrayList<ArrayList<MapSpot>> allSpotsForTilePlacement = new ArrayList<>();

        for(MapSpot spot : edgeSpots){

            for(MapSpot adj : spot.getAdjacentMapSpots()){

                if(map.getHexagon(adj) == null){

                    ArrayList<MapSpot> possibleEmptySpots = new ArrayList<>();

                    for(MapSpot adjadj : adj.getAdjacentMapSpots()){
                        if(map.getHexagon(adjadj) == null)
                            possibleEmptySpots.add(adjadj);
                    }

                    ArrayList<ArrayList<MapSpot>> allCombinationsOfMapSpots = combinations(possibleEmptySpots);

                    for(int i = 0; i < allCombinationsOfMapSpots.size(); i++){
                        ArrayList<MapSpot> PossibleTile = allCombinationsOfMapSpots.get(i);

                        if(areAllEmptyAndAdjacent(PossibleTile.get(0), PossibleTile.get(1), PossibleTile.get(2))){
                            allSpotsForTilePlacement.add(PossibleTile);
                        }
                        else{
                            allCombinationsOfMapSpots.remove(i);
                            i--;
                        }
                    }

                }
            }
        }

        return allSpotsForTilePlacement;

    }

    private ArrayList<ArrayList<MapSpot>> combinations(ArrayList<MapSpot> possibleEmptySpots) {
        //TODO
        return new ArrayList<>();
    }

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

        map.setHexagon(m1, h1);
        map.setHexagon(m2, h2);
        map.setHexagon(m3, h3);
        map.setHexagon(m4, h4);
        map.setHexagon(m5, h5);
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
            map.setHexagon(m1, h1);
        else
            throw new RuntimeException("Bad Tile Placement");

        if(map.getHexagon(m2) == null)
            map.setHexagon(m2, h2);
        else
            throw new RuntimeException("Bad Tile Placement");

        if(map.getHexagon(m3) == null)
            map.setHexagon(m3, h3);
        else
            throw new RuntimeException("Bad Tile Placement");

        h1.setLevel(1);
        h2.setLevel(1);
        h3.setLevel(1);
    }

    private ArrayList<MapSpot> generateAllEdgeSpots(){
        Stack<MapSpot> mapSpotStack = new Stack<>();
        ArrayList<MapSpot> edgeSpots = new ArrayList<>();


        mapSpotStack.push(map.getMiddleHexagonMapSpot());

        while(!mapSpotStack.isEmpty()){
            MapSpot spot = mapSpotStack.pop();
            for(MapSpot adj : spot.getAdjacentMapSpots()) {
                if (map.getHexagon(adj) == null){
                    edgeSpots.add(spot);
                    break;
                }
                else{
                    mapSpotStack.push(adj);
                }
            }

        }

        return edgeSpots;

    }

    private boolean areAllEmptyAndAdjacent(MapSpot m1, MapSpot m2, MapSpot m3){
        return isValidConfigurationForTile(m1,m2,m3)
                && map.getHexagon(m1) == null
                && map.getHexagon(m2) == null
                && map.getHexagon(m3) == null;
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

    public FirstLevelTileAdditionHandler(Map map){
        this.map = map;
    }

}
