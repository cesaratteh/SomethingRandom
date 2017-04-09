package Everything.game.action.utils.settlemenet.expanding;

import Everything.game.action.utils.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

public class SettlementExpansionMeeplesCost {

    //-----------
    // Attributes

    private ExpandableSpotsScanner expandableSpotsScanner;

    //------------
    // Constructor

    public SettlementExpansionMeeplesCost(final ExpandableSpotsScanner expandableSpotsScanner) {
        this.expandableSpotsScanner = expandableSpotsScanner;
    }

    //--------
    // Methods

    public int calculate(final Settlement settlement, final Map map) throws NoValidActionException {

        final ArrayList<MapSpot> expandableSpots = expandableSpotsScanner.scan(settlement, map);

        int costSum = 0;

        for (MapSpot expandableSpot : expandableSpots) {
            costSum += map.getHexagon(expandableSpot).getLevel();
        }

        if(costSum <= 0)
            throw new NoValidActionException("Meeples cost is 0");

        return costSum;
    }
}
