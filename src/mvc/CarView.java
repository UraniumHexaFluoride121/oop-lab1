package mvc;

import main.DrawableData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

// This panel represents the animated part of the view with the car images.

public class CarView extends JFrame implements Consumer<ArrayList<DrawableData>> {
    private ArrayList<DrawableData> data = new ArrayList<>();
    private final JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (DrawableData data : data) {
                g.drawImage(data.image(), data.pos().x, data.pos().y, null);
            }
        }
    };

    // Initializes the panel and reads the images
    public CarView(String title, int x, int y) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(x, y + 240));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(panel);
        panel.setDoubleBuffered(true);
        panel.setPreferredSize(new Dimension(x, y));
        panel.setBackground(Color.green);
    }

    @Override
    public void accept(ArrayList<DrawableData> data) {
        this.data = data;
        repaint();
    }
}
