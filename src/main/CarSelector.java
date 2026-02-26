package main;

import mvc.CarController;
import mvc.CarModel;

import java.awt.*;
import java.awt.event.ItemEvent;

public class CarSelector {
    private int latestIndex = -1;

    public CarSelector(CarController controller, CarModel model) {
        controller.addButton("Add Car", e -> {
            if (latestIndex == -1)
                return;
            int index = latestIndex;
            if (index == 0) {
                index = (int) (Math.random() * 3 + 1);
            }
            float y = (float) (Math.random() * (CarModel.getHeight() - 100));
            switch (index) {
                case 1 -> model.addCar("Volvo240", CarFactory.volvo240(0, y, "test"));
                case 2 -> model.addCar("Saab95", CarFactory.saab95(0, y, "test"));
                case 3 -> model.addCar("Scania", CarFactory.scania(0, y, "test"));
            }
        });
        controller.addButton("Remove Car", e -> {
            model.removeCar();
        });
        List comp = new List();
        comp.add("Car");
        comp.add("Volvo");
        comp.add("Saab");
        comp.add("Scania");
        comp.addItemListener(l -> {
            if (l.getStateChange() == ItemEvent.SELECTED)
                latestIndex = (int) l.getItem();
        });
        controller.addComponent(comp);
    }
}
