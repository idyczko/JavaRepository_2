package com.capgemini.taxi;

import java.util.Comparator;

public class DataComparator implements Comparator<Data> {

	private Position userPosition;

	public DataComparator(Position userPosition) {
		this.userPosition = userPosition;
	}

	public int compare(Data o1, Data o2) {
		return (o1.getPosition().distance(userPosition) - o2.getPosition().distance(userPosition));
	}

}
