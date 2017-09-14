package model;

import java.util.Observable;
import javax.swing.Timer;
import controller.CircusCtrl;

public class TheTimer extends Observable {

	private long startMillis = 0, lastUpdate = 0, remaining = 0;
	private int time;
	private CircusCtrl circusCtrl;
	private Timer timeDisplayer;

	public TheTimer(CircusCtrl cirCtrl) {

		circusCtrl = cirCtrl;
		addObserver(new LevelObserver(circusCtrl));
		time = 0;
	}

	public void startTimer() {

		timeDisplayer = new Timer(1000, e -> {

			if (!circusCtrl.isInGame()) {
				lastUpdate = (int) ((System.currentTimeMillis() - startMillis + 500L) / 1000L);
				remaining = lastUpdate - time;
			} else
				time = (int) ((int) ((System.currentTimeMillis() - startMillis - lastUpdate + 500L) / 1000L)
						- remaining);
			setChanged();
			notifyObservers(time);
		});

		timeDisplayer.setInitialDelay(0);
		startMillis = System.currentTimeMillis();
		timeDisplayer.start();

	}

	public int getTime() {
		return time;
	}

	public void stop() {
		timeDisplayer.stop();
	}

	public void setTime(int t) {
		remaining = -t;
	}
}
