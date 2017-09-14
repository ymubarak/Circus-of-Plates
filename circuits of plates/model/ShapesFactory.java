package model;

import java.util.LinkedList;

public class ShapesFactory {

	private LinkedList<Class<?>> shapes;
	private static ShapesFactory shapesFactory;
	private int random;

	private ShapesFactory() {

		ShapesCollection shapesCollection = new ShapesCollection();
		shapes = shapesCollection.getLoadedShapes();
	}

	public static ShapesFactory getFactory() {

		if (shapesFactory == null)
			shapesFactory = new ShapesFactory();

		return shapesFactory;
	}

	public Shapes orderShapes() {

		random = (int) (shapes.size() * Math.random());
		try {
			return (Shapes) shapes.get(random).newInstance();
		} catch (InstantiationException | NullPointerException | IllegalAccessException e) {
			return null;
		}
	}
}
