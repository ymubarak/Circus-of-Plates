package controller;

import java.awt.Point;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Interfaces.State;
import model.*;
import view.Circus;
import view.GameOver;

public class CircusCtrl {

	private JTextField field1 = new JTextField(), field2 = new JTextField();
	private Object[] message = { "Player1 Name :", field1, "Player1 Name :", field2 };
	private String Player1Name, Player2Name;
	private static Timer taskPerformer;
	private boolean barsActivated = false;
	private static TheTimer timeDisplayer;
	private int farFromCeil = 150, speed = 40, queuePace = 50, delayCount = 0, suspendPresent = 0, random1 = 0,
			random2 = 0, random3 = 0, random4 = 0, jokeOwner = 0;
	private Point[] posArray;
	private LinkedList<Shapes> shapes, destroyedShapes, wentToStack;
	private static Circus circus;
	private ShapesPool shapesPool;
	private State onBar, falling;
	private final static Logger logger = LogManager.getLogger();

	public CircusCtrl(Circus circus) {
		CircusCtrl.circus = Circus.getCurrentCircus();
		shapesPool = new ShapesPool();
		shapes = new LinkedList<Shapes>();
		destroyedShapes = new LinkedList<Shapes>();
		wentToStack = new LinkedList<Shapes>();
		onBar = new OnBar(circus);
		falling = new Falling();
		timeDisplayer = new TheTimer(this);

	}

	public void setBasicPositions() {
		posArray = new Point[4];
		for (int i = 0; i < posArray.length; i++) {
			posArray[i] = new Point();
		}
		posArray[0].x = -1;
		posArray[0].y = 1225 - circus.getBigLeftBar().getFarFromGrnd() - 2;
		posArray[1].x = -1;
		posArray[1].y = 1225 - circus.getSmlLeftBar().getFarFromGrnd() - 2;
		posArray[2].x = Circus.WIDTH + 1;
		posArray[2].y = 1225 - circus.getBigRightBar().getFarFromGrnd() - 2;
		posArray[3].x = Circus.WIDTH + 1;
		posArray[3].y = 1225 - circus.getSmlRightBar().getFarFromGrnd() - 2;

	}

	public void setQueuePace(int q) {
		queuePace = q;
	}

	public void setNumOfColor(int n) {
		circus.setNumOfColor(n);
	}

	public void setTime(int t) {
		timeDisplayer.setTime(t);
	}

	public boolean isInGame() {
		return circus.isInGame();
	}

	public void activeMoveBars() {
		barsActivated = true;
	}

	public void getPlayerNames() {
		JOptionPane.showConfirmDialog(circus.getParent(), message, "Enter player names", JOptionPane.OK_CANCEL_OPTION);
		Player1Name = field1.getText();
		Player2Name = field2.getText();
		if (Player1Name.isEmpty()) {
			Player1Name = "Player1";
		}
		if (Player2Name.isEmpty()) {
			Player2Name = "Player2";
		}
		circus.setPlayerNames(Player1Name, Player2Name);
	}

	public void createShapes() {
		for (int i = 0; i < posArray.length; i++) {
			if (suspendPresent == 40) {
				suspendPresent = 0;
				Present present = new Present(null, posArray[i], i);
				shapes.add(present);
				logger.info("New present is created  ");
			} else {
				//suspendPresent++;
				shapes.add(shapesPool.getShape(posArray[i], circus.getRandomColor(), i));
				logger.info("a new" + shapes.getLast().getClass().getName() + "shape is created");
			}
		}
	}

	public void playGame() {
		taskPerformer = new Timer(1, e -> {
			if (circus.isInGame()) {
				moveShapes();
				circus.handleScoreBoard(timeDisplayer.getTime());
				if (delayCount >= queuePace) {
					delayCount = 0;
					createShapes();
				} else
					delayCount++;
				if (circus.getGameSaved())
					circus.savedGame();
				if (circus.getGameLoaded())
					circus.loadedGame();
				if (barsActivated)
					moveBars();
				checkGameEnd();
				circus.repaint();
			}
		});
		taskPerformer.start();
		timeDisplayer.startTimer();
	}

	private void moveShapes() {
		for (Shapes s : shapes) {
			if (s.Free()) {
				if (circus.getPlayer(0).withinLeftStack(s.getPosition(), s.getWidth(),
						circus.getPlayer(0).getLocation(), circus.getPlayer(0).getLeftStackPoint().y)) {
					wentToStack.add(s);
					circus.getPlayer(0).updateLeftStack(s);
					jokeOwner = 0;
					logger.info(circus.getPlayer(0).getName() +"'s left stack increased" );
				} else if (circus.getPlayer(0).withinRightStack(s.getPosition(), s.getWidth(),
						circus.getPlayer(0).getLocation(), circus.getPlayer(0).getRightStackPoint().y)) {
					wentToStack.add(s);
					circus.getPlayer(0).updateRightStack(s);
					jokeOwner = 0;
					logger.info(circus.getPlayer(0).getName() +"'s right stack increased" );
				} else if (circus.getPlayer(1).withinLeftStack(s.getPosition(), s.getWidth(),
						circus.getPlayer(1).getLocation(), circus.getPlayer(1).getLeftStackPoint().y)) {
					wentToStack.add(s);
					circus.getPlayer(1).updateLeftStack(s);
					jokeOwner = 1;
					logger.info(circus.getPlayer(1).getName() +"'s left stack increased" );
				} else if (circus.getPlayer(1).withinRightStack(s.getPosition(), s.getWidth(),
						circus.getPlayer(1).getLocation(), circus.getPlayer(1).getRightStackPoint().y)) {
					wentToStack.add(s);
					circus.getPlayer(1).updateRightStack(s);
					jokeOwner = 1;
					logger.info(circus.getPlayer(1).getName() +"'s right stack increased" );
				} else if (s.getPosition().y >= Circus.HEIGHT - 140) {
					destroyedShapes.add(s);
					logger.info(destroyedShapes.getLast().getClass().getName() + "shape is added to reusabelPool");
				} else {
					falling.move(s);
				}
			} else {
				onBar.move(s);
			}
		}
		shapes.removeAll(wentToStack);
		shapes.removeAll(destroyedShapes);
		circus.setShapes(shapes);
		// wentToStack.clear();
		shapesPool.updatePool(destroyedShapes);
		circus.repaint();
	}

	public void moveBars() {
		int pace = 5;
		if (random1 == 0) {
			if (circus.getBigLeftBar().getWidth() > circus.getBigLeftBar().getImg().getWidth(null) / 4) {
				circus.getBigLeftBar().setWidth(circus.getBigLeftBar().getWidth() - pace);
			} else
				random1 = 1;
		} else {
			if (circus.getBigLeftBar().getWidth() < circus.getBigLeftBar().getImg().getWidth(null)) {
				circus.getBigLeftBar().setWidth(circus.getBigLeftBar().getWidth() + pace);
			} else
				random1 = 0;
		}
		if (random2 == 0) {
			if (circus.getSmlLeftBar().getWidth() > circus.getSmlLeftBar().getImg().getWidth(null) / 4) {
				circus.getSmlLeftBar().setWidth(circus.getSmlLeftBar().getWidth() - pace);
			} else
				random2 = 1;
		} else {
			if (circus.getSmlLeftBar().getWidth() < circus.getSmlLeftBar().getImg().getWidth(null)) {
				circus.getSmlLeftBar().setWidth(circus.getSmlLeftBar().getWidth() + pace);
			} else
				random2 = 0;
		}
		if (random3 == 0) {
			if (circus.getBigRightBar().getWidth() > circus.getBigRightBar().getImg().getWidth(null) / 4) {
				circus.getBigRightBar().setWidth(circus.getBigRightBar().getWidth() - pace);

			} else
				random3 = 1;
		} else {
			if (circus.getBigRightBar().getWidth() < circus.getBigRightBar().getImg().getWidth(null)) {
				circus.getBigRightBar().setWidth(circus.getBigRightBar().getWidth() + pace);
			} else
				random3 = 0;
		}
		if (random4 == 0) {
			if (circus.getSmlRightBar().getWidth() > circus.getSmlRightBar().getImg().getWidth(null) / 4) {
				circus.getSmlRightBar().setWidth(circus.getSmlRightBar().getWidth() - pace);
			} else
				random4 = 1;
		} else {
			if (circus.getSmlRightBar().getWidth() < circus.getSmlRightBar().getImg().getWidth(null)) {
				circus.getSmlRightBar().setWidth(circus.getSmlRightBar().getWidth() + pace);
			} else
				random4 = 0;
		}

	}

	private void checkGameEnd() {

		if ((circus.getPlayer(0).getLeftStackPoint().y < farFromCeil
				&& circus.getPlayer(0).getRightStackPoint().y < farFromCeil)
				|| (circus.getPlayer(1).getLeftStackPoint().y < farFromCeil
						&& circus.getPlayer(1).getRightStackPoint().y < farFromCeil)) {
			playEndSound();
			gameOver();
			logger.info("Game Over" );
		}

	}

	private void gameOver() {

		ScorePair Pair1 = new ScorePair(circus.getPlayer(0).getName(), circus.getPlayer(0).getScore());
		ScorePair Pair2 = new ScorePair(circus.getPlayer(1).getName(), circus.getPlayer(1).getScore());
		HighScores newScore = new HighScores();
		newScore.updateHighScores(Pair1, Pair2);
		newScore.save();
		circus.setGameStatus(false);
		new GameOver(circus.getFrame(), Pair1, Pair2);
		killThread();

	}

	public static void killThread() {

		timeDisplayer.stop();
		taskPerformer.stop();
		//circus = null;
		System.out.println("killed");
	}

	private void playEndSound() {

		File file = new File(getClass().getResource("/supportMedia/endgame.wav").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		AudioPlayer.main(null, path);
	}
}
