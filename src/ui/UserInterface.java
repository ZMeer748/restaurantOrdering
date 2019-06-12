package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Spring;
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
import javax.swing.SpringLayout;

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
		frame.setTitle("Order System...");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		SpringLayout sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);

		JScrollPane menuScrollPane = new JScrollPane();
		// sl_mainPanel.putConstraint(SpringLayout.NORTH, menuScrollPane, 70,
		// SpringLayout.NORTH, mainPanel);
		// sl_mainPanel.putConstraint(SpringLayout.WEST, menuScrollPane, 14,
		// SpringLayout.WEST, mainPanel);
		// sl_mainPanel.putConstraint(SpringLayout.SOUTH, menuScrollPane, 630,
		// SpringLayout.NORTH, mainPanel);
		// sl_mainPanel.putConstraint(SpringLayout.EAST, menuScrollPane, 614,
		// SpringLayout.WEST, mainPanel);
		mainPanel.add(menuScrollPane);

		JPanel itemsContainPanel = new JPanel();
		itemsContainPanel.setPreferredSize(new Dimension(566, Menu.getNum() * 112));
		menuScrollPane.setViewportView(itemsContainPanel);
		itemsContainPanel.setLayout(null);

		for (MenuItemContainPanel micp : MICPList) {
			itemsContainPanel.add(micp);
		}

		JLabel lblMenu = new JLabel("MENU");
		// sl_mainPanel.putConstraint(SpringLayout.NORTH, lblMenu, 13,
		// SpringLayout.NORTH, mainPanel);
		// sl_mainPanel.putConstraint(SpringLayout.WEST, lblMenu, 14, SpringLayout.WEST,
		// mainPanel);
		lblMenu.setFont(new Font("微软雅黑", Font.BOLD, 32));
		mainPanel.add(lblMenu);

		JLabel lblPleaseOrdering = new JLabel("Please ordering.");
		// sl_mainPanel.putConstraint(SpringLayout.NORTH, lblPleaseOrdering, 29,
		// SpringLayout.NORTH, mainPanel);
		// sl_mainPanel.putConstraint(SpringLayout.WEST, lblPleaseOrdering, 131,
		// SpringLayout.WEST, mainPanel);
		// sl_mainPanel.putConstraint(SpringLayout.EAST, lblPleaseOrdering, 295,
		// SpringLayout.WEST, mainPanel);
		lblPleaseOrdering.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblPleaseOrdering);

		SpringLayout.Constraints lblMenuCons = sl_mainPanel.getConstraints(lblMenu);
		lblMenuCons.setX(Spring.constant(14));
		lblMenuCons.setY(Spring.constant(13));

		SpringLayout.Constraints lblPleaseOrderingCons = sl_mainPanel.getConstraints(lblPleaseOrdering);
		lblPleaseOrderingCons.setX(Spring.sum(lblMenuCons.getConstraint(SpringLayout.EAST), Spring.constant(6)));
		lblPleaseOrderingCons.setY(Spring.constant(29));

		SpringLayout.Constraints menuScrollPaneCons = sl_mainPanel.getConstraints(menuScrollPane);
		menuScrollPaneCons.setX(Spring.constant(14));
		menuScrollPaneCons.setY(Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH), Spring.constant(6)));

		SpringLayout.Constraints mainPanelCons = sl_mainPanel.getConstraints(mainPanel);
		mainPanelCons.setConstraint(SpringLayout.SOUTH,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(14)));

		sl_mainPanel.putConstraint(SpringLayout.EAST, menuScrollPane, 614, SpringLayout.WEST, mainPanel);
	}
}
