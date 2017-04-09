package Everything.game.action.utils.settlemenet.expanding;

import Everything.game.action.utils.NoValidActionException;
import Everything.game.action.utils.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;

import java.util.ArrayList;

/**
 * Scans available map spots where a Totoro can be placed
 */
public class SettlementTouchingExpansionScanner {

    //-----------
    // Attributes

    private SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner;
    private ExpandableSpotsScanner expandableSpotsScanner;

    //-------------
    // Constructors

    public SettlementTouchingExpansionScanner(final SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner ,
                                              final ExpandableSpotsScanner expandableSpotsScanner) {
        this.settlementAdjacentMapSpotsScanner = settlementAdjacentMapSpotsScanner;
        this.expandableSpotsScanner = expandableSpotsScanner;
    }

    //---------------
    // Public Methods

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {

        final ArrayList<MapSpot> settlementAdjacentMapSpots = settlementAdjacentMapSpotsScanner.generate(settlement, map);
        final ArrayList<MapSpot> settlementExpandableMapSpots = expandableSpotsScanner.scan(settlement, map);

        final ArrayList<MapSpot> expansionSpots = new ArrayList<>();

        boolean intersection[][][] = new boolean[Map.size()][Map.size()][Map.size()];

        for (final MapSpot mapSpot : settlementAdjacentMapSpots) {
            intersection[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()] = true;
        }

        for (final MapSpot mapSpot : settlementExpandableMapSpots) {
            if (intersection[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()]) {
                expansionSpots.add(mapSpot);
            }
        }

        if(expansionSpots.size() < 1)
            throw new NoValidActionException("No valid expansion spots");

        return expansionSpots;
    }
}
