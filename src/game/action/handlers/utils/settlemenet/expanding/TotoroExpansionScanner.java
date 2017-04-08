package game.action.handlers.utils.settlemenet.expanding;

import game.action.handlers.utils.NoValidActionException;
import game.action.handlers.utils.Nuking.SettlementAdjacentMapSpotsScanner;
import models.Map;
import models.MapSpot;
import models.Settlement;

import java.util.ArrayList;

/**
 * Scans available map spots where a Totoro can be placed
 */
public class TotoroExpansionScanner {

    //-----------
    // Attributes

    private SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner;
    private ExpandableSpotsScanner expandableSpotsScanner;

    //-------------
    // Constructors

    public TotoroExpansionScanner(final SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner ,
                                  final ExpandableSpotsScanner expandableSpotsScanner) {
        this.settlementAdjacentMapSpotsScanner = settlementAdjacentMapSpotsScanner;
        this.expandableSpotsScanner = expandableSpotsScanner;
    }

    //---------------
    // Public Methods

    public ArrayList<MapSpot> scan(final Settlement settlement, final Map map) throws NoValidActionException{

        if(settlement.size() < 5)
            throw new NoValidActionException("Cannot build totoro on a settlement smaller than 5");

        final ArrayList<MapSpot> settlementAdjacentMapSpots = settlementAdjacentMapSpotsScanner.generate(settlement, map);
        final ArrayList<MapSpot> settlementExpandableMapSpots = expandableSpotsScanner.scan(settlement, map);

        final ArrayList<MapSpot> totoroExpansionSpots = new ArrayList<>();

        boolean intersection[][][] = new boolean[Map.size()][Map.size()][Map.size()];


        for (final MapSpot mapSpot : settlementAdjacentMapSpots) {
            intersection[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()] = true;
        }

        for (final MapSpot mapSpot : settlementExpandableMapSpots) {
            if (intersection[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()]) {
                totoroExpansionSpots.add(mapSpot);
            }
        }

        if(totoroExpansionSpots.size() < 1)
            throw new NoValidActionException("No totoro expansion spots");

        return totoroExpansionSpots;
    }
}
