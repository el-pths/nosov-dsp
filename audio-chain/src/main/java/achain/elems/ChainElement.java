package achain.elems;

public abstract class ChainElement {

    public static final int SAMPLE_RATE = 44100;
    public static final int BYTES_PER_SAMPLE = 2;
    public static final int BITS_PER_BYTE = 8;

    protected ChainElement follower;

    public void addFollower(ChainElement follower) {
        this.follower = follower;
    }
    
    public abstract void nextValue(int value);

}
