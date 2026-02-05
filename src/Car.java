import java.awt.*;

public abstract class Car extends VehicleShell implements Movable, Transportable {

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }


    @Override
    public void move() {
        x += Math.cos(Math.toRadians(angle)) * getCurrentSpeed();
        y += Math.sin(Math.toRadians(angle)) * getCurrentSpeed();
    }

    @Override
    public void turnLeft() {
        angle = (angle + 90) % 360;
    }

    @Override
    public void turnRight() {
        angle = (angle - 90) % 360;
    }

    // Transports a car with a vehicle
    public void transport(VehicleShell vehicle){
        x = vehicle.getX();
        y = vehicle.getY();
    }

    // Unloads a vehicle from another vehicle putting them 1 unit behind in the same direction
    public void unload(VehicleShell vehicle){
        transport(vehicle);
        angle = (vehicle.getAngle() - 180) % 360; // turn 180 degrees
        // move one step
        x += Math.cos(Math.toRadians(angle));
        y += Math.sin(Math.toRadians(angle));

        angle = (angle - 180) % 380; // Turn back
    }


}
