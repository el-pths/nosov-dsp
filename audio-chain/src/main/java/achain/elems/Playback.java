package achain.elems;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Playback extends ChainElement {

    private int buffer = 0;

    public void addFollower(ChainElement follower) {
        throw new UnsupportedOperationException("Playback couldn't have followers for now");
    }

    public void nextValue(int value) {
        buffer = value;
        dump();
    }

    public byte[] generateLine() {
        byte[] line = new byte[BYTES_PER_SAMPLE];
        line[1] = (byte) ((buffer >> 8) % 0xFF);
        line[0] = (byte) (buffer % 0xFF);
        return line;
    }

    void dump() {
        AudioFormat format = new AudioFormat(SAMPLE_RATE, BYTES_PER_SAMPLE * BITS_PER_BYTE, 1, true, false);
        try {
            SourceDataLine sdl = AudioSystem.getSourceDataLine(format);
            sdl.open();
            sdl.start();
            byte[] line = new byte[BYTES_PER_SAMPLE];
            line = generateLine();
            sdl.write(line, 0, line.length);
            sdl.drain();
            sdl.stop();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
