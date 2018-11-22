import java.awt.Color;

public abstract class Ferry extends Boat {
    public Ferry(double enginePower, double currentSpeed, Color color, String modelName, double x, double y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, weight);
    }
}
