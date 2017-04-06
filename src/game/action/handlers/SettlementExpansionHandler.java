package game.action.handlers;

import models.*;

import java.util.ArrayList;
import java.util.Stack;

class SettlementExpansionHandler {

    private final Settlement settlement;
    private Map map;

    /**
     * Returns an ArrayList of the viable MapSpots the settlement can expand to.
     * Only returns the first layer of expandable spots (expandable layer of hexagons immediately surrounding settlement)
     * Can be used for tiger/totoro expansions but not meeples.
     */

    ArrayList<MapSpot> generateExpandableSettlementArea(){
        ArrayList<MapSpot> validSpotsForExpansion = new ArrayList<>();
        ArrayList<MapSpot> settlementMapSpots = settlement.getMapSpots();

        boolean visited[][] = new boolean[map.size()][map.size()];

        for (MapSpot settlementMapSpot : settlementMapSpots) {
            visited[settlementMapSpot.getX()][settlementMapSpot.getY()] = true;
        }

        for (MapSpot curr : settlementMapSpots) {

            for (int j = 0; j < curr.getAdjacentMapSpots().size(); j++) {

                MapSpot currentAdjacentSpot = curr.getAdjacentMapSpots().get(j);

                if (canBeExpandedTo(curr, currentAdjacentSpot, visited)) {
                    visited[currentAdjacentSpot.getX()][currentAdjacentSpot.getY()] = true;
                    validSpotsForExpansion.add(currentAdjacentSpot);
                }

            }
        }

        return validSpotsForExpansion;
    }

    ArrayList<MapSpot> generateAllTigerSpots(){
        ArrayList<MapSpot> validSpots = generateExpandableSettlementArea();
        ArrayList<MapSpot> tigerSpots = new ArrayList<>();

        if(settlement.getMapSpots().size() >= 5) {
            for (MapSpot spot : validSpots) {
                if (isValidTigerSpot(spot)) {
                    tigerSpots.add(spot);
                }
            }
        }
        return tigerSpots;
    }

    ArrayList<MapSpot> generateAllTotoroSpots(){
        ArrayList<MapSpot> validSpots = generateExpandableSettlementArea();
        ArrayList<MapSpot> totoroSpots = new ArrayList<>();

        if(settlement.getMapSpots().size() >= 5) {
            for (MapSpot spot : validSpots) {
                if (isValidTotoroSpot(spot)) {
                    totoroSpots.add(spot);
                }
            }
        }
        return totoroSpots;
    }

    /**
     *
     * Sounds kinda confusing
     * Returns a list of all possible expandable spots including all
     * automatically expanded to spots surrounding the settlement.
     * Used when doing meeple expansion and allows for insight into expansion
     */
    ArrayList<ArrayList<MapSpot>> generateAllChainedSpots(){
        final ArrayList<ArrayList<MapSpot>> allChainedSpots = new ArrayList<>();
        final ArrayList<MapSpot> validSpotsForExpansion = generateExpandableSettlementArea();

        consolidateAdjacentMapSpots(validSpotsForExpansion);

        for (MapSpot spot : validSpotsForExpansion) {
            allChainedSpots.add(generateChainedSpots(spot));
        }

        return allChainedSpots;
    }

    /**
     *
     * generates a list of all spots with the same terrain that can be chained from a
     * MapSpot that is adjacent to the settlement
     */
    ArrayList<MapSpot> generateChainedSpots(MapSpot mapSpot){

        if(!isAdjacentToSettlement(mapSpot))
            throw new RuntimeException("MapSpot not adjacent to settlement");

        ArrayList<MapSpot> validSpotsForExpansion = generateExpandableSettlementArea();
        ArrayList<MapSpot> chainedSpots = new ArrayList<>();

        if(isIn(validSpotsForExpansion, mapSpot)){

            chainedSpots.add(mapSpot);

            Stack<MapSpot> mapStack = new Stack<>();
            mapStack.add(mapSpot);

            boolean[][] visited = new boolean[map.size()][map.size()];
            visited[mapSpot.getX()][mapSpot.getY()] = true;

            while (!mapStack.isEmpty()) {

                MapSpot seedMapSpot = mapStack.pop();

                for (int j = 0; j < seedMapSpot.getAdjacentMapSpots().size(); j++) {

                    MapSpot currentMapSpot = seedMapSpot.getAdjacentMapSpots().get(j);

                    if (canChainExpand(seedMapSpot, currentMapSpot, visited)){
                        chainedSpots.add(currentMapSpot);
                        mapStack.push(currentMapSpot);
                    }

                    visited[currentMapSpot.getX()][currentMapSpot.getY()] = true;

                }

            }

        }
        else{
            throw new RuntimeException("Bad Expansion");
        }

        return chainedSpots;
    }

    void expandWithMeeples(final MapSpot mapSpot) {
        ArrayList<MapSpot> validExpansionSpots = generateExpandableSettlementArea();

        if (isIn(validExpansionSpots, mapSpot)) {

            ArrayList<MapSpot> chainedSpots = generateChainedSpots(mapSpot);

            for (MapSpot spot : chainedSpots) {
                Hexagon hex = map.getHexagon(spot);
                hex.addMeeples(settlement.getTeam());
                settlement.add(spot,hex);
            }
        } else {
            throw new RuntimeException("Bad expansion with Meeples");
        }
    }

    void expandWithTotoro(final MapSpot mapSpot) {
        ArrayList<MapSpot> validExpansionSpots = generateExpandableSettlementArea();

        if (isIn(validExpansionSpots, mapSpot) && settlement.getMapSpots().size() >= 5) {
            map.getHexagon(mapSpot).addTotoro(settlement.getTeam());
            settlement.add(mapSpot, map.getHexagon(mapSpot));

        } else {
            throw new RuntimeException("Bad expansion with Totoro");

        }
    }

    void expandWithTiger(final MapSpot mapSpot) {
        ArrayList<MapSpot> validExpansionSpots = generateExpandableSettlementArea();

        if(isIn(validExpansionSpots, mapSpot)
                && settlement.getMapSpots().size() >= 5
                && map.getHexagon(mapSpot).getLevel() >= 3) {
            map.getHexagon(mapSpot).addTiger(settlement.getTeam());
            settlement.add(mapSpot, map.getHexagon(mapSpot));
        } else {
            throw new RuntimeException("Bad expansion with Tiger");
        }
    }

    private boolean canChainExpand(MapSpot seedMapSpot, MapSpot currentMapSpot, boolean[][] visited) {
        return canBeExpandedTo(seedMapSpot, currentMapSpot, visited)
                 && map.getHexagon(seedMapSpot).getTerrainType()
                    == map.getHexagon(currentMapSpot).getTerrainType();
    }

    private void consolidateAdjacentMapSpots(ArrayList<MapSpot> validSpotsForExpansion) {
        for(int i = 0; i < validSpotsForExpansion.size(); i++){
            for(int j = i+1; j < validSpotsForExpansion.size(); j++){
                if(areAdjacentAndHaveSameTerrain(validSpotsForExpansion.get(i),validSpotsForExpansion.get(j))){
                    validSpotsForExpansion.remove(j);
                    j--;
                }
            }
        }
    }

    private boolean areAdjacentAndHaveSameTerrain(MapSpot m1, MapSpot m2){
        return map.getHexagon(m1).getTerrainType() == map.getHexagon(m2).getTerrainType()
                && map.getHexagon(m1).getTerrainType() != Terrain.VOLCANO
                && m1.isAdjacentTo(m2);
    }

    private boolean canBeExpandedTo(MapSpot seedSpot, MapSpot currentSpot, boolean[][] visited) {
        int levelDiff;
        if (currentSpot != null && map.getHexagon(currentSpot) != null && map.getHexagon(seedSpot) != null){
            levelDiff = map.getHexagon(currentSpot).getLevel() - map.getHexagon(seedSpot).getLevel();
        }
        else return false;

        return (levelDiff <= 1 && levelDiff >= -1)
                && map.getHexagon(currentSpot).isEmpty()
                && map.getHexagon(currentSpot).getTerrainType() != Terrain.VOLCANO
                && !visited[currentSpot.getX()][currentSpot.getY()];
    }

    private boolean isValidTigerSpot(MapSpot spot) {
        Hexagon hex = map.getHexagon(spot);
        return hex != null && (hex.getTerrainType() != Terrain.VOLCANO && hex.isEmpty() && hex.getLevel() >= 3);
    }

    private boolean isValidTotoroSpot(MapSpot spot) {
        Hexagon hex = map.getHexagon(spot);
        return hex != null && (hex.getTerrainType() != Terrain.VOLCANO && hex.isEmpty());
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

    private boolean isAdjacentToSettlement(MapSpot m){
        boolean adjacent = false;
        for(MapSpot adj : m.getAdjacentMapSpots()){
            for(MapSpot spot : settlement.getMapSpots()){
                if(isEqual(spot, adj))
                    adjacent = true;
            }
        }
        return adjacent;
    }

    SettlementExpansionHandler(final Map map, final Settlement settlement){
        this.map = map;
        this.settlement = settlement;
    }
}
