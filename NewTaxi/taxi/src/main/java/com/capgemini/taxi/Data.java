package com.capgemini.taxi;

public class Data {
	private Position position;
	private int id;

	public Data(Position position, int id) {
		this.position = position;
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

}
