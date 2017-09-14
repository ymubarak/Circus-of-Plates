package model;

import java.io.Serializable;

public class ScorePair implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String playerName;
	private int score;

	public ScorePair() {

	}

	public ScorePair(String playerName, int score) {

		this.playerName = playerName;
		this.score = score;
	}

	public String getPlayerName() {

		return playerName;
	}

	public int getScore() {

		return score;

	}
}
