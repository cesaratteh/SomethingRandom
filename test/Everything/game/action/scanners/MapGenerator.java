package Everything.game.action.scanners;

import Everything.models.*;

public class MapGenerator {

    /**
     * Generates a disk with a line of two hexagons sitcking out of it from the bottom right
     */
    public static Map generateCircleWithOutsideLine(final Terrain circleTerrain,
                               final Terrain outsideTerrain,
                               final Terrain insideTerrain,
                               final Team circleTeam,
                               final Team outsideTeam,
                               final Team insideTeam) {

        Map map = new Map();

        MapSpot ms = map.getMiddleHexagonMapSpot();
        Hexagon temp = new Hexagon(insideTerrain, 1, 1);
        temp.addMeeplesAccordingToLevel(insideTeam);
        map.setHexagon(ms, temp);

        ms = ms.right();
        temp = new Hexagon(circleTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(circleTeam);
        map.setHexagon(ms, temp);

        ms = ms.topLeft();
        temp = new Hexagon(circleTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(circleTeam);
        map.setHexagon(ms, temp);

        ms = ms.left();
        temp = new Hexagon(circleTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(circleTeam);
        map.setHexagon(ms, temp);

        ms = ms.bottomLeft();
        temp = new Hexagon(circleTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(circleTeam);
        map.setHexagon(ms, temp);

        ms = ms.bottomRight();
        temp = new Hexagon(circleTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(circleTeam);
        map.setHexagon(ms, temp);

        ms = ms.right();
        temp = new Hexagon(circleTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(circleTeam);
        map.setHexagon(ms, temp);

        ms = ms.right();
        temp = new Hexagon(outsideTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(outsideTeam);
        map.setHexagon(ms, temp);

        ms = ms.bottomRight();
        temp = new Hexagon(outsideTerrain, 2, 2);
        temp.addMeeplesAccordingToLevel(outsideTeam);
        map.setHexagon(ms, temp);

        return map;
    }
}
