package main;

import java.awt.*;

public class VolvoFL6 extends VehicleWithBed implements IStorage<Vehicle> {
    private boolean isRaised = true;
    private final Storage<Vehicle> storage = new Storage<>(2, 2.5, this::getX, this::getY);

    public VolvoFL6(double x, double y) {
        super(2, 102.7, new Color(88, 116, 209), "VolvoFL6", 12, x, y);
    }

    @Override
    public boolean bedPositionBlocksDriving() {
        return !isRaised;
    }

    @Override
    public void raise() {
        isRaised = true;
    }

    @Override
    public void lower() {
        if (canBedMove())
            isRaised = false;
    }

    @Override
    public void load(Vehicle t) {
        if (isRaised)
            return;
        storage.load(t);
    }

    @Override
    public void unload() {
        if (isRaised)
            return;
        storage.unload();
    }
}
