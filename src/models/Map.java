package models;

import java.util.Stack;
import java.util.Vector;

public class Map {

    //-----------
    // Attributes

    public static final int mapSize = calculateMaximumMapWidthAndHeight();
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


    public void PrintMap(){
        //Some edge cases exist, but this is just for us to visualize the map
        String[] Lines = new String[mapSize];
        int LinesToPrint = mapSize;
        int AddtoLines=0;
        int IsEvenRow = 1;
        for(int i =0; i< mapSize; i++){
            String ThisLine = "        ";

            for(int k =0; k< mapSize; k++){
                Hexagon Temp = map[k][i];
                if (Temp != null){ ThisLine+="H-  ";}

                else{
                    if (IsEvenRow % 2 == 0){
                        ThisLine+="    ";;
                    }
                    else{
                        ThisLine+="    ";;
                    }
                }
            }

            Boolean LineIsEmpty = true;
            for (int k = 0; k < mapSize*4; k++){
                if(ThisLine.charAt(k) == 'H') {
                    //System.out.print("Ran");
                    LineIsEmpty = false;
                }
            }

            if(LineIsEmpty == false){
                Lines[AddtoLines] = ThisLine;
                AddtoLines++;
            }
            else{
                LinesToPrint--;
            }
            IsEvenRow++;
        }
        int MinValue=mapSize*4;
        int MaxValue = 0;

        for(int j = 0; j < LinesToPrint; j++){
            for(int k = 0; k<mapSize*4; k++){
                Boolean FirstHex = true;
                if(Lines[j].charAt(k) == 'H' && FirstHex == true){
                    MinValue= Math.min(MinValue,k);
                    FirstHex = false;
                }
                else{}
                if(Lines[j].charAt(k) == 'H'){
                    MaxValue = Math.max(k,MaxValue);
                }
            }
        }
        for(int j = 0; j < LinesToPrint; j++){
            System.out.println(Lines[j].substring(MinValue-4,MaxValue+4));
        }
    }

}
