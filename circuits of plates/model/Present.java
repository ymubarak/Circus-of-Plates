package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Present extends Shapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Present() {
		super();
	}

	public Present(Color color, Point position, int barNum) {
		super(color, position, barNum);
		BufferedImage img = null;
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("circusOfPlates/supportMedia/gift.png").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImg(img);

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(getImg(), getPosition().x, getPosition().y, null);
	}

}
