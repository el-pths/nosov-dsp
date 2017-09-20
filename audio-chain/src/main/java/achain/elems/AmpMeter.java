package achain.elems;

public class AmpMeter extends ChainElement {
    
    private int count = 0;

    private int[] buffer = new int[SAMPLE_RATE];

    public void addFollower(ChainElement follower) {
        throw new UnsupportedOperationException("AmpMeter couldn't have followers for now");
    }
    
    public void nextValue(int value) {
        buffer[count] = value;
        count += 1;
        if (count == buffer.length) {
            dump();
        }
    }

    double calcAverage(int[] b) {
        double sum = 0;
        for (int x : b) {
            sum += x;
        }
        return sum / b.length;
    }
    
    double calcAmplitude(int[] b, double avg) {
        double sum = 0;
        for (int x : b) {
            sum += Math.pow(x - avg, 2);
        }
        return Math.sqrt(sum / b.length);
    }
    
    void dump() {
        double avg = calcAverage(buffer);
        double amp = calcAmplitude(buffer, avg);
        count = 0;
        System.out.println("Amplitude: " + amp);
    }

}
