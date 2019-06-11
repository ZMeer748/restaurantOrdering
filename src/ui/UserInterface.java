package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import menu.Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenu;

public class UserInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
		{
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		}
		catch(Exception e)
		{
			//TODO exception
		}
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
		frame.setTitle("Welcome");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JScrollPane menuScrollPane = new JScrollPane();
		menuScrollPane.setBounds(14, 70, 0, 0);
		menuScrollPane.setSize(600, 560);
		mainPanel.add(menuScrollPane);
		
		JPanel itemsContainPanel = new JPanel();
		itemsContainPanel.setPreferredSize(new Dimension(566, Menu.getNum() * 100));
		System.out.println(Menu.getNum() * 100);
//		itemsContainPanel.setPreferredSize(new Dimension(579, 900));
		menuScrollPane.setViewportView(itemsContainPanel);
		itemsContainPanel.setLayout(null);
		
		MenuItemContainPanel micp = new MenuItemContainPanel(Menu.getItem(2));
		micp.setBounds(6, 6, 553, 100);
		itemsContainPanel.add(micp);
		micp.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		
		MenuItemContainPanel micp2 = new MenuItemContainPanel(Menu.getItem(4));
		micp2.setBounds(6, 112, 553, 100);
		itemsContainPanel.add(micp2);
		micp2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("微软雅黑", Font.BOLD, 32));
		lblMenu.setBounds(14, 13, 103, 43);
		mainPanel.add(lblMenu);
		
		JLabel lblPleaseOrdering = new JLabel("Please ordering.");
		lblPleaseOrdering.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPleaseOrdering.setBounds(131, 29, 164, 22);
		mainPanel.add(lblPleaseOrdering);
	}
}
