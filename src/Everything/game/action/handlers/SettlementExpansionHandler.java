package Everything.game.action.handlers;

import Everything.models.*;

import java.util.ArrayList;

public class SettlementExpansionHandler {

    public void expandWithMeeples(final ArrayList<MapSpot> expandableMapSpots, final Map map, final Team team) throws CannotPerformActionException {

        for (final MapSpot mapSpot : expandableMapSpots) {
            if (satisfiesMeeplesRequirements(mapSpot, map)) {
                final Hexagon hex = map.getHexagon(mapSpot);
                hex.addMeeplesAccordingToLevel(team);
            } else {
                throw new CannotPerformActionException("Cannot expand with meeples");
            }
        }
    }

    public void expandWithTotoro(final MapSpot mapSpot, final Map map, final Team team) throws CannotPerformActionException {
        if(satisfiesTotoroRequirements(mapSpot, map))
            map.getHexagon(mapSpot).addTotoro(team);
        else
            throw new CannotPerformActionException("Cannot expand with totoro here");
    }

    public void expandWithTiger(final MapSpot mapSpot, final Map map, final Team team) throws CannotPerformActionException {
        if(satisfiesTigerRequirements(mapSpot, map))
            map.getHexagon(mapSpot).addTotoro(team);
        else
            throw new CannotPerformActionException("Cannot expand with tigers");
    }

    private boolean satisfiesMeeplesRequirements(final MapSpot mapSpot, final Map map) {
        return isThereAHexagonCoveringMapSpot(mapSpot, map) &&
                isHexagonUnoccupied(mapSpot, map) &&
                isHexagonNonVolcanic(mapSpot, map);
    }

    private boolean satisfiesTotoroRequirements(final MapSpot mapSpot, final Map map) {
        return isThereAHexagonCoveringMapSpot(mapSpot, map) &&
                isHexagonUnoccupied(mapSpot, map) &&
                isHexagonNonVolcanic(mapSpot, map);
    }

    private boolean satisfiesTigerRequirements(final MapSpot mapSpot, final Map map) {
        return isThereAHexagonCoveringMapSpot(mapSpot, map) &&
                isOnLevelThreeOrHigher(mapSpot, map) &&
                isHexagonUnoccupied(mapSpot, map) &&
                isHexagonNonVolcanic(mapSpot, map);
    }

    private boolean isThereAHexagonCoveringMapSpot(final MapSpot mapSpot, final Map map) {
        return map.getHexagon(mapSpot) != null;
    }

    private boolean isOnLevelThreeOrHigher(final MapSpot mapSpot, final Map map) {
        return map.getHexagon(mapSpot).getLevel() >= 3;
    }

    private boolean isHexagonUnoccupied(final MapSpot mapSpot, final Map map) {
        return map.getHexagon(mapSpot).isEmpty();
    }

    private boolean isHexagonNonVolcanic(final MapSpot mapSpot, final Map map) {
        return map.getHexagon(mapSpot).getTerrainType() != Terrain.VOLCANO;
    }
}
