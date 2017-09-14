package model;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Interfaces.Container;
import Interfaces.Iterator;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class ShapesCollection implements Container {

	private LinkedList<Class<?>> shapesClasses;
	private DynamicLinkage D;
	private File dir;
	private File[] dirFiles;
	private final static Logger logger = LogManager.getLogger();

	public ShapesCollection() {

		shapesClasses = new LinkedList<Class<?>>();
		D = new DynamicLinkage();
		load();
	}

	private void load() {

		URI dirPath;
		try {
			dirPath = getClass().getResource("/externalShapes").toURI();
			dir = new File(dirPath);

		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.catching(e);
		}
		dirFiles = dir.listFiles();

		if (dirFiles != null) {

			for (Iterator iter = getIterator(); iter.hasNext();) {
				File shapeFile = (File) iter.next();
				shapesClasses.add(D.loadShape(shapeFile));
			}

		} else {
			System.out.println("invalid Dir !");
			logger.debug("Invalid Dirictory of Shapes");

		}

		System.out.println("classes groups : ");
		for (Class<?> a : shapesClasses)
			System.out.println(a);

	}

	public LinkedList<Class<?>> getLoadedShapes() {

		return shapesClasses;
	}

	public int numOfLoadedShapes() {

		return shapesClasses.size();
	}

	@Override
	public Iterator getIterator() {
		return new ShapesIterator();
	}

	private class ShapesIterator implements Iterator {

		int index;

		@Override
		public boolean hasNext() {
			if (index < dirFiles.length)
				return true;
			return false;
		}

		@Override
		public Object next() {
			if (this.hasNext())
				return dirFiles[index++];
			return null;
		}

	}

}
