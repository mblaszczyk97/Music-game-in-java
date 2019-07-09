package rythm_code_11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;


public class Game extends Thread {
	
	public static int lineOne = (int) (Main.SCREEN_Height*0.25)+18;
	public static int lineTwo = (int) (Main.SCREEN_Height*0.25)+108;
	public static int lineThree = (int) (Main.SCREEN_Height*0.25)+198;
	public static int lineFour = (int) (Main.SCREEN_Height*0.25)+288;
	
	private Image gameInfo = new ImageIcon(Main.resourcesLocation+"gameInfo.png").getImage();
	private Image gameCheck = new ImageIcon(Main.resourcesLocation+"gameCheck.png").getImage();
	//private Image notesRoute = new ImageIcon(Main.resourcesLocation+"notesRoute.png")).getImage();
	private Image line = new ImageIcon(Main.resourcesLocation+"line.png").getImage();
	//private Image note = new ImageIcon(Main.resourcesLocation+"note.png")).getImage();
	private Image notesRoute1 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
	private Image notesRoute2 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
	private Image notesRoute3 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
	private Image notesRoute4 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
	private Image mark;
	private Image gameCheckHit1;
	private Image gameCheckHit2;
	private Image gameCheckHit3;
	private Image gameCheckHit4;

	
	private String titleName;
	private String difficulty;
	private int score;
	public int getScore() {
		return score;
	}

	public synchronized void setScore(int score) {
		this.score = score;
	}

	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false, -15);
	}
	
	public void screenDraw(Graphics2D g) {
		
		String scoreS = Integer.toString(this.score);
		g.drawImage(notesRoute1, 0, (int) (Main.SCREEN_Height*0.25)+18, null);
		g.drawImage(line, 0, (int) (Main.SCREEN_Height*0.25)+107, null);
		g.drawImage(notesRoute2, 0, (int) (Main.SCREEN_Height*0.25)+108, null);
		g.drawImage(line, 0, (int) (Main.SCREEN_Height*0.25)+197, null);
		g.drawImage(notesRoute3, 0, (int) (Main.SCREEN_Height*0.25)+198, null);
		g.drawImage(line, 0, (int) (Main.SCREEN_Height*0.25)+287, null);
		g.drawImage(notesRoute4, 0, (int) (Main.SCREEN_Height*0.25)+288, null);
		g.drawImage(gameInfo, 0, (int) (Main.SCREEN_Height)-60, null);
		g.drawImage(gameCheck, 40, (int) (Main.SCREEN_Height*0.25), null);
		for(int i=0; i<noteList.size();i++) {
			Note note = noteList.get(i);
			if(note.getX() < 2) {
				mark = new ImageIcon(Main.resourcesLocation+"markMiss.png").getImage();
			}
			if(!note.isProceeded()) {
				
				noteList.remove(i); // usuwamy kó³ka kiedy przejd¹ za pole
				i--;
			}else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString(titleName, 10, (int) (Main.SCREEN_Height)-15);
		g.drawString(difficulty, (int) (Main.SCREEN_WIDTH)-100, (int) (Main.SCREEN_Height)-15);
		g.drawString(scoreS, (int) (Main.SCREEN_WIDTH*0.5), (int) (Main.SCREEN_Height)-15);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("^", 70, (int) (Main.SCREEN_Height*0.25)+78);
		g.drawString(">", 70, (int) (Main.SCREEN_Height*0.25)+167);
		g.drawString("<", 70, (int) (Main.SCREEN_Height*0.25)+257);
		g.drawString("v", 70, (int) (Main.SCREEN_Height*0.25)+347);
		g.drawImage(mark, (int) (Main.SCREEN_WIDTH*0.5)-180, (int) (Main.SCREEN_Height*0.25)-45, null);
		g.drawImage(gameCheckHit1, 39, (int) (Main.SCREEN_Height*0.25)+15, null);
		g.drawImage(gameCheckHit2, 39, (int) (Main.SCREEN_Height*0.25)+105, null);
		g.drawImage(gameCheckHit3, 39, (int) (Main.SCREEN_Height*0.25)+195, null);
		g.drawImage(gameCheckHit4, 39, (int) (Main.SCREEN_Height*0.25)+285, null);

	}
	
	public void pressW() {
		judge("1");
		notesRoute1 = new ImageIcon(Main.resourcesLocation+"notesRoutePressed.png").getImage();
		gameCheckHit1 = new ImageIcon(Main.resourcesLocation+"gameChceckHit.png").getImage();
		new Music("noteBass.mp3", false, -5).start();
	}
	public void releaseW() {
		notesRoute1 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
		gameCheckHit1 = null;
	}
	public void pressS() {
		judge("2");
		notesRoute2 = new ImageIcon(Main.resourcesLocation+"notesRoutePressed.png").getImage();
		gameCheckHit2 = new ImageIcon(Main.resourcesLocation+"gameChceckHit.png").getImage();
		new Music("noteBass.mp3", false, -5).start();
	}
	public void releaseS() {
		notesRoute2 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
		gameCheckHit2 = null;
	}
	public void pressA() {
		judge("3");
		notesRoute3 = new ImageIcon(Main.resourcesLocation+"notesRoutePressed.png").getImage();
		gameCheckHit3 = new ImageIcon(Main.resourcesLocation+"gameChceckHit.png").getImage();
		new Music("noteBass.mp3", false, -5).start();
	}
	public void releaseA() {
		notesRoute3 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
		gameCheckHit3 = null;
	}
	public void pressD() {
		judge("4");
		notesRoute4 = new ImageIcon(Main.resourcesLocation+"notesRoutePressed.png").getImage();
		gameCheckHit4 = new ImageIcon(Main.resourcesLocation+"gameChceckHit.png").getImage();
		new Music("noteBass.mp3", false, -5).start();
	}
	public void releaseD() {
		notesRoute4 = new ImageIcon(Main.resourcesLocation+"notesRoute.png").getImage();
		gameCheckHit4 = null;
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	
	
	public void dropNotes(String titleName) {
		Beat[] beats1 = new Beat[142];
		if(titleName.equalsIgnoreCase(musicTitle.substring(0,musicTitle.length()-4)) && difficulty.equals("Easy")) {
			//System.out.println(musicTitle.substring(0,musicTitle.length()-4));//tytu³ piosenki granej z folderu music
			
			String csvFile = "/Workspaces/ProjektGraRytmiczna/src/music/"+musicTitle.substring(0,musicTitle.length()-4)+".txt";
	        String line = "";
	        String cvsSplitBy = ",";
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	int i=0;
	            while ((line = br.readLine()) != null) {
	            	
	                String[] notes = line.split(cvsSplitBy);
	                int noteDropTime=Integer.parseInt(notes[0]);
	                String noteDropType=notes[1].toString();
	                beats1[i]=new Beat(noteDropTime, noteDropType);
	                i++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			
		}else if(titleName.equalsIgnoreCase(musicTitle.substring(0,musicTitle.length()-4)) && difficulty.equals("Hard")) {


		}
		int i=0;
		gameMusic.start();
		while(i < beats1.length && !isInterrupted()) {
			@SuppressWarnings("unused")
			boolean dropped = false;
			if(beats1[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats1[i].getNoteName(), this);
				note.start();
				noteList.add(note);
				i++;
				dropped=true;
			}
		}
		
	}
	
	public void judge(String input) {
		for(int i=0; i<noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	public void judgeEvent(String judge) {
		if(judge.equals("Miss")) {
			mark = new ImageIcon(Main.resourcesLocation+"markMiss.png").getImage();
		}
		else if(judge.equals("Good")) {
			mark = new ImageIcon(Main.resourcesLocation+"markGood.png").getImage();
		}
		else if(judge.equals("Great")) {
			mark = new ImageIcon(Main.resourcesLocation+"markGreat.png").getImage();
		}
		else if(judge.equals("Perfect")) {
			mark = new ImageIcon(Main.resourcesLocation+"markPerfect.png").getImage();
		}
		else if(judge.equals("Late")) {
			mark = new ImageIcon(Main.resourcesLocation+"markGood.png").getImage();
		}
		else if(judge.equals("Miss")) {
			mark = new ImageIcon(Main.resourcesLocation+"markMiss.png").getImage();
		}
		else {
			mark = null;
		}
	}
}
