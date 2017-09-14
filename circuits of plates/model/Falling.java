package model;

import java.awt.Point;

import Interfaces.State;

public class Falling implements State {


	@Override
	public void move(Shapes s) {

		Point p = new Point();
		p.x = s.getPosition().x;
		p.y = s.getPosition().y + 2;
		s.setPosition(p);
	}

}
