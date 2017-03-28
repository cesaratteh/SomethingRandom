package game.action.handlers;

import models.Hexagon;
import models.Map;
import models.MapSpot;
import models.Terrain;

/**
 * Nukes a tile and places another tile over it - while checking the game rules
 */
public class NukingAndStackingHandler {

    final Map map;

    NukingAndStackingHandler(final Map map){
        this.map = map;
    }

    public void NukeSpots(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3, Hexagon h1, Hexagon h2, Hexagon h3){
        if(!MapSpotsCanBeNuked(nuked1,nuked2,nuked3)){
            throw new RuntimeException("Cannot nuke those spots");
        }
        if(!hasOneVolcano(h1,h2,h3)){
            throw new RuntimeException("Not 1 volcano");
        }
        if(!volcanoesMatch(nuked1,nuked2,nuked3,h1,h2,h3)){
            throw new RuntimeException("Volcanoes do not match up");
        }

        int newLevel = map.getHexagon(nuked1).getLevel()+1;

        h1.setLevel(newLevel);
        h2.setLevel(newLevel);
        h3.setLevel(newLevel);

        map.getHexagonArray()[nuked1.getX()][nuked1.getY()] = h1;
        map.getHexagonArray()[nuked2.getX()][nuked2.getY()] = h2;
        map.getHexagonArray()[nuked3.getX()][nuked3.getY()] = h3;

    }

    private boolean volcanoesMatch(MapSpot n1, MapSpot n2, MapSpot n3, Hexagon h1, Hexagon h2, Hexagon h3) {
        //if the volcanoes do not match up, then the tile needs to be rotated
        if(map.getHexagon(n1).getTerrainType() == Terrain.VOLCANO && h1.getTerrainType()==Terrain.VOLCANO) return true;
        if(map.getHexagon(n2).getTerrainType() == Terrain.VOLCANO && h2.getTerrainType()==Terrain.VOLCANO) return true;
        if(map.getHexagon(n3).getTerrainType() == Terrain.VOLCANO && h3.getTerrainType()==Terrain.VOLCANO) return true;
        return false;
    }

    public boolean MapSpotsCanBeNuked(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3){
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
            if(nuked1.getAdjacentMapSpots().get(i).isEqual(nuked2)
                    || nuked1.getAdjacentMapSpots().get(i).isEqual(nuked3)){
                count++;
            }
        }
        if(count != 2) return false;
        count = 0;
        for(int i = 0; i<nuked2.getAdjacentMapSpots().size(); i++){
            if(nuked2.getAdjacentMapSpots().get(i).isEqual(nuked1)
                    || nuked2.getAdjacentMapSpots().get(i).isEqual(nuked3)){
                count++;
            }
        }
        if(count != 2) return false;
        count = 0;
        for(int i = 0; i<nuked3.getAdjacentMapSpots().size(); i++){
            if(nuked3.getAdjacentMapSpots().get(i).isEqual(nuked1)
                    || nuked3.getAdjacentMapSpots().get(i).isEqual(nuked2)){
                count++;
            }
        }
        if(count != 2) return false;

        return true;
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
