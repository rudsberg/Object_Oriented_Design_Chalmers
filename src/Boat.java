import java.awt.Color;

/**
 * Boat is an abstract class which is an extension {@link Vehicle} describes the functions and capabilities of a boat.
 * It has the following variables describing it:
 * enginePower, currentSpeed, color, modelName, x, y, direction, weight.
 *
 */

public abstract class Boat extends Vehicle {
    public Boat(double enginePower, double currentSpeed, Color color, String modelName, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, weight);
    }
}
