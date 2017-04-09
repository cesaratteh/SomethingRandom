package Everything.game.action.ai;


import Everything.game.action.handlers.FirstLevelTileAdditionHandler;
import Everything.game.action.handlers.NukingAndStackingHandler;

public class  TilePlacementDecisionMaker {

    //-----------
    // attributes

    private final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler;
    private final NukingAndStackingHandler nukingAndStackingHandler;

    //-------------
    // Constructors

    public TilePlacementDecisionMaker(final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler,
                                      final NukingAndStackingHandler nukingAndStackingHandler) {

        this.firstLevelTileAdditionHandler = firstLevelTileAdditionHandler;
        this.nukingAndStackingHandler = nukingAndStackingHandler;
    }

    //--------
    // Methods



}
