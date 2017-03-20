package models;

public class Map {

    //-----------
    // Attributes

    private Hexagon[][] map;

    //-------------
    // Constructors

    public Map() {
        map = new Hexagon[calculateMaximumMapWidthAndHeight()][calculateMaximumMapWidthAndHeight()];
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
    private int calculateMaximumMapWidthAndHeight() {
        final int totalNumberOfTiles = NUMBER_OF_STARTING_TILES_PER_PLAYER * 2; // *2 because there are two players
        return (totalNumberOfTiles * 3 * 2) + 10; // *2 because we are starting from the middle; +10 just to be safe
    }

    //-------------
    // Getters

    public MapSpot getMiddleHexagonMapSpot() {
        final int n = calculateMaximumMapWidthAndHeight();
        return new MapSpot(n, n);
    }

    public Hexagon getMiddleHexagon()
    {
        final MapSpot middleHexagonPosition = getMiddleHexagonMapSpot();
        return map[middleHexagonPosition.getX()][middleHexagonPosition.getY()];
    }

    //----------
    // Constants

    private static final int NUMBER_OF_STARTING_TILES_PER_PLAYER = 24; // FIXME: 3/19/2017 Filler number; Find the actual number of starting tiles
}
