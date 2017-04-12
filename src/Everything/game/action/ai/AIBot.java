package Everything.game.action.ai;

import Everything.Server.MoveObjects.MakeMoveInstruction;
import Everything.Server.MoveObjects.WeJustDidThisMove;
import Everything.game.action.handlers.*;
import Everything.game.action.scanners.NoValidActionException;
import Everything.game.action.scanners.Nuking.SettlementAdjacentMapSpotsScanner;
import Everything.game.action.scanners.Nuking.SettlementAdjacentVolcanoesScanner;
import Everything.game.action.scanners.Nuking.SettlementLevelOneTwoSpotsNukingScanner;
import Everything.game.action.scanners.PlacingOnLevelOne.RandomLevelOneTileScanner;
import Everything.game.action.scanners.PlacingOnLevelOne.SettlementLevelOneTilePlacementScanner;
import Everything.game.action.scanners.SettlementFounding.FoundingNextToSettlementScanner;
import Everything.game.action.scanners.SettlementFounding.RandomSettlementFoundingScanner;
import Everything.game.action.scanners.SettlementsFactory;
import Everything.game.action.scanners.settlemenet.expanding.ExpansionToSpecificTerrainScanner;
import Everything.game.action.scanners.settlemenet.expanding.SettlementExpansionMeeplesCost;
import Everything.game.action.scanners.settlemenet.expanding.TigerSpotScanner;
import Everything.game.action.scanners.settlemenet.expanding.TotoroSpotScanner;
import Everything.models.*;

import java.util.ArrayList;

// TODO: 4/9/2017 1. Place tile in random spot 2. Found villager in random spot 3. Optimal expansion logic

public class AIBot {

    //-----------
    // attributes

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
    private RandomLevelOneTileScanner randomLevelOneTileScanner;
    private SettlementLevelOneTilePlacementScanner settlementLevelOneTilePlacementScanner;

    //Settlement Expansion
    private ExpansionToSpecificTerrainScanner expansionToSpecificTerrainScanner;
    private SettlementExpansionMeeplesCost settlementExpansionMeeplesCost;
    private TigerSpotScanner tigerSpotScanner;
    private TotoroSpotScanner totoroSpotScanner;

    //Settlement Founding
    private FoundingNextToSettlementScanner foundingNextToSettlementScanner;
    private RandomSettlementFoundingScanner randomSettlementFoundingScanner;

    //-------------
    // Constructors

    public AIBot(SettlementsFactory settlementsFactory, FirstLevelTileAdditionHandler firstLevelTileAdditionHandler, NukingAndStackingHandler nukingAndStackingHandler, SettlementExpansionHandler settlementExpansionHandler, SettlementFoundingHandler settlementFoundingHandler, SettlementAdjacentMapSpotsScanner settlementAdjacentMapSpotsScanner, SettlementAdjacentVolcanoesScanner settlementAdjacentVolcanoesScanner, SettlementLevelOneTwoSpotsNukingScanner settlementLevelOneTwoSpotsNukingScanner, RandomLevelOneTileScanner randomLevelOneTileScanner, SettlementLevelOneTilePlacementScanner settlementLevelOneTilePlacementScanner, ExpansionToSpecificTerrainScanner expansionToSpecificTerrainScanner, SettlementExpansionMeeplesCost settlementExpansionMeeplesCost, TigerSpotScanner tigerSpotScanner, TotoroSpotScanner totoroSpotScanner, FoundingNextToSettlementScanner foundingNextToSettlementScanner, RandomSettlementFoundingScanner randomSettlementFoundingScanner) {
        this.settlementsFactory = settlementsFactory;
        this.firstLevelTileAdditionHandler = firstLevelTileAdditionHandler;
        this.nukingAndStackingHandler = nukingAndStackingHandler;
        this.settlementExpansionHandler = settlementExpansionHandler;
        this.settlementFoundingHandler = settlementFoundingHandler;
        this.settlementAdjacentMapSpotsScanner = settlementAdjacentMapSpotsScanner;
        this.settlementAdjacentVolcanoesScanner = settlementAdjacentVolcanoesScanner;
        this.settlementLevelOneTwoSpotsNukingScanner = settlementLevelOneTwoSpotsNukingScanner;
        this.randomLevelOneTileScanner = randomLevelOneTileScanner;
        this.settlementLevelOneTilePlacementScanner = settlementLevelOneTilePlacementScanner;
        this.expansionToSpecificTerrainScanner = expansionToSpecificTerrainScanner;
        this.settlementExpansionMeeplesCost = settlementExpansionMeeplesCost;
        this.tigerSpotScanner = tigerSpotScanner;
        this.totoroSpotScanner = totoroSpotScanner;
        this.foundingNextToSettlementScanner = foundingNextToSettlementScanner;
        this.randomSettlementFoundingScanner = randomSettlementFoundingScanner;
    }


    //--------
    // Methods

    public WeJustDidThisMove playTurn(final MakeMoveInstruction makeMoveInstruction, final Map map, final Player player) {
        final Tile tile = new Tile(new Hexagon(makeMoveInstruction.getTerrainA(), makeMoveInstruction.getMoveNumber()),
                new Hexagon(makeMoveInstruction.getTerrainB(), makeMoveInstruction.getMoveNumber()),
                new Hexagon(Terrain.VOLCANO, makeMoveInstruction.getMoveNumber()));

        final WeJustDidThisMove placementAndBuildMove = new WeJustDidThisMove();

        WeJustDidThisMove placementMove = doATilePlacementMove(map, player, tile);
        WeJustDidThisMove buildMove = doABuildMove(map, player);

        placementAndBuildMove.setMoveNumber(makeMoveInstruction.getMoveNumber());

        placementAndBuildMove.setBuildType(buildMove.getBuildType());
        placementAndBuildMove.setBuildSpot(buildMove.getBuildSpot());
        placementAndBuildMove.setTerrain(buildMove.getTerrain());

        placementAndBuildMove.setTileSpot(placementMove.getTileSpot());
        placementAndBuildMove.setOrientation(placementMove.getOrientation());

        return placementAndBuildMove;
    }

    private WeJustDidThisMove placeTileNextToSettlement(Map map, Settlement friendlySettlement, Tile tile) throws NoValidActionException, CannotPerformActionException {
        TileMapSpot tileMapSpot
                = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

        return firstLevelTileAdditionHandler.addTileToMap(tile.getH1(),
                tile.getH2(),
                tile.getH3(),
                tileMapSpot.getM1(),
                tileMapSpot.getM2(),
                tileMapSpot.getM3(),
                map);
    }

    private WeJustDidThisMove placeTileInRandomSpot(Map map, Tile tile) throws NoValidActionException, CannotPerformActionException {
        randomLevelOneTileScanner.scan(map);
        TileMapSpot tileMapSpot = randomLevelOneTileScanner.scan(map);

        return firstLevelTileAdditionHandler.addTileToMap(tile.getH1(),
                tile.getH2(),
                tile.getH3(),
                tileMapSpot.getM1(),
                tileMapSpot.getM2(),
                tileMapSpot.getM3(),
                map);
    }

    private WeJustDidThisMove doATilePlacementMove(final Map map, final Player player, final Tile tile) {
        final ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(map, player.getTeam());

        final ArrayList<Settlement> enemySettlements =
                settlementsFactory.generateSettlements(map, (player.getTeam() == Team.FRIENDLY) ? Team.ENEMY : Team.FRIENDLY);

        if (player.getNumberOfTotorosLeft() > 0) {
            if (friendlySettlements.size() == 0) {
                try {
                    return placeTileInRandomSpot(map, tile);
                } catch (NoValidActionException | CannotPerformActionException e) {
                }
            } else {
                for (Settlement friendlySettlement : friendlySettlements) {
                    if (friendlySettlement.size() >= 5 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            return placeTileNextToSettlement(map, friendlySettlement, tile);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else if (friendlySettlement.size() == 4 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            return placeTileNextToSettlement(map, friendlySettlement, tile);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else if (friendlySettlement.size() == 3 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            return placeTileNextToSettlement(map, friendlySettlement, tile);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else if (friendlySettlement.size() == 2 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            return placeTileNextToSettlement(map, friendlySettlement, tile);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else if (friendlySettlement.size() == 1 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            return placeTileNextToSettlement(map, friendlySettlement, tile);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else {
                        // Place tile in any random spot
                        try {
                            return placeTileInRandomSpot(map, tile);
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    }
                }
            }
        } else if (player.getNumberOfMeeplesLeft() > 0) {
            // Place tile in any random spot
            try {
                return placeTileInRandomSpot(map, tile);
            } catch (NoValidActionException | CannotPerformActionException e) {
            }
        }


        // Could not place tile anywhere
        WeJustDidThisMove gameOverMove = new WeJustDidThisMove();
        gameOverMove.setBuildType(5);
        System.out.println("AIBot: Reached incorrect place, we should always be able to place a tile");
        return gameOverMove;
    }

    private WeJustDidThisMove expandIfResultingSettlementIsSizeX(Map map, Settlement settlement, Team team, int sizeX) throws NoValidActionException, CannotPerformActionException {

        for (Terrain currentTerrain : Terrain.values()) {
            if (currentTerrain != Terrain.VOLCANO) {
                ArrayList<MapSpot> expansionSpots = expansionToSpecificTerrainScanner.scan(settlement, map, currentTerrain);

                if (settlement.size() + expansionSpots.size() == sizeX) {
                    settlementExpansionHandler.expandWithMeeples(expansionSpots, map, team);

                    WeJustDidThisMove expansionMove = new WeJustDidThisMove();
                    expansionMove.setBuildType(2);
                    expansionMove.setBuildSpot(settlement.getMapSpots().get(0));
                    expansionMove.setTerrain(currentTerrain);

                    return expansionMove;
                }
            }
        }

        throw new NoValidActionException("expandIfResultingSettlementIsSizeFix couldn't make a move");
    }

    private WeJustDidThisMove foundMeepleToIncreaseSettlementSize(Map map, Settlement settlement, Team team) throws NoValidActionException, CannotPerformActionException {
        ArrayList<MapSpot> foundingSpots = foundingNextToSettlementScanner.scan(settlement, map);
        return settlementFoundingHandler.foundSettlement(foundingSpots.get(0), map, team);
    }

    private WeJustDidThisMove doABuildMove(final Map map, final Player player) {
        final ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(map, player.getTeam());

        if (player.getNumberOfTotorosLeft() > 0) {
            if (friendlySettlements.size() == 0) {
                try {
                    MapSpot foundingSpot = randomSettlementFoundingScanner.scan(map);
                    return settlementFoundingHandler.foundSettlement(foundingSpot, map, player.getTeam());
                } catch (NoValidActionException | CannotPerformActionException e) {
                }
            } else {
                for (Settlement friendlySettlement : friendlySettlements) {
                    if (friendlySettlement.size() >= 5 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            ArrayList<MapSpot> totoroSpots = totoroSpotScanner.scan(friendlySettlement, map);
                            return settlementExpansionHandler.expandWithTotoro(totoroSpots.get(0), map, player.getTeam());

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else if (friendlySettlement.size() == 4 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            return foundMeepleToIncreaseSettlementSize(map, friendlySettlement, player.getTeam());
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                    } else if (friendlySettlement.size() == 3 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            return expandIfResultingSettlementIsSizeX(map, friendlySettlement, player.getTeam(), 5);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                        try {
                            return foundMeepleToIncreaseSettlementSize(map, friendlySettlement, player.getTeam());
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                    } else if (friendlySettlement.size() == 2 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            return expandIfResultingSettlementIsSizeX(map, friendlySettlement, player.getTeam(), 5);
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                        try {
                            return expandIfResultingSettlementIsSizeX(map, friendlySettlement, player.getTeam(), 4);
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                        try {
                            return foundMeepleToIncreaseSettlementSize(map, friendlySettlement, player.getTeam());
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                    } else if (friendlySettlement.size() == 1 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            return expandIfResultingSettlementIsSizeX(map, friendlySettlement, player.getTeam(), 5);

                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                        try {
                            return expandIfResultingSettlementIsSizeX(map, friendlySettlement, player.getTeam(), 4);
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                        try {
                            return expandIfResultingSettlementIsSizeX(map, friendlySettlement, player.getTeam(), 3);
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }

                        try {
                            return foundMeepleToIncreaseSettlementSize(map, friendlySettlement, player.getTeam());
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    } else {
                        try {
                            // Place villager on any hex
                            final MapSpot foundingSpots = randomSettlementFoundingScanner.scan(map);
                            return settlementFoundingHandler.foundSettlement(foundingSpots, map, player.getTeam());
                        } catch (NoValidActionException | CannotPerformActionException e) {
                        }
                    }
                }
            }
        } else if (player.getNumberOfMeeplesLeft() > 0) {
            // Iterate through expansion options to exhaust meeple count ....
            //  a. check meeple count minus expansion cost
            //  b. if difference == 0, then immediately expand
            //  c. if difference > 0, then check if new max is found
            //  d. if all costs for expansion are greater than meeple count, then found villager in any random spot

            ArrayList<MapSpot> maxExpansionSpots = null;
            Settlement maxSettlementToExpand = null;
            Terrain maxTerrain = null;
            int maxCost = Integer.MIN_VALUE;

            for (Settlement friendlySettlement : friendlySettlements) {
                for (Terrain expansionTerrain : Terrain.values()) {
                    try {
                        if (expansionTerrain != Terrain.VOLCANO) {

                            ArrayList<MapSpot> expandableSpots = expansionToSpecificTerrainScanner.scan(friendlySettlement, map, expansionTerrain);
                            int meeplesCost = settlementExpansionMeeplesCost.calculate(expandableSpots, map);
                            if (player.isHasEnoughMeeples(meeplesCost)) {

                                if (meeplesCost == player.getNumberOfMeeplesLeft()) {
                                    try {
                                        settlementExpansionHandler.expandWithMeeples(expandableSpots, map, player.getTeam());
                                        WeJustDidThisMove expansionMove = new WeJustDidThisMove();
                                        expansionMove.setBuildType(2);
                                        expansionMove.setBuildSpot(friendlySettlement.getMapSpots().get(0));
                                        expansionMove.setTerrain(expansionTerrain);
                                        return expansionMove;
                                    } catch (CannotPerformActionException e) {
                                    }
                                } else {
                                    if (meeplesCost > maxCost) {
                                        maxCost = meeplesCost;
                                        maxExpansionSpots = expandableSpots;
                                        maxSettlementToExpand = friendlySettlement;
                                        maxTerrain = expansionTerrain;
                                    }
                                }
                            }
                        }
                    } catch (NoValidActionException e) {
                    }
                }
            }
            if (maxCost > 1 && maxExpansionSpots != null && maxSettlementToExpand != null && maxTerrain != null &&
                    maxExpansionSpots.size() > 1) {
                try {
                    settlementExpansionHandler.expandWithMeeples(maxExpansionSpots, map, player.getTeam());
                    WeJustDidThisMove expansionMove = new WeJustDidThisMove();
                    expansionMove.setBuildType(2);
                    expansionMove.setBuildSpot(maxSettlementToExpand.getMapSpots().get(0));
                    expansionMove.setTerrain(maxTerrain);
                    return expansionMove;
                } catch (CannotPerformActionException e) {
                }
            }


            try {
                MapSpot foundingSpot = randomSettlementFoundingScanner.scan(map);
                return settlementFoundingHandler.foundSettlement(foundingSpot, map, player.getTeam());
            } catch (NoValidActionException | CannotPerformActionException e) {
            }
        }

        WeJustDidThisMove gameOverMove = new WeJustDidThisMove();
        gameOverMove.setBuildType(5);
        return gameOverMove;
    }
}
