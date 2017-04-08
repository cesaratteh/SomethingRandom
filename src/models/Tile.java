package models;

/**
 * This Object is only used to pass 3 hexagons around
 */
public class Tile {

    //-----------
    // Attributes

    private Hexagon h1;
    private Hexagon h2;
    private Hexagon h3;

    //-------------
    // Constructors

    public Tile(final Hexagon h1, final Hexagon h2, final Hexagon h3) {
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
    }

    public Hexagon getH1() {
        return h1;
    }

    public Hexagon getH2() {
        return h2;
    }

    public Hexagon getH3() {
        return h3;
    }
}
