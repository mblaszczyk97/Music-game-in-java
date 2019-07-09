package rythm_code_11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(RythmCode.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()==Main.controll1) {
			RythmCode.game.pressW();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode()==Main.controll2) {
			RythmCode.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode()==Main.controll3) {
			RythmCode.game.pressA();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode()==Main.controll4) {
			RythmCode.game.pressD();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(RythmCode.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode()==Main.controll1) {
			RythmCode.game.releaseW();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode()==Main.controll2) {
			RythmCode.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode()==Main.controll3) {
			RythmCode.game.releaseA();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode()==Main.controll4) {
			RythmCode.game.releaseD();
		}
	}
	
}

