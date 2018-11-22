import java.awt.*;
/**
 *
 * A PassengerCar is an abstract class which is a more specific kind of {@link Car} . It inherits all functions
 * from the {@link Car} but can be used to extend the {@link Car}-tree.
 */

public abstract class PassengerCar extends Car {
    public PassengerCar(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
    }
}
