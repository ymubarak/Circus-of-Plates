package model;

import Interfaces.LevelStrategy;
import controller.CircusCtrl;

public class Level2 implements LevelStrategy{

	private CircusCtrl circusCtrl;

	public Level2(CircusCtrl cirCtrl) {
		
		circusCtrl = cirCtrl;
	}
	
	@Override
	public void getHarder() {

		circusCtrl.setNumOfColor(7);
		circusCtrl.setQueuePace(25);
		playLevelSound();
	}

}
