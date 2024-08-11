package simpleface.simpleface;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip MusiClip;
	URL soundURL[] = new URL[30];
	long cliptimePosition;
	
	// honelsty sound for kill sucks and hit kinda sucks , cmake sure they are satisfying and not anoying , maybe add a shoot sound 

	public Sound() {
		soundURL[0] = getClass().getClassLoader().getResource("gameMusic2L.wav");// change to game music
		soundURL[1] = getClass().getClassLoader().getResource("enemyHit.wav");
		soundURL[2] = getClass().getClassLoader().getResource("kill().wav");
		soundURL[3] = getClass().getClassLoader().getResource("playerHit()(1).wav");
		soundURL[4] = getClass().getClassLoader().getResource("playerKill.wav");
		soundURL[5] = getClass().getClassLoader().getResource("upgradeInstalled.wav");
		soundURL[6] = getClass().getClassLoader().getResource("upgradeBulletReady.wav");
		soundURL[7] = getClass().getClassLoader().getResource("highScore.wav");
		soundURL[8] = getClass().getClassLoader().getResource("rocket ready.wav");
		soundURL[9] = getClass().getClassLoader().getResource("nuke ready.wav");
		soundURL[10] = getClass().getClassLoader().getResource("gameOver2.wav");
		soundURL[11] = getClass().getClassLoader().getResource("powerup.wav");
		soundURL[12] = getClass().getClassLoader().getResource("splatPoison.wav");
		soundURL[13] = getClass().getClassLoader().getResource("gameMusic.wav");
		soundURL[14] = getClass().getClassLoader().getResource("rocket whoosh.wav");
		soundURL[15] = getClass().getClassLoader().getResource("Explosion.wav");
		soundURL[16] = getClass().getClassLoader().getResource("katanaslice1.wav");
		soundURL[17] = getClass().getClassLoader().getResource("wakeup.wav");
		soundURL[18] = getClass().getClassLoader().getResource("gunshot.wav");
		soundURL[19] = getClass().getClassLoader().getResource("gunshot2.wav");
		soundURL[20] = getClass().getClassLoader().getResource("explosiongunshot3.wav");// this sound sucks change it 
//		soundURL[2] = getClass().getClassLoader().getResource("kill().wav");
	}
	
	public void setFile( int i ) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			MusiClip = AudioSystem.getClip();
			MusiClip.open(ais);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		
		MusiClip.start();
	}
	public void loop() {
		MusiClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		cliptimePosition = MusiClip.getMicrosecondPosition();
		MusiClip.stop();
	}
	public void continuePlaying() {
		MusiClip.setMicrosecondPosition(cliptimePosition);
		MusiClip.start();
	}
}
