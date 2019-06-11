package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Map;
import java.util.TreeMap;

import menu.*;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class UserInterface_test {

	Menu menu = new Menu();

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
		{
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		}
		catch(Exception e)
		{
			//TODO exception
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
		for(Map.Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
			JPanel tempJPanel = new JPanel();
			JPanelMap.put(entry.getKey(), tempJPanel);
		}

		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 70, 0, 0);
		scrollPane.setSize(600, 590);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(579, Menu.getNum() * 100));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(14, 13, 553, 100);
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
		button.setIcon(new ImageIcon(UserInterface_test.class.getResource("/org/jb2011/lnf/beautyeye/ch3_button/imgs/np/btn_general_pressed.9.png")));
		button.setFont(new Font("宋体", Font.PLAIN, 30));
		button.setBounds(490, 24, 50, 50);
		panel_1.add(button);
		
		textField = new JTextField();
		textField.setFont(new Font("黑体", Font.PLAIN, 20));
		textField.setBounds(441, 24, 35, 50);
		panel_1.add(textField);
//		textField.setColumns(3);
		
		JLabel label = new JLabel("￥30.0");
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(102, 62, 72, 25);
		panel_1.add(label);

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("黑体", Font.PLAIN, 44));
		lblMenu.setBounds(14, 13, 525, 44);
		frame.getContentPane().add(lblMenu);

	}
}
