package UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.*;

public class UI extends Application {

    //-----------
    // attributes

    private Map map;

    //--------
    // Methods

    private void drawMap(GraphicsContext gc) {

        Map map = new Map();
        MapSpot mapSpot = map.getMiddleHexagonMapSpot();

        Hexagon temp = new Hexagon(Terrain.GRASSLAND, 2, 4);
        temp.addMeeples(4, Team.FRIENDLY);

        map.addHexagon(mapSpot, temp);

        temp = new Hexagon(Terrain.JUNGLE, 2, 4);
        temp.addMeeples(3, Team.ENEMY);

        map.addHexagon(mapSpot.left(),temp);
        map.addHexagon(mapSpot.topLeft(), new Hexagon(Terrain.LAKE, 4, 2));
        map.addHexagon(mapSpot.topRight(), new Hexagon(Terrain.JUNGLE, 4, 2));
        map.addHexagon(mapSpot.right(), new Hexagon(Terrain.VOLCANO, 4, 2));
        map.addHexagon(mapSpot.bottomRight(), new Hexagon(Terrain.JUNGLE, 4, 2));
        map.addHexagon(mapSpot.bottomLeft(), new Hexagon(Terrain.ROCKY, 4, 2));

        gc.setFill(Color.WHITE);
        gc.setLineWidth(4);

        final Hexagon[][] hexagonArray = map.getHexagonArray();

        int leftBound = -1;
        int rightBound = -1;
        int topBound = -1;
        int bottomBound = -1;


        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.size(); j++) {
                if (hexagonArray[i][j] != null && leftBound == -1) {
                    leftBound = i + 1;
                }

                if (hexagonArray[i][j] != null) {
                    rightBound = i + 1;
                }

                if (hexagonArray[j][i] != null && topBound == -1) {
                    topBound = i + 1;
                }

                if (hexagonArray[j][i] != null)
                    bottomBound = i + 1;
            }
        }

        if (leftBound % 2 != topBound % 2)
            leftBound--;
        MapSpot currentMapSpot = new MapSpot(leftBound, topBound);
        DrawableHexagon currentDrawable = new DrawableHexagon(50, 50, 50);


        while (currentMapSpot.getY() < bottomBound){
            while (currentMapSpot.getX() < rightBound) {

                Hexagon hexagon = map.getHexagon(currentMapSpot);

                if (hexagon != null) {
                    switch (hexagon.getOccupiedBy()) {
                        case FRIENDLY:
                            gc.setStroke(Color.GREEN);
                            break;
                        case ENEMY:
                            gc.setStroke(Color.RED);
                            break;
                        default:
                            gc.setStroke(Color.BLACK);
                            break;
                    }

                    switch (hexagon.getTerrainType()) {
                        case GRASSLAND:
                            gc.setFill(Color.LAWNGREEN);
                            break;
                        case JUNGLE:
                            gc.setFill(Color.FORESTGREEN);
                            break;
                        case LAKE:
                            gc.setFill(Color.DODGERBLUE);
                            break;
                        case VOLCANO:
                            gc.setFill(Color.DARKORANGE);
                            break;
                        case ROCKY:
                            gc.setFill(Color.GRAY);
                            break;
                        default:
                            gc.setFill(Color.WHITE);
                            break;
                    }

                    gc.fillPolygon(
                            currentDrawable.getPolylineXPoints(),
                            currentDrawable.getPolylineYPoints(),
                            7);
                    gc.strokePolyline(
                            currentDrawable.getPolylineXPoints(),
                            currentDrawable.getPolylineYPoints(),
                            7);
                }


                currentDrawable = currentDrawable.right();
                currentMapSpot = currentMapSpot.right();
            }

            currentMapSpot = currentMapSpot.bottomRight();
            currentDrawable = currentDrawable.bottomRight();

            while (currentMapSpot.getX() > leftBound) {
                Hexagon hexagon = map.getHexagon(currentMapSpot);

                if (hexagon != null) {
                    switch (hexagon.getOccupiedBy()) {
                        case FRIENDLY:
                            gc.setStroke(Color.GREEN);
                            break;
                        case ENEMY:
                            gc.setStroke(Color.RED);
                            break;
                        default:
                            gc.setStroke(Color.BLACK);
                            break;
                    }

                    switch (hexagon.getTerrainType()) {
                        case GRASSLAND:
                            gc.setFill(Color.LAWNGREEN);
                            break;
                        case JUNGLE:
                            gc.setFill(Color.FORESTGREEN);
                            break;
                        case LAKE:
                            gc.setFill(Color.DODGERBLUE);
                            break;
                        case VOLCANO:
                            gc.setFill(Color.DARKORANGE);
                            break;
                        case ROCKY:
                            gc.setFill(Color.GRAY);
                            break;
                        default:
                            gc.setFill(Color.WHITE);
                            break;
                    }

                    gc.fillPolygon(
                            currentDrawable.getPolylineXPoints(),
                            currentDrawable.getPolylineYPoints(),
                            7);
                    gc.strokePolyline(
                            currentDrawable.getPolylineXPoints(),
                            currentDrawable.getPolylineYPoints(),
                            7);
                }


                currentDrawable = currentDrawable.left();
                currentMapSpot = currentMapSpot.left();
            }

            currentMapSpot = currentMapSpot.bottomLeft();
            currentDrawable = currentDrawable.bottomLeft();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tiger Island");

        Group root = new Group();
        Canvas canvas = new Canvas(1500, 1150);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawMap(gc);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
