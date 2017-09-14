package model;

import java.util.Observable;
import java.util.Observer;

import Interfaces.LevelStrategy;
import controller.CircusCtrl;

public class LevelObserver implements Observer {

	private LevelStrategy level1, level2, level3;

	public LevelObserver(CircusCtrl cirCtrl) {

		level1 = new Level1(cirCtrl);
		level2 = new Level2(cirCtrl);
		level3 = new Level3(cirCtrl);
	}

	@Override
	public void update(Observable timer, Object time) {

		if ((int) time == 120) {
			level3.getHarder();
		} else if ((int) time == 60) {
			level2.getHarder();
		} else if ((int) time == 30) {
			level1.getHarder();
		}
	}
}
