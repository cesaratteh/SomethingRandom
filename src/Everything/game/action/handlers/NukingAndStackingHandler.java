package Everything.game.action.handlers;

import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;

import java.util.ArrayList;

/**
 * Nukes a tile and places another tile over it - while checking the game rules
 */
public class NukingAndStackingHandler {

    //--------
    // methods

    public WeJustDidThisMove NukeSpots(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3, Hexagon h1, Hexagon h2, Hexagon h3, Map map) throws CannotPerformActionException {
        if(!MapSpotsCanBeNuked(nuked1,nuked2,nuked3, map)){
            throw new RuntimeException("Cannot nuke those spots");
        }
        if(!hasOneVolcano(h1,h2,h3)){
            throw new RuntimeException("Not 1 volcano");
        }
        if(!volcanoesMatch(nuked1,nuked2,nuked3,h1,h2,h3, map)){
            throw new RuntimeException("Volcanoes do not match up");
        }
        if(MapSpotsContainWholeSettlement(nuked1,nuked2,nuked3, map)){
            throw new RuntimeException("Cannot nuke a single hex settlement");
        }

        int newLevel = map.getHexagon(nuked1).getLevel()+1;

        h1.setLevel(newLevel);
        h2.setLevel(newLevel);
        h3.setLevel(newLevel);

        map.setHexagon(nuked1, h1);
        map.setHexagon(nuked2, h2);
        map.setHexagon(nuked3, h3);

        WeJustDidThisMove move = new WeJustDidThisMove();


        if (h1.getTerrainType() == Terrain.VOLCANO) {
            move.setTileSpot(nuked1);
            move.setOrientation(findOrientation(nuked1, nuked2, nuked3));
        } else if (h2.getTerrainType() == Terrain.VOLCANO) {
            move.setTileSpot(nuked2);
            move.setOrientation(findOrientation(nuked2, nuked1, nuked3));
        } else {
            move.setTileSpot(nuked3);
            move.setOrientation(findOrientation(nuked3, nuked1, nuked2));
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

    private boolean MapSpotsContainWholeSettlement(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3, Map map) {
        SettlementsFactory settlementsFactory = new SettlementsFactory();
        ArrayList<Settlement> friendlySettlements = settlementsFactory.generateSettlements(map, Team.FRIENDLY);
        ArrayList<Settlement> enemySettlements = settlementsFactory.generateSettlements(map, Team.ENEMY);
        for(Settlement s : friendlySettlements){
            int nukedCount = 0;

            if(s.size() <= 3){

                for(MapSpot m : s.getMapSpots()){
                    if((m.isEqual(nuked1)) || (m.isEqual(nuked2)) || (m.isEqual(nuked3)))
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
                    if((m.isEqual(nuked1)) || (m.isEqual(nuked2)) || (m.isEqual(nuked3)))
                        nukedCount++;
                }

            }

            if(nukedCount == s.size())
                return true;

        }

        return false;
    }

    private boolean volcanoesMatch(MapSpot n1, MapSpot n2, MapSpot n3, Hexagon h1, Hexagon h2, Hexagon h3, Map map) {
        //if the volcanoes do not match up, then the tile needs to be rotated
        if(map.getHexagon(n1).getTerrainType() == Terrain.VOLCANO && h1.getTerrainType()==Terrain.VOLCANO) return true;
        if(map.getHexagon(n2).getTerrainType() == Terrain.VOLCANO && h2.getTerrainType()==Terrain.VOLCANO) return true;
        //noinspection RedundantIfStatement
        if(map.getHexagon(n3).getTerrainType() == Terrain.VOLCANO && h3.getTerrainType()==Terrain.VOLCANO) return true;
        return false;
    }

    private boolean MapSpotsCanBeNuked(MapSpot nuked1, MapSpot nuked2, MapSpot nuked3, Map map){
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
            if( (nuked1.getAdjacentMapSpots().get(i).isEqual(nuked2))
                    || (nuked1.getAdjacentMapSpots().get(i).isEqual(nuked3))){
                count++;
            }
        }
        if(count != 2) return false;
        count = 0;
        for(int i = 0; i<nuked2.getAdjacentMapSpots().size(); i++){
            if((nuked2.getAdjacentMapSpots().get(i).isEqual(nuked1))
                    || (nuked2.getAdjacentMapSpots().get(i).isEqual(nuked3))){
                count++;
            }
        }
        if(count != 2) return false;
        count = 0;
        for(int i = 0; i<nuked3.getAdjacentMapSpots().size(); i++){
            if((nuked3.getAdjacentMapSpots().get(i).isEqual(nuked1))
                    || (nuked3.getAdjacentMapSpots().get(i).isEqual(nuked2))){
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
