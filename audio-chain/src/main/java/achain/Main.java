package achain;

import achain.elems.*;

public class Main {

    public static void main(String... args) {
        Microphone mic = new Microphone();
        SineGenerator sin = new SineGenerator();
        Repeater rep = new Repeater();
        AmpMeter amp = new AmpMeter();
        Playback plb = new Playback();
        sin.addFollower(rep);
        rep.addFollower(plb);
        //rep.addFollower(amp);
        sin.start();
        //mic.start();
    }

}
