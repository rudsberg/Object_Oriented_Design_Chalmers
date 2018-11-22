import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadersTest {
    List<SemiTruck> loaders = new ArrayList<>();
    List<CarFerry> carFerries = new ArrayList<>();

    LoadersTest() {
        loaders.add(new VolvoSemiTruck(500, 60, Color.cyan, "", 2, 0, 0, Direction.DOWN, 15_000));
        carFerries.add(new CarFerry(4000, 5, Color.green, "my boat", 50, 50, Direction.RIGHT, 50, 7000, 50_000, 40));
    }

    @Test
    void loadVehicle() {
        for (SemiTruck semi : loaders) {
            loadSpecificVehicle(semi);
        }

        for (CarFerry carFerry : carFerries) {
            loadSpecificVehicle(carFerry);
        }
    }

    private <T extends Vehicle & Loadables> void loadSpecificVehicle(T loader) {
        int amountLoaded = loader.getAmountVehiclesLoaded();
        Vehicle car = createCar(loader.getX(), loader.getY(), 2000);
        Vehicle awayCar = createCar(loader.getX() + 2, loader.getY() + 3, 2000);
        Vehicle oversizedCar = createCar(loader.getX(), loader.getY(), loader.getMaxVehicleWeight() + 100);

        // Load two cars normal way
        getReadyToLoad(loader);
        loader.loadVehicle(car);
        assertEquals(amountLoaded + 1, loader.getAmountVehiclesLoaded());
        loader.loadVehicle(car);
        assertEquals(amountLoaded + 2, loader.getAmountVehiclesLoaded());

        // Try to load with closed truck bed
        amountLoaded = loader.getAmountVehiclesLoaded();
        loader.closeTruckBed();
        loader.loadVehicle(car);
        assertEquals(amountLoaded, loader.getAmountVehiclesLoaded());

        // Try to load car too far away
        getReadyToLoad(loader);
        amountLoaded = loader.getAmountVehiclesLoaded();
        TestHelperFunctions.moveVehicleDistanceAway(car, loader.getMaxDistanceToLoad() + 10);
        loader.loadVehicle(car);
        assertEquals(amountLoaded, loader.getAmountVehiclesLoaded());

        // Try to load too heavy car
        getReadyToLoad(loader);
        amountLoaded = loader.getAmountVehiclesLoaded();
        loader.loadVehicle(oversizedCar);
        assertEquals(amountLoaded, loader.getAmountVehiclesLoaded());

        // Try to load itself
        getReadyToLoad(loader);
        amountLoaded = loader.getAmountVehiclesLoaded();
        loader.loadVehicle(loader);
        assertEquals(amountLoaded, loader.getAmountVehiclesLoaded());

        // Check that loaded car has same position as loader
        getReadyToLoad(loader);
        getCapacityTo(loader, loader.getAmountVehiclesLoaded() / 2);
        loader.loadVehicle(awayCar);
        assertEquals(awayCar.getX(), loader.getX());
    }

    @Test
    void unLoadVehicle() {
        for (SemiTruck truck : loaders) {
            unSpecificLoadVehicle(truck);
        }

        for (CarFerry carFerry : carFerries) {
            unSpecificLoadVehicle(carFerry);
        }
    }

    private <V extends Vehicle & Loadables> void unSpecificLoadVehicle(V truck) {
        int amountLoaded;
        Vehicle car = createCar(truck.getX(), truck.getY(), 2000);

        // With two cars as starting point, try to unload three times
        getReadyToLoad(truck);
        getCapacityTo(truck, 0);
        truck.loadVehicle(car);
        truck.loadVehicle(car);
        amountLoaded = truck.getAmountVehiclesLoaded();
        truck.unLoadVehicle();
        assertEquals(amountLoaded - 1, truck.getAmountVehiclesLoaded());
        truck.unLoadVehicle();
        assertEquals(amountLoaded - 2, truck.getAmountVehiclesLoaded());
        truck.unLoadVehicle();
        assertEquals(amountLoaded - 2, truck.getAmountVehiclesLoaded());

        // Try to load with truck bed closed
        getReadyToLoad(truck);
        truck.loadVehicle(car);
        amountLoaded = truck.getAmountVehiclesLoaded();
        truck.closeTruckBed();
        truck.unLoadVehicle();
        assertEquals(amountLoaded, truck.getAmountVehiclesLoaded());
    }

    @Test
    void isFull() {
        for (SemiTruck loader : loaders) {
            isSpecificFull(loader);
        }

        for (CarFerry carFerry : carFerries) {
            isSpecificFull(carFerry);
        }
    }

    private <V extends Vehicle & Loadables> void isSpecificFull(V loader) {
        int maxCapacaty = loader.getMaxLoadCapacity();
        loader.openTruckBed();

        getCapacityTo(loader, maxCapacaty);
        assertTrue(loader.isFull());

        getCapacityTo(loader, 0);
        assertFalse(loader.isFull());

        getCapacityTo(loader, maxCapacaty - 1);
        assertFalse(loader.isFull());

        getCapacityTo(loader, maxCapacaty / 2);
        assertFalse(loader.isFull());
    }

    @Test
    void isTruckBedOpen() {
        for (SemiTruck loader : loaders) {
            isTruckBedOpenSpecifik(loader);
        }

        for (CarFerry carFerry : carFerries) {
            isTruckBedOpenSpecifik(carFerry);
        }
    }

    private <V extends Vehicle & Loadables> void isTruckBedOpenSpecifik(V loader) {
        loader.openTruckBed();
        assertTrue(loader.isTruckBedOpen());

        loader.closeTruckBed();
        assertTrue(loader.isTruckBedClosed());
    }

    private <V extends Vehicle & Loadables> void getReadyToLoad(V loader) {
        loader.stopEngine();
        loader.openTruckBed();
    }

    private <V extends Vehicle & Loadables> void getCapacityTo(V loader, int num) {
        Vehicle car = createCar(loader.getX(), loader.getY(), 2000);
        do {
            if (loader.getAmountVehiclesLoaded() <= num)
                loader.loadVehicle(car);
            else
                loader.unLoadVehicle();
        } while (loader.getAmountVehiclesLoaded() != num);
    }

    private Vehicle createCar(double x, double y, int weight) {
        return new Volvo240(300, 30, Color.red, "Vrålåket", 4, x, y, Direction.DOWN, weight);
    }
}