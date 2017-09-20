package achain.elems;

import javax.sound.sampled.*;

public class Microphone extends ChainElement implements Runnable {
    
    private TargetDataLine line;

    public Microphone() {
        line = openTargetLine();
    }

    public void start() {
        new Thread(this).start();
    }

    TargetDataLine openTargetLine() {
        AudioFormat format = new AudioFormat(SAMPLE_RATE, BYTES_PER_SAMPLE * BITS_PER_BYTE, 1, true, false);
        TargetDataLine line;
        try {
            line = AudioSystem.getTargetDataLine(format);
            line.open();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        line.start();
        return line;
    }

    public void run() {
        byte[] b = new byte[BYTES_PER_SAMPLE];
        while (true) {
            line.read(b, 0, BYTES_PER_SAMPLE);
            int value = b[1];
            value <<= 8;
            value += ((int) b[0]) & 0xFF;
            follower.nextValue(value);
        }
    }

    public void nextValue(int value) {
        throw new UnsupportedOperationException("You can't send data to Microphone");
    }

}
