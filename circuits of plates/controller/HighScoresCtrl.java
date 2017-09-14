package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.HighScores;
import model.ScorePair;
import view.*;

public class HighScoresCtrl implements ActionListener {

	private HighScoresView highScoresView;
	private HighScores highScores;
	private LinkedList<ScorePair> scores;
	private final static Logger logger =LogManager.getLogger();

	public HighScoresCtrl(HighScoresView h) {

		highScoresView = h;
		highScores = new HighScores();
		scores = highScores.getHighScores();
		highScoresView.gatherScores(scores);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(highScoresView.getBack())) {
			highScoresView.setVisible(false);
			highScoresView.getFrame().add(MainMenu.getMainMenu());
			logger.info("Back to Main Menu");
		}

	}
}
