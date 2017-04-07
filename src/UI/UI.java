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

    //--------
    // Methods

    private void drawMap(GraphicsContext gc) {

        Map map = new Map();
        MapSpot mapSpot = map.getMiddleHexagonMapSpot();

        Hexagon temp = new Hexagon(Terrain.GRASSLAND, 2, 4);
        temp.addMeeples(Team.FRIENDLY);
        map.addHexagon(mapSpot, temp);

        temp = new Hexagon(Terrain.JUNGLE, 2, 4);
        temp.addTotoro(Team.ENEMY);
        map.addHexagon(mapSpot.left(), temp);

        temp = new Hexagon(Terrain.LAKE, 2, 2);
        temp.addTiger(Team.FRIENDLY);
        map.addHexagon(mapSpot.topLeft(), temp);

        map.addHexagon(mapSpot.topRight(), new Hexagon(Terrain.JUNGLE, 4, 2));
        map.addHexagon(mapSpot.right(), new Hexagon(Terrain.VOLCANO, 4, 2));
        map.addHexagon(mapSpot.bottomRight(), new Hexagon(Terrain.JUNGLE, 4, 2));
        map.addHexagon(mapSpot.bottomLeft(), new Hexagon(Terrain.ROCKY, 4, 2));

        gc.setFill(Color.WHITE);


        int widthAndHeight = 10;

        MapSpot currentMapSpot = new MapSpot(5, 0, -5);
        DrawableHexagon currentDrawable = new DrawableHexagon(50, 50, 50);


        for (int i = 0; i < widthAndHeight; i++){
            for(int j = 0; j < widthAndHeight; j++){

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

                    gc.setLineWidth(4);
                    gc.fillPolygon(
                            currentDrawable.getPolylineXPoints(4),
                            currentDrawable.getPolylineYPoints(4),
                            7);
                    gc.strokePolyline(
                            currentDrawable.getPolylineXPoints(4),
                            currentDrawable.getPolylineYPoints(4),
                            7);

                    gc.setLineWidth(1);
                    gc.setStroke(Color.BLACK);
                    gc.strokeText("Lvl: " + hexagon.getLevel(),
                            currentDrawable.getCenterX() - 22,
                            currentDrawable.getCenterY());
                    if (hexagon.isHasTotoro()) {
                        gc.strokeText("Totoro",
                                currentDrawable.getCenterX() - 22,
                                currentDrawable.getCenterY() + 20);
                    } else if (hexagon.isHasTiger()) {
                        gc.strokeText("Tiger",
                                currentDrawable.getCenterX() - 22,
                                currentDrawable.getCenterY() + 20);
                    } else if (hexagon.getNumberOfMeeples() > 0) {
                        gc.strokeText("M: " + hexagon.getNumberOfMeeples(),
                                currentDrawable.getCenterX() - 22,
                                currentDrawable.getCenterY() + 20);
                    }
                }


                currentDrawable = currentDrawable.right();
                currentMapSpot = currentMapSpot.right();
            }

            currentMapSpot = currentMapSpot.bottomRight();
            currentDrawable = currentDrawable.bottomRight();

            for(int j = 0; j < widthAndHeight; j++) {
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

                    gc.setLineWidth(4);
                    gc.fillPolygon(
                            currentDrawable.getPolylineXPoints(4),
                            currentDrawable.getPolylineYPoints(4),
                            7);
                    gc.strokePolyline(
                            currentDrawable.getPolylineXPoints(4),
                            currentDrawable.getPolylineYPoints(4),
                            7);


                    gc.setLineWidth(1);
                    gc.setStroke(Color.BLACK);
                    gc.strokeText("Lvl: " + hexagon.getLevel(),
                            currentDrawable.getCenterX() - 22,
                            currentDrawable.getCenterY());
                    if (hexagon.isHasTotoro()) {
                        gc.strokeText("Totoro",
                                currentDrawable.getCenterX() - 22,
                                currentDrawable.getCenterY() + 20);
                    } else if (hexagon.isHasTiger()) {
                        gc.strokeText("Tiger",
                                currentDrawable.getCenterX() - 22,
                                currentDrawable.getCenterY() + 20);
                    } else if (hexagon.getNumberOfMeeples() > 0) {
                        gc.strokeText("M: " + hexagon.getNumberOfMeeples(),
                                currentDrawable.getCenterX() - 22,
                                currentDrawable.getCenterY() + 20);
                    }
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
