import java.awt.*;

/**
 * Extends the abstract car class. Saab95 also has a turbo functionallity which will affect its speed factor.
 */
public class Saab95 extends PassengerCar {
    private boolean turboOn;

    Saab95(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, double x, double y, Direction direction, int weight) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    /**
     * Calculates and return the speed factor. If the turbo is turned it will increase the speed factor.
     * @return  a double value of the speed factor.
     */
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}