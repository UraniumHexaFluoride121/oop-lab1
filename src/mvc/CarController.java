package mvc;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

import static mvc.CarModel.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarController {

    // The controller member
    CarModel carModel;

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    private int index = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarController(CarModel cc, CarView view) {
        this.carModel = cc;
        initComponents(view);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(CarView drawPanel) {
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        drawPanel.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, Integer.MAX_VALUE));

        addButton("Gas", e -> carModel.gas(gasAmount));
        addButton("Saab Turbo on", e -> carModel.turboOn());
        addButton("Scania Lift Bed", e -> carModel.raise());
        addButton("Break", e -> carModel.brake(gasAmount));
        addButton("Saab Turbo off", e -> carModel.turboOff());
        addButton("Lower Lift Bed", e -> carModel.lower());

        controlPanel.setPreferredSize(new Dimension((CarModel.getWidth() / 2) + 4, 200));
        drawPanel.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(CarModel.getWidth() / 5 - 15, 200));
        drawPanel.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(CarModel.getWidth() / 5 - 15, 200));
        drawPanel.add(stopButton);

        // Make the frame pack all it's components by respecting the sizes if possible.
        drawPanel.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        drawPanel.setLocation(dim.width / 2 - drawPanel.getSize().width / 2, dim.height / 2 - drawPanel.getSize().height / 2);
        // Make the frame visible
        drawPanel.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        drawPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        controlPanel.add(button, index++);
        button.addActionListener(action);
    }
}