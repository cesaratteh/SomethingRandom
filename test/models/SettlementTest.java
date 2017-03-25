package models;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;



import static org.junit.Assert.assertEquals;


public class SettlementTest {
    final Random random = new Random();
    private Map map = new Map();
    private Hexagon getRandomHexagon() {
        Hexagon hex = RandomGenerator.generateRandomHexagon();
        return hex;
    }
    private ArrayList<MapSpot> getRandomListOfHexes(int size) {

        ArrayList<MapSpot> list = new ArrayList<MapSpot>();
        for(int i = 0; i<size;i++)
        {
            MapSpot place = new MapSpot(i,i);
            map.addHexagon(place,getRandomHexagon());
            list.add(place);
        }

        return list;
    }
    @Test
    public void getSizeTest(){
        final Settlement s = new Settlement(getRandomListOfHexes(10),RandomGenerator.generateRandomTeam(),map);
        assertEquals(10, s.getSize());
    }
    @Test
    public void getTeamTest(){
        final Settlement s = new Settlement(getRandomListOfHexes(10),Team.FRIENDLY,map);
        assertEquals(Team.FRIENDLY,s.getTeam());
    }
    @Test
    public void getListOfContainedHexesTest(){
        ArrayList<MapSpot> randomlist = getRandomListOfHexes(10);
        final Settlement s = new Settlement(randomlist,Team.FRIENDLY,map);
        assertEquals(s.getListOfHexes(), randomlist);
    }

}
