package ui.demoTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import static javax.swing.ScrollPaneConstants.*;

import menu.Menu;
import menu.MenuItem;
import ui.MenuItemContainPanel;

public class UserInterface_test_SpringLayout {

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
					UserInterface_test_SpringLayout window = new UserInterface_test_SpringLayout();
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
	public UserInterface_test_SpringLayout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);

		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("微软雅黑", Font.BOLD, 32));
		mainPanel.add(lblMenu);

		JScrollPane menuScrollPane = new JScrollPane();
		menuScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(menuScrollPane);

		JPanel micpContainPanel = new JPanel();
		menuScrollPane.setViewportView(micpContainPanel);

		MenuItemContainPanel micp = new MenuItemContainPanel(new MenuItem(1, "兰州拉面", 13.5));
		// micp.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		micpContainPanel.add(micp);

		SpringLayout sl_mainPanel = new SpringLayout();
		SpringLayout sl_micpContainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);
		micpContainPanel.setLayout(sl_micpContainPanel);

		SpringLayout.Constraints mainPanelCons = sl_mainPanel.getConstraints(mainPanel);
		SpringLayout.Constraints micpContainPanelCons = sl_micpContainPanel.getConstraints(micpContainPanel);

		SpringLayout.Constraints lblMenuCons = sl_mainPanel.getConstraints(lblMenu);
		lblMenuCons.setX(Spring.constant(10));
		lblMenuCons.setY(Spring.constant(6));

		SpringLayout.Constraints menuScrollPaneCons = sl_mainPanel.getConstraints(menuScrollPane);
		menuScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		menuScrollPaneCons.setConstraint(SpringLayout.WEST, lblMenuCons.getConstraint(SpringLayout.WEST));
		menuScrollPaneCons.setWidth(Spring.constant(600));

		SpringLayout.Constraints menuScrollPaneBarCons = sl_mainPanel
				.getConstraints(menuScrollPane.getVerticalScrollBar());

		micpContainPanelCons.setHeight(Spring.constant(600));
		micpContainPanelCons.setConstraint(SpringLayout.EAST, menuScrollPaneCons.getConstraint(SpringLayout.EAST));

		SpringLayout.Constraints micpCons = sl_micpContainPanel.getConstraints(micp);
		micpCons.setConstraint(SpringLayout.WEST,
				Spring.sum(micpContainPanelCons.getConstraint(SpringLayout.WEST), Spring.constant(6)));
		micpCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(micpContainPanelCons.getConstraint(SpringLayout.NORTH), Spring.constant(6)));
		micpCons.setConstraint(SpringLayout.EAST,
				Spring.sum(micpContainPanelCons.getConstraint(SpringLayout.EAST), Spring.constant(-6)));
		micpCons.setHeight(Spring.constant(100));

		mainPanelCons.setConstraint(SpringLayout.SOUTH,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));

		System.out.println(menuScrollPaneBarCons.getWidth().getValue());
		System.out.println(micpContainPanelCons.getConstraint(SpringLayout.EAST).getValue());
		System.out.println(micpContainPanelCons.getWidth().getValue());

		System.out.println(micpCons.getConstraint(SpringLayout.EAST).getValue());
		micp.setLayouts();

	}
}
