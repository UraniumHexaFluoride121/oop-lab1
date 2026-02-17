package graphics;

import main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    ArrayList<Workshop<?>> workshops = new ArrayList<>();

    private static Rectangle bounds;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        cc.addCar("Volvo240", new Volvo240(0, 280, "1"));
        cc.addCar("Saab95", new Saab95(0, 100, "2"));
        cc.addCar("Scania", new Scania(0, 200, "3"));

        cc.addWorkshop("VolvoBrand", new Workshop<>(3, 20, 300, 300, Volvo240.class));

        // Start the timer
        cc.timer.start();

        bounds = new Rectangle(cc.frame.getSize());

    }

    private void addCar(String imageName, Vehicle vehicle) {
        cars.add(vehicle); // CarContltrllers lista.  "Säger till CarController att läga till den nya bil i sin lisat"
        frame.drawPanel.addCar(imageName, vehicle); // DrawPanel lista.  "Säger till DrawPanel att läga till den nya bil i sin lisat"
    }

    private void addWorkshop(String imageName, Workshop<?> workshop) {
        workshops.add(workshop); // CarContltrllers lista.  "Säger till CarController att läga till den nya bil i sin lisat"
        frame.drawPanel.addWorkshop(imageName, workshop); // DrawPanel lista.  "Säger till DrawPanel att läga till den nya bil i sin lisat"
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                Rectangle hitbox = frame.drawPanel.getHitbox(car);
                if (!bounds.contains(hitbox)) {
                    car.turnLeft();
                    car.turnLeft();
                    car.move();
                    x = (int) Math.round(car.getX());
                    y = (int) Math.round(car.getY());
                    car.stopEngine();
                    car.startEngine();
                }
                hitbox = frame.drawPanel.getHitbox(car);
                for (Workshop<?> workshop : workshops) {
                    if (frame.drawPanel.getHitbox(workshop).intersects(hitbox)) {
                        workshop.tryLoad(car);
                    }
                }

                frame.drawPanel.moveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }


    void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95 saab)
                saab.setTurboOn();
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95 saab)
                saab.setTurboOff();
        }
    }

    void raise() {
        for (Vehicle car : cars) {
            if (car instanceof Scania scania)
                scania.raise();
        }
    }

    void lower() {
        for (Vehicle car : cars) {
            if ( car instanceof  Scania scania)
                scania.lower();
        }
    }
}
