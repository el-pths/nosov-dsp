package achain.elems;

public class FuzzBox extends ChainElement {

    private int maxOverdriveValue = 1000;
    private double overdriveCorection = 0.3;
    private int maxDistortionValue = 5000;
    
    private boolean overdrive = true;
    private boolean distortion = false;
    
    public void nextValue(int value) {
        if (overdrive) {
            if (value > maxOverdriveValue) {
                value = (int) (Math.round((value - maxOverdriveValue) * overdriveCorection) + value);
            }
            if (value < (-1) * maxOverdriveValue) {
                value = (int) (Math.round((value + maxOverdriveValue) * overdriveCorection) + value);
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

