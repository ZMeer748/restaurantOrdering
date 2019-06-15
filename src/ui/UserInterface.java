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

public class UserInterface implements ActionListener {

	private JFrame frame;
	JPanel mainPanel;

	JLabel lblMenu, lblPleaseOrdering;
	MenuScrollPane menuScrollPane;
	JButton btnSubmit, btnReset;

	SpringLayout sl_menuPanel;

	SpringLayout.Constraints menuPanelCons, lblMenuCons, lblPleaseOrderingCons, menuScrollPaneCons, btnSubmitCons,
			btnResetCons;

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

		btnSubmit = new JButton("提交");
		btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		mainPanel.add(btnSubmit);

		btnReset = new JButton("重置");
		btnReset.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnReset.setForeground(Color.WHITE);
		btnReset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		mainPanel.add(btnReset);
		btnReset.addActionListener(this);
		btnReset.setActionCommand("Reset");

		// Layout Setting...
		sl_menuPanel = new SpringLayout();
		mainPanel.setLayout(sl_menuPanel);

		lblMenuCons = sl_menuPanel.getConstraints(lblMenu);
		lblPleaseOrderingCons = sl_menuPanel.getConstraints(lblPleaseOrdering);
		menuScrollPaneCons = sl_menuPanel.getConstraints(menuScrollPane);
		menuPanelCons = sl_menuPanel.getConstraints(mainPanel);
		btnSubmitCons = sl_menuPanel.getConstraints(btnSubmit);
		btnResetCons = sl_menuPanel.getConstraints(btnReset);

		lblMenuCons.setConstraint(SpringLayout.WEST, defaultWEST);
		lblMenuCons.setConstraint(SpringLayout.NORTH, defaultNORTH);

		lblPleaseOrderingCons.setX(Spring.sum(lblMenuCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));
		lblPleaseOrderingCons.setConstraint(SpringLayout.SOUTH, lblMenuCons.getConstraint(SpringLayout.SOUTH));

		menuScrollPaneCons.setConstraint(SpringLayout.WEST, defaultWEST);
		menuScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblMenuCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		menuScrollPaneCons.setWidth(Spring.constant(600));

		menuPanelCons.setConstraint(SpringLayout.EAST,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));

		menuScrollPane.setLayouts(menuScrollPaneCons);

		btnSubmitCons.setConstraint(SpringLayout.EAST,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(-3)));
		btnSubmitCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));

		btnResetCons.setConstraint(SpringLayout.EAST,
				Spring.sum(btnSubmitCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));
		btnResetCons.setConstraint(SpringLayout.NORTH, btnSubmitCons.getConstraint(SpringLayout.NORTH));

		menuPanelCons.setConstraint(SpringLayout.SOUTH,
				Spring.sum(btnSubmitCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Reset")) {
			System.out.println("Reset");
			menuScrollPane.resetAllNumTextField();
		}
	}
	
}
