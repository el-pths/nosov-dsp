package achain.utils;

import java.awt.*;
import javax.swing.*;

public class DrawingWindow {

    private JFrame frame;
    private JPanel panel;

    public DrawingWindow() {
        frame = new JFrame("Sound Plotter");
        panel = new JPanel() {
            public void paint(Graphics g) {
                onRedraw(g, getWidth(), getHeight());
            }
        };
        frame.getContentPane().add(panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
    
    public void redraw() {
        panel.invalidate();
        panel.repaint();
    }
    
    public void onRedraw(Graphics g, int w, int h) {
        g.drawLine(0, 0, w, h);
    }

}

