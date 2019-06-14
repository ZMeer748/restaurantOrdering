package ui.demoTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import menu.Menu;
import menu.MenuItem;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;

public class UserInterface_test {

	Menu menu = new Menu();

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO exception
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface_test window = new UserInterface_test();
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
	public UserInterface_test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Menu.getList_test();
		Map<Integer, JPanel> JPanelMap = new TreeMap<>();
		for (Map.Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
			JPanel tempJPanel = new JPanel();
			JPanelMap.put(entry.getKey(), tempJPanel);
		}

		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 77, 600, 583);
		panel_3.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(566, Menu.getNum() * 100));
		scrollPane.setViewportView(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 6, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, panel_1, 106, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, panel_1, 559, SpringLayout.WEST, panel);
		panel.add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		JPanel panel_2 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 13, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 14, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, 87, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, 88, SpringLayout.WEST, panel_1);
		panel_2.setBackground(Color.ORANGE);
		panel_1.add(panel_2);

		JLabel lblNewLabel = new JLabel("Sirloin Steak with Spaghetti");
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel, 13, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel, 102, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblNewLabel, 49, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel, 373, SpringLayout.WEST, panel_1);
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 18));
		panel_1.add(lblNewLabel);

		JButton btnNewButton = new JButton("-");
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewButton, 24, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewButton, 377, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnNewButton, 74, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnNewButton, 427, SpringLayout.WEST, panel_1);
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnNewButton);

		JButton button = new JButton("+");
		sl_panel_1.putConstraint(SpringLayout.NORTH, button, 24, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, button, 490, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, button, 74, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, button, 540, SpringLayout.WEST, panel_1);
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		panel_1.add(button);

		textField = new JTextField();
		sl_panel_1.putConstraint(SpringLayout.NORTH, textField, 24, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField, 441, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField, 74, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField, 476, SpringLayout.WEST, panel_1);
		textField.setFont(new Font("黑体", Font.PLAIN, 20));
		panel_1.add(textField);
		// textField.setColumns(3);

		JLabel label = new JLabel("￥30.0");
		sl_panel_1.putConstraint(SpringLayout.NORTH, label, 62, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label, 102, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, label, 87, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, label, 174, SpringLayout.WEST, panel_1);
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_1.add(label);

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setBounds(14, 13, 88, 51);
		panel_3.add(lblMenu);
		lblMenu.setFont(new Font("黑体", Font.PLAIN, 44));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(628, 77, 479, 320);
		panel_3.add(scrollPane_1);

	}
}
