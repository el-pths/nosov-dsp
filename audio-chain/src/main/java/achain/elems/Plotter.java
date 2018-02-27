package achain.elems;

import java.awt.*;

import achain.utils.DrawingWindow;

public class Plotter extends ChainElement {

    private DrawingWindow drawing;
    
    private int redrawIntervalSec = 1;
    
    private int scale = 10;
    
    private int[] values;
    
    private int curPos;
    
    private int max, min;

    public Plotter() {
        values = new int[redrawIntervalSec * SAMPLE_RATE];
        drawing = new PlotterDrawing();
    }
    
    private void reset() {
        curPos = 0;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public void nextValue(int value) {
        values[curPos] = value;
        if (value < min) {
            min = value;
            System.out.printf("min: %d%n", min);
        }
        if (value > max) {
            max = value;
            System.out.printf("max: %d%n", max);
        }
        curPos += 1;
        if (curPos == values.length) {
            drawing.redraw();
            reset();
        }
        follower.nextValue(value);
    }
    
    private class PlotterDrawing extends DrawingWindow {

        public void onRedraw(Graphics g, int w, int h) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);
            int i = 0;
            int zeroLine = calcScreenY(0, h);
            /*
            g.setColor(Color.GREEN);
            for (int x = w - 1; x >= 0; x--) {
                double avg = 0;
                for (int j = 0; j < scale; j++) {
                    avg += (i >= 0) ? values[i--] : 0;
                }
                avg /= scale;
                g.drawLine(x, zeroLine, x, calcScreenY(avg, h));
            }
            */
            System.out.printf("%d %d %d%n", min, max, zeroLine);
            g.setColor(Color.RED);
            g.drawLine(0, zeroLine, w - 1, zeroLine);
        }
        
        private int calcScreenY(double y, int height) {
            double k = height / (max - min + 3);
            return (int) (height - (y - (min + 1)) * k);
        }

    }
    
}

