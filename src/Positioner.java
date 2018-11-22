public class Positioner implements Positionable {
    private double x;
    private double y;
    private static final double MAX_DISTANCE_CHANGE = 120;

    public Positioner(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between two positionables. Är det nödvändigt? Självdokumenterande..
     * @param p1 Positionable 1
     * @param p2 Positionable 2
     * @return double of distance between them
     */
    public static double getDistanceBetween(Positionable p1, Positionable p2) {
        double deltaX = p1.getX() - p2.getX();
        double deltaY = p1.getY() - p2.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Enables loadable vehicles, such as semi trucks, to set the loaded cars position to its own position.
     */
    public void setsPositionToSameAs(Positionable loadedVehicle) {
        loadedVehicle.setX(this.getX());
        loadedVehicle.setY(this.getY());
    }


    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    /**
     *
     * A setter which is limited by a set distance @MAX_DISTANCE_CHANGE.
     * @param x Distance you want to move the object.
     */
    @Override
    public void setX(double x) {
        if (isTooFarAway(new Positioner(x, this.getY())))
            return;

        this.x = x;
    }
    /**
     *
     * A setter which is limited by a set distance @MAX_DISTANCE_CHANGE.
     * @param y Distance you want to move the object.
     */
    @Override
    public void setY(double y) {
        if (isTooFarAway(new Positioner(this.getX(), y)))
            return;

        this.y = y;
    }

    /**
     * Boolean check if Positionable p is farther away than the allowed MAX_DISTANCE_CHANGE
     * @return A boolean
     */
    private boolean isTooFarAway(Positionable p) {
        return Positioner.getDistanceBetween(this, p) > MAX_DISTANCE_CHANGE;
    }
}
