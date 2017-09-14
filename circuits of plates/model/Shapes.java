package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;

abstract public class Shapes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private transient BufferedImage img;
	private int witdh, height;
	private Point position;
	private int barNum;
	private boolean free;

	public Shapes() {
		free = false;
		witdh = height = 24;
	}

	public Shapes(Color color, Point position, int barNum) {
		this.color = color;
		this.position = position;
		this.barNum = 0;

	}

	public int getWidth() {

		if (img != null)
			return img.getWidth();

		return witdh;
	}

	public int getHeight() {

		if (img != null)
			return img.getHeight();

		return height;
	}

	public BufferedImage getImg() {
		return img;

	}

	public Color getColor() {
		return color;

	}

	public Point getPosition() {
		return position;
	}

	public int getBarNum() {
		return barNum;
	}

	public void setBarNum(int bn) {
		barNum = bn;
	}

	public boolean Free() {
		return free;
	}

	public void setFree(boolean b) {
		this.free = b;
	}

	public void setImg(BufferedImage img) {
		this.img = img;

	}

	public void setColor(Color col) {
		this.color = col;

	}

	public void setPosition(Point p) {

		this.position = p;

	}

	abstract public void draw(Graphics2D g);

}
