package game.action.handlers;

import game.action.handlers.utils.SettlementsFactory;
import models.*;

import java.util.ArrayList;

/**
 * Nukes a tile and places another tile over it - while checking the game rules
 */
public class NukingAndStackingHandler {


    //-----------
    // attributes

    private final Map map;


    //-------------
    // constructors

    public NukingAndStackingHandler(final Map map){
        this.map = map;
    }


    //--------
    // methods

    // FIXME: 3/30/2017 Needs one more validation test - "Can't completely wipe out a settlement".

    void NukeSpots(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3, Hexagon h1, Hexagon h2, Hexagon h3){
        if(!MapSpotsCanBeNuked(nuked1,nuked2,nuked3)){
            throw new RuntimeException("Cannot nuke those spots");
        }
        if(!hasOneVolcano(h1,h2,h3)){
            throw new RuntimeException("Not 1 volcano");
        }
        if(!volcanoesMatch(nuked1,nuked2,nuked3,h1,h2,h3)){
            throw new RuntimeException("Volcanoes do not match up");
        }
        if(MapSpotsContainWholeSettlement(nuked1,nuked2,nuked3)){
            throw new RuntimeException("Cannot nuke a single hex settlement");
        }

        int newLevel = map.getHexagon(nuked1).getLevel()+1;

        h1.setLevel(newLevel);
        h2.setLevel(newLevel);
        h3.setLevel(newLevel);

        map.setHexagon(nuked1, h1);
        map.setHexagon(nuked2, h2);
        map.setHexagon(nuked3, h3);
    }

    private boolean MapSpotsContainWholeSettlement(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3) {
        SettlementsFactory settlementsFactory = new SettlementsFactory(map);
        ArrayList<Settlement> friendlySettlements = settlementsFactory.generateSettlements(Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(Team.ENEMY);
        for(Settlement s : friendlySettlements){
            int nukedCount = 0;

            if(s.size() <= 3){

                for(MapSpot m : s.getMapSpots()){
                    if((m == nuked1) || (m == nuked2) || (m == nuked3))
                        nukedCount++;
                }

            }

            if(nukedCount == s.size())
                return true;

        }

        for(Settlement s : enemySettlements){
            int nukedCount = 0;

            if(s.size() <= 3){

                for(MapSpot m : s.getMapSpots()){
                    if( (m == nuked1) || (m == nuked2) || (m == nuked3))
                        nukedCount++;
                }

            }

            if(nukedCount == s.size())
                return true;

        }

        return false;
    }

    private boolean volcanoesMatch(MapSpot n1, MapSpot n2, MapSpot n3, Hexagon h1, Hexagon h2, Hexagon h3) {
        //if the volcanoes do not match up, then the tile needs to be rotated
        if(map.getHexagon(n1).getTerrainType() == Terrain.VOLCANO && h1.getTerrainType()==Terrain.VOLCANO) return true;
        if(map.getHexagon(n2).getTerrainType() == Terrain.VOLCANO && h2.getTerrainType()==Terrain.VOLCANO) return true;
        //noinspection RedundantIfStatement
        if(map.getHexagon(n3).getTerrainType() == Terrain.VOLCANO && h3.getTerrainType()==Terrain.VOLCANO) return true;
        return false;
    }

    boolean MapSpotsCanBeNuked(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3){
        if(nuked1 != null && nuked2 != null && nuked3 != null){
            Hexagon hex1 = map.getHexagon(nuked1);
            Hexagon hex2 = map.getHexagon(nuked2);
            Hexagon hex3 = map.getHexagon(nuked3);

            if( hex1 != null
                    && hex2 != null
                    && hex3 != null
                    && haveSameTileID(hex1, hex2, hex3)
                    && areOnSameLevel(hex1, hex2, hex3)
                    && hasNoTotorosOrTigers(hex1, hex2, hex3)
                    && hasOneVolcano(hex1, hex2, hex3)
                    && areAdjacent(nuked1,nuked2,nuked3)){
                return true;
            }
        }
        return false;
    }

    private boolean areAdjacent(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3) {
        int count = 0;
        for(int i = 0; i<nuked1.getAdjacentMapSpots().size(); i++){
            if( (nuked1.getAdjacentMapSpots().get(i) == nuked2)
                    || (nuked1.getAdjacentMapSpots().get(i) == nuked3)){
                count++;
            }
        }
        if(count != 2) return false;
        count = 0;
        for(int i = 0; i<nuked2.getAdjacentMapSpots().size(); i++){
            if((nuked2.getAdjacentMapSpots().get(i) == nuked1)
                    || (nuked2.getAdjacentMapSpots().get(i) == nuked3)){
                count++;
            }
        }
        if(count != 2) return false;
        count = 0;
        for(int i = 0; i<nuked3.getAdjacentMapSpots().size(); i++){
            if((nuked3.getAdjacentMapSpots().get(i) == nuked1)
                    || (nuked3.getAdjacentMapSpots().get(i) == nuked2)){
                count++;
            }
        }
        return count == 2;
    }

    private boolean hasOneVolcano(Hexagon hex1, Hexagon hex2, Hexagon hex3) {
        int volcanoes = 0;
        if(hex1.getTerrainType() == Terrain.VOLCANO) volcanoes++;
        if(hex2.getTerrainType() == Terrain.VOLCANO) volcanoes++;
        if(hex3.getTerrainType() == Terrain.VOLCANO) volcanoes++;

        return volcanoes == 1;

    }

    private boolean hasNoTotorosOrTigers(Hexagon hex1, Hexagon hex2, Hexagon hex3) {
        return !hex1.isHasTiger() && !hex2.isHasTiger() && !hex3.isHasTiger() && !hex1.isHasTotoro() && !hex2.isHasTotoro() && !hex3.isHasTotoro();
    }

    private boolean areOnSameLevel(Hexagon hex1, Hexagon hex2, Hexagon hex3) {
        return hex1.getLevel() == hex2.getLevel() && hex1.getLevel() == hex3.getLevel();
    }

    private boolean haveSameTileID(Hexagon hex1, Hexagon hex2, Hexagon hex3) {
        return ( hex1.getTileId() != hex2.getTileId() ) ||
                hex1.getTileId() != hex3.getTileId();
    }
}
