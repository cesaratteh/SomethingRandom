package game.action.handlers.ai;

import game.action.handlers.FirstLevelTileAdditionHandler;
import game.action.handlers.NukingAndStackingHandler;
import game.action.handlers.SettlementExpansionHandler;
import game.action.handlers.SettlementFoundingHandler;
import game.action.handlers.utils.SettlementsFactory;
import models.Map;
import models.Player;

public class AIBot {

    //-----------
    // attributes

    private Player player;

    private FirstLevelTileAdditionHandler firstLevelTileAdditionHandler;
    private NukingAndStackingHandler nukingAndStackingHandler;
    private SettlementExpansionHandler settlementExpansionHandler;
    private SettlementFoundingHandler settlementFoundingHandler;

    //-------------
    // Constructors

    public AIBot(final Player player,
                 final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler,
                 final NukingAndStackingHandler nukingAndStackingHandler,
                 final SettlementExpansionHandler settlementExpansionHandler,
                 final SettlementFoundingHandler settlementFoundingHandler) {

        this.player = player;
        this.firstLevelTileAdditionHandler = firstLevelTileAdditionHandler;
        this.nukingAndStackingHandler = nukingAndStackingHandler;
        this.settlementExpansionHandler = settlementExpansionHandler;
        this.settlementFoundingHandler = settlementFoundingHandler;
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
    public void doATilePlacementMove(Map map) {
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
    public void doABuildMove(Map map) {
    }
}
