import java.awt.Color;

/**
 *
 * A Ferry is an abstract class which is a more specific kind of {@link Boat} . It inherits all functions
 * from the {@link Boat} but can be used to extend the {@link Boat}-tree.
 */

public abstract class Ferry extends Boat {
    public Ferry(double enginePower, double currentSpeed, Color color, String modelName, double x, double y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, weight);
    }
}
