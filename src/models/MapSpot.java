package models;

import java.util.ArrayList;

public class MapSpot {

    //-----------
    // Attributes

    private int x;
    private int y;
    private int z;

    //-------------
    // Constructors

    public MapSpot(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //-------
    //Methods

    public ArrayList<MapSpot> getAdjacentMapSpots() {
        final ArrayList<MapSpot> adjacentMapSpots = new ArrayList<>();
        adjacentMapSpots.add(this.left());
        adjacentMapSpots.add(this.topLeft());
        adjacentMapSpots.add(this.topRight());
        adjacentMapSpots.add(this.right());
        adjacentMapSpots.add(this.bottomRight());
        adjacentMapSpots.add(this.bottomLeft());

        return adjacentMapSpots;
    }

    public MapSpot left() {
        final int tempX = this.x - 1;
        final int tempY = this.y + 1;
        final int tempZ = this.z;

        return new MapSpot(tempX, tempY, tempZ);
    }

    public MapSpot topLeft() {
        final int tempX = this.x;
        final int tempY = this.y + 1;
        final int tempZ = this.z - 1;

        return new MapSpot(tempX, tempY, tempZ);
    }

    public MapSpot topRight() {
        final int tempX = this.x + 1;
        final int tempY = this.y;
        final int tempZ = this.z - 1;

        return new MapSpot(tempX, tempY, tempZ);
    }

    public MapSpot right() {
        final int tempX = this.x + 1;
        final int tempY = this.y - 1;
        final int tempZ = this.z;

        return new MapSpot(tempX, tempY, tempZ);
    }

    public MapSpot bottomRight() {
        final int tempX = this.x;
        final int tempY = this.y - 1;
        final int tempZ = this.z + 1;

        return new MapSpot(tempX, tempY, tempZ);
    }

    public MapSpot bottomLeft() {
        final int tempX = this.x - 1;
        final int tempY = this.y;
        final int tempZ = this.z + 1;

        return new MapSpot(tempX, tempY, tempZ);
    }

    /**
     * You don't need to call this method.
     * You can do mapSpot1 == mapSpot2 to use it
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MapSpot) {
            MapSpot mapSpot = (MapSpot) obj;

            return (mapSpot.getX() == this.getX() &&
                    mapSpot.getY() == this.getY() &&
                    mapSpot.getZ() == this.getZ());
        } else {
            return false;
        }
    }

    public boolean isAdjacentTo(final MapSpot mapSpot){
        for (final MapSpot adjacentMapSpot : this.getAdjacentMapSpots()) {
            if (adjacentMapSpot == mapSpot) {
                return true;
            }
        }

        return false;
    }

    //---------
    // Getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
