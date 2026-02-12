package graphics;

import main.Vehicle;
import main.Workshop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    HashMap<Vehicle, DrawableData> cars = new HashMap<>();
    HashMap<Workshop<?>, DrawableData> workshops = new HashMap<>();

    void moveit(int x, int y, Vehicle drawable) {
        cars.get(drawable).pos.x = x;
        cars.get(drawable).pos.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    void addCar(String imageName, Vehicle vehicle) {
        addDrawable(imageName, vehicle, new Point((int) vehicle.getX(), (int) vehicle.getY()), cars);
    }

    void addWorkshop(String imageName, Workshop<?> workshop) {
        addDrawable(imageName, workshop, new Point((int) workshop.getX(), (int) workshop.getY()), workshops);
    }

    private <T> void addDrawable(String imageName, T drawable, Point point, HashMap<T, DrawableData> map) {
        BufferedImage image;
        try {
            image = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/" + imageName + ".jpg"));
        } catch (IOException e) { // "catch (IOException e) " Om den inte hittar filen, thore Exaption. Typs som "raise error" i python
            throw new RuntimeException("Image not found: " + imageName);
        }
        map.put(drawable, new DrawableData(image, point, new Dimension(image.getWidth(), image.getHeight())));
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g, workshops.values());
        draw(g, cars.values());
    }

    private void draw(Graphics g, Collection<DrawableData> drawables) {
        for (DrawableData data : drawables) {
            g.drawImage(data.image(), data.pos().x, data.pos().y, null);
        }
    }

    Rectangle getHitbox(Vehicle vehicle) {
        return new Rectangle(new Point((int) vehicle.getX(), (int) vehicle.getY()), cars.get(vehicle).size());
    }

    Rectangle getHitbox(Workshop<?> workshop) {
        return new Rectangle(workshops.get(workshop).pos(), workshops.get(workshop).size());
    }

    private record DrawableData(BufferedImage image, Point pos, Dimension size) {

    }
}
