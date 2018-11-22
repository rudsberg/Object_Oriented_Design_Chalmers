import java.awt.*;

public class CarFerry extends Ferry implements Loadables {
    private VehicleLoader vehicleLoader;

    public CarFerry(double enginePower, double currentSpeed, Color color, String modelName, double x, double y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD) {
        super(enginePower, currentSpeed, color, modelName, x, y, direction, MAX_LOAD_CAPACATY, MAX_VEHICLE_SIZE, weight, MAX_DISTANCE_TO_LOAD);
        this.vehicleLoader = new VehicleLoader(MAX_DISTANCE_TO_LOAD, MAX_VEHICLE_SIZE, MAX_LOAD_CAPACATY, Load_Type.FRONT_TO_BACK, getPosition(), getReferenceWeight());
    }

    @Override
    public int getAmountVehiclesLoaded() {
        return vehicleLoader.getAmountVehiclesLoaded();
    }

    @Override
    public int getMaxLoadCapacaty() {
        return vehicleLoader.getMaxLoadCapacaty();
    }

    @Override
    double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void loadVehicle(Vehicle v) {
        if (this == v)
            return;

        vehicleLoader.loadVehicle(v);
    }

    @Override
    public void unLoadVehicle() {
        vehicleLoader.unLoadVehicle();
    }

    @Override
    public void closeTruckBed() {
        vehicleLoader.closeTruckBed();
    }

    @Override
    public void openTruckBed() {
        vehicleLoader.openTruckBed();
    }

    @Override
    public boolean isFull() {
        return vehicleLoader.isFull();
    }

    @Override
    public boolean isTruckBedClosed() {
        return vehicleLoader.isTruckBedClosed();
    }

    @Override
    public boolean isTruckBedOpen() {
        return vehicleLoader.isTruckBedOpen();
    }

    @Override
    public boolean inRange(Positionable v) {
        return vehicleLoader.inRange(v);
    }

    @Override
    public int getMaxLoadAmount() {
        return vehicleLoader.getMaxLoadAmount();
    }

    @Override
    public int getMaxVehicleWeight() {
        return vehicleLoader.getMaxVehicleWeight();
    }

    @Override
    public int getMaxDistanceToLoad() {
        return vehicleLoader.getMaxDistanceToLoad();
    }
}
