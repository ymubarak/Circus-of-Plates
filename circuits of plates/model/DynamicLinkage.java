package model;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamicLinkage {

	private Class<?> loadedClass;
	private final static Logger logger = LogManager.getLogger();

	public DynamicLinkage() {

		loadedClass = null;
	}

	public Class<?> loadShape(File shapeClass) {

		try {
			String[] pathSections = shapeClass.getAbsolutePath().replace('\\', ' ').split(" ");
			int len = pathSections.length;
			String finalDistination = pathSections[len - 2] + "." + pathSections[len - 1].split("\\.")[0];
			loadedClass = getClass().getClassLoader().loadClass(finalDistination);
		} catch (ClassNotFoundException e) {
			logger.catching(e);
		}

		return loadedClass;
	}

}
