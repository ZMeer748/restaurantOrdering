package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;

import menu.*;

public class UserInterface {

	Menu menu = new Menu();

	private JFrame frame;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 70, 0, 0);
		scrollPane.setSize(525, 590);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 700));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 13, 474, 100);
		panel.add(panel_1);

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("黑体", Font.PLAIN, 44));
		lblMenu.setBounds(14, 13, 525, 44);
		frame.getContentPane().add(lblMenu);

	}
}
