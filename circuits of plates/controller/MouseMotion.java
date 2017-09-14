package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import view.Circus;

public class MouseMotion implements MouseListener, MouseMotionListener {

	private Circus circus;

	public MouseMotion(Circus circus) {

		this.circus = circus;
		this.circus.addMouseListener(this);
		this.circus.addMouseMotionListener(this);
		this.circus.setCursor(this.circus.getToolkit()
				.createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		if (circus.isInGame()) {
			if ((e.getX() - circus.getPlayer(0).getWidth() / 2 > -1)
					&& (e.getX() + circus.getPlayer(0).getWidth() / 2 < Circus.WIDTH + 50))
				circus.getPlayer(0).setLocation(e.getX() - circus.getPlayer(0).getWidth() / 2);
			circus.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
