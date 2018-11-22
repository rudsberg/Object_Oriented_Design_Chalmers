public interface Loadables {
    void loadVehicle(Vehicle v);
    void unLoadVehicle();
    void closeTruckBed();
    void openTruckBed();

    boolean isFull();
    boolean isTruckBedClosed();
    boolean isTruckBedOpen();
    boolean inRange(Positionable v);

    int getMaxLoadAmount();
    int getMaxVehicleWeight();
    int getMaxDistanceToLoad();
    int getMaxLoadCapacity();
    int getAmountVehiclesLoaded();
}
