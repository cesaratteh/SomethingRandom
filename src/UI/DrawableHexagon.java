package UI;

public class DrawableHexagon {

    //-----------
    // Attributes

    private double centerX;
    private double centerY;
    private double radius;


    //-------------
    // Constructors

    public DrawableHexagon(final double centerX, final double centerY, final double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    //--------
    // Methods

    int strokeWidth = 4;
    public DrawableHexagon right() {
        return new DrawableHexagon(
                centerX + (radius + strokeWidth) * Math.sin(2 * HEXAGON_ANGLE) * 2,
                centerY,
                radius);
    }

    public DrawableHexagon left() {
        return new DrawableHexagon(
                centerX - (radius + strokeWidth) * Math.sin(2 * HEXAGON_ANGLE) * 2,
                centerY,
                radius);
    }

    public DrawableHexagon topLeft() {
        return new DrawableHexagon(
                centerX - (radius + strokeWidth) * Math.sin(2 * HEXAGON_ANGLE),
                centerY - (1.5) * (radius + strokeWidth),
                radius);
    }

    public DrawableHexagon bottomLeft() {
        return new DrawableHexagon(
                centerX - (radius + strokeWidth) * Math.sin(2 * HEXAGON_ANGLE),
                centerY + (1.5) * (radius + strokeWidth),
                radius);
    }

    public DrawableHexagon topRight() {
        return new DrawableHexagon(
                centerX + (radius + strokeWidth) * Math.sin(2 * HEXAGON_ANGLE),
                centerY - (1.5) * (radius + strokeWidth),
                radius);
    }

    public DrawableHexagon bottomRight() {
        return new DrawableHexagon(
                centerX + (radius + strokeWidth) * Math.sin(2 * HEXAGON_ANGLE),
                centerY + (1.5) * (radius + strokeWidth),
                radius);
    }

    public double[] getPolylineXPoints() {
        final double[] xPoints = new double[7];

        for (int i = 0; i < 6; i++)
        {
            xPoints[i] = centerX + radius * Math.sin(i * HEXAGON_ANGLE);
        }

        xPoints[6] = xPoints[0];

        return xPoints;
    }

    public double[] getPolylineYPoints() {
        final double[] yPoints = new double[7];

        for (int i = 0; i < 6; i++)
        {
            yPoints[i] = centerY + radius * Math.cos(i * HEXAGON_ANGLE);
        }

        yPoints[6] = yPoints[0];

        return yPoints;
    }

    //-----------
    // Constants

    private final static double HEXAGON_ANGLE = (1/3.0) * Math.PI;
}
