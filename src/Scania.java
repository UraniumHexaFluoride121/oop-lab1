import java.awt.*;

public class Scania extends VehicleWithBed {
    private double angle = 0;

    public Scania() {
        super(2, 100, new Color(0x09, 0xCD, 0xDA), "Scania", 15);
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public boolean bedPositionBlocksDriving() {
        return Math.abs(angle) > 0.001;
    }

    @Override
    public void raise() {
        if (canBedMove())
            angle = Math.min(70, angle + 10);
    }

    @Override
    public void lower() {
        angle = Math.max(0, angle - 10);
    }
}
