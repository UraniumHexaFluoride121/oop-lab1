import java.awt.*;
import java.util.Stack;

public class VolvoFL6 extends Truck implements CarTransport{
    private final int maxCars = 4;
    private int nrOfCars = 0;
    private final Stack<Transportable> loadedCars;

    public VolvoFL6() {
        super(2, 100, Color.blue, "VolvoFL6");
        this.loadedCars = new Stack<Transportable>();
    }

    @Override
    protected double speedFactor() {
        return getEnginePower()*0.01;
    }

    public void raiseBed() {
        if (getCurrentSpeed() == 0){
            setBedAngle(0);
        }
    }

    public void lowerBed() {
        if (getCurrentSpeed() == 0) {
            setBedAngle(1);
        }
    }

    public Stack<Transportable> getLoadedCars(){
        return loadedCars;
    }

    @Override
    public void loadCar(Transportable car){
        if (getBedAngle() == 1 && (Math.abs(getX() - car.getX()) < 10 && (Math.abs(getY() - car.getY()) < 10))){
            if (nrOfCars < maxCars){
                loadedCars.push(car);
                nrOfCars++;
            }
        }
    }

    @Override
    public void releaseCar(){
        if (nrOfCars > 0 && getBedAngle() == 1){
            Transportable car = loadedCars.pop();
            nrOfCars--;
            car.unload(this);
        }
        else{ // proper error probably needed
            System.out.println("no");
        }

    }

    @Override
    public void move(){
        if (getBedAngle() == 0){
            super.move();
            for(Transportable car : loadedCars){
                car.transport(this);
            }
        }
    }


}
