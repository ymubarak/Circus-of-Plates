package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.HighScoresCtrl;
import model.*;

public class HighScoresView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton back;
	private BufferedImage img = null;
	private String gatheredScores;
	private JLabel ScoresLabel;
	private HighScoresCtrl highScoresCtrl;

	public HighScoresView(JFrame frame) {

		this.frame = frame;
		ScoresLabel = new JLabel();
		gatheredScores = "";
		highScoresCtrl = new HighScoresCtrl(this);
		initView();

	}

	public void gatherScores(LinkedList<ScorePair> scores) {

		for (ScorePair p : scores) {
			gatheredScores += "  " + p.getPlayerName();
			for (int i = 0; i < 30 - p.getPlayerName().length(); i++)
				gatheredScores += ".";
			gatheredScores += " " + p.getScore() + "   <br>";
		}
		ScoresLabel.setText("<html><pre>" + gatheredScores + "</pre></html>");

	}

	public void initView() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/supportMedia/back.png"));

		back = new JButton(icon);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel innerPanel = new JPanel();
		ScoresLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		ScoresLabel.setForeground(Color.blue);
		ScoresLabel.setOpaque(true);
		ScoresLabel.setBackground(new Color(128, 128, 128, 128));
		innerPanel.add(ScoresLabel);
		innerPanel.setOpaque(false);
		innerPanel.setBorder(BorderFactory.createEmptyBorder(300, 2, 0, 2));
		add(innerPanel);

		JPanel innerPanel2 = new JPanel();
		innerPanel2.add(back);
		innerPanel2.setOpaque(false);
		innerPanel2.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 2));
		innerPanel2.add(back);
		add(innerPanel2);

		JLabel contentPane = new JLabel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);
		frame.setVisible(true);

		back.addActionListener(highScoresCtrl);

		// scoresBackGrnd
		File file = new File(getClass().getResource("/supportMedia/highScoresBk.jpg").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);
		Graphics2D gc = (Graphics2D) g;
		gc.drawImage(img, -50, 0, null);
	}

	public JButton getBack() {

		return back;
	}

	public JFrame getFrame() {

		return frame;
	}

}
