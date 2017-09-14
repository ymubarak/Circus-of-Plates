package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Save;
import view.Circus;

public class HandleKeyBoard implements KeyListener {

	private int moveStep = 50;
	private Circus circus;
	private final static Logger logger =LogManager.getLogger();

	public HandleKeyBoard(Circus circus) {
		this.circus = circus;
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub

		if (evt != null) {
			int c = evt.getKeyCode();

			if (c == KeyEvent.VK_P) {

				circus.handlePauseLabel();
			}

			if (c == KeyEvent.VK_ESCAPE) {
				if (circus.isInGame()) {
					circus.showMenu();
					logger.info("Show inGame Menu and game paused");
				} else {
					circus.hideMenu();
					logger.info("Hide inGame Menu and game resumed");
				}
			}

			if (circus.isInGame()) {

				if (c == KeyEvent.VK_LEFT && (circus.getPlayer(0).getLocation() > 0))
					circus.getPlayer(0).setLocation(circus.getPlayer(0).getLocation() - moveStep);
				if (c == KeyEvent.VK_RIGHT
						&& (circus.getPlayer(0).getLocation() < Circus.WIDTH - circus.getPlayer(0).getWidth()))
					circus.getPlayer(0).setLocation(circus.getPlayer(0).getLocation() + moveStep);
				if (c == KeyEvent.VK_A && (circus.getPlayer(1).getLocation() > 0))
					circus.getPlayer(1).setLocation(circus.getPlayer(1).getLocation() - moveStep);
				if (c == KeyEvent.VK_D
						&& (circus.getPlayer(1).getLocation() < Circus.WIDTH - circus.getPlayer(1).getWidth()))
					circus.getPlayer(1).setLocation(circus.getPlayer(1).getLocation() + moveStep);
				if (c == KeyEvent.VK_S && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					new Save();
					circus.activeGameSaved();
					logger.info("Game Saved");
				}
				if (c == KeyEvent.VK_L && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					// Load load = new Load();
					//circus.activeGameLoaded();
				}
			}

		}
		circus.repaint();
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// TODO Auto-generated method stub

	}
}
