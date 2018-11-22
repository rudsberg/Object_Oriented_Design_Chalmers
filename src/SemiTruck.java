import java.awt.Color;

/**
 * Semitruck inherits all functionalities from {@link Car} and {@link Vehicle}. It also implements from {@link Loadables}, it therefore
 * has a functionalities to load other vehicles. Naturally, semi-trucks load from the back and removes items from the back.
 */
public abstract class SemiTruck extends Car implements Loadables {
    private VehicleLoader vehicleLoader;

    public SemiTruck(double enginePower, double currentSpeed, Color color, String modelName, int nrDoors, int x, int y, Direction direction, int MAX_LOAD_CAPACATY, int MAX_VEHICLE_SIZE, int weight, int MAX_DISTANCE_TO_LOAD) {
        super(enginePower, currentSpeed, color, modelName, nrDoors, x, y, direction, weight);
        this.vehicleLoader = new VehicleLoader(MAX_DISTANCE_TO_LOAD, MAX_VEHICLE_SIZE, MAX_LOAD_CAPACATY, Load_Type.BACK_TO_BACK, getPosition(), getReferenceWeight());
    }

    public VehicleLoader getVehicleLoader() {
        return vehicleLoader;
    }

    /**
     * Delegates to {@see VehicleLoader#getAmountVehiclesLoaded()}
     * @return
     */
    @Override
    public int getAmountVehiclesLoaded() {
        return vehicleLoader.getAmountVehiclesLoaded();
    }

    /**
     * Delegates to {@see VehicleLoader#getMaxLoadCapacaty()}
     * @return
     */
    @Override
    public int getMaxLoadCapacity() {
        return vehicleLoader.getMaxLoadCapacity();
    }

    /**
     * Delegates to {@see VehicleLoader#startEngine()}
     * @return
     */
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
