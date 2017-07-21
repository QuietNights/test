package Audio;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.*;

public class AudioPlayer {

	private Clip clip;
	public FloatControl volume;
	
	public AudioPlayer(String s) {
		
		try {
			InputStream audioSrc = getClass().getResourceAsStream(s);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);			
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2, 
					baseFormat.getSampleRate(), 
					false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		}
		catch (Exception e) {
			System.out.println(" BIG FUCKER BOY BIG FUCKER BOY BIG FUCKER BOY BIG FUCKER BOY BIG FUCKER BOY BIG FUCKER BOY BIG FUCKER BOY ");
			e.printStackTrace();
		}
	}
	
	public void playMusic() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.setLoopPoints(0, clip.getFrameLength()-1);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		
	}
	
	public void playSFX() {
		if(clip == null) return;
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
}
