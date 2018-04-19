package achain.elems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SineGenerator extends ChainElement implements Runnable {

	private int time = 100;
	private double counter = 0;
	private int amplitude = 15000;
	private double gz = 220;
	private byte flag = 1; // flag to inform the programm about aplitude change
							// direction

	public byte[] bytes;
	public int lastB = 0;

	public void run() {
		bytes = new byte[(int) (SAMPLE_RATE * BYTES_PER_SAMPLE * time / 1000)];
		byte[] b = new byte[BYTES_PER_SAMPLE];
		Timer timer = new Timer(time, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (counter > 14) {
					flag = 1;
				}
				if (counter < 0.2) {
					flag = 0;
				}
				if (flag == 0) {
					counter += 0.2;
				}
				if (flag == 1) {
					counter -= 0.2;
				}
				generateLine(counter);
			}
		});
		timer.start();
		generateLine(counter);
		while (true) {
			b = getBytes(BYTES_PER_SAMPLE);
			int value = b[1];
			value <<= 8;
			value += ((int) b[0]) & 0xFF;
			follower.nextValue(value);
		}
	}

	private void generateLine(double counter) {
		for (int i = 0; i < (int) (SAMPLE_RATE * BYTES_PER_SAMPLE * time / 1000); i += BYTES_PER_SAMPLE) {
			short count = (short) (Math.sin(i / (SAMPLE_RATE / gz) * 2.0 * Math.PI)
					* (amplitude - (counter * (SAMPLE_RATE * BYTES_PER_SAMPLE * time / 1000) + i) / 8));

			bytes[i + 1] = (byte) (count >> 8);
			bytes[i] = (byte) (count - bytes[i + 1]);
		}
	}

	public void start() {
		new Thread(this).start();
	}

	private byte[] getBytes(int weight) {
		byte[] b = new byte[weight];
		for (int i = 0; i < weight; i++) {
			b[i] = bytes[i + lastB];
		}
		lastB += weight;
		if (lastB >= bytes.length) {
			lastB = 0;
		}
		return b;
	}

	public void nextValue(int value) {
		throw new UnsupportedOperationException("Generator needs not any next value!");
	}
}