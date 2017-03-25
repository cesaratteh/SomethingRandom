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

    public MapSpot left() {
        final int tempX = this.x - 2;
        final int tempY = this.y;

        if (isMapSpotValid(tempX, tempY)) {
            return new MapSpot(tempX, tempY);
        } else {
            return null;
        }
    }

    public MapSpot topLeft() {
        final int tempX = this.x - 1;
        final int tempY = this.y - 1;

        if (isMapSpotValid(tempX, tempY)) {
            return new MapSpot(tempX, tempY);
        } else {
            return null;
        }
    }

    public MapSpot topRight() {
        final int tempX = this.x + 1;
        final int tempY = this.y - 1;

        if (isMapSpotValid(tempX, tempY)) {
            return new MapSpot(tempX, tempY);
        } else {
            return null;
        }
    }

    public MapSpot right() {
        final int tempX = this.x + 2;
        final int tempY = this.y;

        if (isMapSpotValid(tempX, tempY)) {
            return new MapSpot(tempX, tempY);
        } else {
            return null;
        }
    }

    public MapSpot bottomRight() {
        final int tempX = this.x + 1;
        final int tempY = this.y + 1;

        if (isMapSpotValid(tempX, tempY)) {
            return new MapSpot(tempX, tempY);
        } else {
            return null;
        }
    }

    public MapSpot bottomLeft() {
        final int tempX = this.x - 1;
        final int tempY = this.y + 1;

        if (isMapSpotValid(tempX, tempY)) {
            return new MapSpot(tempX, tempY);
        } else {
            return null;
        }
    }


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
