package Everything.game.action.scanners.settlemenet.expanding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;
import Everything.models.Terrain;

import java.util.ArrayList;

/**
 * Scans available map spots where a Totoro can be placed
**/
public class SettlementTouchingExpansionScanner {

    //-----------
    // Attributes

    private SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner;

    //-------------
    // Constructors

    public SettlementTouchingExpansionScanner(final SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner) {
        this.settlementAdjacentMapSpotsScanner = settlementAdjacentMapSpotsScanner;
    }

    //---------------
    // Public Methods

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException {

        final ArrayList<MapSpot> settlementAdjacentMapSpots = settlementAdjacentMapSpotsScanner.generate(settlement, map);

        final ArrayList<MapSpot> expansionSpots = new ArrayList<>();

        for (final MapSpot mapSpot : settlementAdjacentMapSpots) {
            if (satisfiesTotoroOrTigerExpansionOrMeepleFoundingRequirements(mapSpot, map)) {
                expansionSpots.add(mapSpot);
            }
        }

        if(expansionSpots.size() < 1)
            throw new NoValidActionException("No valid expansion spots");

        return expansionSpots;
    }

    //----------------
    // Private Methods

    private boolean satisfiesTotoroOrTigerExpansionOrMeepleFoundingRequirements(final MapSpot mapSpot,
                                                                                final Map map) {
        return map.getHexagon(mapSpot) != null &&
                map.getHexagon(mapSpot).getTerrainType() != Terrain.VOLCANO &&
                map.getHexagon(mapSpot).isEmpty();
    }
}
