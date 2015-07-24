package com.capgemini.taxi;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class DataListCellRenderer extends DefaultListCellRenderer {
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof Data) {
			Data data = (Data) value;
			Position userPosition = new Position(5, 2);
			setText(String.valueOf(data.getId()) + String.valueOf(data.getPosition().distance(userPosition)));
			setToolTipText(String.valueOf(data.getId()) + String.valueOf(data.getPosition().distance(userPosition)));
			// setIcon(ingredient.getIcon());
			setFont(new Font("Serif", Font.BOLD, 34));
		}
		return this;
	}
}
