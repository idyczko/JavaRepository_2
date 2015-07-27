package com.capgemini.taxi;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UserInterface {

	public JFrame frame;
	private JPopupMenu popup;
	final DefaultListModel model = new DefaultListModel();
	final JList list = new JList(model);
	private JTextField textField;

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
		final JLabel status = new JLabel("StatusBar");
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		addToModel(new Data(new Position(5, 5), 5));
		status.setBorder(BorderFactory.createEtchedBorder());
		frame = new JFrame();
		frame.setBounds(100, 100, 386, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 177, 240, 214);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(list);
		list.setFixedCellHeight(30);
		list.setCellRenderer(new DataListCellRenderer());

		final JLabel lblListaTakswek = new JLabel("Lista taksówek:");
		lblListaTakswek.setBounds(38, 153, 196, 23);
		frame.getContentPane().add(lblListaTakswek);
		final JPanel pnl = (JPanel) frame.getContentPane();
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(pnl, "Are you sure to quit?", "Question", JOptionPane.QUESTION_MESSAGE);
			}
		});
		btnNewButton.setBounds(25, 91, 97, 25);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setMnemonic(KeyEvent.VK_B);
		btnNewButton.setToolTipText("Press me!");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 368, 26);
		frame.getContentPane().add(menuBar);

		JMenu file = new JMenu("File");
		JMenu imp = new JMenu("Import");
		JMenu view = new JMenu("View");
		JMenu helpMenu = new JMenu("Help");
		view.setMnemonic(KeyEvent.VK_V);
		file.setMnemonic(KeyEvent.VK_F);
		JCheckBoxMenuItem checkItem = new JCheckBoxMenuItem("Show statusbar");
		checkItem.setMnemonic(KeyEvent.VK_S);
		checkItem.setDisplayedMnemonicIndex(5);
		checkItem.setSelected(true);
		final JToolBar toolbar = new JToolBar();
		JButton exitButton = new JButton("exit");
		toolbar.add(exitButton);
		checkItem.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					status.setVisible(true);
					toolbar.setVisible(true);
				} else {
					status.setVisible(false);
					toolbar.setVisible(false);
				}

			}

		});
		JMenuItem newsfMi = new JMenuItem("Import newsfeed list...");
		JMenuItem bookmMi = new JMenuItem("Import bookmarks...");
		JMenuItem mailMi = new JMenuItem("Import mail...");

		imp.add(newsfMi);
		imp.add(bookmMi);
		imp.add(mailMi);
		view.add(checkItem);

		JMenuItem newMi = new JMenuItem("New");
		JMenuItem openMi = new JMenuItem("Open", KeyEvent.VK_O);
		JMenuItem saveMi = new JMenuItem("Save");

		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		file.add(newMi);
		file.add(openMi);
		file.add(saveMi);
		file.addSeparator();
		file.add(imp);
		file.addSeparator();
		file.add(eMenuItem);
		menuBar.add(file);
		menuBar.add(view);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(helpMenu);
		// menuBar.add(imp);
		menuBar.add(status, BorderLayout.EAST);
		popup = new JPopupMenu();

		JMenuItem maxMi = new JMenuItem("Maximize");
		maxMi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (frame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}

			}
		});

		popup.add(maxMi);

		JMenuItem quitMi = new JMenuItem("Quit");
		quitMi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		popup.add(quitMi);

		frame.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				int i = Integer.parseInt(textField.getText());
				System.out.println(i);
				// System.exit(0);
			}
		});

		menuBar.add(toolbar, BorderLayout.NORTH);

		final JCheckBox chckbxChekc = new JCheckBox("Chekc");
		chckbxChekc.setSelected(true);
		chckbxChekc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chckbxChekc.isSelected()) {
					status.setVisible(true);
					toolbar.setVisible(true);
				} else {
					status.setVisible(false);
					toolbar.setVisible(false);
				}
			}
		});
		chckbxChekc.setBounds(214, 69, 113, 25);
		frame.getContentPane().add(chckbxChekc);

		final JSlider slider = new JSlider(0, 150);
		slider.setBounds(156, 93, 200, 23);
		frame.getContentPane().add(slider);
		final JLabel lbl = new JLabel("mute", JLabel.CENTER);
		frame.getContentPane().add(lbl);
		String[] options = { "one", "Two", "three" };
		JComboBox comboBox = new JComboBox(options);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblListaTakswek.setText(e.getItem().toString());
				}
			}
		});
		comboBox.setBounds(197, 424, 146, 31);
		frame.getContentPane().add(comboBox);
		// model.addElement("cze");
		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.getViewport().add(list);
		scrollPane.setBounds(161, 259, 146, -59);
		frame.getContentPane().add(scrollPane);

		textField = new JTextField();
		textField.setBounds(25, 57, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		// int i = Integer.parseInt(str);
		lbl.setVisible(true);
		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent event) {

				int value = slider.getValue();

				if (value == 0) {
					lblListaTakswek.setText("mute");
				} else if (value > 0 && value <= 30) {
					lblListaTakswek.setText("min");
				} else if (value > 30 && value < 80) {
					lblListaTakswek.setText("med");
				} else {
					lblListaTakswek.setText("max");
				}
			}
		});

	}
}
