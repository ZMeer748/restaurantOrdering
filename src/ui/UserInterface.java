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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import menu.Menu;
import order.Order;

public class UserInterface implements ActionListener {

	private JFrame frame;
	static JPanel mainPanel;

	static JLabel lblMenu, lblPleaseOrdering;
	static MenuScrollPane menuScrollPane;
	static JButton btnSubmit, btnReset, btnAskNumAdd, btnAskNumMinus;
	static AskNumPanel askNumPanel;

	static SpringLayout sl_menuPanel;

	static SpringLayout.Constraints mainPanelCons, lblMenuCons, lblPleaseOrderingCons, menuScrollPaneCons,
			btnSubmitCons, btnResetCons, askNumPanelCons;

	static final Spring defaultNORTH = Spring.constant(10), defaultWEST = Spring.constant(10);

	// --------------------------- 右侧 --------------------------------------
	static JLabel lblYourChoice, lblTotal, lblRemarks;
	static JScrollPane orderTableScrollPane, textAreaRemarksScrollPane;
	static JTable orderTable;
	static String[] orderTableColumnsTitle = { "名称", "价格", "数量" };
	static JTextField textFieldTotal;
	static JTextArea textAreaRemarks;
	static JButton btnConfirm;

	static SpringLayout.Constraints lblYourChoiceCons, orderTableScrollPaneCons, lblRemarksCons,
			textAreaRemarksScrollPaneCons, lblTotalCons, textFieldTotalCons, btnConfirmCons;

	static DefaultTableModel orderTableModel;

	static String[][] tableContainers;

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

		// ----------------------- 面板左侧内容 ----------------------------------

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

		// ----------------------- 面板右侧内容 ----------------------------------

		lblYourChoice = new JLabel("Your Choice");
		lblYourChoice.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblYourChoice);

		orderTableScrollPane = new JScrollPane();
		mainPanel.add(orderTableScrollPane);

		tableContainers = new String[Menu.getNum() + 10][3];
		orderTableModel = new DefaultTableModel(tableContainers, orderTableColumnsTitle);
		orderTable = new JTable(orderTableModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		orderTableScrollPane.setViewportView(orderTable);

		textAreaRemarksScrollPane = new JScrollPane();
		mainPanel.add(textAreaRemarksScrollPane);

		lblRemarks = new JLabel("Remarks");
		lblRemarks.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblRemarks);

		textAreaRemarks = new JTextArea();
		textAreaRemarks.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textAreaRemarksScrollPane.setViewportView(textAreaRemarks);

		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("微软雅黑", Font.BOLD, 18));
		mainPanel.add(lblTotal);

		textFieldTotal = new JTextField();
		textFieldTotal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldTotal.setHorizontalAlignment(JTextField.CENTER);
		textFieldTotal.setEditable(false);
		mainPanel.add(textFieldTotal);

		btnConfirm = new JButton("确认订单");
		btnConfirm.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		mainPanel.add(btnConfirm);
		setBtnConfirmVisible(false);

		// ---------------------------- 面板布局 --------------------------------
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

		lblYourChoiceCons = sl_menuPanel.getConstraints(lblYourChoice);
		orderTableScrollPaneCons = sl_menuPanel.getConstraints(orderTableScrollPane);
		lblRemarksCons = sl_menuPanel.getConstraints(lblRemarks);
		textAreaRemarksScrollPaneCons = sl_menuPanel.getConstraints(textAreaRemarksScrollPane);
		lblTotalCons = sl_menuPanel.getConstraints(lblTotal);
		textFieldTotalCons = sl_menuPanel.getConstraints(textFieldTotal);
		btnConfirmCons = sl_menuPanel.getConstraints(btnConfirm);

		// -------------------------- 左侧面板布局 -------------------------------
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

		// 顾客人数面板
		askNumPanelCons.setConstraint(SpringLayout.NORTH, btnSubmitCons.getConstraint(SpringLayout.NORTH));
		askNumPanelCons.setConstraint(SpringLayout.WEST, menuScrollPaneCons.getConstraint(SpringLayout.WEST));

		// -------------------------- 右侧面板布局 -------------------------------

		lblYourChoiceCons.setX(Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));
		lblYourChoiceCons.setY(lblPleaseOrderingCons.getY());

		orderTableScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblYourChoiceCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		orderTableScrollPaneCons.setConstraint(SpringLayout.WEST,
				Spring.sum(lblYourChoiceCons.getConstraint(SpringLayout.WEST), Spring.constant(0)));

		lblRemarksCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(6)));
		lblRemarksCons.setConstraint(SpringLayout.WEST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.WEST), Spring.constant(0)));

		textAreaRemarksScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblRemarksCons.getConstraint(SpringLayout.SOUTH), Spring.constant(6)));
		textAreaRemarksScrollPaneCons.setConstraint(SpringLayout.WEST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.WEST), Spring.constant(0)));
		textAreaRemarksScrollPaneCons.setConstraint(SpringLayout.EAST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(0)));
		textAreaRemarksScrollPaneCons.setHeight(Spring.constant(100));

		btnConfirmCons.setConstraint(SpringLayout.EAST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(0)));
		btnConfirmCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(textAreaRemarksScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));

		textFieldTotalCons.setConstraint(SpringLayout.EAST,
				Spring.sum(btnConfirmCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));
		textFieldTotalCons.setWidth(Spring.constant(100));
		textFieldTotalCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(btnConfirmCons.getConstraint(SpringLayout.NORTH), Spring.constant(2)));

		lblTotalCons.setConstraint(SpringLayout.EAST,
				Spring.sum(textFieldTotalCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));
		lblTotalCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(btnConfirmCons.getConstraint(SpringLayout.NORTH), Spring.constant(6)));

		// --------------------------- 主面板的 SOUTH 与 EAST --------------------

		Spring confirmCornerWidthSpring = Spring.sum(btnConfirmCons.getWidth(), Spring.constant(200));

		Spring maxSouthSpring = Spring.max(btnConfirmCons.getConstraint(SpringLayout.SOUTH),
				btnSubmitCons.getConstraint(SpringLayout.SOUTH));
		Spring maxEastSpring = Spring.max(orderTableScrollPaneCons.getConstraint(SpringLayout.EAST),
				Spring.sum(lblYourChoiceCons.getConstraint(SpringLayout.WEST), confirmCornerWidthSpring));

		mainPanelCons.setConstraint(SpringLayout.SOUTH, Spring.sum(maxSouthSpring, Spring.constant(10)));
		mainPanelCons.setConstraint(SpringLayout.EAST, Spring.sum(maxEastSpring, Spring.constant(10)));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Reset")) {
			System.out.println("Reset");
			menuScrollPane.resetAllNumTextField();
			askNumPanel.setOne();
			totalClear();
			fillTable();
			setBtnConfirmVisible(false);
		} else if (e.getActionCommand().equals("Submit")) {
			System.out.println("Submit");
			System.out.println("CusNum: " + Order.getNumOfCustomer());
			setTotal();
			fillTable();
			setBtnConfirmVisible(true);
		}
	}

	static void fillTable() {
		int i = 0;
		for (; i < tableContainers.length; i++) {
			orderTableModel.setValueAt("", i, 0);
			orderTableModel.setValueAt("", i, 1);
			orderTableModel.setValueAt("", i, 2);
		}
		i = 0;
		for (String[] str : Order.toTableString()) {
			orderTableModel.setValueAt(str[0], i, 0);
			orderTableModel.setValueAt(str[1], i, 1);
			orderTableModel.setValueAt(str[2], i, 2);
			i++;
		}
	}

	static void setTotal() {
		textFieldTotal.setText("" + Order.getTotalCost(false));
	}

	public static void totalClear() {
		textFieldTotal.setText("");
	}

	public static void setBtnConfirmVisible(boolean isVisible) {
		fillTable();
		btnConfirm.setVisible(isVisible);
	}

	public static void autoSubmit() {
		setTotal();
		fillTable();
		setBtnConfirmVisible(true);
	}

}
