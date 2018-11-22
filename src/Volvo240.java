import java.awt.*;

/**
 * Extends the abstract car class. Volvo240 also has a trim factor which will affect its speed factor.
 */
public class Volvo240 extends PassengerCar {

    private final static double trimFactor = 1.25;

    Volvo240(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
    }

    /**
     * Calculates and return the speed factor. The trim factor will affect the speed factor.
     * @return  a double value of the speed factor.
     */
    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}