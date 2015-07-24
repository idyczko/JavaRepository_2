package com.capgemini.taxi;

public class Taxi extends Data implements Runnable {
	private WorkArea workArea;
	private static final int CYCLES_TO_CHANGE_AVAILABILITY = 5;
	private static final int MILLIS_TO_ASK_TAXI = 5000;
	private boolean taxiAvailable;

	public Taxi(WorkArea workArea, int id) {
		super(workArea.generateRandomPosition(), id);
		this.workArea = workArea;
		this.taxiAvailable = true;
	}

	public void run() {
		int i = 0;
		while (true) {
			i++;
			moveTaxi();
			try {
				Thread.sleep(MILLIS_TO_ASK_TAXI);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			if (i == CYCLES_TO_CHANGE_AVAILABILITY) {
				i = 0;
				setTaxiAvailable(Math.random() > 0.5);
			}
		}
	}

	public void moveTaxi() {
		Position point = new Position(0, 0);
		do {
			point.setX(super.getPosition().getX() + (int) Math.floor(Math.random() * 5) - 2);
			point.setY(super.getPosition().getY() + (int) Math.floor(Math.random() * 5) - 2);
		} while (workArea.pointExceeds(point));
		super.setPosition(point);
	}


	public boolean isTaxiAvailable() {
		return taxiAvailable;
	}

	public void setTaxiAvailable(boolean taxiAvailable) {
		this.taxiAvailable = taxiAvailable;
	}

	public Position getPosition() {
		return super.getPosition();
	}

	public void setPosition(Position position) {
		super.setPosition(position);
	}

	public WorkArea getWorkArea() {
		return workArea;
	}

	public void setWorkArea(WorkArea workArea) {
		this.workArea = workArea;
	}

	public int getId() {
		return super.getId();
	}

}
