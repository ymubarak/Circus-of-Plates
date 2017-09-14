package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int location, score, width, height;
	private Stack<Shapes> leftStack, rightStack;
	private Point leftStackPos, rightStackPos;
	private transient BufferedImage img;
	private final static Logger logger = LogManager.getLogger();

	public Player(String name, int location, BufferedImage playerImage) {

		this.name = name;
		this.location = location;
		this.img = playerImage;
		this.width = img.getWidth();
		this.height = img.getHeight();
		leftStack = new Stack<Shapes>();
		rightStack = new Stack<Shapes>();
		leftStackPos = new Point(location + 61, 1127 - height);
		rightStackPos = new Point(location + getWidth() - 90, 1137 - height);
		score = 0;

	}

	public String getName() {

		return name;
	}

	public int getLocation() {

		return location;
	}

	public Point getLeftStackPoint() {

		return leftStackPos;
	}

	public Point getRightStackPoint() {

		return rightStackPos;
	}

	public boolean withinLeftStack(Point p, int shapeWidth, int loc, int leftStHeight) {
		if (p.x >= loc + 61 && p.x <= loc + shapeWidth + 61
				&& (p.y + 24 >= leftStHeight - 5 && p.y + 24 <= leftStHeight) && leftStHeight > 100)
			return true;
		return false;
	}

	public boolean withinRightStack(Point p, int shapeWidth, int loc, int rightStHeight) {

		if (p.x >= loc + width - 90 && p.x <= loc + width + shapeWidth - 90
				&& (p.y + 24 >= rightStHeight - 5 && p.y + 24 <= rightStHeight) && rightStHeight > 100)
			return true;
		return false;
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getScore() {
		return score;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setName(String name) {

	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	public void setLocation(int loc) {
		this.location = loc;
	}

	public boolean intersects() {
		return false;
	}

	public void draw(Graphics2D g) {

		g.drawImage(img, location, 1020 - height, null);

		int i = 1;
		for (Shapes s1 : leftStack) {
			leftStackPos.x = location + 61;
			leftStackPos.y = 1116 - height - s1.getHeight() * i;
			s1.setPosition(leftStackPos);
			s1.draw(g);
			i++;
		}
		i = 1;
		for (Shapes s2 : rightStack) {

			rightStackPos.x = location + getWidth() - 90;
			rightStackPos.y = 1113 - height - s2.getHeight() * i;
			s2.setPosition(rightStackPos);
			s2.draw(g);
			i++;
		}
	}

	public void updateLeftStack(Shapes s) {

		leftStack.push(s);
		try {
			TimeUnit.MICROSECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (s.getColor() == null) {
			this.score++;
			logger.info(getName() + "'s score increased to " + score);
			scorePointSound();
			// Circus.joke = 0;
			leftStack.pop();

		}
		int size = leftStack.size();
		if (size >= 3) {

			if (leftStack.peek().getColor() == leftStack.get(size - 2).getColor()
					&& leftStack.peek().getColor() == leftStack.get(size - 3).getColor()) {
				for (int i = 0; i < 3; i++)
					leftStack.pop();

				this.score++;
				logger.info(getName() + "'s score increased to " + score);
				// Circus.joke = 0;
				scorePointSound();
			}
		}

	}

	public void updateRightStack(Shapes s) {

		rightStack.push(s);
		try {
			TimeUnit.MICROSECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (s.getColor() == null) {
			this.score++;
			scorePointSound();
			// Circus.joke = 0;
			logger.info(getName() + "'s score increased to " + score);
			rightStack.pop();
		}
		int size = rightStack.size();
		if (size >= 3) {

			if (rightStack.peek().getColor() == rightStack.get(size - 2).getColor()
					&& rightStack.peek().getColor() == rightStack.get(size - 3).getColor()) {
				for (int i = 0; i < 3; i++)
					rightStack.pop();

				this.score++;
				logger.info(getName() + "'s score increased to " + score);
				scorePointSound();
				// Circus.joke = 0;
			}
		}
	}

	public void scorePointSound() {

		File file = new File(getClass().getResource("/supportMedia/scoreSound.wav").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		AudioPlayer.main(null, path);
	}
}
