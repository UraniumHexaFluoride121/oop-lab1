package mvc;

import main.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;


/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarModel {
    private static final int X = 800;
    private static final int Y = 800 - 240;
    private static final Rectangle bounds = new Rectangle(X, Y);

    // member fields:
    private final HashMap<HasPosition, HitboxData> hitboxData = new HashMap<>();
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // A list of cars, modify if needed
    private final ArrayList<Vehicle> cars = new ArrayList<>();
    private final ArrayList<Workshop<?>> workshops = new ArrayList<>();

    private final ArrayList<Consumer<ArrayList<DrawableData>>> listeners = new ArrayList<>();

    private <T> void addHitbox(String imageName, HasPosition obj) {
        BufferedImage image;
        try {
            image = ImageIO.read(CarView.class.getResourceAsStream("/pics/" + imageName + ".jpg"));
        } catch (IOException e) { // "catch (IOException e) " Om den inte hittar filen, thore Exaption. Typs som "raise error" i python
            throw new RuntimeException("Image not found: " + imageName);
        }
        hitboxData.put(obj, new HitboxData(image, new Dimension(image.getWidth(), image.getHeight())));
    }

    public void addCar(String imageName, Vehicle vehicle) {
        cars.add(vehicle); // CarContltrllers lista.  "Säger till CarController att läga till den nya bil i sin lisat"
        addHitbox(imageName, vehicle);
    }

    public void addWorkshop(String imageName, Workshop<?> workshop) {
        workshops.add(workshop); // CarContltrllers lista.  "Säger till CarController att läga till den nya bil i sin lisat"
        addHitbox(imageName, workshop);
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                Rectangle hitbox = getHitbox(car);
                if (!bounds.contains(hitbox)) {
                    car.turnLeft();
                    car.turnLeft();
                    car.move();
                    car.stopEngine();
                    car.startEngine();
                }
                hitbox = getHitbox(car);
                for (Workshop<?> workshop : workshops) {
                    if (getHitbox(workshop).intersects(hitbox)) {
                        workshop.tryLoad(car);
                    }
                }
            }
            // draw all objects
            notifyListeners();
        }
    }

    private void notifyListeners() {
        ArrayList<DrawableData> data = new ArrayList<>();
        for (Workshop<?> workshop : workshops) {
            Point p = new Point((int) workshop.getX(), (int) workshop.getY());
            data.add(new DrawableData(p, hitboxData.get(workshop).image));
        }
        for (Vehicle car : cars) {
            Point p = new Point((int) car.getX(), (int) car.getY());
            data.add(new DrawableData(p, hitboxData.get(car).image));
        }
        for (Consumer<ArrayList<DrawableData>> listener : listeners) {
            listener.accept(data);
        }
    }

    public void startTimer() {
        timer.start();
    }

    public void registerListener(Consumer<ArrayList<DrawableData>> listener) {
        listeners.add(listener);
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    public void startEngine() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    public void stopEngine() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    public void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95 saab)
                saab.setTurboOn();
        }
    }

    public void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95 saab)
                saab.setTurboOff();
        }
    }

    public void raise() {
        for (Vehicle car : cars) {
            if (car instanceof Scania scania)
                scania.raise();
        }
    }

    public void lower() {
        for (Vehicle car : cars) {
            if (car instanceof Scania scania)
                scania.lower();
        }
    }

    private Rectangle getHitbox(HasPosition vehicle) {
        return new Rectangle(new Point((int) vehicle.getX(), (int) vehicle.getY()), hitboxData.get(vehicle).size());
    }

    private record HitboxData(BufferedImage image, Dimension size) {

    }

    public static int getWidth() {
        return bounds.width;
    }

    public static int getHeight() {
        return bounds.height;
    }
}
