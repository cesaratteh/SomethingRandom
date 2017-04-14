package Everything.game.action.scanners.settlemenet.expanding;

import Everything.game.action.scanners.NoValidActionException;
import Everything.models.Map;
import Everything.models.MapSpot;
import Everything.models.Settlement;
import Everything.models.Team;

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




        ArrayList<MapSpot> scannerSpots = settlementTouchingExpansionScanner.scan(settlement, map);
        ArrayList<MapSpot> validSpots = new ArrayList<>();

        // FIXME: 4/13/2017 REMOVE THIS IF SERVER TEAM FIX THEIR BUG
        for (MapSpot m : scannerSpots) {
            boolean badSpot = false;
            for (MapSpot adj : m.getAdjacentMapSpots()) {
                if (map.getHexagon(adj) != null && map.getHexagon(adj).getOccupiedBy() == Team.FRIENDLY &&
                        !settlement.isMapSpotInSettlement(adj)) {

                    badSpot = true;
                }
            }

            if (!badSpot) {
                validSpots.add(m);
            }
        }

        if (validSpots.isEmpty()) {
            throw new NoValidActionException("No valid spots for totoros");
        }

        return validSpots;



//        return scannerSpots;
    }
}
