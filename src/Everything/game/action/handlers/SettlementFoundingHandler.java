package Everything.game.action.handlers;

import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Team;

/**
 * Shows available options for settlement expansion
 * Expands while checking the game rules
 */
public class SettlementFoundingHandler {

    public void foundSettlement(final MapSpot mapSpotToFoundOn,
                                final Map map,
                                final Team team) throws CannotPerformActionException {

        if(mapSpotSatisfiesFoundingRequirements(map, mapSpotToFoundOn))
            map.getHexagon(mapSpotToFoundOn).addMeeplesAccordingToLevel(team);
        else
            throw new CannotPerformActionException("Cannot found settlement here");
    }

    private boolean mapSpotSatisfiesFoundingRequirements(final Map map, final MapSpot mapSpot) {
        return isThereAHexagonCoveringMapSpot(map, mapSpot) &&
                isOnLevelOne(map, mapSpot) &&
                isHexagonUnoccupied(map, mapSpot);
    }

    private boolean isThereAHexagonCoveringMapSpot(final Map map, final MapSpot mapSpot) {
        return map.getHexagon(mapSpot) != null;
    }

    private boolean isOnLevelOne(final Map map, final MapSpot mapSpot) {
        return map.getHexagon(mapSpot).getLevel() == 1;
    }

    private boolean isHexagonUnoccupied(final Map map, final MapSpot mapSpot) {
        return map.getHexagon(mapSpot).isEmpty();
    }
}