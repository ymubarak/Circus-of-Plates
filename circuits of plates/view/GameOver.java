package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.GameOverMenCtrl;
import model.ScorePair;

public class GameOver extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton playAgain, exit;
	private BufferedImage img = null;
	private JLabel winnerLabel;
	private final static Logger logger = LogManager.getLogger();

	public GameOver(JFrame frame, ScorePair p1, ScorePair p2) {

		this.frame = frame;
		winnerLabel = new JLabel();
		initialPanel();
		chooseWinner(p1, p2);

	}

	public void initialPanel() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/supportMedia/playAgain.png"));

		playAgain = new JButton(icon);
		playAgain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		playAgain.setBorder(BorderFactory.createEmptyBorder());
		playAgain.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/exit.png"));

		exit = new JButton(icon);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setContentAreaFilled(false);

		// setLayout(new GridLayout(2, 1, 0, 20));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel innerPanel = new JPanel();
		winnerLabel.setFont(new Font("Arial", Font.PLAIN, 60));
		winnerLabel.setForeground(Color.white);
		innerPanel.add(winnerLabel);
		innerPanel.setOpaque(false);
		innerPanel.setBorder(BorderFactory.createEmptyBorder(350, 2, 0, 2));
		add(innerPanel);

		JPanel innerPanel2 = new JPanel();
		innerPanel2.add(playAgain);
		innerPanel2.add(exit);
		innerPanel2.setOpaque(false);
		innerPanel2.setBorder(BorderFactory.createEmptyBorder(150, 2, 0, 2));
		add(innerPanel2);

		JLabel contentPane = new JLabel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);

		frame.setVisible(true);

		addListner(new GameOverMenCtrl(this));

	}

	private void addListner(ActionListener listener) {

		playAgain.addActionListener(listener);
		exit.addActionListener(listener);

	}

	public void chooseWinner(ScorePair p1, ScorePair p2) {

		if (p1.getScore() > p2.getScore()) {
			winnerLabel.setText(
					"<html><pre>" + p1.getPlayerName() + " Wins <br>Score : " + p1.getScore() + "<br></pre></html>");
			logger.info(p1.getPlayerName() + " wins the game with score :" + p1.getScore());
		} else if (p1.getScore() < p2.getScore()) {
			winnerLabel.setText(
					"<html><pre>" + p2.getPlayerName() + " Wins <br>Score : " + p2.getScore() + "<br></pre></html>");
			logger.info(p2.getPlayerName() + " wins the game with score :" + p2.getScore());
		} else {
			winnerLabel
					.setText("<html><pre>   No body wins <br>Score of both : " + p1.getScore() + "<br></pre></html>");
			logger.info("Fair Game ,nobody wins");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);
		Graphics2D gc = (Graphics2D) g;
		File file = new File(getClass().getResource("/supportMedia/over.jpg").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gc.drawImage(img, 0, 0, null);
	}

	public JButton getPlayAgain() {

		return playAgain;
	}

	public JButton getExit() {

		return exit;
	}

	public JFrame getFrame() {

		return frame;
	}
}
