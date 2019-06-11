package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import menu.Menu;
import menu.MenuItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JMenu;

public class UserInterface {

	private JFrame frame;
	ArrayList<MenuItemContainPanel> MICPList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			// TODO exception
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

		int itemCount = 0;
		int marginTop = 6;
		MICPList = new ArrayList<>();
		for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
			MenuItemContainPanel micp = new MenuItemContainPanel(entry.getValue());
			System.out.println(entry.getValue().getName());
			if (itemCount != 0) {
				marginTop += 112;
			}
			System.out.println(marginTop);
			micp.setBounds(6, marginTop, 553, 100);
			MICPList.add(micp);
			itemCount++;
		}

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
		itemsContainPanel.setPreferredSize(new Dimension(566, Menu.getNum() * 112));
		// itemsContainPanel.setPreferredSize(new Dimension(579, 900));
		menuScrollPane.setViewportView(itemsContainPanel);
		itemsContainPanel.setLayout(null);

		// MenuItemContainPanel micp = new MenuItemContainPanel(Menu.getItem(2));
		// micp.setBounds(6, 6, 553, 100);
		// itemsContainPanel.add(micp);

		// MenuItemContainPanel micp2 = new MenuItemContainPanel(Menu.getItem(4));
		// micp2.setBounds(6, 118, 553, 100);
		// itemsContainPanel.add(micp2);

		for (MenuItemContainPanel micp : MICPList) {
			itemsContainPanel.add(micp);
		}

		// itemsContainPanel.add(MICPList.get(0));

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
