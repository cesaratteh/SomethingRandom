package Everything.game.action.scanners.settlemenet.expanding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

public class SettlementExpansionMeeplesCost {

    //--------
    // Methods

    public int calculate(final ArrayList<MapSpot> expandableSpots, final Map map) throws NoValidActionException {

        int costSum = 0;

        for (final MapSpot expandableSpot : expandableSpots) {
            costSum += map.getHexagon(expandableSpot).getLevel();
        }

        if(costSum <= 0)
            throw new NoValidActionException("Meeples cost is 0");

        return costSum;
    }
}
