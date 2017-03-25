package models;

/**
 * x,y have to be both odd or both even.
 * Assuming 0 is even
 */
public class MapSpot {

    //-----------
    // Attributes

    private int x;
    private int y;

    //-------------
    // Constructors

    public MapSpot(final int x, final int y) {
        if (!isMapSpotValid(x, y)) {
            throw new RuntimeException("Creating an invalid map spot");
        }

        this.x = x;
        this.y = y;
    }

    //-------
    //Methods

    private boolean isMapSpotValid(final int x, final int y) {
        boolean inHexagonalPlace;
        boolean insideMapBoundary;

        if ((x % 2) != (y % 2)) {
            inHexagonalPlace = false;
        } else {
            inHexagonalPlace = true;
        }

        if ((x >= Map.mapSize) | (y >= Map.mapSize)) {
            insideMapBoundary = false;
        } else {
            insideMapBoundary = true;
        }

        return inHexagonalPlace && insideMapBoundary;
    }

    //---------
    // Getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
