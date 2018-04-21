package achain.elems;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class WavPlayer extends ChainElement implements Runnable {
    
    private AudioInputStream line;

    public WavPlayer() {
        line = openInputStream();
    }

    public void start() {
        new Thread(this).start();
    }

    AudioInputStream openInputStream() {
        AudioInputStream line = null;
        try {
        	File file = new File("wolf1.wav");
            line = AudioSystem.getAudioInputStream(file);
            AudioFileFormat format = AudioSystem.getAudioFileFormat(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception");
        }
        return line;
    }

    public void run() {
        byte[] b = new byte[BYTES_PER_SAMPLE];
        while (true) {
            try {
				line.read(b, 0, BYTES_PER_SAMPLE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            int value = b[1];
            value <<= 8;
            value += ((int) b[0]) & 0xFF;
            follower.nextValue(value);
        }
    }

    public void nextValue(int value) {
        throw new UnsupportedOperationException("You can't send data to WavPlayer");
    }

}
