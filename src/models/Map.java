package models;

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

    public void addHexagon(final MapSpot hexagonMapSpot, final Hexagon hexagon) {
        map[hexagonMapSpot.getX()][hexagonMapSpot.getY()][hexagonMapSpot.getZ()] = hexagon;
    }

    //-------------
    // Getters

    public int size() {
        return MAP_SIZE;
    }

    public Hexagon[][][] getHexagonArray() {
        return map;
    }

    /**
     * returns NULL if the spot is empty
     **/
    public Hexagon getHexagon(final MapSpot mapSpot) {
        return map[mapSpot.getX()][mapSpot.getY()][mapSpot.getZ()];
    }

    public MapSpot getMiddleHexagonMapSpot() {
        return new MapSpot(MAP_SIZE / 2, MAP_SIZE / 2, MAP_SIZE / 2);
    }

    /**
     * returns NULL if the spot is empty
     **/
    Hexagon getMiddleHexagon() {
        final MapSpot middleHexagonPosition = getMiddleHexagonMapSpot();
        return map[middleHexagonPosition.getX()][middleHexagonPosition.getY()][middleHexagonPosition.getZ()];
    }

    //----------
    // Constants

    private static final int MAP_SIZE = 200;
}



/**
 * This broke after refactoring
 */
//    public void PrintMap(){
//        //Some edge cases exist, but this is just for us to visualize the map
//        String[] Lines = new String[mapSize];
//        int LinesToPrint = mapSize;
//        int AddtoLines=0;
//        int IsEvenRow = 1;
//        for(int i =0; i< mapSize; i++){
//            String ThisLine = "        ";
//
//            for(int k =0; k< mapSize; k++){
//                Hexagon Temp = map[k][i];
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
//            for (int k = 0; k < mapSize*4; k++){
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
//        String[] Lines2 = new String[mapSize];
//        LinesToPrint = mapSize;
//        AddtoLines=0;
//        IsEvenRow = 1;
//        for(int i =0; i< mapSize; i++){
//            String ThisLine = "        ";
//
//            for(int k =0; k< mapSize; k++){
//                Hexagon Temp = map[k][i];
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
//            for (int k = 0; k < mapSize*4; k++){
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
//        int MinValue=mapSize*4;
//        int MaxValue = 0;
//
//        for(int j = 0; j < LinesToPrint; j++){
//            for(int k = 0; k<mapSize*4; k++){
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
//    }
//}
