package Everything.models;

import java.util.ArrayList;

public class MapSpot {

    //-----------
    // Attributes

    private int x;
    private int y;
    private int z;

    //-------------
    // Constructors

    /**
     * This accepts a range from -100 to 100 for x, y, z.
     * It follows the exact representation in the network protocol from the website
     */
    public MapSpot(final int xCentered, final int yCentered, final int zCentered) {

        this.x = xCentered + OFFSET;
        this.y = yCentered + OFFSET;
        this.z = zCentered + OFFSET;
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

    private MapSpot constructMapSpotWithoutOffset(final int x, final int y, final int z) {
        return new MapSpot(x - OFFSET, y - OFFSET, z - OFFSET);
    }

    public MapSpot left() {
        final int tempX = this.x - 1;
        final int tempY = this.y + 1;
        final int tempZ = this.z;

        return constructMapSpotWithoutOffset(tempX, tempY, tempZ);
    }

    public MapSpot topLeft() {
        final int tempX = this.x;
        final int tempY = this.y + 1;
        final int tempZ = this.z - 1;

        return constructMapSpotWithoutOffset(tempX, tempY, tempZ);
    }

    public MapSpot topRight() {
        final int tempX = this.x + 1;
        final int tempY = this.y;
        final int tempZ = this.z - 1;

        return constructMapSpotWithoutOffset(tempX, tempY, tempZ);
    }

    public MapSpot right() {
        final int tempX = this.x + 1;
        final int tempY = this.y - 1;
        final int tempZ = this.z;

        return constructMapSpotWithoutOffset(tempX, tempY, tempZ);
    }

    public MapSpot bottomRight() {
        final int tempX = this.x;
        final int tempY = this.y - 1;
        final int tempZ = this.z + 1;

        return constructMapSpotWithoutOffset(tempX, tempY, tempZ);
    }

    public MapSpot bottomLeft() {
        final int tempX = this.x - 1;
        final int tempY = this.y;
        final int tempZ = this.z + 1;

        return constructMapSpotWithoutOffset(tempX, tempY, tempZ);
    }

    public boolean isEqual(final MapSpot mapSpot) {

        return (mapSpot.getX() == this.getX() &&
                mapSpot.getY() == this.getY() &&
                mapSpot.getZ() == this.getZ());
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

    public int getXForServer() {
        return x - OFFSET;
    }

    public int getyForServer() {
        return y - OFFSET;
    }

    public int getZForServer() {
        return z - OFFSET;
    }

    //----------
    // Constants

    private final static int OFFSET = Map.size() / 2;

    @Override
    public String toString() {
        return "MapSpot{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
