package rythm_code_11;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RythmCode extends JFrame {
	public int weigh;
	public int heigh;
	FileWriter fileWriter = null;
	private Image screenImage;
	private Graphics screenGraphic;

	// Dostosowanie obrazku MENU do monitora
	private ImageIcon exitPressed = new ImageIcon(Main.resourcesLocation + "exitPressed.png");
	private ImageIcon exitBasic = new ImageIcon(Main.resourcesLocation + "exit.png");
	private ImageIcon soundOn = new ImageIcon(Main.resourcesLocation + "soundOn.png");
	private ImageIcon soundOf = new ImageIcon(Main.resourcesLocation + "soundOf.png");
	private ImageIcon back = new ImageIcon(Main.resourcesLocation + "menuButtonBack.png");
	private ImageIcon backPressed = new ImageIcon(Main.resourcesLocation + "menuButtonBackPressed.png");
	private ImageIcon playButton = new ImageIcon(Main.resourcesLocation + "menuButtonPlay.png");
	private ImageIcon playButtonPressed = new ImageIcon(Main.resourcesLocation + "menuButtonPressedPlay.png");
	private ImageIcon easy = new ImageIcon(Main.resourcesLocation + "easy.png");
	private ImageIcon easyPressed = new ImageIcon(Main.resourcesLocation + "easyPressed.png");
	private ImageIcon hard = new ImageIcon(Main.resourcesLocation + "hard.png");
	private ImageIcon hardPressed = new ImageIcon(Main.resourcesLocation + "hardPressed.png");
	private ImageIcon optionsButton = new ImageIcon(Main.resourcesLocation + "menuButtonOptions.png");
	private ImageIcon optionsButtonPressed = new ImageIcon(Main.resourcesLocation + "menuButtonPressedOptions.png");
	private ImageIcon exitButton = new ImageIcon(Main.resourcesLocation + "menuButtonExit.png");
	private ImageIcon exitButtonPressed = new ImageIcon(Main.resourcesLocation + "menuButtonPressedExit.png");
	private ImageIcon arrowLeft = new ImageIcon(Main.resourcesLocation + "arrowLeft.png");
	private ImageIcon arrowLeftPressed = new ImageIcon(Main.resourcesLocation + "arrowLeftPressed.png");
	private ImageIcon arrowRight = new ImageIcon(Main.resourcesLocation + "arrowRight.png");
	private ImageIcon arrowRightPressed = new ImageIcon(Main.resourcesLocation + "arrowRightPressed.png");
	private ImageIcon minimalized = new ImageIcon(Main.resourcesLocation + "optionsMinimalized.png");
	private ImageIcon fullscreen = new ImageIcon(Main.resourcesLocation + "optionsFullscreen.png");
	private ImageIcon minimalizedPressed = new ImageIcon(Main.resourcesLocation + "optionsMinimalizedPressed.png");
	private ImageIcon fullscreenPressed = new ImageIcon(Main.resourcesLocation + "optionsFullscreenPressed.png");
	private ImageIcon optionsControllsButton = new ImageIcon(Main.resourcesLocation + "optionsControlls.png");
	private ImageIcon optionsControllsPressedButton = new ImageIcon(Main.resourcesLocation + "optionsControllsPressed.png");
	private ImageIcon optionsControllsConfirmButton = new ImageIcon(Main.resourcesLocation + "optionsControllsConfirm.png");
	private ImageIcon optionsControllsConfirmPressedButton = new ImageIcon(Main.resourcesLocation + "optionsControllsConfirmPressed.png");
	private ImageIcon optionsLines = new ImageIcon(Main.resourcesLocation+"notesRoute.png");
	public String titleName;
	private Image introBackground = new ImageIcon(Main.resourcesLocation + "backgroundTitle.jpg").getImage()
			.getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_Height, DISPOSE_ON_CLOSE);
	private Image playBackground = new ImageIcon(Main.resourcesLocation + "musicSelection.jpg").getImage()
			.getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_Height, DISPOSE_ON_CLOSE);

	boolean isWindowed = true;

	private JLabel menuBar = new JLabel(new ImageIcon(Main.resourcesLocation + "menuBar.png"));

	private JButton exit = new JButton(exitBasic);
	private JButton mute = new JButton(soundOn);
	private JButton left = new JButton(arrowLeft);
	private JButton right = new JButton(arrowRight);
	private JButton menuPlay = new JButton(playButton);
	private JButton menuOptions = new JButton(optionsButton);
	private JButton menuExit = new JButton(exitButton);
	private JButton easyButton = new JButton(easy);
	private JButton hardButton = new JButton(hard);
	private JButton backButton = new JButton(back);
	private JButton backToMenuButton = new JButton(back);
	private JButton optionsMinimalized = new JButton(minimalized);
	private JButton optionsFullscreen = new JButton(fullscreen);
	private JButton optionsControlls = new JButton(optionsControllsButton);
	private JButton optionsControllsConfirm = new JButton(optionsControllsConfirmButton);
	private JButton optionsControllsLine1 = new JButton(optionsLines);
	private JButton optionsControllsLine2 = new JButton(optionsLines);
	private JButton optionsControllsLine3 = new JButton(optionsLines);
	private JButton optionsControllsLine4 = new JButton(optionsLines);
	private int mouseX, mouseY;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean isMute = false;
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	JTextField c1, c2, c3, c4;
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image selectedImage;
	@SuppressWarnings("unused")
	private Image titleImage;
	private Music selectedMusic;
	private int nowSelected = 0;

	private Music introMusic = new Music("intro.mp3", true, -20);
	private KeyListener sterowanie = new KeyListener();
	public static Game game;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void restartApplication() throws IOException {
		final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		final File currentJar = new File("C:/gry/rythmcode/rythm.jar");
		if (!currentJar.getName().endsWith(".jar"))
			return;
		final ArrayList<String> command = new ArrayList<String>();
		command.add(javaBin);
		command.add("-jar");
		command.add(currentJar.getPath());
		final ProcessBuilder builder = new ProcessBuilder(command);
		builder.start();
		System.exit(0);
	}

	public RythmCode() {
		String csvFileSettings = Main.settingsLocation + "settings.txt";
		String csvFileControlls = Main.settingsLocation + "controlls.txt";
		String lineSettings = "";
		String cvsSplitBySettings = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileSettings))) {
			while ((lineSettings = br.readLine()) != null) {
				String[] screenSpecification = lineSettings.split(cvsSplitBySettings);
				if (lineSettings.startsWith("resolution")) {
					weigh = Integer.parseInt(screenSpecification[1]);
					heigh = Integer.parseInt(screenSpecification[2]);
				}
			}
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		if (weigh == (int) screenSize.getWidth() && heigh == (int) screenSize.getHeight()) {
			isWindowed = false;
		}
		String csvFile = "/Workspaces/ProjektGraRytmiczna/src/music/musiclist/musiclist.txt";
		String line = "";
		String cvsSplitBy = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {

				String[] musiclist = line.split(cvsSplitBy);

				String titlesPicture = musiclist[0]; // Napis na miniaturce jako obrazek
				String miniPicture = musiclist[1]; // Miniaturka przy wyborze muzyki
				String gamePicture = musiclist[2]; // Ekran Gry
				String musicSample = musiclist[3]; // Próbka do menu wyboru
				String music = musiclist[4]; // Muzyka pe³na
				String titles = musiclist[5]; // Tytu³ wyœwietlany
				trackList.add(new Track(titlesPicture, miniPicture, gamePicture, musicSample, music, titles));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		c1 = new JTextField("^");
		c1.setBounds(20, (int) (Main.SCREEN_Height * 0.25) + 18, 30, 30);
		c2 = new JTextField("<");
		c2.setBounds(20, (int) (Main.SCREEN_Height * 0.25) + 108, 30, 30);
		c3 = new JTextField(">");
		c3.setBounds(20, (int) (Main.SCREEN_Height * 0.25) + 198, 30, 30);
		c4 = new JTextField("v");
		c4.setBounds(20, (int) (Main.SCREEN_Height * 0.25) + 288, 30, 30);
		add(c1);
		add(c2);
		add(c3);
		add(c4);
		c1.setVisible(false);
		c2.setVisible(false);
		c3.setVisible(false);
		c4.setVisible(false);
		optionsControllsLine1.setBounds(50, (int) (Main.SCREEN_Height * 0.25) + 18, 300, 30);
		optionsControllsLine1.setBorderPainted(false);
		optionsControllsLine1.setContentAreaFilled(false);
		optionsControllsLine1.setFocusPainted(false);
		optionsControllsLine2.setBounds(50, (int) (Main.SCREEN_Height * 0.25) + 108, 300, 30);
		optionsControllsLine2.setBorderPainted(false);
		optionsControllsLine2.setContentAreaFilled(false);
		optionsControllsLine2.setFocusPainted(false);
		optionsControllsLine3.setBounds(50, (int) (Main.SCREEN_Height * 0.25) + 198, 300, 30);
		optionsControllsLine3.setBorderPainted(false);
		optionsControllsLine3.setContentAreaFilled(false);
		optionsControllsLine3.setFocusPainted(false);
		optionsControllsLine4.setBounds(50, (int) (Main.SCREEN_Height * 0.25) + 288, 300, 30);
		optionsControllsLine4.setBorderPainted(false);
		optionsControllsLine4.setContentAreaFilled(false);
		optionsControllsLine4.setFocusPainted(false);
		add(optionsControllsLine1);
		add(optionsControllsLine2);
		add(optionsControllsLine3);
		add(optionsControllsLine4);
		optionsControllsLine1.setVisible(false);
		optionsControllsLine2.setVisible(false);
		optionsControllsLine3.setVisible(false);
		optionsControllsLine4.setVisible(false);

		// System.out.println(weigh+"x"+Main.SCREEN_Height);
		// Plansza (okno) jest tworzone
		setUndecorated(true);
		setTitle("Rythm Code");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_Height);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(sterowanie);

		introMusic.start();

		// Przycisk Wyjœcia---------------------------------------
		exit.setBounds(Main.SCREEN_WIDTH - 30, 0, 30, 30);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setIcon(exitPressed);
				exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit.setIcon(exitBasic);
				exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				System.exit(0);

			}
		});
		add(exit);

		// Przycisk mute, wyciszaj¹cy dzwiêki
		mute.setBounds(Main.SCREEN_WIDTH - 60, 0, 30, 30);
		mute.setBorderPainted(false);
		mute.setContentAreaFilled(false);
		mute.setFocusPainted(false);
		mute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// mute.setIcon(exitPressed);
				mute.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// mute.setIcon(exitBasic);
				mute.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (isMute == false) {
					mute.setIcon(soundOf);
					introMusic.suspend();
					isMute = true;
				} else {
					mute.setIcon(soundOn);
					introMusic.resume();
					isMute = false;
				}
			}
		});
		add(mute);

		// Powrót do menu z wyboru piosenek
		backButton.setVisible(false);
		backButton.setBounds(0, (int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.9), 390, 90);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backPressed);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(back);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				backMain();

			}
		});
		add(backButton);

		// powrót do menu g³ównego
		backToMenuButton.setVisible(false);
		backToMenuButton.setBounds(0, (int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.9), 390, 90);
		backToMenuButton.setBorderPainted(false);
		backToMenuButton.setContentAreaFilled(false);
		backToMenuButton.setFocusPainted(false);
		backToMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backToMenuButton.setIcon(backPressed);
				backToMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backToMenuButton.setIcon(back);
				backToMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				backMainMenu();

			}
		});
		add(backToMenuButton);

		// lewy przycisk wyboru muzyki
		left.setVisible(false);
		left.setBounds((int) (Main.SCREEN_WIDTH * 0.5) - (int) (Main.SCREEN_WIDTH * 0.2) - 130,
				(int) (Main.SCREEN_Height * 0.5), 120, 60);
		left.setBorderPainted(false);
		left.setContentAreaFilled(false);
		left.setFocusPainted(false);
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				left.setIcon(arrowLeftPressed);
				left.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				left.setIcon(arrowLeft);
				left.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				selectLeft();
			}
		});
		add(left);

		// prawy przycisk wyboru muzyki
		right.setVisible(false);
		right.setBounds((int) (Main.SCREEN_WIDTH * 0.5) + (int) (Main.SCREEN_WIDTH * 0.2) + 10,
				(int) (Main.SCREEN_Height * 0.5), 120, 60);
		right.setBorderPainted(false);
		right.setContentAreaFilled(false);
		right.setFocusPainted(false);
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				right.setIcon(arrowRightPressed);
				right.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				right.setIcon(arrowRight);
				right.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				selectRight();
			}
		});
		add(right);

		// poziom trudnosci - easy
		easyButton.setVisible(false);
		easyButton.setBounds((int) (Main.SCREEN_WIDTH * 0.5) - (int) (Main.SCREEN_WIDTH * 0.2),
				(int) (Main.SCREEN_Height * 0.5) + (int) (Main.SCREEN_Height * 0.1), 100, 50);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyPressed);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easy);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		// poziom trudnosci - hard
		hardButton.setVisible(false);
		hardButton.setBounds((int) (Main.SCREEN_WIDTH * 0.5) - (int) (Main.SCREEN_WIDTH * 0.1),
				(int) (Main.SCREEN_Height * 0.5) + (int) (Main.SCREEN_Height * 0.1), 100, 50);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardPressed);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hard);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		// Menu Graj-------------------------------------------------
		menuPlay.setBounds(0, (int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.9), 360, 90);
		menuPlay.setBorderPainted(false);
		menuPlay.setContentAreaFilled(false);
		menuPlay.setFocusPainted(false);
		menuPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuPlay.setIcon(playButtonPressed);
				menuPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPlay.setIcon(playButton);
				menuPlay.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				introMusic.resume();
				introMusic.close();
				/*
				 * Music selectedMusic = new Music("Makemonogatari OP1 - Staples - Sample.mp3",
				 * true, 0); selectedMusic.start();
				 */
				selectTrack(0);
				menuPlay.setVisible(false);
				menuOptions.setVisible(false);
				menuExit.setVisible(false);
				mute.setVisible(false);
				left.setVisible(true);
				right.setVisible(true);
				easyButton.setVisible(true);
				hardButton.setVisible(true);
				backToMenuButton.setVisible(true);
				// backButton.setVisible(true);
				introBackground = playBackground;
				isMainScreen = true;

			}
		});
		add(menuPlay);

		// Menu Opcje---------------------------------------------------------
		menuOptions.setBounds(0, (int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.7), 360, 90);
		menuOptions.setBorderPainted(false);
		menuOptions.setContentAreaFilled(false);
		menuOptions.setFocusPainted(false);
		menuOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuOptions.setIcon(optionsButtonPressed);
				menuOptions.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuOptions.setIcon(optionsButton);
				menuOptions.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (introMusic != null) {
					introMusic.resume();
					introMusic.close();
				}
				menuPlay.setVisible(false);
				menuOptions.setVisible(false);
				menuExit.setVisible(false);
				backToMenuButton.setVisible(true);
				optionsMinimalized.setVisible(true);
				optionsFullscreen.setVisible(true);
				optionsControlls.setVisible(true);
				introBackground = playBackground;
			}
		});
		add(menuOptions);

		// opcje gra w oknie
		optionsMinimalized.setBounds((int) (Main.SCREEN_WIDTH * 0.5 - 360),
				(int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.7), 360, 90);
		optionsMinimalized.setBorderPainted(false);
		optionsMinimalized.setContentAreaFilled(false);
		optionsMinimalized.setFocusPainted(false);
		optionsMinimalized.setVisible(false);
		if (weigh == (int) ((int) screenSize.getWidth() / 1.5) && heigh == (int) ((int) screenSize.getHeight() / 1.5)) {
			optionsMinimalized.setIcon(minimalizedPressed);
		}
		optionsMinimalized.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (weigh == (int) ((int) screenSize.getWidth() / 1.5)
						&& heigh == (int) ((int) screenSize.getHeight() / 1.5)) {

				} else {
					optionsMinimalized.setIcon(minimalizedPressed);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (weigh == (int) ((int) screenSize.getWidth() / 1.5)
						&& heigh == (int) ((int) screenSize.getHeight() / 1.5)) {

				} else {
					optionsMinimalized.setIcon(minimalized);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					fileWriter = new FileWriter(csvFileSettings);
					Main.SCREEN_WIDTH = (int) ((int) screenSize.getWidth() / 1.5);
					Main.SCREEN_Height = (int) ((int) screenSize.getHeight() / 1.5);
					fileWriter.append("resolution,");
					fileWriter.append(String.valueOf((int) ((int) screenSize.getWidth() / 1.5)));
					fileWriter.append(",");
					fileWriter.append(String.valueOf((int) ((int) screenSize.getHeight() / 1.5)));
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}

				try {
					restartApplication();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// dispose();
				// introMusic.close();
				// new RythmCode();

			}
		});
		add(optionsMinimalized);

		// opcje gra w pe³nym ekranie
		optionsFullscreen.setBounds((int) (Main.SCREEN_WIDTH * 0.5),
				(int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.7), 360, 90);
		optionsFullscreen.setBorderPainted(false);
		optionsFullscreen.setContentAreaFilled(false);
		optionsFullscreen.setFocusPainted(false);
		optionsFullscreen.setVisible(false);
		if (weigh == (int) ((int) screenSize.getWidth()) && heigh == (int) ((int) screenSize.getHeight())) {
			optionsFullscreen.setIcon(fullscreenPressed);
		}
		optionsFullscreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (weigh == (int) ((int) screenSize.getWidth()) && heigh == (int) ((int) screenSize.getHeight())) {

				} else {
					optionsFullscreen.setIcon(fullscreenPressed);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (weigh == (int) ((int) screenSize.getWidth()) && heigh == (int) ((int) screenSize.getHeight())) {

				} else {
					optionsFullscreen.setIcon(fullscreen);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					fileWriter = new FileWriter(csvFileSettings);
					Main.SCREEN_WIDTH = (int) ((int) screenSize.getWidth() / 1);
					Main.SCREEN_Height = (int) ((int) screenSize.getHeight() / 1);
					fileWriter.append("resolution,");
					fileWriter.append(String.valueOf((int) ((int) screenSize.getWidth() / 1)));
					fileWriter.append(",");
					fileWriter.append(String.valueOf((int) ((int) screenSize.getHeight() / 1)));
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}

				try {
					restartApplication();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// dispose();
				// introMusic.close();
				// new RythmCode();

			}
		});
		add(optionsFullscreen);

		optionsControlls.setBounds((int) (Main.SCREEN_WIDTH * 0.5 - 180),
				(int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.4), 360, 90);
		optionsControlls.setBorderPainted(false);
		optionsControlls.setContentAreaFilled(false);
		optionsControlls.setFocusPainted(false);
		optionsControlls.setVisible(false);
		optionsControlls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				optionsControlls.setIcon(optionsControllsPressedButton);
				optionsControlls.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				optionsControlls.setIcon(optionsControllsButton);
				optionsControlls.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (introMusic != null) {
					introMusic.resume();
					introMusic.close();
				}
				optionsControlls.setVisible(false);
				backToMenuButton.setVisible(true);
				optionsMinimalized.setVisible(false);
				optionsFullscreen.setVisible(false);
				introBackground = playBackground;
				optionsControllsConfirm.setVisible(true);
				c1.setVisible(true);
				c2.setVisible(true);
				c3.setVisible(true);
				c4.setVisible(true);
				optionsControllsLine1.setVisible(true);
				optionsControllsLine2.setVisible(true);
				optionsControllsLine3.setVisible(true);
				optionsControllsLine4.setVisible(true);

			}
		});
		add(optionsControlls);
		
		optionsControllsConfirm.setBounds((int) (460),
				(int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.5), 360, 90);
		optionsControllsConfirm.setBorderPainted(false);
		optionsControllsConfirm.setContentAreaFilled(false);
		optionsControllsConfirm.setFocusPainted(false);
		optionsControllsConfirm.setVisible(false);
		optionsControllsConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				optionsControllsConfirm.setIcon(optionsControllsConfirmPressedButton);
				optionsControllsConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				optionsControllsConfirm.setIcon(optionsControllsConfirmButton);
				optionsControllsConfirm.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				
				String controll1 = c1.getText().substring(0, 1);
				String controll2 = c2.getText().substring(0, 1);
				String controll3 = c3.getText().substring(0, 1);
				String controll4 = c4.getText().substring(0, 1);
				System.out.println(controll1+controll2+controll3+controll4);
				
				try {
					fileWriter = new FileWriter(csvFileControlls);
					fileWriter.append("Controlls,");
					fileWriter.append(String.valueOf(controll1));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(controll2));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(controll3));
					fileWriter.append(",");
					fileWriter.append(String.valueOf(controll4));
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						fileWriter.flush();
						fileWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				
				try {
					restartApplication();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		add(optionsControllsConfirm);
		
		// Menu Wyjœcie z gry------------------------------------------------------
		menuExit.setBounds(0, (int) (Main.SCREEN_Height - Main.SCREEN_Height * 0.2), 360, 90);
		menuExit.setBorderPainted(false);
		menuExit.setContentAreaFilled(false);
		menuExit.setFocusPainted(false);
		menuExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuExit.setIcon(exitButtonPressed);
				menuExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEntered.mp3", false, -20);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuExit.setIcon(exitButton);
				menuExit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);

			}
		});
		add(menuExit);

		// Mouse Listener i Mouse Motion Listener
		// dzia³aj¹ po to by przesuwaæ okno trzymaj¹c TitleBar
		menuBar.setBounds(0, 0, Main.SCREEN_WIDTH, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		menuBar.setVisible(isWindowed);
		mute.setVisible(isWindowed);
		exit.setVisible(isWindowed);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_Height);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	private void screenDraw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawImage(introBackground, 0, 0, null);
		// je¿eli jesteœmy w menu wyboru gry rysuje nazwe piosenki i zdjêcie albumu
		if (isMainScreen) {
			g.setColor(Color.white);
			g.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawImage(selectedImage, (int) (Main.SCREEN_WIDTH * 0.5) - (int) (Main.SCREEN_WIDTH * 0.2),
					(int) (Main.SCREEN_Height * 0.5) - (int) (Main.SCREEN_Height * 0.2), null);
			// g.drawImage(titleImage,(int) (Main.SCREEN_WIDTH*0.5)-(int)
			// (Main.SCREEN_WIDTH*0.2), (int) (Main.SCREEN_Height*0.5)-(int)
			// (Main.SCREEN_Height*0.2), null);
			g.drawString(titleName, (int) (Main.SCREEN_WIDTH * 0.5) - (int) (Main.SCREEN_WIDTH * 0.1),
					(int) (Main.SCREEN_Height * 0.5) - (int) (Main.SCREEN_Height * 0.2));
		}
		// je¿eli gramy rysuje interfejs gry
		if (isGameScreen) {

			game.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();

	}

	// sprawdzany teraz wyœwietlany utworów ¿eby wiedzieæ jaki obraz wyœwietliæ,
	// jaki napis i jak¹ muzykê puœciæ
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.resourcesLocation + "" + trackList.get(nowSelected).getTitleImage()).getImage();
		titleName = trackList.get(nowSelected).getTitleName();
		selectedImage = new ImageIcon(Main.resourcesLocation + "" + trackList.get(nowSelected).getStartImage())
				.getImage()
				.getScaledInstance((int) (Main.SCREEN_WIDTH * 0.4), (int) (Main.SCREEN_Height * 0.4), DISPOSE_ON_CLOSE);
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true, -20);
		selectedMusic.start();
	}

	// przechodzimy liste utworów w lewo
	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}

	// przechodzimy liste utworów w prawo
	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}

	// zmieniamy po wybraniu poziomu (klikniêciu na nim) plansze na odpowiedni¹ dla
	// utworu
	// na koñcu ustawiamy grê czyli przekazujemy tytu³, poziom trudnoœci i nazwê
	// utworu
	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		left.setVisible(false);
		right.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);

		introBackground = new ImageIcon(Main.resourcesLocation + "" + trackList.get(nowSelected).getGameImage())
				.getImage().getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_Height, DISPOSE_ON_CLOSE);
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);

	}

	// powracamy do wyboru piosenek
	public void backMain() {
		isMainScreen = true;
		left.setVisible(true);
		right.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);

		introBackground = new ImageIcon(Main.resourcesLocation + "musicSelection.jpg").getImage()
				.getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_Height, DISPOSE_ON_CLOSE);
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}

	public void backMainMenu() {
		isMainScreen = false;
		left.setVisible(false);
		right.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);

		introBackground = new ImageIcon(Main.resourcesLocation + "BackgroundTitle.jpg").getImage()
				.getScaledInstance(Main.SCREEN_WIDTH, Main.SCREEN_Height, DISPOSE_ON_CLOSE);
		backToMenuButton.setVisible(false);
		isGameScreen = false;
		menuPlay.setVisible(true);
		menuOptions.setVisible(true);
		menuExit.setVisible(true);
		mute.setVisible(true);
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		nowSelected = 0;
		if (introMusic != null) {
			introMusic.close();
		}
		c1.setVisible(false);
		c2.setVisible(false);
		c3.setVisible(false);
		c4.setVisible(false);
		optionsControllsLine1.setVisible(false);
		optionsControllsLine2.setVisible(false);
		optionsControllsLine3.setVisible(false);
		optionsControllsLine4.setVisible(false);
		optionsControllsConfirm.setVisible(false);
		optionsMinimalized.setVisible(false);
		optionsFullscreen.setVisible(false);
		optionsControlls.setVisible(false);
		// introMusic.resume();
		introMusic = new Music("intro.mp3", true, -20);
		introMusic.start();
	}

	// wchodzimy do wyboru piosenek
	public void enterMain() {
		backToMenuButton.setVisible(true);
		menuPlay.setVisible(false);
		menuOptions.setVisible(false);
		menuExit.setVisible(false);
		introBackground = playBackground;
		isMainScreen = true;
		mute.setVisible(false);
		left.setVisible(true);
		right.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		// backButton.setVisible(true);
		selectTrack(0);
	}
}
