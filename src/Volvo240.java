import java.awt.*;

public class Volvo240 extends Car {
    public final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, Color.BLACK, "Volvo240");
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }

    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    @Override
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}
