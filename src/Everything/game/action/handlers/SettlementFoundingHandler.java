package Everything.game.action.handlers;

import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.models.*;

/**
 * Shows available options for settlement expansion
 * Expands while checking the game rules
 */
public class SettlementFoundingHandler {

    public WeJustDidThisMove foundSettlement(final MapSpot mapSpotToFoundOn,
                                             final Map map,
                                             final Player player) throws CannotPerformActionException {

        if (mapSpotSatisfiesFoundingRequirements(map, mapSpotToFoundOn) &&
                player.isHasEnoughMeeples(1)) {

            map.getHexagon(mapSpotToFoundOn).addMeeplesAccordingToLevel(player.getTeam());
            player.takeXMeeplesFromPlayer(1);
        } else {
            throw new CannotPerformActionException("Cannot found settlement here because:" +
                    " number of meepels left " + player.getNumberOfMeeplesLeft() +
                    " OR mapSpotSatisfies " + mapSpotSatisfiesFoundingRequirements(map, mapSpotToFoundOn));
        }

        WeJustDidThisMove move = new WeJustDidThisMove();

        move.setBuildType(1);
        move.setBuildSpot(mapSpotToFoundOn);

        return move;
    }

    private boolean mapSpotSatisfiesFoundingRequirements(final Map map, final MapSpot mapSpot) {
        return isThereAHexagonCoveringMapSpot(map, mapSpot) &&
                isOnLevelOne(map, mapSpot) &&
                isHexagonUnoccupied(map, mapSpot) &&
                !hexIsVolcano(map, mapSpot);
    }

    private boolean hexIsVolcano(Map map, MapSpot mapSpot) {
        return map.getHexagon(mapSpot).getTerrainType() == Terrain.VOLCANO;
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