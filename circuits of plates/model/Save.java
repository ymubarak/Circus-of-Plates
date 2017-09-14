package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import view.Circus;

public class Save {

	private String path = "gameSaved";
	private Circus circus;

	public Save() {

		circus = Circus.getCurrentCircus();

		saveState();
		SaveImages();

	}

	private void saveState() {

		FileOutputStream fileOut;
		try {

			checkFiles();
			fileOut = new FileOutputStream(new File(path + ".txt"));
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(circus.getPlayer(0));
			objOut.writeObject(circus.getPlayer(1));
			objOut.writeObject(circus.getTime());
			objOut.writeObject(circus.getTheme());
			objOut.close();
			System.out.println("Saved as : " + path + ".txt");
			Date date = new Date();
			GamePair gp = new GamePair(path, date.toString());
			SavedGames newSavedGame = new SavedGames();
			newSavedGame.updateSavedGames(gp);
			newSavedGame.save();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkFiles() {

		File file;
		for (int i = 0; i >= 0; i++) {
			file = new File(path + i + ".txt");
			if (i == 9) {
				path += i;
				break;
			}
			if (!file.exists()) {
				path += i;
				break;
			}
		}

	}

	private void SaveImages() {
		try {
			// retrieve image
			BufferedImage bi1 = circus.getPlayer(0).getImg();
			File outputfile1 = new File("player1.png");
			ImageIO.write(bi1, "png", outputfile1);
			BufferedImage bi2 = circus.getPlayer(1).getImg();
			File outputfile2 = new File("player2.png");
			ImageIO.write(bi2, "png", outputfile2);
		} catch (IOException e) {
		}
	}

}
