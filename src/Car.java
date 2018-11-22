import java.awt.*;

/**
 *  Car is an abstract class with variables describing engine power, current speed, color, model name, number of doors, a x- and y-coordinates,
 *  current direction (up, right, down or left) and the max speed change. A car has the ability to move in four directions, increase and decrease
 *  its speed and other car-like functionalities.
 */
public abstract class Car extends Vehicle {
    private final int nrDoors; // Number of doors on the car

    public Car(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, weight);
        this.nrDoors = nrDoors;
    }

    public int getNrDoors() {
        return nrDoors;
    }
}
