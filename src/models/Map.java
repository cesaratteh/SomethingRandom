package models;

import game.action.handlers.FirstLevelTileAdditionHandler;

public class Map {

    //-----------
    // Attributes

    public static final int mapSize = calculateMaximumMapWidthAndHeight();
    private Hexagon[][] map;
    private FirstLevelTileAdditionHandler handler;

    //-------------
    // Constructors

    public Map() {
        map = new Hexagon[mapSize][mapSize];
        handler = new FirstLevelTileAdditionHandler(this);
        this.addFirstTileToMap();
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

    public int size() {
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

    public void addTileToMap(Hexagon h1, Hexagon h2, Hexagon h3, MapSpot m1, MapSpot m2, MapSpot m3){
        handler.addTileToMap(h1,h2,h3, m1,m2,m3);
    }

    public void addFirstTileToMap(){
        MapSpot m1 = this.getMiddleHexagonMapSpot();
        MapSpot m2 = m1.topLeft();
        MapSpot m3 = m1.topRight();
        MapSpot m4 = m1.bottomLeft();
        MapSpot m5 = m1.bottomRight();

        Hexagon h1 = new Hexagon(Terrain.VOLCANO,1,0);
        Hexagon h2 = new Hexagon(Terrain.JUNGLE,1,0);
        Hexagon h3 = new Hexagon(Terrain.LAKE,1,0);
        Hexagon h4 = new Hexagon(Terrain.ROCKY,1,0);
        Hexagon h5 = new Hexagon(Terrain.GRASSLAND,1,0);

        handler.addFirstTileToMap(h1,h2,h3,h4,h5,m1,m2,m3,m4,m5);
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


                if (Temp != null){

                    String Slevel = String.valueOf(Temp.getLevel());
                    ThisLine+= Slevel + Temp.ConvertTerrainToCharacter() + "  ";

                }

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
                if(ThisLine.charAt(k) != ' ') {
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
        String[] Lines2 = new String[mapSize];
        LinesToPrint = mapSize;
        AddtoLines=0;
        IsEvenRow = 1;
        for(int i =0; i< mapSize; i++){
            String ThisLine = "        ";

            for(int k =0; k< mapSize; k++){
                Hexagon Temp = map[k][i];


                if (Temp != null){

                    String Slevel = String.valueOf(Temp.getLevel());
                    if(Temp.isEmpty()){
                        ThisLine+="-U  ";
                    }
                    else if(Temp.isHasTotoro() == true){
                        ThisLine+= "T" + Temp.ConvertTeamToChar() + "  ";
                    }
                    else{
                        ThisLine+= (String.valueOf(Temp.getNumberOfMeeples()) + Temp.ConvertTeamToChar() + "  " );
                    }


                }

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
                if(ThisLine.charAt(k) != ' ') {

                    LineIsEmpty = false;
                }
            }

            if(LineIsEmpty == false){
                Lines2[AddtoLines] = ThisLine;
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
                if(Lines[j].charAt(k) != ' ' && FirstHex == true){
                    MinValue= Math.min(MinValue,k);
                    FirstHex = false;
                }
                else{}
                if(Lines[j].charAt(k) != ' '){
                    MaxValue = Math.max(k,MaxValue);
                }
            }
        }
        for(int j = 0; j < LinesToPrint; j++){
            System.out.println(Lines[j].substring(MinValue-4,MaxValue+4));
            System.out.println(Lines2[j].substring(MinValue-4,MaxValue+4));
        }
    }

}
