package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.GameOver;
import view.ThemeMenu;

public class GameOverMenCtrl implements ActionListener {

	private GameOver gameOver;
	private final static Logger logger = LogManager.getLogger();

	public GameOverMenCtrl(GameOver gOver) {

		gameOver = gOver;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source.equals(gameOver.getPlayAgain())) {

			new ThemeMenu(gameOver.getFrame());
			logger.info("Play Again");

		} else if (source.equals(gameOver.getExit())) {
			gameOver.getFrame().dispose();
			logger.info("Exited the Game ");
		}
	}
}
