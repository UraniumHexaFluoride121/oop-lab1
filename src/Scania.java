import java.awt.*;

public class Scania extends Truck{

    public Scania() {
        super(2, 100, Color.red, "Scania");
    }

    @Override
    protected double speedFactor() {
        return 1;
    }

    public void raiseBed(int degrees){
        if (getCurrentSpeed() == 0){
            int newAngle = getBedAngle() + degrees;
            setBedAngle(Math.min(newAngle, 70));
        }
        // Error otherwise?
    }

    public void lowerBed(int degrees){
        int newAngle = getBedAngle() - degrees;
        setBedAngle(Math.max(newAngle, 0));
    }




}
