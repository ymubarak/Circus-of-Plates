package view;

import java.awt.FlowLayout;
import javax.swing.JFrame;

import controller.WindowHandler;

public class GameFrame extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private static GameFrame gameFrame;

	public static GameFrame getGameFrame() {
		return gameFrame;
	}

	private GameFrame() {
		super("Circus of plates");
		gameFrame = this;
		initUI();
	}

	private void initUI() {

		FlowLayout layout = new FlowLayout();
		setLayout(layout);
		MainMenu.getMainMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		addWindowListener(new WindowHandler(this));

	}

	public static void main(String[] args) {
		new GameFrame();

	}
}
