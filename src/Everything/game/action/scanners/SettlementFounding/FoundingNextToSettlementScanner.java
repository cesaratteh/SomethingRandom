package Everything.game.action.scanners.SettlementFounding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.settlemenet.expanding.SettlementTouchingExpansionScanner;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

public class FoundingNextToSettlementScanner {

    //-----------
    // Attributes

    private SettlementTouchingExpansionScanner settlementTouchingExpansionScanner;

    //-------------
    // Constructors

    public FoundingNextToSettlementScanner(final SettlementTouchingExpansionScanner settlementTouchingExpansionScanner) {
        this.settlementTouchingExpansionScanner = settlementTouchingExpansionScanner;
    }

    //--------
    // Methods

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {
        return settlementTouchingExpansionScanner.scan(settlement, map);
    }
}
