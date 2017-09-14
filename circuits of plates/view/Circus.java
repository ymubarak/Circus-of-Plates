package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.*;
import controller.MouseMotion;
import model.PlatesBar;
import model.Player;
import model.Shapes;

public class Circus extends JPanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3037533212216126787L;
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1040;
	static int joke = 50;
	private int delaySaveLabel = 0, delayLoadLabel = 0, numOfColors = 3, jokeOwner = 0, time = 0;
	private boolean atTheBeginging, inGame, gameSaved, gameLoaded, bypassIntro;
	private Player player1, player2;
	private PlatesBar smlLeftBar, bigLeftBar, smlRightBar, bigRightBar;
	private BufferedImage img, backGround, jokeImg;
	private JFrame frame;
	private Color[] colorsArray = { Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };
	private LinkedList<Shapes> shapes;
	private JLabel score, pause;
	private String Player1Name, Player2Name, theme;
	private InGameMenu inGameMenu;
	private static Circus circus;
	private CircusCtrl circusCtrl;
	private final static Logger logger = LogManager.getLogger();
//	private final static Logger logger = LogManager.getLogger(StatementClass.class);

	public static Circus getCurrentCircus() {

		return circus;
	}

	public Circus(boolean bypassIntro) {

		circus = this;
		this.frame = GameFrame.getGameFrame();
		this.bypassIntro = bypassIntro;
		score = new JLabel();
		pause = new JLabel();
		shapes = new LinkedList<Shapes>();
		atTheBeginging = inGame = true;
		gameSaved = gameLoaded = false;
		theme = "/supportMedia/themes.jpg";
		circusCtrl = new CircusCtrl(this);
		initPanel();

	}

	private void initPanel() {

		frame.requestFocusInWindow();
		HandleKeyBoard keyBoard = new HandleKeyBoard(this);
		frame.addKeyListener(keyBoard);
		new MouseMotion(this);
		inGameMenu = InGameMenu.getInGameMenu();
		setFocusable(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		score.setForeground(Color.white);
		score.setFont(new Font("Consolas", Font.PLAIN, 25));
		score.setBorder(BorderFactory.createEmptyBorder(10, 805, 0, 2));
		add(score, BorderLayout.NORTH);
		pause.setForeground(Color.yellow);
		pause.setFont(new Font("Arial", Font.PLAIN, 80));
		pause.setText(" Paused");
		pause.setBorder(BorderFactory.createEmptyBorder(340, 805, 0, 2));
		setVisible(true);
		createCircus();
		circusCtrl.playGame();
	}

	public void createCircus() {

		JLabel contentPane = new JLabel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.getContentPane().add(this);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		setBackGrndImage();
		if (!bypassIntro)
			circusCtrl.getPlayerNames();
		createBars();
		circusCtrl.setBasicPositions();
		createPlayers();
		circusCtrl.createShapes();
	}

	private void createBars() {

		img = null;
		File file = new File(getClass().getResource("/supportMedia/barL.png").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bigLeftBar = new PlatesBar(img, img.getWidth(), 1150, 0);
		smlLeftBar = new PlatesBar(img, img.getWidth() / 2, 1000, 1);

		file = new File(getClass().getResource("/supportMedia/barR.png").getFile());
		path = file.getAbsolutePath().replaceAll("%20", " ");

		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bigRightBar = new PlatesBar(img, img.getWidth(), 1150, 2);
		smlRightBar = new PlatesBar(img, img.getWidth() / 2, 1000, 3);

	}

	public void setPlayerNames(String p1Name, String p2Name) {
		Player1Name = p1Name;
		Player2Name = p2Name;
	}

	public void createPlayers() {

		img = null;
		File file = new File(getClass().getResource("/supportMedia/player1.png").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");

		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage outputImage1 = new BufferedImage(406, 600, img.getType());
		Graphics2D g2d1 = outputImage1.createGraphics();
		g2d1.drawImage(img, 0, 0, 406, 600, null);
		g2d1.dispose();

		player1 = new Player(Player1Name, WIDTH - img.getWidth() + 35, outputImage1);

		file = new File(getClass().getResource("/supportMedia/player2.png").getFile());
		path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage outputImage2 = new BufferedImage(406, 600, img.getType());
		Graphics2D g2d = outputImage2.createGraphics();
		g2d.drawImage(img, 0, 0, 406, 600, null);
		g2d.dispose();

		player2 = new Player(Player2Name, 0, outputImage2);

		atTheBeginging = false;

	}

	public Color getRandomColor() {

		return colorsArray[(int) Math.floor(numOfColors * Math.random())];
	}

	private void setBackGrndImage() {

		File file = new File(getClass().getResource(theme).getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			backGround = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		file = new File(getClass().getResource("/supportMedia/joke.png").getFile());
		path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			jokeImg = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getScoreText() {
		return "<html></pre>" + player1.getName() + ": " + player1.getScore() + "  " + player2.getName() + ": "
				+ player2.getScore() + "<br></pre>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Time: " + ((time / 3600) % 24) + ":"
				+ ((time / 60) % 60) + ":" + (time % 60) + "</html>";
	}

	public void handleScoreBoard(long t) {
		time = (int) t;
		score.setText(getScoreText());
	}

	public void savedGame() {

		if (delaySaveLabel < 20) {
			delaySaveLabel++;
			pause.setBorder(BorderFactory.createEmptyBorder(340, 700, 0, 2));
			pause.setText("Game Saved !");
			add(pause);
			frame.setVisible(true);
		} else {
			delaySaveLabel = 0;
			gameSaved = false;
			remove(pause);
		}
	}

	public void loadedGame() {
		if (delayLoadLabel < 20) {
			delayLoadLabel++;
			pause.setBorder(BorderFactory.createEmptyBorder(340, 700, 0, 2));
			pause.setText("Game Loaded !");
			add(pause);
			frame.setVisible(true);
		} else {
			delayLoadLabel = 0;
			gameLoaded = false;
			remove(pause);
		}
	}

	public void handlePauseLabel() {

		if (isInGame()) {
			pause.setBorder(BorderFactory.createEmptyBorder(340, 805, 0, 2));
			pause.setText(" Pause");
			add(pause);
			frame.setVisible(true);
			logger.info("Game paused");
		} else {
			circus.remove(pause);
			logger.info("Game resumed");
		}
		setGameStatus(!isInGame());
	}

	public void showMenu() {

		add(inGameMenu);
		inGameMenu.setVisible(true);
		setGameStatus(false);
		score.setVisible(false);
		frame.setVisible(true);
	}

	public void hideMenu() {

		remove(inGameMenu);
		setGameStatus(true);
		score.setVisible(true);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);
		Graphics2D gc = (Graphics2D) g;
		drawTheme(gc);
		if (!atTheBeginging) {
			drawPlayers(gc);
			drawBars(gc);
			drawShapes(gc);
		}
	}

	private void drawTheme(Graphics2D gc) {

		gc.drawImage(backGround, 0, 0, null);

	}

	private void drawPlayers(Graphics2D gc) {

		player1.draw(gc);
		player2.draw(gc);
	}

	private void drawBars(Graphics2D gc) {

		bigLeftBar.draw(gc);
		smlLeftBar.draw(gc);
		bigRightBar.draw(gc);
		smlRightBar.draw(gc);
	}

	private void drawShapes(Graphics2D gc) {

		for (Shapes s : shapes) {
			s.draw(gc);
		}
		// this is a joke that shows funny face win gaining a point :)
		// if (joke <= 50) {
		// joke++;
		// if (jokeOwner == 0)
		// gc.drawImage(jokeImg, player1.getLocation() + player1.getWidth() / 2
		// - 65, 1200 - player1.getHeight(),
		// null);
		// else
		// gc.drawImage(jokeImg, player2.getLocation() + player2.getWidth() / 2
		// - 65, 1200 - player2.getHeight(),
		// null);
		// }
	}

	public Player getPlayer(int i) {
		if (i == 0)
			return player1;
		return player2;
	}

	public void setPlayer(Player playerA, Player playerB) {

		player1 = playerA;
		player2 = playerB;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int t) {
		circusCtrl.setTime(t);
	}

	public String getTheme() {

		return theme;
	}

	public void setTheme(String s) {

		theme = s;
		setBackGrndImage();
	}

	public boolean getGameSaved() {

		return gameSaved;
	}

	public boolean getGameLoaded() {

		return gameLoaded;
	}

	public void activeGameSaved() {

		gameSaved = true;
	}

	public void activeGameLoaded() {

		gameLoaded = true;
	}

	public boolean isInGame() {

		return inGame;
	}

	public void setGameStatus(boolean st) {

		inGame = st;
	}

	public JFrame getFrame() {
		return frame;
	}

	public PlatesBar getBigRightBar() {
		return bigRightBar;
	}

	public PlatesBar getSmlRightBar() {
		return smlRightBar;
	}

	public PlatesBar getBigLeftBar() {
		return bigLeftBar;
	}

	public PlatesBar getSmlLeftBar() {
		return smlLeftBar;
	}

	public void setShapes(LinkedList<Shapes> s) {
		shapes = s;
	}

	public void setNumOfColor(int n) {
		numOfColors = n;
	}

	public void resize() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("supportMedia/player1.png").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");

		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player1.setImage(img);

		classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource("supportMedia/player2.png").getFile());
		path = file.getAbsolutePath().replaceAll("%20", " ");

		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player2.setImage(img);

	}
}