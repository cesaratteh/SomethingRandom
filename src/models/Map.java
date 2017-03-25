package models;

public class Map {

    //-----------
    // Attributes

    private static final int mapSize = calculateMaximumMapWidthAndHeight();
    private Hexagon[][] map;

    //-------------
    // Constructors

    public Map() {
        map = new Hexagon[mapSize][mapSize];
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
    private static int calculateMaximumMapWidthAndHeight() {
        final int totalNumberOfTiles = NUMBER_OF_STARTING_TILES_PER_PLAYER * 2; // *2 because there are two players
        return (totalNumberOfTiles * 3 * 2) + 10; // *3 because read the above block comment; *2 because we are starting from the middle; +10 just to be safe
    }

    //-------------
    // Getters

    public int getMapSize() {
        return mapSize;
    }

    public Hexagon[][] getHexagonArray() {
        return map;
    }

    /**
     * returns NULL if the spot is empty
     **/
    public Hexagon getHexagon(final MapSpot mapSpot) {
        return map[mapSpot.getX()][mapSpot.getY()];
    }

    public MapSpot getMiddleHexagonMapSpot() {
        return new MapSpot(mapSize / 2, mapSize / 2);
    }

    /**
     * returns NULL if the spot is empty
     **/
    public Hexagon getMiddleHexagon() {
        final MapSpot middleHexagonPosition = getMiddleHexagonMapSpot();
        return map[middleHexagonPosition.getX()][middleHexagonPosition.getY()];
    }

    //----------
    // Constants

    private static final int NUMBER_OF_STARTING_TILES_PER_PLAYER = 24; // FIXME: 3/19/2017 Filler number; Find the actual number of starting tiles

}
