import java.awt.*;

public class Scania extends Car{
    private int bedAngle;

    public Scania() {
        super(2, 100, Color.red, "Scania");
        bedAngle = 0;
    }

    @Override
    protected double speedFactor() {
        return 1;
    }

    public void raiseBed(int degrees){
        if (getCurrentSpeed() == 0){
            int newAngle = bedAngle + degrees;
            bedAngle = Math.min(newAngle, 70);
        }
        // Error otherwise?
    }


    public void lowerBed(int degrees){
        int newAngle = bedAngle - degrees;
        bedAngle = Math.max(newAngle, 0);
    }


    public int getBedAngle(){
        return bedAngle;
    }

    @Override
    public void move(){
        if (getBedAngle() == 0){
            super.move();
        }
    }


}
