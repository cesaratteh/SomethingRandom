//package Everything.game.action.handlers;
//
//import Everything.models.*;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//public class NukingAndStackingHandlerTest {
//
//    private Map map;
//    private Player player;
//
//    @Before
//    public void generateMapForTesting(){
//        map = new Map();
//        player = new Player(Team.FRIENDLY);
//        MapSpot curr = map.getMiddleHexagonMapSpot();
//
//        map.setHexagon(curr, new Hexagon(Terrain.GRASSLAND, 1, 1));
//        map.setHexagon(curr.left(), new Hexagon(Terrain.ROCKY, 1, 1));
//        map.setHexagon(curr.topLeft(), new Hexagon(Terrain.VOLCANO, 1, 1));
//
//        map.setHexagon(curr.topRight(), new Hexagon(Terrain.VOLCANO, 1, 2));
//        map.setHexagon(curr.topRight().topRight(), new Hexagon(Terrain.GRASSLAND, 1, 2));
//        map.setHexagon(curr.topRight().topLeft(), new Hexagon(Terrain.LAKE, 1, 2));
//
//        map.setHexagon(curr.left().left(), new Hexagon(Terrain.VOLCANO, 1, 3));
//        map.setHexagon(curr.topLeft().left(), new Hexagon(Terrain.LAKE, 1, 3));
//        map.setHexagon(curr.left().left().topLeft(), new Hexagon(Terrain.ROCKY, 1, 3));
//
//    }
//
//    @Test
//    public void TestSameTileCantBeNuked(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.left();
//        MapSpot m3 = m1.topLeft();
//
//        Assert.assertEquals(handler.MapSpotsCanBeNuked(m1,m2,m3,map),false);
//    }
//
//    @Test
//    public void TestNonAdjacentHexesCantBeNuked(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.left();
//        MapSpot m3 = m1.topRight();
//
//        Assert.assertEquals(handler.MapSpotsCanBeNuked(m1,m2,m3,map),false);
//    }
//
//    @Test
//    public void TestMultipleVolcanoesCanBeNuked(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.topLeft();
//        MapSpot m3 = m1.topRight();
//
//        Assert.assertEquals(handler.MapSpotsCanBeNuked(m1,m2,m3,map),false);
//    }
//
//    @Test
//    public void TestNonExistentHexagonCantBeNuked(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.bottomRight();
//        MapSpot m3 = m1.topRight();
//
//        Assert.assertEquals(handler.MapSpotsCanBeNuked(m1,m2,m3,map),false);
//    }
//
//    @Test
//    public void TestCanBeNuked(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot().left();
//        MapSpot m2 = m1.topLeft();
//        MapSpot m3 = m1.topRight();
//
//        Assert.assertEquals(handler.MapSpotsCanBeNuked(m1,m2,m3,map),true);
//    }
//
//    @Test
//    public void TestValidNuking(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot().left();
//        MapSpot m2 = m1.topLeft();
//        MapSpot m3 = m1.topRight();
//
//        Hexagon h1 = new Hexagon(Terrain.GRASSLAND,1,4);
//        Hexagon h2 = new Hexagon(Terrain.JUNGLE,1,4);
//        Hexagon h3 = new Hexagon(Terrain.VOLCANO,1,4);
//
//        handler.NukeSpots(m1,m2,m3,h1,h2,h3,map);
//
//        m1 = map.getMiddleHexagonMapSpot().left();
//        m2 = m1.topLeft();
//        m3 = m1.topRight();
//
//        boolean successfulNuke;
//        if(map.getHexagon(m1) == h1
//                && map.getHexagon(m2) == h2
//                && map.getHexagon(m3) == h3
//                && h1.getLevel() == 2
//                && h2.getLevel() == 2
//                && h3.getLevel() == 2)
//            successfulNuke = true;
//        else
//            successfulNuke = false;
//
//        Assert.assertEquals(successfulNuke,true);
//    }
//
//    @Test
//    public void TestNukingOffTheBoard(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.right();
//        MapSpot m3 = m1.topRight();
//
//        Hexagon h1 = new Hexagon(Terrain.GRASSLAND,1,4);
//        Hexagon h2 = new Hexagon(Terrain.JUNGLE,1,4);
//        Hexagon h3 = new Hexagon(Terrain.VOLCANO,1,4);
//
//        try {
//            handler.NukeSpots(m1, m2, m3, h1, h2, h3, map);
//        }
//        catch(RuntimeException e){
//            if(e.getMessage().equals("Cannot nuke those spots")) Assert.assertTrue(true);
//        }
//    }
//
//    @Test
//    public void TestNukingAllThreeHexesSameTile(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.left();
//        MapSpot m3 = m1.topLeft();
//
//        Hexagon h1 = new Hexagon(Terrain.GRASSLAND,1,4);
//        Hexagon h2 = new Hexagon(Terrain.JUNGLE,1,4);
//        Hexagon h3 = new Hexagon(Terrain.VOLCANO,1,4);
//
//        try {
//            handler.NukeSpots(m1, m2, m3, h1, h2, h3, map);
//        }
//        catch(RuntimeException e){
//            if(e.getMessage().equals("Cannot nuke those spots")) Assert.assertTrue(true);
//        }
//    }
//
//    @Test
//    public void TestNukingUsingMoreThanOneVolcanoHexesToBePlaced(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.left();
//        MapSpot m3 = m1.topLeft();
//
//        Hexagon h1 = new Hexagon(Terrain.GRASSLAND,1,4);
//        Hexagon h2 = new Hexagon(Terrain.VOLCANO,1,4);
//        Hexagon h3 = new Hexagon(Terrain.VOLCANO,1,4);
//
//        try {
//            handler.NukeSpots(m1, m2, m3, h1, h2, h3, map);
//        }
//        catch(RuntimeException e){
//            if(e.getMessage().equals("Not 1 volcano")) Assert.assertTrue(true);
//        }
//    }
//
//    @Test
//    public void TestNukingMoreThanOneVolcanoHex(){
//        NukingAndStackingHandler handler = new NukingAndStackingHandler();
//
//        MapSpot m1 = map.getMiddleHexagonMapSpot();
//        MapSpot m2 = m1.topRight();
//        MapSpot m3 = m1.topLeft();
//
//        Hexagon h1 = new Hexagon(Terrain.GRASSLAND,1,4);
//        Hexagon h2 = new Hexagon(Terrain.JUNGLE,1,4);
//        Hexagon h3 = new Hexagon(Terrain.VOLCANO,1,4);
//
//        try {
//            handler.NukeSpots(m1, m2, m3, h1, h2, h3, map);
//        }
//        catch(RuntimeException e){
//            if(e.getMessage().equals("Volcanoes do not match up")) Assert.assertTrue(true);
//        }
//    }
//
//}