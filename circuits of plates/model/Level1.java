package model;

import Interfaces.LevelStrategy;
import controller.CircusCtrl;

public class Level1 implements LevelStrategy {

	private CircusCtrl circusCtrl;

	public Level1(CircusCtrl cirCtrl) {
		
		circusCtrl = cirCtrl;
	}

	@Override
	public void getHarder() {
		// TODO Auto-generated method stub
		circusCtrl.setNumOfColor(5);
		circusCtrl.setQueuePace(35);
		playLevelSound();
	}

}
