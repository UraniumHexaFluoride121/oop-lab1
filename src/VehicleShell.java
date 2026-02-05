import java.awt.*;

public abstract class VehicleShell {
    private final int nrDoors; // Number of doors on the car
    private Color color; // Color of the car
    public final String modelName; // The car model name
    protected final double enginePower;
    private double currentSpeed = 0;
    protected double angle = 0, x = 0, y = 0;

    public VehicleShell(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.modelName = modelName;
        this.enginePower = enginePower;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle(){
        return angle;
    }

    public double getEnginePower(){
        return this.enginePower;
    }

    public double getCurrentSpeed(){
        return this.currentSpeed;
    }

    protected abstract double speedFactor();

    // setters ish for engine management


    public void startEngine() {
        currentSpeed = 0.1;
    }


    public void stopEngine() {
        currentSpeed = 0;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(enginePower, currentSpeed + speedFactor() * amount);
    }


    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(currentSpeed - speedFactor() * amount, 0);
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException();
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1)
            throw new IllegalArgumentException();
        decrementSpeed(amount);
    }

}
