import java.awt.*;

public class Saab95 extends Car {
    private boolean turboOn;

    public Saab95() {
        super(2, 125, Color.RED, "Saab95");
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(enginePower, getCurrentSpeed() + speedFactor() * amount);
    }

    @Override
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(0, getCurrentSpeed() - speedFactor() * amount);
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }
}
