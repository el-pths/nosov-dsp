package achain;

import achain.elems.*;

public class Main {

    public static void main(String... args) {
        Microphone mic = new Microphone();
        SineGenerator sin = new SineGenerator();
        WavPlayer wpl = new WavPlayer();
        //FileWriter writer = new FileWriter();
        Plotter plotter = new Plotter();
        //Repeater rep = new Repeater();
        //AmpMeter amp = new AmpMeter();
        Playback plb = new Playback();
        FuzzBox fuz = new FuzzBox();
        Delay del = new Delay();
        //Amplifer amp = new Amplifer();
        //sin.addFollower(fuz);
        wpl.addFollower(fuz);
        //mic.addFollower(fuz);
        fuz.addFollower(del);
        del.addFollower(plotter);
        //amp.addFollower(plotter);
        plotter.addFollower(plb);
        //writer.addFollower(plb);
        //rep.addFollower(amp);
        //sin.start();
        //mic.start();
        wpl.start();
    }

}
