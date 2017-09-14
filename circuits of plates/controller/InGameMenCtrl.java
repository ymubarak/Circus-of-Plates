package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Save;
import view.*;

public class InGameMenCtrl implements ActionListener {

	private InGameMenu inGameMenu;
	private Circus circus;
	private final static Logger logger =LogManager.getLogger();

	public InGameMenCtrl(InGameMenu menu) {

		inGameMenu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object source = e.getSource();
		this.circus = Circus.getCurrentCircus();

		if (source.equals(inGameMenu.getResume())) {
			circus.setGameStatus(true);
			circus.hideMenu();
			inGameMenu.getFrame().requestFocusInWindow();
			logger.info("Resume Game ");

		} else if (source.equals(inGameMenu.getNewGame())) {

			int reply = JOptionPane.showConfirmDialog(null, "Current game will be ended, Are you sure? \n", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				circus.setVisible(false);
				CircusCtrl.killThread();
				new ThemeMenu(inGameMenu.getFrame());
				logger.info("Quit current game");
				logger.info("Play New Game ");
			}
		} else if (source.equals(inGameMenu.getSave())) {
			circus.setGameStatus(true);
			new Save();
			circus.hideMenu();
			circus.activeGameSaved();
			logger.info("Saved Game done");
		} else if (source.equals(inGameMenu.getLoad())) {
			new LoadPage(inGameMenu.getFrame());
			logger.info("Loaded game done ");
		} else if (source.equals(inGameMenu.getToMenu())) {

			int reply = JOptionPane.showConfirmDialog(null, "Do you want to back to main menu ? \n", "Delete",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {

				circus.setVisible(false);
				CircusCtrl.killThread();
				inGameMenu.getFrame().add(MainMenu.getMainMenu());
				inGameMenu.getFrame().setVisible(true);
				logger.info("Quit currrent game ,Go to main menu");
			}

		}
	}
}
