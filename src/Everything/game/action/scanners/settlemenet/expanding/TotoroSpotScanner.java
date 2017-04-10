package Everything.game.action.scanners.settlemenet.expanding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

public class TotoroSpotScanner {

    //----------
    // Attributes

    private SettlementTouchingExpansionScanner settlementTouchingExpansionScanner;

    //-------------
    // Constructors

    public TotoroSpotScanner(final SettlementTouchingExpansionScanner settlementTouchingExpansionScanner) {
        this.settlementTouchingExpansionScanner = settlementTouchingExpansionScanner;
    }

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {

        if(settlement.size() < 5)
            throw new NoValidActionException("Settlement must be bigger than 5 to place totoro");

        return settlementTouchingExpansionScanner.scan(settlement, map);
    }
}
