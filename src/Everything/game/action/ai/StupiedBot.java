package Everything.game.action.ai;

import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.Server.OrientationAndVolcanoLocationCalculator;
import Everything.game.action.handlers.*;
import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.PlacingOnLevelOne.RandomLevelOneTileScanner;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.models.*;

import java.util.ArrayList;

public class StupiedBot {

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

    public StupiedBot(final OrientationAndVolcanoLocationCalculator orientationAndVolcanoLocationCalculator,
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

        final Tile tile = new Tile(new Hexagon(makeMoveInstruction.getTerrainA(), makeMoveInstruction.getMoveNumber()),
                new Hexagon(makeMoveInstruction.getTerrainB(), makeMoveInstruction.getMoveNumber()),
                new Hexagon(Terrain.VOLCANO, makeMoveInstruction.getMoveNumber()));

        final WeJustDidThisMove weJustDidThisMove = new WeJustDidThisMove();

        //Tile placement move
        try{
            weJustDidThisMove.setMoveNumber(makeMoveInstruction.getMoveNumber());
            final TileMapSpot tileMapSpot = randomLevelOneTileScanner.scan(map);

            firstLevelTileAdditionHandler.addTileToMap(tile.getH1(), tile.getH2(), tile.getH3(), tileMapSpot.getM1(), tileMapSpot.getM2(), tileMapSpot.getM3(), map);

            final MapSpot volcanoMapSpot = orientationAndVolcanoLocationCalculator.getVolcanoMapSpot(map, tileMapSpot.getM1());

            weJustDidThisMove.setTileSpot(volcanoMapSpot);
            weJustDidThisMove.setOrientation(orientationAndVolcanoLocationCalculator.getOrientation(map, volcanoMapSpot));

            // build move
            if (tileMapSpot.getM1().isEqual(volcanoMapSpot)) {
                settlementFoundingHandler.foundSettlement(tileMapSpot.getM2(), map, player.getTeam());

                weJustDidThisMove.setBuildType(1);
                weJustDidThisMove.setBuildSpot(tileMapSpot.getM2());
            } else {
                settlementFoundingHandler.foundSettlement(tileMapSpot.getM2(), map, player.getTeam());

                weJustDidThisMove.setBuildType(1);
                weJustDidThisMove.setBuildSpot(tileMapSpot.getM1());
            }

            return weJustDidThisMove;
        }catch (NoValidActionException | CannotPerformActionException e){
            System.out.println(e);
            return weJustDidThisMove;
        }
    }
}
