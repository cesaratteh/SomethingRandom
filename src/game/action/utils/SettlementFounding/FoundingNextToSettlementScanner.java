package game.action.utils.SettlementFounding;

import game.action.utils.NoValidActionException;
import game.action.utils.settlemenet.expanding.SettlementTouchingExpansionScanner;
import models.Map;
import models.MapSpot;
import models.Settlement;

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

    private ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {
        return settlementTouchingExpansionScanner.scan(settlement, map);
    }
}
