package model;

import java.awt.Point;

import Interfaces.State;
import view.Circus;

public class OnBar implements State {

	private Circus circus;

	public OnBar(Circus circus) {

		this.circus = circus;
	}


	@Override
	public void move(Shapes s) {

		Point p = new Point();
		if (s.getPosition().x <= circus.getBigLeftBar().getWidth() && s.getBarNum() == 0) {
			p.x = s.getPosition().x + 2;
			p.y = s.getPosition().y;
			s.setPosition(p);
		} else if (s.getPosition().x <= circus.getSmlLeftBar().getWidth() && s.getBarNum() == 1) {
			p.x = s.getPosition().x + 2;
			p.y = s.getPosition().y;
			s.setPosition(p);
		} else if (s.getPosition().x >= Circus.WIDTH - circus.getBigRightBar().getWidth() && s.getBarNum() == 2) {
			p.x = s.getPosition().x - 2;
			p.y = s.getPosition().y;
			s.setPosition(p);
		} else if (s.getPosition().x >= Circus.WIDTH - circus.getSmlRightBar().getWidth() && s.getBarNum() == 3) {
			p.x = s.getPosition().x - 2;
			p.y = s.getPosition().y;
			s.setPosition(p);
		} else
			s.setFree(true);
	}

}
