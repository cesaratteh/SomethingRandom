package Everything.game.action.ai;

import Everything.game.action.handlers.FirstLevelTileAdditionHandler;
import Everything.game.action.handlers.NukingAndStackingHandler;
import Everything.game.action.handlers.SettlementExpansionHandler;
import Everything.game.action.handlers.SettlementFoundingHandler;
import Everything.game.action.utils.NoValidActionException;
import Everything.game.action.utils.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.game.action.utils.Nuking.SettlementAdjacentVolcanoesScanner;
import Everything.game.action.utils.Nuking.SettlementLevelOneTwoSpotsNukingScanner;
import Everything.game.action.utils.PlacingOnLevelOne.SettlementLevelOneTilePlacementScanner;
import Everything.game.action.utils.SettlementFounding.FoundingNextToSettlementScanner;
import Everything.game.action.utils.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.game.action.utils.SettlementsFactory;
import Everything.game.action.utils.settlemenet.expanding.ExpandableSpotsScanner;
import Everything.game.action.utils.settlemenet.expanding.SettlementExpansionMeeplesCost;
import Everything.game.action.utils.settlemenet.expanding.TigerSpotScanner;
import Everything.game.action.utils.settlemenet.expanding.TotoroSpotScanner;
import Everything.models.*;

import java.util.ArrayList;

public class AIBotBackup {

    //-----------
    // attributes

    private Player player;
    private SettlementsFactory settlementsFactory;

    // Handlers
    private FirstLevelTileAdditionHandler firstLevelTileAdditionHandler;
    private NukingAndStackingHandler nukingAndStackingHandler;
    private SettlementExpansionHandler settlementExpansionHandler;
    private SettlementFoundingHandler settlementFoundingHandler;

    // Nuking
    private SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner;
    private SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner;
    private SettlementLevelOneTwoSpotsNukingScanner settlementLevelOneTwoSpotsNukingScanner;

    // Placing
    private SettlementLevelOneTilePlacementScanner settlementLevelOneTilePlacementScanner;

    //Settlement Expansion
    private SettlementExpansionMeeplesCost settlementExpansionMeeplesCost;
    private TotoroSpotScanner totoroSpotScanner;
    private TigerSpotScanner tigerSpotScanner;
    private ExpandableSpotsScanner meeplesExpandableSpotsScanner;

    //Settlement Founding
    private FoundingNextToSettlementScanner foundingNextToSettlementScanner;
    private RandomSettlementFoundingScanner randomSettlementFoundingScanner;

    //-------------
    // Constructors


    public AIBotBackup(final Player player,
                 final SettlementsFactory settlementsFactory,
                 final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler,
                 final NukingAndStackingHandler nukingAndStackingHandler,
                 final SettlementExpansionHandler settlementExpansionHandler,
                 final SettlementFoundingHandler settlementFoundingHandler,
                 final SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner,
                 final SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner,
                 final SettlementLevelOneTwoSpotsNukingScanner settlementLevelOneTwoSpotsNukingScanner,
                 final SettlementLevelOneTilePlacementScanner settlementLevelOneTilePlacementScanner,
                 final SettlementExpansionMeeplesCost settlementExpansionMeeplesCost,
                 final TotoroSpotScanner totoroSpotScanner,
                 final TigerSpotScanner tigerSpotScanner,
                 final ExpandableSpotsScanner meeplesExpandableSpotsScanner,
                 final FoundingNextToSettlementScanner foundingNextToSettlementScanner,
                 final RandomSettlementFoundingScanner randomSettlementFoundingScanner) {
        this.player = player;
        this.settlementsFactory = settlementsFactory;
        this.firstLevelTileAdditionHandler = firstLevelTileAdditionHandler;
        this.nukingAndStackingHandler = nukingAndStackingHandler;
        this.settlementExpansionHandler = settlementExpansionHandler;
        this.settlementFoundingHandler = settlementFoundingHandler;
        this.settlementAdjacentMapSpotsScanner = settlementAdjacentMapSpotsScanner;
        this.settlementAdjacentVolcanoesScanner = settlementAdjacentVolcanoesScanner;
        this.settlementLevelOneTwoSpotsNukingScanner = settlementLevelOneTwoSpotsNukingScanner;
        this.settlementLevelOneTilePlacementScanner = settlementLevelOneTilePlacementScanner;
        this.settlementExpansionMeeplesCost = settlementExpansionMeeplesCost;
        this.totoroSpotScanner = totoroSpotScanner;
        this.tigerSpotScanner = tigerSpotScanner;
        this.meeplesExpandableSpotsScanner = meeplesExpandableSpotsScanner;
        this.foundingNextToSettlementScanner = foundingNextToSettlementScanner;
        this.randomSettlementFoundingScanner = randomSettlementFoundingScanner;
    }

    //--------
    // Methods

    /**
     * Logic:
     * <p>
     * ordered by priority:
     * [1] Try to make a 5 hut settlement with empty spots next to it
     * [2] Try to make lvl 3 stack OR stack on level 2 to allow level 3 hut
     * [3] Put a tile somewhere random on level 1
     * <p>
     * Give map spot AND rotation to server
     */
    public void doATilePlacementMove(final Map map, final Tile tile) {
        final ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(player.getTeam());

        final ArrayList<Settlement> enemySettlements =
                settlementsFactory.generateSettlements((player.getTeam() == Team.FRIENDLY) ? Team.ENEMY : Team.FRIENDLY);

        for (Settlement settlement : enemySettlements) {
            if (settlement.size() >= 4 && settlement.getNumberOfTotoros() < 1) {
                try {
                    final TileMapSpot tileMapSpotToNukeOnSettlment =
                            settlementLevelOneTwoSpotsNukingScanner.findTileMapSpotToNukeOnSettlment(settlement, map);

                    map.setHexagon(tileMapSpotToNukeOnSettlment.getM1(), tile.getH1());
                    map.setHexagon(tileMapSpotToNukeOnSettlment.getM2(), tile.getH2());
                    map.setHexagon(tileMapSpotToNukeOnSettlment.getM3(), tile.getH3());
                    return;
                } catch (NoValidActionException e) {
                }
            }
        }

        for (Settlement friendlySettlement : friendlySettlements) {
            if (friendlySettlement.size() >= 4 && friendlySettlement.getNumberOfTotoros() < 1) {
                try {

                    TileMapSpot tileMapSpot
                            = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

                    map.setHexagon(tileMapSpot.getM1(), tile.getH1());
                    map.setHexagon(tileMapSpot.getM2(), tile.getH2());
                    map.setHexagon(tileMapSpot.getM3(), tile.getH3());
                    return;
                } catch (NoValidActionException e) {}
            }
        }
    }

    /**
     * Logic:
     * <p>
     * ordered by priority:
     * [1] Place totoro
     * [2] Place tiger
     * [3] Expand using meeples and following a greedy aproach (maximize score) IF YOU HAVE ENOUGH MEEPLES
     * [4] Found with 1 meeple
     * <p>
     * Give result to server
     */
    public void doABuildMove(final Map map) {
        final ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(player.getTeam());

        for (Settlement friendlySettlement : friendlySettlements) {
            if (friendlySettlement.size() >= 5 && friendlySettlement.getNumberOfTotoros() < 1) {
                try {

                    ArrayList<MapSpot> totoroSpots = totoroSpotScanner.scan(friendlySettlement, map);

                    for (MapSpot totoroSpot : totoroSpots) {
                        if (map.getHexagon(totoroSpot).getLevel() < 3) {
                            map.getHexagon(totoroSpot).addTotoro(Team.FRIENDLY);
                            return;
                        }
                    }

                    map.getHexagon(totoroSpots.get(0)).addTotoro(Team.FRIENDLY);
                    return;
                } catch (NoValidActionException e) {}
            }
        }

        for (Settlement friendlySettlement : friendlySettlements) {
            try {

                final ArrayList<MapSpot> tigerSpots = tigerSpotScanner.scan(friendlySettlement, map);

                map.getHexagon(tigerSpots.get(0)).addTiger(Team.FRIENDLY);
                return;
            } catch (NoValidActionException e) {}
        }

        for (Settlement friendlySettlement : friendlySettlements) {
            try {
                if (friendlySettlement.getNumberOfTotoros() < 1) {
                    final ArrayList<MapSpot> expandableMapSpots
                            = meeplesExpandableSpotsScanner.scan(friendlySettlement, map);

                    if (expandableMapSpots.size() + friendlySettlement.size() < 7) {
                        settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                        return;
                    }
                }

            } catch (NoValidActionException e) {}
        }

        for (Settlement friendlySettlement : friendlySettlements) {
            try {
                if (friendlySettlement.getNumberOfTotoros() < 1) {
                    final ArrayList<MapSpot> expandableMapSpots
                            = meeplesExpandableSpotsScanner.scan(friendlySettlement, map);

                    if (expandableMapSpots.size() + friendlySettlement.size() > 7) {
                        ArrayList<MapSpot> foundingSpots = foundingNextToSettlementScanner.scan(friendlySettlement, map);
                        map.getHexagon(foundingSpots.get(0)).addMeeplesAccordingToLevel(Team.FRIENDLY);
                        return;
                    }
                }

            } catch (NoValidActionException e) {}
        }
    }
}
