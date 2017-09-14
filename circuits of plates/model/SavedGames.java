package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class SavedGames {

	private LinkedList<GamePair> savedGames;
	private static final int MAXSIZE = 10;

	public SavedGames() {

		savedGames = new LinkedList<GamePair>();
		loadSavedGames();
	}

	public void updateSavedGames(GamePair g) {

		if (savedGames.size() <= MAXSIZE)
			savedGames.add(g);

	}

	public LinkedList<GamePair> getSavedGames() {

		return savedGames;
	}

	@SuppressWarnings("unchecked")
	public void loadSavedGames() {

		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(new File("savedGames.txt"));
			ObjectInputStream objInput = new ObjectInputStream(fileInput);
			savedGames = (LinkedList<GamePair>) objInput.readObject();
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
			fileOut = new FileOutputStream(new File("savedGames.txt"));
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(savedGames);
			objOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteGame(String name) {

		save();
		File file = new File(name + ".txt");
		if (file.exists()) {
			file.delete();
		}
	}
}
