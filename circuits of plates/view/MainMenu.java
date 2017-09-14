package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;
import model.*;

public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton newGame, highScores, load, exit;
	private BufferedImage img = null;
	private JPanel innerSelectPanel = null;
	private static MainMenu mainMenu;

	public static MainMenu getMainMenu() {

		if (mainMenu == null)
			mainMenu = new MainMenu();

		return mainMenu;
	}

	private MainMenu() {

		this.frame = GameFrame.getGameFrame();
		initMenu();

	}

	public void initMenu() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/supportMedia/newgame.png"));
		newGame = new JButton(icon);
		newGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newGame.setBorder(BorderFactory.createEmptyBorder());
		newGame.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/highScores.png"));
		highScores = new JButton(icon);
		highScores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		highScores.setBorder(BorderFactory.createEmptyBorder());
		highScores.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/load.png"));
		load = new JButton(icon);
		load.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		load.setBorder(BorderFactory.createEmptyBorder());
		load.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/exit.png"));

		exit = new JButton(icon);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setContentAreaFilled(false);
		display();
		playAudio(0);
	}

	public void display() {

		innerSelectPanel = new JPanel(new GridLayout(4, 1, 0, 40));
		innerSelectPanel.add(newGame);
		innerSelectPanel.add(highScores);
		innerSelectPanel.add(load);
		innerSelectPanel.add(exit);
		innerSelectPanel.setOpaque(false);
		innerSelectPanel.setBorder(BorderFactory.createEmptyBorder(120, 2, 0, 2));
		add(innerSelectPanel);
		JLabel contentPane = new JLabel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		addListner(new MainMenCtrl(this));

	}

	private void addListner(ActionListener listener) {

		newGame.addActionListener(listener);
		highScores.addActionListener(listener);
		load.addActionListener(listener);
		exit.addActionListener(listener);

	}

	public void playAudio(int theme) {
		if (theme == 0) {
			File file = new File(getClass().getResource("/supportMedia/CircusMusic1.wav").getFile());
			String path = file.getAbsolutePath().replaceAll("%20", " ");
			AudioPlayer.main(null, path);
		} else {

			File file = new File(getClass().getResource("/supportMedia/CircusMusic2.wav").getFile());
			String path = file.getAbsolutePath().replaceAll("%20", " ");
			AudioPlayer.main(null, path);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);

		Graphics2D gc = (Graphics2D) g;
		try {
			File file = new File(getClass().getResource("/supportMedia/preview.jpg").getFile());
			String path = file.getAbsolutePath().replaceAll("%20", " ");
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.drawImage(img, 0, 0, null);

	}

	public JButton getNewGame() {

		return newGame;
	}

	public JButton getHighScores() {

		return highScores;
	}

	public JButton getLoad() {

		return load;
	}

	public JButton getExit() {

		return exit;
	}

	public JFrame getFrame() {

		return frame;
	}

}
