package Everything.game.action.ai;

import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
import Everything.game.action.handlers.*;
import Everything.game.action.scanners.PlacingOnLevelOne.RandomLevelOneTileScanner;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.models.*;

public class TestingBot {

    //-----------
    // Attributes

    private OrientationAndVolcanoLocationCalculator orientationAndVolcanoLocationCalculator;

    // Handlers
    private FirstLevelTileAdditionHandler firstLevelTileAdditionHandler;
    private SettlementFoundingHandler settlementFoundingHandler;

    //Placement
    private RandomLevelOneTileScanner randomLevelOneTileScanner;

    // Founding
    private RandomSettlementFoundingScanner randomSettlementFoundingScanner;

    public TestingBot(final OrientationAndVolcanoLocationCalculator orientationAndVolcanoLocationCalculator,
                      final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler,
                      final SettlementFoundingHandler settlementFoundingHandler,
                      final RandomLevelOneTileScanner randomLevelOneTileScanner,
                      final RandomSettlementFoundingScanner randomSettlementFoundingScanner) {
        this.orientationAndVolcanoLocationCalculator = orientationAndVolcanoLocationCalculator;
        this.firstLevelTileAdditionHandler = firstLevelTileAdditionHandler;
        this.settlementFoundingHandler = settlementFoundingHandler;
        this.randomLevelOneTileScanner = randomLevelOneTileScanner;
        this.randomSettlementFoundingScanner = randomSettlementFoundingScanner;
    }


    public WeJustDidThisMove playTurn(final MakeMoveInstruction makeMoveInstruction, final Map map, final Player player) {

        final WeJustDidThisMove weJustDidThisMove = new WeJustDidThisMove();

        weJustDidThisMove.setBuildSpot(new MapSpot(0, 1, 0));
        weJustDidThisMove.setOrientation(0);
        weJustDidThisMove.setTileSpot(new MapSpot(1, 3, 0));
        weJustDidThisMove.setMoveNumber(4);
        weJustDidThisMove.setTerrain(Terrain.GRASS);
        weJustDidThisMove.setBuildType(1);
        return new WeJustDidThisMove();
    }
}
