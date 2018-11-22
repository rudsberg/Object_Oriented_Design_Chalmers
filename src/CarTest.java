import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Random rand = new Random();
    List<Car> cars = new ArrayList<>(
            Arrays.asList(
                    new Volvo240(300, 40, Color.red, "Volvo v70", 4, 0, 0, Direction.DOWN, 2000),
                    new Saab95(240, 70, Color.blue, "Min moderna saab", 6, 100, 50, Direction.RIGHT, 1500),
                    new Scania(400, 60, Color.blue, "Min moderna scania", 2, 50, 50, Direction.RIGHT, 20000)
            ));
    private final static int CARS_TO_TEST = 10_000;

    CarTest() {
        genCars(CARS_TO_TEST);
    }

    private void genCars(int amount) {
        for (int i = 0; i < amount; i++) {
            Car cRef = cars.get(i % cars.size());

            if (cRef instanceof Volvo240) {
                cars.add(new Volvo240(genEnginePower(), genCurrentSpeed(), Color.red, "", genNumDoors(), genX(), genY(), genDir(Direction.class), 2000));
            } else if (cRef instanceof Saab95) {
                cars.add(new Saab95(genEnginePower(), genCurrentSpeed(), Color.red, "", genNumDoors(), genX(), genY(), genDir(Direction.class), 2100));
            } else {
                cars.add(new Volvo240(genEnginePower(), genCurrentSpeed(), Color.red, "", genNumDoors(), genX(), genY(), genDir(Direction.class), 20000));
            }
        }
    }

    private int genEnginePower() {
        return rand.nextInt(400) + 50;
    }

    private int genCurrentSpeed() {
        return rand.nextInt(110);
    }

    private int genNumDoors() {
        return rand.nextInt(4) + 2;
    }

    private int genX() {
        return rand.nextInt(300);
    }

    private int genY() {
        return rand.nextInt(300);
    }

    public <T extends Enum<?>> T genDir(Class<T> clazz){
        int x = rand.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    @org.junit.jupiter.api.Test
    void move() {
        for (Car c : cars) {
            c.gas(1);
            c.move();
            double initX = c.getX();
            double initY = c.getY();
            double speed = c.getCurrentSpeed();
            double marginError = 0.0001;

            c.setDirection(Direction.RIGHT);
            c.move();
            assertTrue(initX + speed - c.getX() < marginError);
            assertTrue(initX + speed - c.getX() < marginError);
            assertTrue(initY - c.getY() < marginError);

            c.setDirection(Direction.DOWN);
            c.move();
            assertTrue(initX + speed - c.getX() < marginError);
            assertTrue(initY + speed - c.getY() < marginError);

            c.setDirection(Direction.LEFT);
            c.move();
            assertTrue(initX - c.getX() < marginError);
            assertTrue(initY + speed - c.getY() < marginError);

            c.setDirection(Direction.UP);
            c.move();
            assertTrue(initX - c.getX() < marginError);
            assertTrue(initY - c.getY() < marginError);
        }
    }

    @org.junit.jupiter.api.Test
    void turnLeft() {
        for (Car c : cars) {
            c.setDirection(Direction.UP);
            c.turnLeft();
            assertEquals(Direction.LEFT, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.DOWN, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.RIGHT, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.UP, c.getDirection());
        }
    }

    @org.junit.jupiter.api.Test
    void turnRight() {
        for (Car c : cars) {
            c.setDirection(Direction.UP);
            c.turnRight();
            assertEquals(Direction.RIGHT, c.getDirection());
            c.turnRight();
            assertEquals(Direction.DOWN, c.getDirection());
            c.turnRight();
            assertEquals(Direction.LEFT, c.getDirection());
            c.turnRight();
            assertEquals(Direction.UP, c.getDirection());
        }
    }

    @Test
    void turning() {
        for (Car c : cars) {
            c.setDirection(Direction.DOWN);
            c.turnRight();
            assertEquals(Direction.LEFT, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.DOWN, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.RIGHT, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.UP, c.getDirection());
            c.turnLeft();
            assertEquals(Direction.LEFT, c.getDirection());
            c.turnRight();
            assertEquals(Direction.UP, c.getDirection());
        }
    }


    @org.junit.jupiter.api.Test
    void startEngine() {
        for (Car c : cars) {
            c.startEngine();
            assertEquals(0.1, c.getCurrentSpeed());
        }
    }

    @org.junit.jupiter.api.Test
    void stopEngine() {
        for (Car c : cars) {
            c.stopEngine();
            assertEquals(0, c.getCurrentSpeed());
        }
    }

    @org.junit.jupiter.api.Test
    void gas() {
        for (Car c : cars) {
            double middleGas = c.getMaxSpeedChange() / 2;
            double middleSpeed = c.getEnginePower() / 2;
            double tempSpeed;
            double minValue = 0.01;

            c.gas(c.getMaxSpeedChange() + 1);
            assertEquals(c.getCurrentSpeed(), c.getCurrentSpeed());

            c.gas(-0.1);
            assertEquals(c.getCurrentSpeed(), c.getCurrentSpeed());

            tempSpeed = TestHelperFunctions.getSpeedTo(c, middleSpeed);
            c.gas(middleGas);
            assertEquals(tempSpeed + c.speedFactor() * middleGas, c.getCurrentSpeed());

            TestHelperFunctions.getSpeedTo(c, c.getEnginePower() - minValue);
            c.gas(c.getMaxSpeedChange());
            assertEquals(c.getEnginePower(), c.getCurrentSpeed());
        }
    }

    @org.junit.jupiter.api.Test
    void brake() {
        for (Car c : cars) {
            double middleBreak = c.getMaxSpeedChange() / 2;
            double middleSpeed = c.getEnginePower() / 2;
            double tempSpeed;
            double minValue = 0.01;

            c.brake(c.getMaxSpeedChange() + 1);
            assertEquals(c.getCurrentSpeed(), c.getCurrentSpeed());

            c.brake(-0.1);
            assertEquals(c.getCurrentSpeed(), c.getCurrentSpeed());

            tempSpeed = TestHelperFunctions.getSpeedTo(c, middleSpeed);
            c.brake(middleBreak);
            assertEquals(tempSpeed - c.speedFactor() * middleBreak, c.getCurrentSpeed());

            TestHelperFunctions.getSpeedTo(c, minValue);
            c.brake(middleBreak);
            assertEquals(c.getCurrentSpeed(), c.getCurrentSpeed());
        }
    }
}