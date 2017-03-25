package models;

import java.util.ArrayList;

public class Settlement {

    //-----------
    // Attributes

    private ArrayList<MapSpot> mapSpots;
    private Team team;
    private int numberOfMeeples;
    private int numberOfTotoros;

    //-------------
    // Constructors

    public Settlement(final Team team){
        mapSpots = new ArrayList<>();
        this.team = team;
        this.numberOfMeeples = 0;
        this.numberOfTotoros = 0;
    }

    //--------
    // Methods

    public void add(final MapSpot mapSpot, final Hexagon hexagon) {
        mapSpots.add(mapSpot);
        numberOfMeeples += hexagon.getNumberOfMeeples();
        numberOfTotoros += (hexagon.isHasTotoro()) ? 1 : 0;
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

    public ArrayList<MapSpot> getMapSpots() {
        return mapSpots;
    }

    public Team getTeam(){
        return team;
    }
}
