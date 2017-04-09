package game.action.ai;

import Server.FriendlyMove;
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
import game.action.utils.settlemenet.expanding.SettlementExpansionMeeplesCost;
import game.action.utils.settlemenet.expanding.TigerSpotScanner;
import game.action.utils.settlemenet.expanding.TotoroSpotScanner;
import models.*;

import java.util.ArrayList;

// TODO: 4/9/2017 1. Place tile in random spot 2. Found villager in random spot 3. Optimal expansion logic

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
    private SettlementExpansionMeeplesCost settlementExpansionMeeplesCost;
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
     * */

    public void doATilePlacementMove(final Map map, final Tile tile) {
        final ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(player.getTeam());

        final ArrayList<Settlement> enemySettlements =
                settlementsFactory.generateSettlements((player.getTeam() == Team.FRIENDLY) ? Team.ENEMY : Team.FRIENDLY);

        if (player.getNumberOfTotorosLeft() > 0) {
            if (friendlySettlements.size() == 0) {
                // Place tile in any random spot (first turn only)
                return;
            }
            else {
                for (Settlement friendlySettlement : friendlySettlements) {
                    if (friendlySettlement.size() >= 5 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            TileMapSpot tileMapSpot
                                    = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

                            map.setHexagon(tileMapSpot.getM1(), tile.getH1());
                            map.setHexagon(tileMapSpot.getM2(), tile.getH2());
                            map.setHexagon(tileMapSpot.getM3(), tile.getH3());
                            return;
                        } catch (NoValidActionException e) {}
                    }
                    else if (friendlySettlement.size() == 4 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            TileMapSpot tileMapSpot
                                    = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

                            map.setHexagon(tileMapSpot.getM1(), tile.getH1());
                            map.setHexagon(tileMapSpot.getM2(), tile.getH2());
                            map.setHexagon(tileMapSpot.getM3(), tile.getH3());
                            return;
                        } catch (NoValidActionException e) {}
                    }
                    else if (friendlySettlement.size() == 3 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            TileMapSpot tileMapSpot
                                    = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

                            map.setHexagon(tileMapSpot.getM1(), tile.getH1());
                            map.setHexagon(tileMapSpot.getM2(), tile.getH2());
                            map.setHexagon(tileMapSpot.getM3(), tile.getH3());
                            return;
                        } catch (NoValidActionException e) {}
                    }
                    else if (friendlySettlement.size() == 2 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            TileMapSpot tileMapSpot
                                    = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

                            map.setHexagon(tileMapSpot.getM1(), tile.getH1());
                            map.setHexagon(tileMapSpot.getM2(), tile.getH2());
                            map.setHexagon(tileMapSpot.getM3(), tile.getH3());
                            return;
                        } catch (NoValidActionException e) {}
                    }
                    else if (friendlySettlement.size() == 1 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {

                            TileMapSpot tileMapSpot
                                    = settlementLevelOneTilePlacementScanner.findTileMapSpotToPlaceTileAroundSettlment(map, friendlySettlement);

                            map.setHexagon(tileMapSpot.getM1(), tile.getH1());
                            map.setHexagon(tileMapSpot.getM2(), tile.getH2());
                            map.setHexagon(tileMapSpot.getM3(), tile.getH3());
                            return;
                        } catch (NoValidActionException e) {}
                    }
                    else {
                        // Place tile in any random spot
                        return;
                    }
                }
            }

        }
        else if (player.getNumberOfMeeplesLeft() > 0) {
            // Place tile in any random spot
            return;
        }
        else {
            return; // Game Over
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
     * **/

    public void doABuildMove(final Map map) {
        final ArrayList<Settlement> friendlySettlements
                = settlementsFactory.generateSettlements(player.getTeam());

        if (player.getNumberOfTotorosLeft() > 0) {
            if (friendlySettlements.size() == 0) {
                try{
                    // Place villager on any hex
                    final MapSpot foundingSpots;
                        foundingSpots = randomSettlementFoundingScanner.scan(map);
                    map.getHexagon(foundingSpots).addMeeplesAccordingToLevel(Team.FRIENDLY);
                    return;
                } catch (NoValidActionException e) {}
                    return;
            }

            else {
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

                    else if (friendlySettlement.size() == 4 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            final ArrayList<MapSpot> foundingSpots = foundingNextToSettlementScanner.scan(friendlySettlement, map);
                            map.getHexagon(foundingSpots.get(0)).addMeeplesAccordingToLevel(Team.FRIENDLY);
                            return;
                        } catch (NoValidActionException e) {}

                    }

                    else if (friendlySettlement.size() == 3 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            final ArrayList<MapSpot> expandableMapSpots
                                    = meeplesExpandableSpotsScanner.scan(friendlySettlement, map);

                            if ((expandableMapSpots.size() + friendlySettlement.size()) == 5) {
                                settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                                return;
                            }

                            else {
                                ArrayList<MapSpot> foundingSpots = foundingNextToSettlementScanner.scan(friendlySettlement, map);
                                map.getHexagon(foundingSpots.get(0)).addMeeplesAccordingToLevel(Team.FRIENDLY);
                                return;
                            }
                        } catch (NoValidActionException e) {}
                    }

                    else if (friendlySettlement.size() == 2 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            final ArrayList<MapSpot> expandableMapSpots
                                    = meeplesExpandableSpotsScanner.scan(friendlySettlement, map);

                            if ((expandableMapSpots.size() + friendlySettlement.size()) == 5) {
                                settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                                return;
                            }

                            else if ((expandableMapSpots.size() + friendlySettlement.size()) == 4) {
                                settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                                return;
                            }

                            else {
                                ArrayList<MapSpot> foundingSpots = foundingNextToSettlementScanner.scan(friendlySettlement, map);
                                map.getHexagon(foundingSpots.get(0)).addMeeplesAccordingToLevel(Team.FRIENDLY);
                                return;
                            }
                        } catch (NoValidActionException e) {}
                    }

                    else if (friendlySettlement.size() == 1 && friendlySettlement.getNumberOfTotoros() < 1) {
                        try {
                            final ArrayList<MapSpot> expandableMapSpots
                                    = meeplesExpandableSpotsScanner.scan(friendlySettlement, map);

                            if ((expandableMapSpots.size() + friendlySettlement.size()) == 5) {
                                settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                                return;
                            }

                            else if ((expandableMapSpots.size() + friendlySettlement.size()) == 4) {
                                settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                                return;
                            }

                            else if ((expandableMapSpots.size() + friendlySettlement.size()) == 3) {
                                settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                                return;
                            }

                            else {
                                ArrayList<MapSpot> foundingSpots = foundingNextToSettlementScanner.scan(friendlySettlement, map);
                                map.getHexagon(foundingSpots.get(0)).addMeeplesAccordingToLevel(Team.FRIENDLY);
                                return;
                            }
                        } catch (NoValidActionException e) {}
                    }

                    else {
                        try{
                            // Place villager on any hex
                            final MapSpot foundingSpots;
                                foundingSpots = randomSettlementFoundingScanner.scan(map);
                            map.getHexagon(foundingSpots).addMeeplesAccordingToLevel(Team.FRIENDLY);
                            return;
                        } catch (NoValidActionException e) {}
                        return;
                    }
                }

            }
        }

        else if (player.getNumberOfMeeplesLeft() > 0) {
            // Iterate through expansion options to exhaust meeple count ....
            //  a. check meeple count minus expansion cost
            //  b. if difference == 0, then immediately expand
            //  c. if difference > 0, then check if new max is found
            //  d. if all costs for expansion are greater than meeple count, then found villager in any random spot

            ArrayList<MapSpot> maxMapSpot = new ArrayList<MapSpot>();
            Settlement maxSettlement = new Settlement(player.getTeam());

            for (Settlement friendlySettlement : friendlySettlements) {
                try {
                    final ArrayList<MapSpot> expandableMapSpots
                            = meeplesExpandableSpotsScanner.scan(friendlySettlement, map);

                    if ((player.getNumberOfMeeplesLeft() - expandableMapSpots.size()) == 0) {
                        settlementExpansionHandler.expandWithMeeples(friendlySettlement.getMapSpots().get(0));
                        return;
                    }

                    if ((player.getNumberOfMeeplesLeft() - expandableMapSpots.size()) > 0) {
                        if (expandableMapSpots.size() > maxMapSpot.size()) {
                            maxMapSpot = expandableMapSpots;
                            maxSettlement = friendlySettlement;
                        }
                    }

                } catch (NoValidActionException e) {}

            }

            try {
                if (maxMapSpot.size() > 1) {
                    settlementExpansionHandler.expandWithMeeples(maxSettlement.getMapSpots().get(0));
                    return;
                }
                else {
                    // Place villager on any hex
                    final MapSpot foundingSpots;
                    foundingSpots = randomSettlementFoundingScanner.scan(map);
                    map.getHexagon(foundingSpots).addMeeplesAccordingToLevel(Team.FRIENDLY);
                    return;
                }

            } catch (NoValidActionException e) {}
        }

        else {
            return;
            // Game Over
            // FIXME: 4/9/2017 No More Build Options
        }

    }
}
