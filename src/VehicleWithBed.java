import java.awt.*;

public abstract class VehicleWithBed extends Vehicle implements HasBed {
    public VehicleWithBed(int nrDoors, double enginePower, Color color, String modelName, double weight) {
        super(nrDoors, enginePower, color, modelName, weight);
    }

    /**
     * Defines whether the bed's current position prevents this vehicle from driving.
     * If this method returns true, calling {@code move()} should have no effect.
     */
    abstract boolean bedPositionBlocksDriving();


    @Override
    public void move() {
        if (!bedPositionBlocksDriving())
            super.move();
    }

    @Override
    public boolean canBedMove() {
        return Math.abs(getCurrentSpeed()) < 0.001;
    }
}
