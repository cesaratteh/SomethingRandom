package models;

import java.util.ArrayList;

public class Settlement {

    //-----------
    // Attributes

    private ArrayList<MapSpot> mapSpots;
    private Team team;
    private int numberOfMeeples;
    private int numberOfTotoros;
    private int numberOfTigers;

    //-------------
    // Constructors

    public Settlement(final Team team){
        mapSpots = new ArrayList<>();
        this.team = team;
        this.numberOfMeeples = 0;
        this.numberOfTotoros = 0;
        this.numberOfTigers = 0;
    }

    //--------
    // Methods

    public void add(final MapSpot mapSpot, final Hexagon hexagon) {
        mapSpots.add(mapSpot);
        numberOfMeeples += hexagon.getNumberOfMeeples();
        numberOfTotoros += (hexagon.isHasTotoro()) ? 1 : 0;
        numberOfTigers += (hexagon.isHasTiger()) ? 1 : 0;
    }

    public boolean isMapSpotInSettlement(final MapSpot mapSpot){
        for(final MapSpot settlementMapSpot: mapSpots){
            if(settlementMapSpot == mapSpot) return true;
        }

        return false;
    }

    //--------
    // Getters

    public int size(){
        return mapSpots.size();
    }

    public ArrayList<MapSpot> getMapSpots() {
        return mapSpots;
    }

    public Team getTeam(){
        return team;
    }

    public int getNumberOfMeeples() {
        return numberOfMeeples;
    }

    int getNumberOfTotoros() {
        return numberOfTotoros;
    }

    public int getNumberOfTigers() {
        return numberOfTigers;
    }
}
