package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HighScores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // Serialize the class
	private LinkedList<ScorePair> scores;
	private final static int MAXSCORES = 10;
	private final static Logger logger = LogManager.getLogger();

	public HighScores() {

		scores = new LinkedList<ScorePair>();
		loadHighScores();

	}

	public void updateHighScores(ScorePair p1, ScorePair p2) {

		if (p1.getScore() > p2.getScore()) {

			if (scores.size() == 0 && p1.getScore() > 0) {
				scores.add(p1);
				logger.info("New High score" + p1.getScore() +"for" + p1.getPlayerName());
			} else {
				for (int i = 0; i < scores.size(); i++) {
					if (p1.getScore() > (int) scores.get(i).getScore()) {
						scores.add(i, p1);
						logger.info("New High score" + p1.getScore() +"for" + p1.getPlayerName());
						if (scores.size() > MAXSCORES)
							scores.removeLast();
						break;
					}
				}
			}

		} else {

			if (scores.size() == 0 && p2.getScore() > 0) {
				scores.add(p2);
				logger.info("New High score" + p2.getScore() +"for" + p2.getPlayerName());
			} else {
				for (int i = 0; i < scores.size(); i++) {
					if (p2.getScore() > (int) scores.get(i).getScore()) {
						scores.add(i, p2);
						logger.info("New High score" + p2.getScore() +"for" + p2.getPlayerName());
						if (scores.size() > MAXSCORES)
							scores.removeLast();
						break;
					}
				}
			}
		}

	}

	public LinkedList<ScorePair> getHighScores() {

		return scores;
	}

	@SuppressWarnings("unchecked")
	public void loadHighScores() {

		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(new File("highScores.txt"));
			ObjectInputStream objInput = new ObjectInputStream(fileInput);
			scores = (LinkedList<ScorePair>) objInput.readObject();
			objInput.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save() {

		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File("highScores.txt"));
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(scores);
			objOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
