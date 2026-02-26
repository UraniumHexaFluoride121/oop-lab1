package main;

import mvc.CarController;
import mvc.CarModel;
import mvc.CarView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Instance of this class
        CarModel model = new CarModel();

        CarView view = new CarView("CarSim 1.0", CarModel.getWidth(), CarModel.getHeight());
        model.registerListener(view);

        // Start a new view and send a reference of self
        CarController controller = new CarController(model, view);
        controller.addButton("Add Car", e -> {
            model.addCar("Volvo240", CarFactory.volvo240(0, (float) (Math.random() * CarModel.getHeight() - 100), "test"));
        });
        controller.addButton("Remove Car", e -> {
            model.removeCar();
        });

        model.addCar("Volvo240", CarFactory.volvo240(0, 280, "1"));
        model.addCar("Saab95", CarFactory.saab95(0, 100, "2"));
        model.addCar("Scania", CarFactory.scania(0, 200, "3"));

        model.addWorkshop("VolvoBrand", new Workshop<>(3, 20, 300, 300, Volvo240.class));

        // Start the timer
        model.startTimer();


    }
}
