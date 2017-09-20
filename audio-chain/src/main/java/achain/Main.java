package achain;

import achain.elems.*;

public class Main {

    public static void main(String... args) {
        Microphone mic = new Microphone();
        Repeater rep = new Repeater();
        AmpMeter amp = new AmpMeter();
        mic.addFollower(rep);
        rep.addFollower(amp);
        mic.start();
    }

}
