import java.awt.*;

public abstract class Truck extends VehicleShell implements Movable{
    private int bedAngle = 0;

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    public int getBedAngle(){
        return bedAngle;
    }

    public void setBedAngle(int angle){
        bedAngle = angle;
    }


    @Override
    public void move() {
        if (bedAngle == 0){
            x += Math.cos(Math.toRadians(angle)) * getCurrentSpeed();
            y += Math.sin(Math.toRadians(angle)) * getCurrentSpeed();
        }
    }

    @Override
    public void turnLeft() {
        angle = (angle + 90) % 360;
    }

    @Override
    public void turnRight() {
        angle = (angle - 90) % 360;
    }
}
