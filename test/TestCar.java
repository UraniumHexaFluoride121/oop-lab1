import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class TestCar {
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;
    private VolvoFL6 volvoTruck;

    @Before
    public void init() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        volvoTruck = new VolvoFL6();
    }

    @Test
    public void testDoors() {
        assertEquals(2, saab.getNrDoors());
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void testColor() {
        assertEquals(Color.RED, saab.getColor());
        assertEquals(Color.BLACK, volvo.getColor());

        saab.setColor(Color.BLUE);
        volvo.setColor(Color.BLUE);
        assertEquals(Color.BLUE, saab.getColor());
        assertEquals(Color.BLUE, volvo.getColor());
    }

    @Test
    public void testEnginePower() {
        assertEquals(100, volvo.getEnginePower(), 0.00001);
        assertEquals(125, saab.getEnginePower(), 0.00001);
    }

    @Test
    public void testSpeed() {
        assertEquals(0, saab.getCurrentSpeed(), 0.00001);
        assertEquals(0, volvo.getCurrentSpeed(), 0.00001);

        saab.startEngine();
        volvo.startEngine();
        assertNotEquals(0, saab.getCurrentSpeed(), 0.00001);
        assertNotEquals(0, volvo.getCurrentSpeed(), 0.00001);

        saab.stopEngine();
        volvo.stopEngine();
        assertEquals(0, saab.getCurrentSpeed(), 0.00001);
        assertEquals(0, volvo.getCurrentSpeed(), 0.00001);
    }

    @Test
    public void testTurbo() {
        saab.setTurboOff();
        double speedFactorNoTurbo = saab.speedFactor();
        saab.setTurboOn();
        assertNotEquals(speedFactorNoTurbo, saab.speedFactor());
        saab.setTurboOff();
        assertEquals(speedFactorNoTurbo, saab.speedFactor(), 0.00001);
    }

    @Test
    public void testGas() {
        saab.startEngine();
        double currentSpeed = saab.getCurrentSpeed();
        saab.gas(0.3);
        assertTrue(currentSpeed < saab.getCurrentSpeed());
        volvo.startEngine();
        currentSpeed = volvo.getCurrentSpeed();
        volvo.gas(0.3);
        assertTrue(currentSpeed < volvo.getCurrentSpeed());

    }

    @Test
    public void testBrake() {
        volvo.startEngine();
        saab.startEngine();
        double currentVolvoSpeed = volvo.getCurrentSpeed();
        double currentSaabSpeed = saab.getCurrentSpeed();
        volvo.brake(0.2);
        saab.brake(0.2);
        assertTrue(currentVolvoSpeed > volvo.getCurrentSpeed());
        assertTrue(currentSaabSpeed > saab.getCurrentSpeed());
    }

    @Test
    public void testBed(){
        scania.startEngine();
        scania.raiseBed(20);
        assertEquals(0, scania.getBedAngle());

        scania.stopEngine();
        scania.raiseBed(20);
        assertEquals(20, scania.getBedAngle());

        scania.lowerBed(30);
        assertEquals(0, scania.getBedAngle());
    }


    @Test
    public void testMove() {
        testPosition(equal(0), equal(0)); // Testa båda bilar x=0 y=0

        saab.move();
        volvo.move();
        testPosition(equal(0), equal(0)); // Ska inte röra sig om motorn är av

        saab.startEngine();
        volvo.startEngine();

        saab.move();
        volvo.move();
        testPosition(notEqual(0), equal(0)); // Ska ha rört sig i x led men ej y led

        saab.turnLeft();
        volvo.turnRight();
        saab.move();
        volvo.move();
        testPosition(notEqual(0), notEqual(0)); // Ska ha rört sig i både x- och y-led
    }


    @Test
    public void transportCar(){
        volvoTruck.lowerBed();
        volvoTruck.loadCar(saab);
        volvoTruck.raiseBed();
        volvoTruck.startEngine();
        volvoTruck.gas(1);

        // drive an arbitrary distance
        volvoTruck.move();
        volvoTruck.turnLeft();
        volvoTruck.move();
        volvoTruck.move();

        assertEquals(volvoTruck.getX(), saab.getX(), 0.00001);
        assertEquals(volvoTruck.getY(), saab.getY(), 0.00001);

        volvoTruck.stopEngine();
        volvoTruck.lowerBed();
        volvoTruck.releaseCar();
        volvoTruck.raiseBed();
        volvoTruck.startEngine();
        
        volvoTruck.gas(1);
        volvoTruck.move();
        volvoTruck.turnLeft();
        volvoTruck.move();
        volvoTruck.move();

        assertNotEquals(volvoTruck.getX(), saab.getX(), 0.00001);
        assertNotEquals(volvoTruck.getY(), saab.getY(), 0.00001);

    }


    private void testPosition(Predicate<Double> xCondition, Predicate<Double> yCondition) {
        assertTrue(xCondition.test(saab.getX()));
        assertTrue(xCondition.test(volvo.getX()));
        assertTrue(yCondition.test(saab.getY()));
        assertTrue(yCondition.test(volvo.getY()));
    }

    private static Predicate<Double> equal(double value) {
        return v -> Math.abs(v - value) < 0.0001;
    }

    private static Predicate<Double> notEqual(double value) {
        return equal(value).negate();
    }
}
