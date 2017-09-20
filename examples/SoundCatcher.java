import java.util.*;
import javax.sound.sampled.*;

public class SoundCatcher {
    
    private int sampleRate = 22050;
    private int amplitude = 125;
    private int baseFreq = 220;
    
    TargetDataLine openTargetLine() {
        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
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
    
    double calcAverage(byte[] b) {
        double sum = 0;
        for (byte x : b) {
            sum += x;
        }
        return sum / b.length;
    }
    
    double calcAmplitude(byte[] b, double avg) {
        double sum = 0;
        for (byte x : b) {
            sum += Math.pow(x - avg, 2);
        }
        return Math.sqrt(sum / b.length);
    }
    
    void record() {
        TargetDataLine line = openTargetLine();
        byte[] b = new byte[sampleRate];
        System.out.println("Use Ctrl-C to stop");
        while (true) {
            System.out.print("reading 1 second... ");
            line.read(b, 0, b.length);
            System.out.print("done... ");
            double avg = calcAverage(b);
            double amplitude = calcAmplitude(b, avg);
            System.out.printf("Average=%6.1f, Amplitude=%6.1f%n", avg, amplitude);
        }
    }
    
    public static void main(String... args) {
        new SoundCatcher().record();
    }
    
}
