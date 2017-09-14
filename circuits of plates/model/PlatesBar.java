package model;

import java.awt.Image;
import java.awt.Graphics2D;

public class PlatesBar {

	private Image img;
	private int farFromGround, width, height, speed;
	private int barNum;

	public PlatesBar(Image img, int width, int farFromGround, int barNum) {
		this.img = img;
		this.width = width;
		this.height = img.getHeight(null);
		this.farFromGround = farFromGround;
		this.barNum = barNum;
		speed = 140;

	}

	public int getSpeed() {

		return speed;
	}

	public Image getImg() {
		return img;
	}

	public int getWidth() {
		return width;
	}

	public int getFarFromGrnd() {
		return farFromGround;
	}

	public int getHeight() {
		return height;
	}

	public void setSpeed(int sp) {

		this.speed = sp;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}

	public void draw(Graphics2D g) {

		if (barNum == 0 || barNum == 1) {

			g.drawImage(img, width - img.getWidth(null), 900 - farFromGround, null);
		} else
			g.drawImage(img, 1900 - width+20, 900 - farFromGround, null);

	}
}
