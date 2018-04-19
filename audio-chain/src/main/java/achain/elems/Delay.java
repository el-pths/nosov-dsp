package achain.elems;

public class Delay extends ChainElement {
	
	private int repeat = 3;
	private int i = 0;
	private int k = 2;
	private int time = 20035;
	private int[] values = new int[time*repeat];

	@Override
	public void nextValue(int value) {
		int temp = 0;
		for(int j = 0; j < repeat; j++){
			int index = j * time + i;
			if(index >= time*repeat){
				index -= time*repeat;
			}
			temp += (int) (values[index]/Math.pow(k, j + 1));
		}
		values[i] = value;
		value += temp;
		i++;
		if(i == time*repeat){
			i = 0;
		}
		follower.nextValue(value);
	}
	
}
