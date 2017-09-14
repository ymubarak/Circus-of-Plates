package externalShapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import model.Shapes;
public class Hexagon extends Shapes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Hexagon() {
		super();
	}
	
	public Hexagon(Color color, Point position, int barNum) {
		super(color, position, barNum);

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		int[] x = { getPosition().x, getPosition().x + 8, getPosition().x + 16, getPosition().x + 24,
				getPosition().x + 16, getPosition().x + 8 };
		int[] y = { getPosition().y + 12, getPosition().y, getPosition().y, getPosition().y + 12, getPosition().y + 24,
				getPosition().y + 24 };

		g.setColor(getColor());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillPolygon(x, y, 6);
	}

}
