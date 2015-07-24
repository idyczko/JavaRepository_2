package com.capgemini.taxi;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	private ArrayList<Data> list = new ArrayList<Data>();
	private static String[] columns = { "Taxi ID", "Proximity" };

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Data getValueAt(int rowIndex, int columnIndex) {
		return list.get(rowIndex);
	}

}
