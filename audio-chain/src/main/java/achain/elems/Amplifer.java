package achain.elems;

public class Amplifer extends ChainElement {
	
	private double amp = 400;
	
    public void nextValue(int value) {
    	value *= amp;
        follower.nextValue(value);
    }
	
}
