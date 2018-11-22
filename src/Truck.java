import java.awt.*;

public abstract class Truck extends Car {
    private double truckBedAngle;
    protected final double MAX_TRUCK_BED_ANGLE;
    protected final double MIN_TRUCK_BED_ANGLE;

    public Truck(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, double MAX_TRUCK_BED_ANGLE, double MIN_TRUCK_BED_ANGLE, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
        this.MAX_TRUCK_BED_ANGLE = MAX_TRUCK_BED_ANGLE;
        this.MIN_TRUCK_BED_ANGLE = MIN_TRUCK_BED_ANGLE;
        this.truckBedAngle = MIN_TRUCK_BED_ANGLE;
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

    @Override
    void gas(double amount) {
        truckBedAngle = MIN_TRUCK_BED_ANGLE;
        super.gas(amount);
    }

    @Override
    void startEngine() {
        truckBedAngle = MIN_TRUCK_BED_ANGLE;
        super.startEngine();
    }
}
