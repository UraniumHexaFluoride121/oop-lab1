import java.awt.*;
import java.util.Objects;
import java.util.Stack;

public class VolvoFL6 extends Car{
    private int bedAngle;
    private final int maxCars = 4;
    private int nrOfCars = 0;
    private Stack<Car> loadedCars;

    public VolvoFL6() {
        super(2, 100, Color.blue, "VolvoFL6");
        bedAngle = 0;
        Stack<Car> loadedCars = new Stack<Car>();
    }

    @Override
    protected double speedFactor() {
        return 1;
    }

    public void raiseBed() {
        if (getCurrentSpeed() == 0){
            bedAngle = 70;
        }
    }

    public void lowerBed() {
        bedAngle = 0;
    }

    public int getBedAngle() {
        return bedAngle;
    }

    public void loadCar(Car car){
        if (bedAngle == 0 && (Math.abs(getX() - car.getX()) < 10 && (Math.abs(getY() - car.getY()) < 10 && !Objects.equals(car.modelName, "VolvoFL6")))){
            if (nrOfCars < maxCars){
                loadedCars.push(car);
                nrOfCars++;
            }
        }
    }

    public void releaseCar(Car car){

    }

    @Override
    public void move(){
        if (bedAngle == 0){
            super.move();
            for(Car car : loadedCars){
                car.move();
            }
        }
    }


}
