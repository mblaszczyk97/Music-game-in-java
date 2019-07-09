package rythm_code_11;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class Main {

	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static String resourcesLocation = "C:/gry/rythmcode/resources/";//te sciezki bêd¹ ustalane przy instalacji programu
	public static String musicLocation = "C:/gry/rythmcode/music/";
	public static String settingsLocation = "C:/gry/rythmcode/settings/";
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_Height = 0;
	public static final int NOTE_SPEED = 3;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 2;
	public static int controll1 = 1;
	public static int controll2 = 1;
	public static int controll3 = 1;
	public static int controll4 = 1;
	
	public static void main(String[] args) {
		boolean isJar = true;
		File f = new File("");
		System.out.print(f.getAbsolutePath());
		if(isJar) {
			resourcesLocation = f.getAbsolutePath()+"/resources/";
			musicLocation = f.getAbsolutePath()+"/music/";
			settingsLocation = f.getAbsolutePath()+"/settings/";
		}else {
			resourcesLocation = f.getAbsolutePath()+"/src/resources/";
			musicLocation = f.getAbsolutePath()+"/src/music/";
			settingsLocation = f.getAbsolutePath()+"/src/settings/";
		}
		f.delete();
		int w=0;
		int h=0;
		String csvFileSettings = settingsLocation+"settings.txt";
	    String lineSettings = "";
	    String cvsSplitBySettings = ",";
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFileSettings))) {
	        while ((lineSettings = br.readLine()) != null) {
	            String[] screenSpecification = lineSettings.split(cvsSplitBySettings);
	            if (lineSettings.startsWith("resolution")) {
	            	w=Integer.parseInt(screenSpecification[1]);
	            	h=Integer.parseInt(screenSpecification[2]); 
	            }
	        }
	    } catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	    SCREEN_WIDTH=w;
	    SCREEN_Height=h;

	    String csvFileControlls = settingsLocation+"controlls.txt";
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFileControlls))) {
	        while ((lineSettings = br.readLine()) != null) {
	            String[] screenSpecification = lineSettings.split(cvsSplitBySettings);
	            if (lineSettings.startsWith("Controlls")) {
	            	controll1=java.awt.event.KeyEvent.getExtendedKeyCodeForChar(screenSpecification[1].charAt(0));
	            	controll2=java.awt.event.KeyEvent.getExtendedKeyCodeForChar(screenSpecification[2].charAt(0));
	            	controll3=java.awt.event.KeyEvent.getExtendedKeyCodeForChar(screenSpecification[3].charAt(0));
	            	controll4=java.awt.event.KeyEvent.getExtendedKeyCodeForChar(screenSpecification[4].charAt(0));
	            }
	        }
	    } catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	    SCREEN_WIDTH=w;
	    SCREEN_Height=h;
	    //System.out.println(SCREEN_WIDTH+"x"+SCREEN_Height);
		new RythmCode();
	}
	
	public static void restart() throws IOException, InterruptedException {
        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg + " ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Main.class.getName()).append(" ");
        Thread.currentThread();
		Thread.sleep(10000); // 10 seconds delay before restart
        Runtime.getRuntime().exec(cmd.toString());
        System.exit(0);
    }

}
