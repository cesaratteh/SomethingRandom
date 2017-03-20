package models;

/**
 * x,y have to be both odd or both even
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
        if ((x % 2) != (x % 2)) {
            throw new RuntimeException("Creating an invalid map spot");
        }

        this.x = x;
        this.y = y;
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
