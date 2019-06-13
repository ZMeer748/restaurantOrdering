package ui;

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
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 165, 0), 1, true));
		panel_1.setBounds(6, 6, 553, 100);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.ORANGE);
		panel_2.setBounds(14, 13, 74, 74);
		panel_1.add(panel_2);

		JLabel lblNewLabel = new JLabel("Sirloin Steak with Spaghetti");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 18));
		lblNewLabel.setBounds(102, 13, 271, 36);
		panel_1.add(lblNewLabel);

		JButton btnNewButton = new JButton("-");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(377, 24, 50, 50);
		panel_1.add(btnNewButton);

		JButton button = new JButton("+");
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(490, 24, 50, 50);
		panel_1.add(button);

		textField = new JTextField();
		textField.setFont(new Font("黑体", Font.PLAIN, 20));
		textField.setBounds(441, 24, 35, 50);
		panel_1.add(textField);
		// textField.setColumns(3);

		JLabel label = new JLabel("￥30.0");
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(102, 62, 72, 25);
		panel_1.add(label);

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setBounds(14, 13, 88, 51);
		panel_3.add(lblMenu);
		lblMenu.setFont(new Font("黑体", Font.PLAIN, 44));

	}
}
