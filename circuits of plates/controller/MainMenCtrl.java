package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.*;

public class MainMenCtrl implements ActionListener {

	private MainMenu mainMenu;
	private final static Logger logger =LogManager.getLogger();

	public MainMenCtrl(MainMenu m) {

		mainMenu = m;
		logger.info("Main menu Opened");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object source = e.getSource();

		if (source.equals(mainMenu.getNewGame())) {
			new ThemeMenu(mainMenu.getFrame());
			logger.info("Play new game ");
		} else if (source.equals(mainMenu.getHighScores())) {
			new HighScoresView(mainMenu.getFrame());
			logger.info("Open highscores menu ");
		} else if (source.equals(mainMenu.getLoad())) {
			new LoadPage(mainMenu.getFrame());
			logger.info("Open Load Menu");
		} else if (source.equals(mainMenu.getExit())) {
			mainMenu.getFrame().dispose();
			logger.info("Exited from the game ");
		}
	}
}
