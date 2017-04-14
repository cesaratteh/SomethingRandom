package Everything.game.action.MapUpdater;

import Everything.Server.MoveObjects.EnemyMove;
import Everything.game.action.handlers.FirstLevelTileAdditionHandler;
import Everything.game.action.handlers.NukingAndStackingHandler;
import Everything.game.action.handlers.SettlementExpansionHandler;
import Everything.game.action.handlers.SettlementFoundingHandler;
import Everything.game.action.scanners.NoValidActionException;
import Everything.models.*;

/**
 * Not done yet, don't use it
 */
public class HandlersBasedUpdater {

    private FirstLevelTileAdditionHandler firstLevelTileAdditionHandler;
    private NukingAndStackingHandler nukingAndStackingHandler;
    private SettlementExpansionHandler settlementExpansionHandler;
    private SettlementFoundingHandler settlementFoundingHandler;

    public HandlersBasedUpdater(final FirstLevelTileAdditionHandler firstLevelTileAdditionHandler,
                                final NukingAndStackingHandler nukingAndStackingHandler,
                                final SettlementExpansionHandler settlementExpansionHandler,
                                final SettlementFoundingHandler settlementFoundingHandler) {
        this.firstLevelTileAdditionHandler = firstLevelTileAdditionHandler;
        this.nukingAndStackingHandler = nukingAndStackingHandler;
        this.settlementExpansionHandler = settlementExpansionHandler;
        this.settlementFoundingHandler = settlementFoundingHandler;
    }

    public void updateMap(final Map map, final EnemyMove enemyMove) throws NoValidActionException {

        doATilePlacement(map, enemyMove);
        doABuild();
    }

    public void setFirstTile(final Map map) {

        MapSpot mapspot = new MapSpot(0, 0, 0);
        Hexagon hex1 = new Hexagon(Terrain.VOLCANO, 1, 0);
        map.setHexagon(mapspot, hex1);

        hex1 = new Hexagon(Terrain.JUNGLE, 1, 0);
        map.setHexagon(mapspot.topLeft(), hex1);

        hex1 = new Hexagon(Terrain.LAKE, 1, 0);
        map.setHexagon(mapspot.topRight(), hex1);

        hex1 = new Hexagon(Terrain.ROCK, 1, 0);
        map.setHexagon(mapspot.bottomLeft(), hex1);

        hex1 = new Hexagon(Terrain.GRASS, 1, 0);
        map.setHexagon(mapspot.bottomRight(), hex1);
    }

    public void doATilePlacement(final Map map, final EnemyMove enemyMove) {
        final MapSpot placementMapSpot = new MapSpot(enemyMove.getTileX(), enemyMove.getTileY(), enemyMove.getTileZ());

        MapSpot hexSpot2;
        MapSpot hexSpot3;

        switch (enemyMove.getOrientation()) {
            case 1:
                hexSpot2 = placementMapSpot.topLeft();
                hexSpot3 = placementMapSpot.topRight();
                break;
            case 2:
                hexSpot2 = placementMapSpot.topRight();
                hexSpot3 = placementMapSpot.right();
                break;
            case 3:
                hexSpot2 = placementMapSpot.right();
                hexSpot3 = placementMapSpot.bottomRight();
                break;
            case 4:
                hexSpot2 = placementMapSpot.bottomRight();
                hexSpot3 = placementMapSpot.bottomLeft();
                break;
            case 5:
                hexSpot2 = placementMapSpot.bottomLeft();
                hexSpot3 = placementMapSpot.left();
                break;
            case 6:
                hexSpot2 = placementMapSpot.left();
                hexSpot3 = placementMapSpot.topLeft();
                break;
            default:
                throw new RuntimeException("REACHED INVALID SPOT IN HANDLERS BASED UPDATER");

        }

        if (map.getHexagon(placementMapSpot) == null) {
            map.setHexagon(placementMapSpot, new Hexagon(Terrain.VOLCANO, 1, enemyMove.getMovenumber()));
            map.setHexagon(hexSpot2, new Hexagon(enemyMove.getTileTerrainA(), 1, enemyMove.getMovenumber()));
            map.setHexagon(hexSpot3, new Hexagon(enemyMove.getTileTerrainB(), 1, enemyMove.getMovenumber()));
        } else {
            map.setHexagon(placementMapSpot, new Hexagon(Terrain.VOLCANO, map.getHexagon(placementMapSpot).getLevel() + 1, enemyMove.getMovenumber()));
            map.setHexagon(hexSpot2, new Hexagon(enemyMove.getTileTerrainA(), 1, enemyMove.getMovenumber()));
            map.setHexagon(hexSpot3, new Hexagon(enemyMove.getTileTerrainB(), 1, enemyMove.getMovenumber()));
        }
    }

    public void doABuild() {

    }
}