package game.action.ai;

import game.action.handlers.SettlementExpansionHandler;
import game.action.handlers.SettlementFoundingHandler;
import game.action.handlers.utils.SettlementsFactory;
import models.Map;
import models.MapSpot;
import models.Player;
import models.Settlement;

import java.util.ArrayList;

public class SettlementDecisionMaker {


    private final Map map;

    public ArrayList<Settlement> settlementsThatCanExpandWithTotoro(Player player){
        SettlementsFactory settlementsFactory = new SettlementsFactory(map);
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(player.getTeam());

        ArrayList<Settlement> validSettlementArrayList = new ArrayList<>();

        for(Settlement settle : settlements){
            SettlementExpansionHandler handler = new SettlementExpansionHandler(map, settle);

            if(settle.size() >= 5){
               if(!handler.generateAllTotoroSpots().isEmpty()){
                   validSettlementArrayList.add(settle);
               }
            }

        }

        return validSettlementArrayList;

    }

    public ArrayList<Settlement> settlementsThatCanExpandWithTiger(Player player){
        SettlementsFactory settlementsFactory = new SettlementsFactory(map);
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(player.getTeam());

        ArrayList<Settlement> validSettlementArrayList = new ArrayList<>();

        for(Settlement settle : settlements){
            SettlementExpansionHandler handler = new SettlementExpansionHandler(map, settle);

            if(settle.size() >= 5){
                if(!handler.generateAllTigerSpots().isEmpty()){
                    validSettlementArrayList.add(settle);
                }
            }

        }

        return validSettlementArrayList;

    }

    public ArrayList<Settlement> settlementsThatCanExpandWithMeeples(Player player){
        SettlementsFactory settlementsFactory = new SettlementsFactory(map);
        ArrayList<Settlement> settlements = settlementsFactory.generateSettlements(player.getTeam());

        ArrayList<Settlement> validSettlementArrayList = new ArrayList<>();

        for(Settlement settle : settlements){
            SettlementExpansionHandler handler = new SettlementExpansionHandler(map, settle);

            if(!handler.generateExpandableSettlementArea().isEmpty()){
                validSettlementArrayList.add(settle);
            }

        }

        return validSettlementArrayList;

    }

    public ArrayList<MapSpot> mapSpotsForSettlementFounding(Player player){
        SettlementFoundingHandler handler = new SettlementFoundingHandler(map);
        return handler.generateValidMapSpotsForSettlementFounding();
    }

    public SettlementDecisionMaker(Map map, Player player){
        this.map = map;
    }

}
