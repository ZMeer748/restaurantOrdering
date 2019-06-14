package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

public class UserInterface {

	private JFrame frame;
	JPanel mainPanel;
	JLabel lblMenu, lblPleaseOrdering;
	MenuScrollPane menuScrollPane;

	SpringLayout sl_mainPanel;

	SpringLayout.Constraints mainPanelCons, lblMenuCons, lblPleaseOrderingCons, menuScrollPaneCons;

	final Spring defaultNORTH = Spring.constant(10), defaultWEST = Spring.constant(10);

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

		frame = new JFrame();
		frame.setTitle("Order System...");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);

		lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("微软雅黑", Font.BOLD, 32));
		mainPanel.add(lblMenu);

		lblPleaseOrdering = new JLabel("Please ordering.");
		lblPleaseOrdering.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblPleaseOrdering);

		menuScrollPane = new MenuScrollPane();
		mainPanel.add(menuScrollPane);

		sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);

		lblMenuCons = sl_mainPanel.getConstraints(lblMenu);
		lblPleaseOrderingCons = sl_mainPanel.getConstraints(lblPleaseOrdering);
		menuScrollPaneCons = sl_mainPanel.getConstraints(menuScrollPane);
		mainPanelCons = sl_mainPanel.getConstraints(mainPanel);

		lblMenuCons.setConstraint(SpringLayout.WEST, defaultWEST);
		lblMenuCons.setConstraint(SpringLayout.NORTH, defaultNORTH);

		lblPleaseOrderingCons.setX(Spring.sum(lblMenuCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));
		lblPleaseOrderingCons.setConstraint(SpringLayout.SOUTH, lblMenuCons.getConstraint(SpringLayout.SOUTH));

		menuScrollPaneCons.setConstraint(SpringLayout.WEST, defaultWEST);
		menuScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		menuScrollPaneCons.setWidth(Spring.constant(600));

		mainPanelCons.setConstraint(SpringLayout.SOUTH,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(14)));

		menuScrollPane.setLayouts(menuScrollPaneCons);
		System.out.println();

	}
}
