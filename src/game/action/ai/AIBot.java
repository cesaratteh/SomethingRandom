package game.action.ai;

import game.action.handlers.FirstLevelTileAdditionHandler;
import game.action.handlers.NukingAndStackingHandler;
import game.action.handlers.SettlementExpansionHandler;
import game.action.handlers.SettlementFoundingHandler;
import game.action.utils.NoValidActionException;
import game.action.utils.Nuking.SettlementAdjacentMapSpotsScanner;
import game.action.utils.Nuking.SettlementAdjacentVolcanoesScanner;
import game.action.utils.Nuking.SettlementLevelOneTwoSpotsNukingScanner;
import game.action.utils.PlacingOnLevelOne.SettlementLevelOneTilePlacementScanner;
import game.action.utils.SettlementFounding.FoundingNextToSettlementScanner;
import game.action.utils.SettlementFounding.RandomSettlementFoundingScanner;
import game.action.utils.SettlementsFactory;
import game.action.utils.settlemenet.expanding.ExpandableSpotsScanner;
import game.action.utils.settlemenet.expanding.TigerSpotScanner;
import game.action.utils.settlemenet.expanding.TotoroSpotScanner;
import models.*;

import java.util.ArrayList;

public class AIBot {

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
    private TotoroSpotScanner totoroSpotScanner;
    private TigerSpotScanner tigerSpotScanner;
    private ExpandableSpotsScanner meeplesExpandableSpotsScanner;

    //Settlement Founding
    private FoundingNextToSettlementScanner foundingNextToSettlementScanner;
    private RandomSettlementFoundingScanner randomSettlementFoundingScanner;

    //-------------
    // Constructors


    public AIBot(final Player player,
                 final SettlementsFactory settlementsFactory,
                 final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler,
                 final NukingAndStackingHandler nukingAndStackingHandler,
                 final SettlementExpansionHandler settlementExpansionHandler,
                 final SettlementFoundingHandler settlementFoundingHandler,
                 final SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner,
                 final SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner,
                 final SettlementLevelOneTwoSpotsNukingScanner settlementLevelOneTwoSpotsNukingScanner,
                 final SettlementLevelOneTilePlacementScanner settlementLevelOneTilePlacementScanner,
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
     *
     * ordered by priority:
     * [1] Try to make a 5 hut settlement with empty spots next to it
     * [2] Try to make lvl 3 stack OR stack on level 2 to allow level 3 hut
     * [3] Put a tile somewhere random on level 1
     *
     * Give map spot AND rotation to server
     */
    public void doATilePlacementMove(final Map map, final Tile tile) {
        final ArrayList<Settlement> friendlySettlements = settlementsFactory.generateSettlements(player.getTeam());

        for (Settlement settlement : friendlySettlements) {
            try{
                TileMapSpot tileSpot =
                        settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, settlement);

                map.setHexagon(tileSpot.getM1(), tile.getH1());
                map.setHexagon(tileSpot.getM2(), tile.getH2());
                map.setHexagon(tileSpot.getM3(), tile.getH3());
                break;
            }catch (NoValidActionException e){}
        }
    }

    /**
     * Logic:
     *
     * ordered by priority:
     * [1] Place totoro
     * [2] Place tiger
     * [3] Expand using meeples and following a greedy aproach (maximize score) IF YOU HAVE ENOUGH MEEPLES
     * [4] Found with 1 meeple
     *
     * Give result to server
     */
    public void doABuildMove(final Map map) {
//        randomSettlementFoundingScanner.scan(map)

    }
}
