import javax.sound.sampled.*;

public class PianoPlay {
	public static void main(String[] args) throws LineUnavailableException {
	    float qlt = 5000;
	    int time = 4;
	    double[] gz = {261.63, 293.66, 329.63, 349.23, 392.00, 440.00, 493.88, 523.25};
	    byte[] line = new byte[(int) (time * qlt)];
	    for(int j = 0; j < gz.length; j++){
	    	for( int i = 0; i < (int) (time * qlt / gz.length); i++ ) {
	    		double count = i / (qlt / gz[j]) * 2.0 * Math.PI;
	    		line[j * ((int) (time * qlt / gz.length)) + i] = (byte)( Math.sin(count) * 100 );
	    	}
	    }
	    AudioFormat format = new AudioFormat( qlt, 8, 1, true, false );
	    SourceDataLine sdl = AudioSystem.getSourceDataLine( format );
	    sdl.open();
	    sdl.start();
	    sdl.write( line, 0, line.length );
	    sdl.drain();
	    sdl.stop();
	}
}
