package achain.elems;

import java.awt.*;
import java.util.*;

import achain.utils.DrawingWindow;

public class Plotter extends ChainElement {

    private PlotterDrawing drawing;
    
    private int redrawIntervalSec = 1;
    
    private int scale = 1;
    
    private int[] values;
    
    private int curPos;
    
    private int max, min;
    
    private boolean dinamicalLimits = true;
    
    private int limits = 10000;

    public Plotter() {
        values = new int[redrawIntervalSec * SAMPLE_RATE];
        drawing = new PlotterDrawing();
    }
    
    private void reset() {
        curPos = 0;
        if(dinamicalLimits){
        	max = Integer.MIN_VALUE;
        	min = Integer.MAX_VALUE;
        }
    }

    public void nextValue(int value) {
        values[curPos] = value;
        if(dinamicalLimits){
        	min = Math.min(min, value);
        	max = Math.max(max, value);
        }else{
        	max = limits;
        	min = (-1)*limits;
        }
        curPos += 1;
        if (curPos == values.length) {
            drawing.max = max;
            drawing.min = min;
            drawing.data = Arrays.copyOf(values, values.length);
            drawing.redraw();
            reset();
        }
        follower.nextValue(value);
    }
    
    private class PlotterDrawing extends DrawingWindow {
        
        int max;
        int min;
        int[] data = new int[0];
        
        public void onRedraw(Graphics g, int w, int h) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);
            int i = data.length - 1;
            int zeroLine = calcScreenY(0, h);
            g.setColor(Color.GREEN);
            for (int x = w - 1; x >= 0; x--) {
                double avg = 0;
                for (int j = 0; j < scale; j++) {
                    avg += (i >= 0) ? data[i--] : 0;
                }
                avg /= scale;
                g.drawLine(x, zeroLine, x, calcScreenY(avg, h));
            }
            g.setColor(Color.RED);
            g.drawLine(0, zeroLine, w - 1, zeroLine);
            g.setColor(Color.YELLOW);
            g.drawString("Scale: " + scale, 0, zeroLine);
            g.drawString("Max: " + max, 0, 15);
            g.drawString("Min: " + min, 0, h);
        }
        
        private int calcScreenY(double y, int height) {
            double k = height / (max - min + 3.0);
            return (int) (height - (y - (min + 1.0)) * k);
        }
        
        public void onClick(int button, int x, int y) {
            scale = (int) (Math.pow(10, Math.log10(scale) + 0.51) + 0.5);
            if (scale > 35) {
                scale = 1;
            }
        }
    }
    
}

