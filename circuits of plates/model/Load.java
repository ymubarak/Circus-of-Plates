package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

import view.Circus;

public class Load {

	private Circus circus;

	public Load(String fileName) {

		circus = Circus.getCurrentCircus();
		LoadState(fileName);
		LoadImages();
	}

	private void LoadState(String fileName) {

		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(new File(fileName + ".txt"));
			ObjectInputStream objInput = new ObjectInputStream(fileInput);
			Player p1 = (Player) objInput.readObject();
			Player p2 = (Player) objInput.readObject();
			circus.setPlayer(p1, p2);
			int time = (int) objInput.readObject();
			circus.setTime(time);
			String theme = (String) objInput.readObject();
			circus.setTheme(theme);
			objInput.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void LoadImages() {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("player1.png"));
			circus.getPlayer(0).setImage(img);
		} catch (IOException e) {

		}

		img = null;
		try {
			img = ImageIO.read(new File("player2.png"));
			circus.getPlayer(1).setImage(img);
		} catch (IOException e) {
		}

	}
}
