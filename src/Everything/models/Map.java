package Everything.models;

public class Map {

    //-----------
    // Attributes

    private Hexagon[][][] map;

    //-------------
    // Constructors

    public Map() {
        map = new Hexagon[MAP_SIZE][MAP_SIZE][MAP_SIZE];
    }

    //--------
    // Methods

    public void setHexagon(final MapSpot hexagonMapSpot, final Hexagon hexagon) {
        map[hexagonMapSpot.getX()][hexagonMapSpot.getY()][hexagonMapSpot.getZ()] = hexagon;
    }

    //-------------
    // Getters

    public static int size() {
        return MAP_SIZE;
    }

    /**
     * Try not to use this. It's not centered. It's really confusing.
     * MapSpot handles centering now.
     * If you need this: 0,0,0 in dave's logic is 100, 100, 100 in this array
     * I am mainly using it right now for tests
     */
    protected Hexagon[][][] getHexagonArray() {
        return map;
    }

    /**
     * returns NULL if the spot is empty
     **/
    public Hexagon getHexagon(final MapSpot mapSpot) {
        return map[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()];
    }

    public MapSpot getMiddleHexagonMapSpot() {
        return new MapSpot(0, 0, 0);
    }

    /**
     * returns NULL if the spot is empty
     **/
    public Hexagon getMiddleHexagon() {
        final MapSpot middleHexagonPosition = getMiddleHexagonMapSpot();
        return map[middleHexagonPosition.getX()][middleHexagonPosition.getY()][middleHexagonPosition.getZ()];
    }

    //----------
    // Constants

    private static final int MAP_SIZE = 200;

/**
 * This broke after refactoring
 */
    public void printMapText() {
        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                for(int k = 0; k < MAP_SIZE; k++) {

                    if (map[i][j][k] != null) {
                        MapSpot temp = new MapSpot(i - 100, j - 100, k - 100);
                        System.out.print(temp);
                        System.out.print(" | Level: " + this.getHexagon(temp).getLevel());
                        System.out.print(" | Meeples: " + this.getHexagon(temp).getNumberOfMeeples());
                        System.out.print(" | Totoros: " + this.getHexagon(temp).isHasTotoro());

                        boolean connected = false;
                        for (MapSpot adj : temp.getAdjacentMapSpots()) {
                            if (this.getHexagon(adj) != null) {
                                connected = true;
                            }
                        }
                        System.out.println(" | Connected: " + connected);
                    }
                }
            }
        }
    }
    public void printMap(){
        //Some edge cases exist, but this is just for us to visualize the map

        int printableSize = MAP_SIZE*3;
        Hexagon[][] hexesPrintableMap = new Hexagon[printableSize][printableSize];
        for(int i = -(MAP_SIZE/2); i < MAP_SIZE/2-1; i++) {
            for(int j = -(MAP_SIZE/2); j < MAP_SIZE/2-1; j++) {
                for(int k = -(MAP_SIZE/2); k < MAP_SIZE/2-1; k++) {
                    if (i + j + k == 0) {
                        MapSpot temp = new MapSpot(i, j, k);

                        int col = temp.getX() + (temp.getZ() - (temp.getZ()&1)) / 2;
                        int row = temp.getZ();

                        hexesPrintableMap[col][row] = this.getHexagon(temp);
                    }
                }
            }
        }

        int leftBound = Integer.MAX_VALUE;
        int rightBound = Integer.MIN_VALUE;
        int topBound = Integer.MAX_VALUE;
        int bottomBound = Integer.MIN_VALUE;

        for(int i = 0; i < MAP_SIZE*3; i++) {
            for (int j = 0; j < MAP_SIZE * 3; j++) {
                if (hexesPrintableMap[i][j] != null) {
                    leftBound = Math.min(leftBound, i);
                    rightBound = Math.max(rightBound, i);
                    topBound = Math.min(topBound, j);
                    bottomBound = Math.max(bottomBound, j);
                }
            }
        }

        String[] lines = new String[printableSize];
        int index = 0;
        String longSpace = "       ";
        String shortSpace = " ";

        boolean once = false;
        for (int j = topBound; j <= bottomBound; j++) {
            for(int i = leftBound; i <= rightBound; i++) {
                Hexagon hex = hexesPrintableMap[i][j];
                if (hex != null) {
                    lines[index] += "" + hex.getTileId() + (hex.ConvertTerrainToCharacter()) + hex.getNumberOfMeeples() + hex.ConvertTeamToChar();
                }
                lines[index] += "" + longSpace;
            }
            index++;
        }

        for(int i = 0 ; i < index; i++) {
            lines[i] = lines[i].replaceAll("null", "");
            if (i % 2 == 1) {
                System.out.println(lines[i]);
            } else {
                System.out.println(shortSpace + lines[i]);
            }
        }

//
//        String[] Lines = new String[MAP_SIZE];
//        int LinesToPrint = MAP_SIZE;
//        int AddtoLines=0;
//        int IsEvenRow = 1;
//        for(int i =0; i< MAP_SIZE; i++){
//            String ThisLine = "        ";
//
//            for(int k =0; k< MAP_SIZE; k++){
//                Hexagon Temp = hexesPrintableMap[k][i];
//
//
//                if (Temp != null){
//
//                    String Slevel = String.valueOf(Temp.getLevel());
//                    ThisLine+= Slevel + Temp.ConvertTerrainToCharacter() + "  ";
//
//                }
//
//                else{
//                    if (IsEvenRow % 2 == 0){
//                        ThisLine+="    ";;
//                    }
//                    else{
//                        ThisLine+="    ";;
//                    }
//                }
//            }
//
//            Boolean LineIsEmpty = true;
//            for (int k = 0; k < MAP_SIZE*4; k++){
//                if(ThisLine.charAt(k) != ' ') {
//                    //System.out.print("Ran");
//                    LineIsEmpty = false;
//                }
//            }
//
//            if(LineIsEmpty == false){
//                Lines[AddtoLines] = ThisLine;
//                AddtoLines++;
//            }
//            else{
//                LinesToPrint--;
//            }
//            IsEvenRow++;
//        }
//        String[] Lines2 = new String[MAP_SIZE];
//        LinesToPrint = MAP_SIZE;
//        AddtoLines=0;
//        IsEvenRow = 1;
//        for(int i =0; i< MAP_SIZE; i++){
//            String ThisLine = "        ";
//
//            for(int k =0; k< MAP_SIZE; k++){
//                Hexagon Temp = hexesPrintableMap[k][i];
//
//
//                if (Temp != null){
//
//                    String Slevel = String.valueOf(Temp.getLevel());
//                    if(Temp.isEmpty()){
//                        ThisLine+="-U  ";
//                    }
//                    else if(Temp.isHasTotoro() == true){
//                        ThisLine+= "T" + Temp.ConvertTeamToChar() + "  ";
//                    }
//                    else{
//                        ThisLine+= (String.valueOf(Temp.getNumberOfMeeples()) + Temp.ConvertTeamToChar() + "  " );
//                    }
//
//
//                }
//
//                else{
//                    if (IsEvenRow % 2 == 0){
//                        ThisLine+="    ";;
//                    }
//                    else{
//                        ThisLine+="    ";;
//                    }
//                }
//            }
//
//            Boolean LineIsEmpty = true;
//            for (int k = 0; k < MAP_SIZE*4; k++){
//                if(ThisLine.charAt(k) != ' ') {
//
//                    LineIsEmpty = false;
//                }
//            }
//
//            if(LineIsEmpty == false){
//                Lines2[AddtoLines] = ThisLine;
//                AddtoLines++;
//            }
//            else{
//                LinesToPrint--;
//            }
//            IsEvenRow++;
//        }
//
//        int MinValue=MAP_SIZE*4;
//        int MaxValue = 0;
//
//        for(int j = 0; j < LinesToPrint; j++){
//            for(int k = 0; k<MAP_SIZE*4; k++){
//                Boolean FirstHex = true;
//                if(Lines[j].charAt(k) != ' ' && FirstHex == true){
//                    MinValue= Math.min(MinValue,k);
//                    FirstHex = false;
//                }
//                else{}
//                if(Lines[j].charAt(k) != ' '){
//                    MaxValue = Math.max(k,MaxValue);
//                }
//            }
//        }
//        for(int j = 0; j < LinesToPrint; j++){
//            System.out.println(Lines[j].substring(MinValue-4,MaxValue+4));
//            System.out.println(Lines2[j].substring(MinValue-4,MaxValue+4));
//        }
    }
}
