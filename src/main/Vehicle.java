package main;

import java.awt.*;

public abstract class Vehicle implements Movable, Storable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public final String modelName; // The car model name
    private double angle = 0, x, y;
    private IStorage<?> storage = null;
    private final double weight;
    private final String id;

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName, double weight, double x, double y, String licensePlate) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.id = licensePlate;
        stopEngine();
    }

    @Override
    public String getID() {return id;}

    @Override
    public double getWeight() {
        return weight;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(enginePower, getCurrentSpeed() + speedFactor() * amount);
    }

    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Any change in speed should be multiplied by the scaling factor provided by this method.
     */
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    public double getX() {
        if (isBeingStored())
            return storage.getX();
        return x;
    }

    public double getY() {
        if (isBeingStored())
            return storage.getY();
        return y;
    }

    /**
     * Tries to increase the speed of this vehicle.
     * @param amount The amount to gas
     * @throws IllegalArgumentException If {@code amount} is less than zero or more than one
     */
    public void gas(double amount) {
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException();
        incrementSpeed(amount);
    }

    /**
     * Tries to decrease the speed of this vehicle. This method will never drop the speed below zero.
     * @param amount The amount to brake
     * @throws IllegalArgumentException If {@code amount} is less than zero or more than one
     */
    public void brake(double amount) {
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException();
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        x += Math.cos(Math.toRadians(angle)) * currentSpeed;
        y += Math.sin(Math.toRadians(angle)) * currentSpeed;
    }

    @Override
    public void turnLeft() {
        angle = (angle + 90) % 360;
    }

    @Override
    public void turnRight() {
        angle = (angle - 90) % 360;
    }

    @Override
    public void store(IStorage<?> t) {
        storage = t;
    }

    @Override
    public void remove(IStorage<?> t) {
        x = getX() - 1;
        y = getY();
        storage = null;
    }

    @Override
    public boolean isBeingStored() {
        return storage != null;
    }
}
