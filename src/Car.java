import java.awt.*;

public abstract class Car implements Movable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public final String modelName; // The car model name
    private double angle = 0, x = 0, y = 0;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
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
    protected abstract double speedFactor();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException();
        incrementSpeed(amount);
    }

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

}
