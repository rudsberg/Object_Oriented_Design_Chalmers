import java.awt.Color;

public abstract class SemiTruck extends Car implements Loadables {
    private VehicleLoader vehicleLoader;

    public SemiTruck(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, int x, int y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
        this.vehicleLoader = new VehicleLoader(MAX_DISTANCE_TO_LOAD, MAX_VEHICLE_SIZE, MAX_LOAD_CAPACATY, Load_Type.BACK_TO_BACK, getPosition(), getReferenceWeight());
    }

    public VehicleLoader getVehicleLoader() {
        return vehicleLoader;
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
    void startEngine() {
        closeTruckBed();
        super.startEngine();
    }

    @Override
    void gas(double amount) {
        closeTruckBed();
        super.gas(amount);
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
    public void loadVehicle(Vehicle v) {
        if (v == this)
            return;

        vehicleLoader.loadVehicle(v);
    }

    @Override
    public void unLoadVehicle() {
        vehicleLoader.unLoadVehicle();
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
