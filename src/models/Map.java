package models;

public class Map {

    //-----------
    // Attributes

    private Hexagon[][] map;

    //-------------
    // Constructors

    public Map() {
        final int widthAndHeight = calculateMaximumMapWidthAndHeight();
        map = new Hexagon[widthAndHeight][widthAndHeight];
    }

    //--------
    // Methods

    public void addHexagon(final MapSpot hexagonMapSpot, final Hexagon hexagon) {
        map[hexagonMapSpot.getX()][hexagonMapSpot.getY()] = hexagon;
    }

    /**
     * Each tile takes up a maximum of 3 matrix indices in each direction - two hexagons and 1 hidden index in the middle
     * The worst case is when tiles are stacked next to each other
     */
    protected int calculateMaximumMapWidthAndHeight() {
        final int totalNumberOfTiles = NUMBER_OF_STARTING_TILES_PER_PLAYER * 2; // *2 because there are two players
        return (totalNumberOfTiles * 3 * 2) + 10; // *3 because read the above block comment; *2 because we are starting from the middle; +10 just to be safe
    }

    //-------------
    // Getters

    /**
     * returns NULL if the spot is empty
    **/
    public Hexagon getHexagon(final MapSpot mapSpot) {
        return map[mapSpot.getX()][mapSpot.getY()];
    }

    public MapSpot getMiddleHexagonMapSpot() {
        final int n = calculateMaximumMapWidthAndHeight();
        return new MapSpot(n/2, n/2);                   //FIXME: 3/23/2017 Using (n,n) creates ArrayIndexOutOfBoundsException; Find starting index
    }

    /**
     * returns NULL if the spot is empty
     **/
    public Hexagon getMiddleHexagon()
    {
        final MapSpot middleHexagonPosition = getMiddleHexagonMapSpot();
        return map[middleHexagonPosition.getX()][middleHexagonPosition.getY()];
    }

    //----------
    // Constants

    private static final int NUMBER_OF_STARTING_TILES_PER_PLAYER = 24; // FIXME: 3/19/2017 Filler number; Find the actual number of starting tiles
}
