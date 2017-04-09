package Everything.game.action.utils.settlemenet.expanding;

import Everything.game.action.utils.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

public class TigerSpotScanner {

    //-----------
    // Attributes

    private SettlementTouchingExpansionScanner settlementTouchingExpansionScanner;

    //-------------
    // Constructors

    public TigerSpotScanner(final SettlementTouchingExpansionScanner settlementTouchingExpansionScanner) {
        this.settlementTouchingExpansionScanner = settlementTouchingExpansionScanner;
    }

    //--------
    // Methods

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {
        final ArrayList<MapSpot> expandableMapSpots = settlementTouchingExpansionScanner.scan(settlement, map);

        final ArrayList<MapSpot> tigerSpots = new ArrayList<>();

        for (MapSpot mapSpot : expandableMapSpots) {
            if (map.getHexagon(mapSpot).getLevel() >= 3) {
                tigerSpots.add(mapSpot);
            }
        }

        if(tigerSpots.size() < 1)
            throw new NoValidActionException("No valid tiger spots");

        return tigerSpots;
    }

}
