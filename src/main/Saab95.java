package main;

import java.awt.*;

public class Saab95 extends Vehicle {
    private boolean turboOn;

    public Saab95(double x, double y) {
        super(2, 125, Color.RED, "Saab95", 2, x, y);
        turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }


    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }
}
