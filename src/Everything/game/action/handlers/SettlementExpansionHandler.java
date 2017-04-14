package Everything.game.action.handlers;

import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.settlemenet.expanding.SettlementExpansionMeeplesCost;
import Everything.models.*;

import java.util.ArrayList;

public class SettlementExpansionHandler {

    private SettlementExpansionMeeplesCost settlementExpansionMeeplesCost;

    public SettlementExpansionHandler(final SettlementExpansionMeeplesCost settlementExpansionMeeplesCost) {
        this.settlementExpansionMeeplesCost = settlementExpansionMeeplesCost;
    }

    public void expandWithMeeples(final ArrayList<MapSpot> expandableMapSpots, final Map map, final Player player) throws CannotPerformActionException {

        int meeplesCost;
        try {
            meeplesCost = settlementExpansionMeeplesCost.calculate(expandableMapSpots, map);
        } catch (NoValidActionException e) {
            throw new CannotPerformActionException("Cost is 0");
        }

        for (final MapSpot mapSpot : expandableMapSpots) {
            if (satisfiesMeeplesRequirements(mapSpot, map) &&
                player.isHasEnoughMeeples(meeplesCost)) {

                final Hexagon hex = map.getHexagon(mapSpot);
                hex.addMeeplesAccordingToLevel(player.getTeam());
                player.takeXMeeplesFromPlayer(hex.getLevel());
            } else {
                throw new CannotPerformActionException("Cannot expand with meeples");
            }
        }

    }

    public WeJustDidThisMove expandWithTotoro(final MapSpot mapSpot, final Map map, final Player player) throws CannotPerformActionException {
        if (satisfiesTotoroRequirements(mapSpot, map) &&
                player.isHasTotorosLeft()) {
            map.getHexagon(mapSpot).addTotoro(player.getTeam());

            WeJustDidThisMove move = new WeJustDidThisMove();
            move.setBuildType(3);
            move.setBuildSpot(mapSpot);
            player.takeATotoroFromPlayer();

            return move;
        } else {
            throw new CannotPerformActionException("Cannot expand with totoro here");
        }
    }

    public WeJustDidThisMove expandWithTiger(final MapSpot mapSpot, final Map map, final Player player) throws CannotPerformActionException {
        if (satisfiesTigerRequirements(mapSpot, map) &&
                player.isHasTigersLeft()) {
            map.getHexagon(mapSpot).addTotoro(player.getTeam());

            WeJustDidThisMove move = new WeJustDidThisMove();
            move.setBuildType(4);
            move.setBuildSpot(mapSpot);
            player.takeATigerFromPlayer();

            return move;
        } else {
            throw new CannotPerformActionException("Cannot expand with tigers");
        }
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
