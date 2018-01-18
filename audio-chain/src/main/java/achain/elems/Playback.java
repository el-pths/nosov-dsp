package achain.elems;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Playback extends ChainElement {

    public SourceDataLine sdl;
    
    public Playback() {
        sdl = openSourceLine();
    }
    
    SourceDataLine openSourceLine() {
        try {
            AudioFormat format = new AudioFormat(
                    SAMPLE_RATE, BYTES_PER_SAMPLE * BITS_PER_BYTE, 1, true, false);
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            line.open();
            line.start();
            return line;
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Problem opening playback device", e);
        }
    }
    
    public void addFollower(ChainElement follower) {
        throw new UnsupportedOperationException("Playback couldn't have followers for now");
    }

    public void nextValue(int value) {
        byte[] data = new byte[BYTES_PER_SAMPLE];
        data[1] = (byte) ((value >> 8) & 0xFF);
        data[0] = (byte) (value & 0xFF);
        sdl.write(data, 0, BYTES_PER_SAMPLE);
    }

}

