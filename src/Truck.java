import java.awt.*;

/**
 * Truck is an abstract class which extends {@link Car}. Truck has a TruckBed which it can open or close up to a certain
 * degree and could therefore theoretically load its
 */

public abstract class Truck extends Car {
    private double truckBedAngle;
    private final double MAX_TRUCK_BED_ANGLE;
    private final double MIN_TRUCK_BED_ANGLE;

    public Truck(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, double MAX_TRUCK_BED_ANGLE, double MIN_TRUCK_BED_ANGLE, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
        this.MAX_TRUCK_BED_ANGLE = MAX_TRUCK_BED_ANGLE;
        this.MIN_TRUCK_BED_ANGLE = MIN_TRUCK_BED_ANGLE;
        this.truckBedAngle = MIN_TRUCK_BED_ANGLE;
    }

    /**
     * This method sets the truckBedAngle according to the parameter it's given and the max/min values defined in the constructor.
     * It makes sure the Truck is standing still, that it can't go above or below allowed intervals(MAX/MIN-values) and
     * if it does , it sets the values to either MAX or MIN accordingly.
     *
     * @param truckBedAngle a double which describes wanted truckBedAngle.
     */
    public void setTruckBedAngle(double truckBedAngle) {
        if (getCurrentSpeed() != 0)
            return;

        if (truckBedAngle > MAX_TRUCK_BED_ANGLE) {
            this.truckBedAngle = MAX_TRUCK_BED_ANGLE;
        } else if (truckBedAngle < MIN_TRUCK_BED_ANGLE) {
            this.truckBedAngle = MIN_TRUCK_BED_ANGLE;
        } else {
            this.truckBedAngle = truckBedAngle;
        }
    }

    /**
     * Increases the car speed if the amount parameter is within 0 and MAX_SPEED_CHANGE and also sets the truckBedAngle
     * to MIN.
     *
     * @param amount the truck should increase its speed
     */
    @Override
    public void gas(double amount) {
        truckBedAngle = MIN_TRUCK_BED_ANGLE;
        super.gas(amount);
    }

    /**
     * Sets the starting speed and closes the truckBed.
     */
    @Override
    public void startEngine() {
        truckBedAngle = MIN_TRUCK_BED_ANGLE;
        super.startEngine();
    }

    public double getMaxTruckBedAngle() {
        return MAX_TRUCK_BED_ANGLE;
    }

    public double getMinTruckBedAngle() {
        return MIN_TRUCK_BED_ANGLE;
    }

    public double getTruckBedAngle() {
        return truckBedAngle;
    }
}
