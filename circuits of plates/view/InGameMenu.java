package view;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.InGameMenCtrl;

public class InGameMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton resume, newGame, save, load, toMenu;
	private JPanel innerSelectPanel = null;
	private static InGameMenu inGameMenu;

	public static InGameMenu getInGameMenu() {

		if (inGameMenu == null)
			inGameMenu = new InGameMenu();

		return inGameMenu;
	}

	private InGameMenu() {

		this.frame = GameFrame.getGameFrame();
		initMenu();

	}

	public void initMenu() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/supportMedia/resume.png"));
		resume = new JButton(icon);
		resume.setLayout(new FlowLayout(FlowLayout.LEADING, 2, 20));
		resume.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		resume.setBorder(BorderFactory.createEmptyBorder());
		resume.setContentAreaFilled(false);
		resume.setBounds(0, 0, 300, 200);

		icon = new ImageIcon(getClass().getResource("/supportMedia/newgame.png"));

		newGame = new JButton(icon);
		newGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newGame.setBorder(BorderFactory.createEmptyBorder());
		newGame.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/save.png"));

		save = new JButton(icon);
		save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		save.setBorder(BorderFactory.createEmptyBorder());
		save.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/load.png"));
		load = new JButton(icon);
		load.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		load.setBorder(BorderFactory.createEmptyBorder());
		load.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/toMenu.png"));
		toMenu = new JButton(icon);
		toMenu.setLayout(new FlowLayout(FlowLayout.LEADING, 2, 20));
		toMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toMenu.setBorder(BorderFactory.createEmptyBorder());
		toMenu.setContentAreaFilled(false);

		display();
	}

	public void display() {

		innerSelectPanel = new JPanel(new GridLayout(5, 1, 0, 20));
		innerSelectPanel.add(resume);
		innerSelectPanel.add(newGame);
		innerSelectPanel.add(save);
		innerSelectPanel.add(load);
		innerSelectPanel.add(toMenu);
		innerSelectPanel.setBorder(BorderFactory.createEmptyBorder(50, 2, 0, 2));
		innerSelectPanel.setOpaque(false);
		add(innerSelectPanel);
		setOpaque(false);
		addListener(new InGameMenCtrl(this));

	}

	private void addListener(ActionListener listener) {

		resume.addActionListener(listener);
		newGame.addActionListener(listener);
		save.addActionListener(listener);
		load.addActionListener(listener);
		toMenu.addActionListener(listener);

	}

	@Override
	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);

	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getResume() {
		return resume;
	}

	public JButton getNewGame() {
		return newGame;
	}

	public JButton getSave() {
		return save;
	}

	public JButton getLoad() {
		return load;
	}

	public JButton getToMenu() {
		return toMenu;
	}
}
