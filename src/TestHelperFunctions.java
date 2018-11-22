public class TestHelperFunctions {
    public static double getSpeedTo(Car c, double toThis) {
        double currentSpeed = c.getCurrentSpeed();
        double offset = 0.3;
        double speedChange = 0.1;

        while (!(currentSpeed <= toThis + offset && currentSpeed >= toThis - offset)) {
            if (currentSpeed > toThis) {
                c.brake(speedChange);
            } else {
                c.gas(speedChange);
            }
            currentSpeed = c.getCurrentSpeed();
        }

        return c.getCurrentSpeed();
    }

    public static void moveVehicleDistanceAway(Vehicle v, double distToTravel) {
        v.startEngine();
        Positioner initPosition = new Positioner(v.getX(), v.getY());
        double distTraveled;

        do {
            v.gas(1);
            v.move();
            distTraveled = Positioner.getDistanceBetween(v, initPosition);
        } while (distTraveled < distToTravel);
    }
}
