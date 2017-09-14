package model;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

public class ShapesPool {

	private LinkedList<Shapes> reusableShapes;
	private ShapesFactory shapesFactory;
	private Shapes shape;

	public ShapesPool() {

		reusableShapes = new LinkedList<Shapes>();
		shapesFactory = ShapesFactory.getFactory();
	}

	public void updatePool(LinkedList<Shapes> reusableShapes) {

		this.reusableShapes = reusableShapes;
	}

	public boolean hasReusableShapes() {

		if (reusableShapes.size() > 0) {
			return true;
		}
		return false;
	}

	public Shapes getShape(Point position, Color color, int barNum) {

		if (hasReusableShapes()) {

			shape = reusableShapes.removeLast();

		} else {
			shape = shapesFactory.orderShapes();
			shape.setColor(color);
		}

		shape.setFree(false);
		shape.setPosition(position);
		shape.setBarNum(barNum);
		return shape;

	}

}
