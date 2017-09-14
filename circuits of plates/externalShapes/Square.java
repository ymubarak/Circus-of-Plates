package externalShapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import model.Shapes;
public class Square extends Shapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Square() {
		super();
	}
	
	public Square(Color color, Point position, int barNum) {
		super(color, position, barNum);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		g.setColor(getColor());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(getPosition().x, getPosition().y, 24, 24);
	}

}
