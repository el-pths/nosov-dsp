package achain.elems;

public class FuzzBox extends ChainElement {

	private int maxOverdriveValue = 3000;
	private double overdriveCorection = 0.1;
	private int maxDistortionValue = 8000;
	
	private boolean overdrive = true;
	private boolean distortion = true;
	
    public void nextValue(int value) {
    	if(overdrive){
    		if((value > maxOverdriveValue)||(value < (-1) * maxOverdriveValue)){
    			value=(int) (Math.round((value-maxOverdriveValue)*overdriveCorection)+value);
    		}
    	}
    	if(distortion){
    		if((value > maxDistortionValue)||(value < (-1) * maxDistortionValue)){
    			value = maxDistortionValue;
    		}
    	}
        follower.nextValue(value);
    }
    
}
