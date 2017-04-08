package models;

import java.util.ArrayList;

public class TileMapSpot {

    //-----------
    // Attributes

    private MapSpot m1;
    private MapSpot m2;
    private MapSpot m3;

    //-------------
    // Constructors

    public TileMapSpot(final MapSpot m1, final MapSpot m2, final MapSpot m3) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
    }

    //--------
    // Getters

    public MapSpot getM1() {
        return m1;
    }

    public MapSpot getM2() {
        return m2;
    }

    public MapSpot getM3() {
        return m3;
    }

    public ArrayList<MapSpot> getAllMapSpots() {
        ArrayList<MapSpot> mapSpots = new ArrayList<>();
        mapSpots.add(m1);
        mapSpots.add(m2);
        mapSpots.add(m3);

        return mapSpots;
    }
}
