import java.util.*;

/**
 * A class primarly used by other classes to delegate functionality to load vehicles. A VehicleLoader can add vehicles
 * and remove vehicles back-to-back and front-to-back.
 */
public class VehicleLoader implements Loadables {
    private final int MAX_DISTANCE_TO_LOAD;
    private final int MAX_ITEM_WEIGHT;
    private final int MAX_LOAD_CAPACITY;
    private final Load_Type LOAD_TYPE;
    private Positioner position;
    private TruckBedState truckBedState;
    private Weight loaderWeight;

    private List<Vehicle> frontToBackLoaded;
    private Deque<Vehicle> backToBackLoaded;

    public VehicleLoader(int MAX_DISTANCE_TO_LOAD, int MAX_ITEM_WEIGHT, int MAX_LOAD_CAPACITY, Load_Type loadType, Positioner position, Weight loaderWeight) {
        this(MAX_DISTANCE_TO_LOAD, MAX_ITEM_WEIGHT, MAX_LOAD_CAPACITY, loadType == Load_Type.BACK_TO_BACK ? new ArrayDeque<>() : null, loadType == Load_Type.FRONT_TO_BACK ? new ArrayList<>() : null, loadType, position, loaderWeight);
    }

    private VehicleLoader(int MAX_DISTANCE_TO_LOAD, int MAX_ITEM_WEIGHT, int MAX_LOAD_CAPACITY, Deque<Vehicle> backToBackLoaded, List<Vehicle> frontToBackLoaded, Load_Type LOAD_TYPE, Positioner position, Weight loaderWeight) {
        this.MAX_DISTANCE_TO_LOAD = MAX_DISTANCE_TO_LOAD;
        this.MAX_ITEM_WEIGHT = MAX_ITEM_WEIGHT;
        this.MAX_LOAD_CAPACITY = MAX_LOAD_CAPACITY;
        this.frontToBackLoaded = frontToBackLoaded;
        this.backToBackLoaded = backToBackLoaded;
        this.LOAD_TYPE = LOAD_TYPE;
        this.position = position;
        this.truckBedState = TruckBedState.CLOSED;
        this.loaderWeight = loaderWeight;
    }

    private boolean isBackToBackLoaded() {
        switch (LOAD_TYPE) {
            case FRONT_TO_BACK:
                return false;
            case BACK_TO_BACK:
                return true;
            default:
                throw new Error("INVALID LOAD TYPE.");
        }
    }

    @Override
    public int getAmountVehiclesLoaded() {
        if (isBackToBackLoaded())
            return backToBackLoaded.size();
        else
            return frontToBackLoaded.size();
    }

    @Override
    public void loadVehicle(Vehicle v) {
        if (!canItLoad(v))
            return;

        if (isBackToBackLoaded()) {
            loadBackToBackVehicle(v);
        } else {
            loadFrontToBackVehicle(v);
            v.turn180Degress();
        }

        v.setsPositionToSameAs(position);
        loaderWeight.addToWeight(v.getWeight());
    }

    private void loadBackToBackVehicle(Vehicle v) {
        backToBackLoaded.push(v);
    }

    private void loadFrontToBackVehicle(Vehicle v) {
        frontToBackLoaded.add(v);
    }

    public boolean canItLoad(Vehicle vehicleToLoad) {
        boolean tooHeavy = vehicleToLoad.getWeight() > MAX_ITEM_WEIGHT;

        return !(isFull() || isTruckBedClosed() || tooHeavy || !inRange(vehicleToLoad));
    }

    @Override
    public void unLoadVehicle() {
        if (isTruckBedClosed() || getAmountVehiclesLoaded() <= 0)
            return;

        Vehicle v;

        if (isBackToBackLoaded())
            v = unloadBackToBack();
        else
            v = unloadFrontToBack();

        v.gas(0.3);
        v.move();
        loaderWeight.removeWeight(v.getWeight());
    }

    @Override
    public void closeTruckBed() {
        truckBedState = TruckBedState.CLOSED;
    }

    @Override
    public void openTruckBed() {
        truckBedState = TruckBedState.OPEN;
    }

    private Vehicle unloadBackToBack() {
        return backToBackLoaded.pop();
    }

    private Vehicle unloadFrontToBack() {
        return frontToBackLoaded.remove(0);
    }

    @Override
    public boolean isFull() {
        if (isBackToBackLoaded())
            return backToBackLoaded.size() >= MAX_LOAD_CAPACITY;
        else
            return frontToBackLoaded.size() >= MAX_LOAD_CAPACITY;
    }

    @Override
    public boolean isTruckBedClosed() {
        return truckBedState == TruckBedState.CLOSED;
    }

    @Override
    public boolean isTruckBedOpen() {
        return truckBedState == TruckBedState.OPEN;
    }

    @Override
    public boolean inRange(Positionable v) {
        return MAX_DISTANCE_TO_LOAD >= Positioner.getDistanceBetween(position, v);
    }

    @Override
    public int getMaxLoadAmount() {
        return MAX_LOAD_CAPACITY;
    }

    @Override
    public int getMaxVehicleWeight() {
        return MAX_ITEM_WEIGHT;
    }

    @Override
    public int getMaxDistanceToLoad() {
        return MAX_DISTANCE_TO_LOAD;
    }

    @Override
    public int getMaxLoadCapacity() {
        return MAX_LOAD_CAPACITY;
    }
}
