package models;


import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Random;



import static org.junit.Assert.assertEquals;


public class SettlementTest {
    private Random random;
    private Map map;
    private Player player;

    private Hexagon getRandomHexagon(int totoro, int meeple) {
        Hexagon hex = RandomGenerator.generateRandomHexagon();

        if(meeple <= 0 && totoro > 0) hex.addTotoro(player.getTeam());
        else if (meeple > 0) hex.addMeeples(player.getTeam());

        return hex;
    }

    private ArrayList<MapSpot> getRandomListOfMapSpots(int size, int totoro, int meeple) {

        ArrayList<MapSpot> list = new ArrayList<>();
        for(int i = 0; i<size;i++)
        {
            MapSpot place = new MapSpot(i,i);
            map.addHexagon(place,getRandomHexagon( totoro, meeple));
            totoro--;
            meeple--;
            list.add(place);
        }

        return list;
    }

    @Before
    public void initializeFields(){
        map = new Map();
        random = new Random();
        player = new Player(RandomGenerator.generateRandomTeam());
    }

    @Test
    public void getSizeTest(){
        ArrayList<MapSpot> randomList = getRandomListOfMapSpots(10, 0,0);
        final Settlement s = new Settlement(RandomGenerator.generateRandomTeam());
        for(MapSpot m : randomList){
            s.add(m, map.getHexagon(m));
        }
        assertEquals(10, s.size());
    }

    @Test
    public void getTeamTest(){
        ArrayList<MapSpot> randomList = getRandomListOfMapSpots(10, 0,0);
        final Settlement s = new Settlement(Team.FRIENDLY);
        for(MapSpot m : randomList){
            s.add(m, map.getHexagon(m));
        }
        assertEquals(Team.FRIENDLY,s.getTeam());
    }

    @Test
    public void getListOfContainedHexesTest(){
        ArrayList<MapSpot> randomList = getRandomListOfMapSpots(10,0,0);
        final Settlement s = new Settlement(Team.FRIENDLY);
        for(MapSpot m : randomList){
            s.add(m, map.getHexagon(m));
        }
        assertEquals(s.getMapSpots(), randomList);
    }

    //broken test

//    @Test
//    public void getNumberOfContainedMeepleTest(){
//        ArrayList<MapSpot> randomList = getRandomListOfMapSpots(11,0,11);
//        final Settlement s = new Settlement(Team.FRIENDLY);
//        for(MapSpot m : randomList) {
//            if (map.getHexagon(m).isEmpty()){
//                map.getHexagon(m).addMeeples(s.getTeam());
//                s.add(m, map.getHexagon(m));
//            }
//        }
//        assertEquals(s.getNumberOfMeeples(), 11);
//    }

    @Test
    public void getNumberOfContainedTotoroTest(){
        ArrayList<MapSpot> randomList = getRandomListOfMapSpots(10,2,0);
        final Settlement s = new Settlement(Team.FRIENDLY);
        for(MapSpot m : randomList){
            s.add(m, map.getHexagon(m));
        }
        assertEquals(s.getNumberOfTotoros(), 2);
    }

}
