package rythm_code_11;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image note = new ImageIcon(Main.resourcesLocation+"note.png").getImage();
	public int x = 95 + 1000/Main.SLEEP_TIME*Main.NOTE_SPEED*Main.REACH_TIME, y;
	private String noteType;
	private boolean proceeded = true;
	private Game game;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType, Game game) {
		//this.x = x;
		this.game=game;
		if(noteType.equals("1")){
			y=Game.lineOne;
		}
		else if(noteType.equals("2")) {
			y=Game.lineTwo;
		}
		else if(noteType.equals("3")) {
			y=Game.lineThree;
		}
		else if(noteType.equals("4")) {
			y=Game.lineFour;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {

			g.drawImage(note, x, y, null);

		
	}
	
	public void drop() {
		x -= Main.NOTE_SPEED;
		if(x < 0) {
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
					
				}
				else {
					interrupt();
					break;
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(x<35) {
			close();
			return "Late";
		}
		else if(x<50) {
			game.setScore(game.getScore()+1000);
			close();
			return "Perfect";
		}
		else if(x<60) {
			game.setScore(game.getScore()+500);
			close();
			return "Great";
		}
		else if(x<80) {
			game.setScore(game.getScore()+100);
			close();
			return "Good";
		}
		else if(x<2) {
			close();
			return "Miss";
		}
		return "null";
	}
	
	public int getX() {
		return x;
	}


}
