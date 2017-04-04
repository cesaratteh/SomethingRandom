package models;

import game.action.handlers.SettlementExpansionHandler;

import java.util.ArrayList;

public class Settlement {

    //-----------
    // Attributes

    private ArrayList<MapSpot> mapSpots;
    private Team team;
    private int numberOfMeeples;
    private int numberOfTotoros;
    private int numberOfTigers;
    private Map map;
    private SettlementExpansionHandler expansionHandler;
    private final Player owner;

    //-------------
    // Constructors

    public Settlement(final Team team, Map map, final Player owner){
        mapSpots = new ArrayList<>();
        this.map = map;
        this.team = team;
        this.numberOfMeeples = 0;
        this.numberOfTotoros = 0;
        this.owner = owner;
        this.expansionHandler = new SettlementExpansionHandler(map, this);
    }

    //--------
    // Methods

    public void add(final MapSpot mapSpot) {
        mapSpots.add(mapSpot);
        numberOfMeeples += map.getHexagon(mapSpot).getNumberOfMeeples();
        numberOfTotoros += (map.getHexagon(mapSpot).isHasTotoro()) ? 1 : 0;
    }

    public void expandWithMeeples(final MapSpot mapSpot){
       ArrayList<MapSpot> validExpansionSpots = expansionHandler.generateExpandableSettlementArea();

       if(validExpansionSpots.contains(mapSpot)){
           this.add(mapSpot);
           map.getHexagon(mapSpot).addMeeples(map.getHexagon(mapSpot).getLevel(),team, owner);
           ArrayList<MapSpot> chainedSpots = expansionHandler.generateChainedSpots(mapSpot);

           for(MapSpot spot : chainedSpots){
               map.getHexagon(spot).addMeeples(map.getHexagon(spot).getLevel(), team, owner);
               numberOfMeeples += map.getHexagon(spot).getLevel();
           }
       }
       else{
           throw new RuntimeException("Bad expansion with Meeples");
       }
    }

    public void expandWithTotoro(final MapSpot mapSpot){
        ArrayList<MapSpot> validExpansionSpots = expansionHandler.generateExpandableSettlementArea();

        if(validExpansionSpots.contains(mapSpot) && mapSpots.size() >= 5){
            this.add(mapSpot);
            map.getHexagon(mapSpot).addTotoro(team, owner);
            numberOfTotoros++;
        }
        else{
            throw new RuntimeException("Bad expansion with Totoro");
        }
    }

    public void expandWithTiger(final MapSpot mapSpot){
        ArrayList<MapSpot> validExpansionSpots = expansionHandler.generateExpandableSettlementArea();

        if(validExpansionSpots.contains(mapSpot)
                && mapSpots.size() >= 5
                && map.getHexagon(mapSpot).getLevel() >= 3){
            this.add(mapSpot);
            map.getHexagon(mapSpot).addTiger(team, owner);
            numberOfTigers++;
        }
        else{
            throw new RuntimeException("Bad expansion with Tiger");
        }
    }

    public ArrayList<MapSpot> getValidExpansionSpots(){
        return expansionHandler.generateExpandableSettlementArea();
    }

    public ArrayList<MapSpot> getChainedSpots(MapSpot mapSpot){
        return expansionHandler.generateChainedSpots(mapSpot);
    }

    //--------
    // Getters

    public int getSize(){
        return mapSpots.size();
    }

    public int getNumberOfMeeples() {
        return numberOfMeeples;
    }

    public int getNumberOfTotoros() {
        return numberOfTotoros;
    }

    public int getNumberOfTigers() {
        return numberOfTigers;
    }

    public ArrayList<MapSpot> getMapSpots() {
        return mapSpots;
    }

    public Team getTeam(){
        return team;
    }

    public Player getOwner() {
        return owner;
    }
}
