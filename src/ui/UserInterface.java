package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import order.Order;

public class UserInterface implements ActionListener {

	private JFrame frame;
	JPanel mainPanel;

	JLabel lblMenu, lblPleaseOrdering;
	MenuScrollPane menuScrollPane;
	JButton btnSubmit, btnReset, btnAskNumAdd, btnAskNumMinus;
	AskNumPanel askNumPanel;

	SpringLayout sl_menuPanel;

	SpringLayout.Constraints mainPanelCons, lblMenuCons, lblPleaseOrderingCons, menuScrollPaneCons, btnSubmitCons,
			btnResetCons, askNumPanelCons;

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

		// 提交与重置按钮
		btnSubmit = new JButton("提交");
		btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		mainPanel.add(btnSubmit);
		btnSubmit.addActionListener(this);
		btnSubmit.setActionCommand("Submit");

		btnReset = new JButton("重置");
		btnReset.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnReset.setForeground(Color.WHITE);
		btnReset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		mainPanel.add(btnReset);
		btnReset.addActionListener(this);
		btnReset.setActionCommand("Reset");

		askNumPanel = new AskNumPanel();
		mainPanel.add(askNumPanel);

		// --------------------------Layout Setting...--------------------------
		sl_menuPanel = new SpringLayout();
		mainPanel.setLayout(sl_menuPanel);

		// 定义各个 Constraints
		lblMenuCons = sl_menuPanel.getConstraints(lblMenu);
		lblPleaseOrderingCons = sl_menuPanel.getConstraints(lblPleaseOrdering);
		menuScrollPaneCons = sl_menuPanel.getConstraints(menuScrollPane);
		mainPanelCons = sl_menuPanel.getConstraints(mainPanel);
		btnSubmitCons = sl_menuPanel.getConstraints(btnSubmit);
		btnResetCons = sl_menuPanel.getConstraints(btnReset);
		askNumPanelCons = sl_menuPanel.getConstraints(askNumPanel);

		// 左上标签
		lblMenuCons.setConstraint(SpringLayout.WEST, defaultWEST);
		lblMenuCons.setConstraint(SpringLayout.NORTH, defaultNORTH);

		lblPleaseOrderingCons.setX(Spring.sum(lblMenuCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));
		lblPleaseOrderingCons.setConstraint(SpringLayout.SOUTH, lblMenuCons.getConstraint(SpringLayout.SOUTH));

		// 菜单滚动面板
		menuScrollPaneCons.setConstraint(SpringLayout.WEST, defaultWEST);
		menuScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		menuScrollPaneCons.setWidth(Spring.constant(600));

		menuScrollPane.setLayouts(menuScrollPaneCons);

		// 提交与重置按钮
		btnSubmitCons.setConstraint(SpringLayout.EAST,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(-3)));
		btnSubmitCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));

		btnResetCons.setConstraint(SpringLayout.EAST,
				Spring.sum(btnSubmitCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));
		btnResetCons.setConstraint(SpringLayout.NORTH, btnSubmitCons.getConstraint(SpringLayout.NORTH));

		//
		askNumPanelCons.setConstraint(SpringLayout.NORTH, btnSubmitCons.getConstraint(SpringLayout.NORTH));
		askNumPanelCons.setConstraint(SpringLayout.WEST, menuScrollPaneCons.getConstraint(SpringLayout.WEST));
		System.out.println(askNumPanelCons.getWidth().getValue());
		System.out.println(askNumPanelCons.getHeight().getValue());

		// 主面板的 SOUTH 与 EAST
		mainPanelCons.setConstraint(SpringLayout.SOUTH,
				Spring.sum(btnSubmitCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		mainPanelCons.setConstraint(SpringLayout.EAST,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Reset")) {
			System.out.println("Reset");
			menuScrollPane.resetAllNumTextField();
		} else if (e.getActionCommand().equals("Submit")) {
			System.out.println("Submit");
			System.out.println("CusNum: " + Order.getNumOfCustomer());
			for (String[] str : Order.toTableString()) {
				System.out.println(str[0] + "\t" + str[1] + "\t" + str[2]);
			}
		}
	}

}
