package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.GamePair;
import model.Load;
import model.SavedGames;
import view.*;

public class LoadPageCtrl implements ActionListener, KeyListener {

	private LoadPage loadPage;
	private LinkedList<GamePair> savedGames;
	private SavedGames sGames;
	private String gamesData;
	private JLabel DataLabel;
	private LinkedList<JLabel> labels;
	private int pointer;
	private final static Logger logger =LogManager.getLogger();

	public LoadPageCtrl(LoadPage lp) {

		loadPage = lp;
		sGames = new SavedGames();
		savedGames = sGames.getSavedGames();
		labels = new LinkedList<JLabel>();
		gamesData = "";
		pointer = 0;
		gatherData();
	}

	private void gatherData() {

		for (GamePair g : savedGames) {
			gamesData = "  " + g.getFileName() + " ................... " + g.getDate() + "  <br>";
			DataLabel = new JLabel();
			DataLabel.setText("<html><pre>" + gamesData + "</pre></html>");
			labels.add(DataLabel);
		}

		loadPage.highLightChoice(labels, pointer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(loadPage.getBack())) {
			loadPage.setVisible(false);
			if (Circus.getCurrentCircus() == null) {
				loadPage.getFrame().add(MainMenu.getMainMenu());
				logger.info("Back to Main menu");
			} else {
				loadPage.getFrame().add(Circus.getCurrentCircus());
				logger.info("Back to the current game menu");
			}
		} else if (e.getSource().equals(loadPage.getDelete())) {

			if (pointer < savedGames.size()) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this game? \n",
						"Delete", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

					labels.remove(pointer);
					String temp = savedGames.remove(pointer).getFileName();
					sGames.deleteGame(temp);
					loadPage.highLightChoice(labels, pointer);
					logger.info("Delted " + temp + " from saved Games");
				}
				loadPage.getFrame().requestFocusInWindow();
				loadPage.getFrame().setVisible(true);
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent evt) {

		if (evt != null) {
			int c = evt.getKeyCode();

			if (c == KeyEvent.VK_UP) {

				pointer--;
				if (pointer < 0)
					pointer = savedGames.size() - 1;
				loadPage.highLightChoice(labels, pointer);

			} else if (c == KeyEvent.VK_DOWN) {

				pointer++;
				if (pointer >= savedGames.size())
					pointer = 0;
				loadPage.highLightChoice(labels, pointer);
			} else if (c == KeyEvent.VK_ENTER) {

				Circus circus;
				if (Circus.getCurrentCircus() == null)
					circus = new Circus(true);
				else {
					CircusCtrl.killThread();
					circus = new Circus(true);
				}

				String temp = savedGames.get(pointer).getFileName();
				new Load(savedGames.get(pointer).getFileName());
				circus.activeGameLoaded();
				logger.info("choose " + temp + " to be loaded");
				logger.info("Load game done");
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent evt) {

	}

	@Override
	public void keyTyped(KeyEvent evt) {

	}
}
