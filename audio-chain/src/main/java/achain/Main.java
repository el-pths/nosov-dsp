package achain;

import achain.elems.*;

public class Main {

    public static void main(String... args) {
        //Microphone mic = new Microphone();
        SineGenerator sin = new SineGenerator();
        //FileWriter writer = new FileWriter();
        Plotter plotter = new Plotter();
        //Repeater rep = new Repeater();
        //AmpMeter amp = new AmpMeter();
        Playback plb = new Playback();
        FuzzBox fuz = new FuzzBox();
        sin.addFollower(fuz);
        fuz.addFollower(plotter);
        plotter.addFollower(plb);
        //writer.addFollower(plb);
        //rep.addFollower(amp);
        sin.start();
        //mic.start();
    }

}
