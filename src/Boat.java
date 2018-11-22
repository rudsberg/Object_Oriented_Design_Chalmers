import java.awt.Color;

public abstract class Boat extends Vehicle {
    public Boat(double enginePower, double currentSpeed, Color color, String modelName, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, weight);
    }
}
