package achain.elems;

import java.util.List;
import java.util.ArrayList;

import achain.utils.WavWriter;

public class FileWriter extends ChainElement {
    
    static final int SECONDS = 5;
    
    List<Integer> samples = new ArrayList<>();
    
    public void nextValue(int value) {
        if (samples.size() < SAMPLE_RATE * SECONDS) {
            samples.add(value);
            if (samples.size() == SAMPLE_RATE * SECONDS) {
                new WavWriter().save("output.wav", SAMPLE_RATE, samples);
            }
        }
        follower.nextValue(value);
    }

}

