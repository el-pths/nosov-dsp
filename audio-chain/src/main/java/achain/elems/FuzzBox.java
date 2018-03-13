package achain.elems;

public class FuzzBox extends ChainElement {

    private int maxOverdriveValue = 2000;
    private double overdriveCorection = 0.4;
    private int maxDistortionValue = 5000;
    
    private boolean overdrive = true;
    private boolean distortion = false;
    
    public void nextValue(int value) {
        if (overdrive) {
            if (value > maxOverdriveValue) {
                value = (int) (Math.round(value - (value - maxOverdriveValue) * overdriveCorection));
            }
            if (value < (-1) * maxOverdriveValue) {
                value = (int) (Math.round(value - (value + maxOverdriveValue) * overdriveCorection));
            }
        }
        if (distortion) {
            if (value > maxDistortionValue) {
                value = maxDistortionValue;
            }
            if (value < (-1) * maxDistortionValue) {
                value = (-1) * maxDistortionValue;
            }
        }
        follower.nextValue(value);
    }
    
}

