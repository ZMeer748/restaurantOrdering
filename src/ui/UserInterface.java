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

		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);

		menuScrollPane = new MenuScrollPane();
		mainPanel.add(menuScrollPane);

		lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("微软雅黑", Font.BOLD, 32));
		mainPanel.add(lblMenu);

		lblPleaseOrdering = new JLabel("Please ordering.");
		lblPleaseOrdering.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblPleaseOrdering);

		lblMenuCons = sl_mainPanel.getConstraints(lblMenu);
		lblMenuCons.setX(Spring.constant(14));
		lblMenuCons.setY(Spring.constant(13));

		lblPleaseOrderingCons = sl_mainPanel.getConstraints(lblPleaseOrdering);
		lblPleaseOrderingCons.setX(Spring.sum(lblMenuCons.getConstraint(SpringLayout.EAST), Spring.constant(6)));
		lblPleaseOrderingCons.setY(Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH),
				Spring.constant(-lblPleaseOrderingCons.getHeight().getValue())));

		menuScrollPaneCons = sl_mainPanel.getConstraints(menuScrollPane);
		menuScrollPaneCons.setX(Spring.constant(14));
		menuScrollPaneCons.setY(Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH), Spring.constant(6)));
		menuScrollPaneCons.setWidth(Spring.constant(600));

		mainPanelCons = sl_mainPanel.getConstraints(mainPanel);
		mainPanelCons.setConstraint(SpringLayout.SOUTH,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(14)));

	}
}
