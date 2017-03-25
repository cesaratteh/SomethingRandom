//package models;
//
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//
//
//import static org.junit.Assert.assertEquals;
//
//
//public class SettlementTest {
//    final Random random = new Random();
//    private Map map = new Map();
//
//    private Hexagon getRandomHexagon(int totoro, int meeple) {
//        Hexagon hex = RandomGenerator.generateRandomHexagon();
//        if(meeple <= 0 && totoro > 0) hex.addTotoro();
//        else if (meeple > 0) hex.addMeeples(1);
//
//        return hex;
//    }
//    private ArrayList<MapSpot> getRandomListOfMapSpots(int size, int totoro, int meeple) {
//
//        ArrayList<MapSpot> list = new ArrayList<>();
//        for(int i = 0; i<size;i++)
//        {
//            MapSpot place = new MapSpot(i,i);
//            map.addHexagon(place,getRandomHexagon( totoro, meeple));
//            totoro--;
//            meeple--;
//            list.add(place);
//        }
//
//        return list;
//    }
//    @Test
//    public void getSizeTest(){
//        final Settlement s = new Settlement(getRandomListOfMapSpots(10,0,0),RandomGenerator.generateRandomTeam(),map);
//        assertEquals(10, s.getSize());
//    }
//    @Test
//    public void getTeamTest(){
//        final Settlement s = new Settlement(getRandomListOfMapSpots(10,0,0),Team.FRIENDLY,map);
//        assertEquals(Team.FRIENDLY,s.getTeam());
//    }
//    @Test
//    public void getListOfContainedHexesTest(){
//        ArrayList<MapSpot> randomlist = getRandomListOfMapSpots(10,0,0);
//        final Settlement s = new Settlement(randomlist,Team.FRIENDLY,map);
//        assertEquals(s.getMapSpots(), randomlist);
//    }
//    @Test
//    public void getNumberOfContainedMeepleTest(){
//        ArrayList<MapSpot> randomlist = getRandomListOfMapSpots(11,0,11);
//        final Settlement s = new Settlement(randomlist,Team.FRIENDLY,map);
//        assertEquals(s.getNumberOfMeeples(), 11);
//    }
//    @Test
//    public void getNumberOfContainedTotoroTest(){
//        ArrayList<MapSpot> randomlist = getRandomListOfMapSpots(10,2,0);
//        final Settlement s = new Settlement(randomlist,Team.FRIENDLY,map);
//        assertEquals(s.getNumberOfTotoros(), 2);
//    }
//
//}
