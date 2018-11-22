import java.awt.*;

/**
 *Scania is a specific type of non-abstract  {@link Truck} .
 */

public class Scania extends Truck {
    private final static double MAX_TRUCK_BED_ANGLE = 70;
    private final static double MIN_TRUCK_BED_ANGLE = 0;

    /**
     *  Constructor given initial truck bed angle and will therefore set initial speed to 0.
     */
    public Scania(double enginePower, Color color, String modelName, int nrDoors, double x, double y, Direction direction, double truckBedAngle, int weight) {
        super(enginePower, 0, color, modelName, nrDoors, x, y, direction, MAX_TRUCK_BED_ANGLE, MIN_TRUCK_BED_ANGLE, weight);
        setTruckBedAngle(truckBedAngle);
    }

    /**
     *  Constructor given initial speed and will therefore set the initial truck bed angle to the minimum truck bed angle.
     */
    public Scania(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, MAX_TRUCK_BED_ANGLE, MIN_TRUCK_BED_ANGLE, weight);
    }

    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01;
    }

}