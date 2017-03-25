//package game.action.handlers.utils;
//
//import models.Map;
//import models.MapSpot;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// * Handles mapping settlements
// * <p>
// * Should use DFS or BFS
// */
//public class SettlementsFactory {
//
//    //-----------
//    // Attributes
//
//    private Map map;
//
//    private boolean visited[][];
//    public ArrayList<Settlement> generateSettlements() {
//        final ArrayList<Settlement> settlements = new ArrayList<Settlement>();
//
//        visited = new boolean[map.getMapSize()][map.getMapSize()];
//
//
//    }
//
//    private void visit(final MapSpot mapSpot) {
//        visited[mapSpot.getX()][mapSpot.getY()] = true;
//
//        MapSpot nextMapSpot = new MapSpot()
//    }
//
//    //-------------
//    // Constructors
//
//    public SettlementsFactory(final Map map) {
//        this.map = map;
//    }
//
//}
