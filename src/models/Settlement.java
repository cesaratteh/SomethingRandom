package models;


import java.util.ArrayList;

public class Settlement {
    //-----------
    // Attributes

    private int size;
    private ArrayList<MapSpot> listOfHexes;
    private int numberOfMeeples;
    private int numberOfTotoro;
    private Team team;

    //-------------
    // Constructors

    public Settlement(final ArrayList<MapSpot> listOfHexes, final Team team, Map map ){
        this.listOfHexes = listOfHexes;
        this.team = team;
        this.size = listOfHexes.size();
        for(int i = 0; i<listOfHexes.size(); i++){
            this.numberOfMeeples += map.getHexagon(listOfHexes.get(i)).getNumberOfMeeples();
            if(map.getHexagon(listOfHexes.get(i)).isHasTotoro()) this.numberOfTotoro++;
        }

    }

    //--------
    // Getters

    public int getSize(){return size;}
    public int getNumberOfTotoro() {return numberOfTotoro;}
    public int getNumberOfMeeples() {return numberOfMeeples;}
    public ArrayList<MapSpot> getListOfHexes() {return listOfHexes;}
    public Team getTeam(){return team;}


}
