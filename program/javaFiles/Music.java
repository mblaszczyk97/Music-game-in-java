package rythm_code_11;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class Music extends Thread {
	private float volume = 0;
	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;	
	public Music(String name, boolean isLoop, float volume)
	{
		try {
			this.volume = volume;
			this.isLoop = isLoop;
			file = new File(Main.musicLocation+ name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
			//player.setGain(-50);
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if (player == null)
		{
		return 0;
		}
		return player.getPosition();
	}
	
	public void close()
	{
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run()
	{
		try {
			do {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
				player.setGain(volume);
				player.play();
			} while (isLoop);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
