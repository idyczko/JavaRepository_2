package com.capgemini.taxi;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int distance(Position position) {
		double a = this.x - position.getX();
		double b = this.y - position.getY();
		return (int) Math.sqrt(a * a + b * b);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
