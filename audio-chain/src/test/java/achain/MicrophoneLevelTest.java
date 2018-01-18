package achain;

import achain.elems.*;
import org.junit.*;

public class MicrophoneLevelTest {

    @Test
    public void micLevelTest() throws InterruptedException {
        Microphone mic = new Microphone();
        AmpMeter amp = new AmpMeter();
        mic.addFollower(amp);
        mic.start();
        Thread.sleep(10000);
    }

}

