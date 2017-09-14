package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.*;

public class ThemeMenuCtrl implements ActionListener {

	private ThemeMenu themeMenu;
	private final static Logger logger = LogManager.getLogger();

	public ThemeMenuCtrl(ThemeMenu ThemeMenu) {

		this.themeMenu = ThemeMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Circus circus = new Circus(false);
		if (e.getSource().equals(themeMenu.getTheme1())) {
			circus.setTheme("/supportMedia/theme1.jpg");
			logger.info("Theme 1 is choosen ");
		} else if (e.getSource().equals(themeMenu.getTheme2())) {
			circus.setTheme("/supportMedia/theme2.jpg");
			logger.info("Theme 2 is choosen ");
		} else if (e.getSource().equals(themeMenu.getTheme3())) {
			circus.setTheme("/supportMedia/theme3.png");
			logger.info("Theme 3 is choosen ");
		}
	}

}
