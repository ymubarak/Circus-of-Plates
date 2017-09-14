package model;

import java.io.Serializable;

public class GamePair implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String date;

	public GamePair() {

	}

	public GamePair(String fileName, String date) {

		this.fileName = fileName;
		this.date = date;
	}

	public String getFileName() {

		return fileName;
	}

	public String getDate() {

		return date;

	}
}
