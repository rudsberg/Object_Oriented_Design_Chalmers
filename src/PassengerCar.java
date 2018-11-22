import java.awt.*;

public abstract class PassengerCar extends Car {
    public PassengerCar(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
    }
}
