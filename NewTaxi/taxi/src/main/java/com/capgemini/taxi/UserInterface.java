package com.capgemini.taxi;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface {

	public JFrame frame;
	final DefaultListModel model = new DefaultListModel();
	final JList list = new JList(model);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	public void addToModel(Data data) {
		model.addElement(data);
		list.setSize(100, model.getSize() * 30);
	}

	private void initialize() {
		list.setFixedCellHeight(30);
		list.setSize(100, 50);
		frame = new JFrame();
		frame.setBounds(100, 100, 386, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//model.addElement(new Data(new Position(5, 5), 5));
		list.setBounds(38, 177, 103, 23);
		frame.getContentPane().add(list);
		list.setCellRenderer(new DataListCellRenderer());

		JLabel lblListaTakswek = new JLabel("Lista taksówek:");
		lblListaTakswek.setBounds(76, 94, 196, 23);
		frame.getContentPane().add(lblListaTakswek);

	}
}
