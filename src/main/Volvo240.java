package main;

import java.awt.*;

public class Volvo240 extends Vehicle {
    public final static double trimFactor = 1.25;

    public Volvo240(double x, double y) {
        super(4, 100, Color.BLACK, "Volvo240", 2, x, y);
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}
