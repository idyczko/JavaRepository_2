package com.capgemini.taxi;

public class WorkArea {
	private Position position_1;
	private Position position_2;

	public WorkArea(Position position_1, Position position_2) {
		this.position_1 = position_1;
		this.position_2 = position_2;

	}

	public Position generateRandomPosition() {
		Position position = new Position(
				(int) Math.random() * (position_1.getX() - position_2.getX()) + position_2.getX(),
				(int) Math.random() * (position_1.getY() - position_2.getY()) + position_2.getY());
		return position;
	}

	public boolean pointExceeds(Position point) {
		return (point.getX() > position_1.getX() || point.getX() < position_2.getX() || point.getY() > position_1.getY()
				|| point.getY() < position_2.getY());
	}

	public Position getPosition_1() {
		return position_1;
	}

	public Position getPosition_2() {
		return position_2;
	}

}
