package achain.elems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SineGenerator extends ChainElement implements Runnable{
	
	private int time = 100; 	
	private int amplitude = 10000; 
	private double gz = 440;
	
	public byte[] bytes;
	public int lastB = 0;

    public void run() {
    	bytes = new byte[(int) (SAMPLE_RATE * BYTES_PER_SAMPLE * time/1000)];
        byte[] b = new byte[BYTES_PER_SAMPLE];
        Timer timer = new Timer(time, new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					generateLine();
				}
        		}
        		);
        timer.start();
		generateLine();
        while (true) {
        	b = getBytes(BYTES_PER_SAMPLE);
            int value = b[1];
            value <<= 8;
            value += ((int) b[0]) & 0xFF;
            follower.nextValue(value);
        }
    }
    
    private void generateLine() {
	    for(int i = 0; i < (int) (SAMPLE_RATE * BYTES_PER_SAMPLE * time/1000); i += BYTES_PER_SAMPLE) {
	   		short count = (short) (Math.sin(i / (SAMPLE_RATE / gz) * 2.0 * Math.PI) * amplitude);
	   		bytes[i+1] = (byte) (count >> 8);
	    	bytes[i] = (byte) (count - bytes[i+1]);
	   	}
	}

	public void start() {
        new Thread(this).start();
    }

	private byte[] getBytes(int weight) {
		byte[] b = new byte[weight];
		for(int i = 0; i < weight; i++){
			b[i] = bytes[i + lastB];
		}
		lastB += weight;
		if(lastB >= bytes.length){
			lastB = 0;
		}
		return b;
	}

	public void nextValue(int value) {
        throw new UnsupportedOperationException("Change this text.");
	}
}
