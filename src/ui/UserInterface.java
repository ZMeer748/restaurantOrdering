package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import db.DB_Order_Process;
import menu.Menu;
import order.Order;

public class UserInterface implements ActionListener, ItemListener, DocumentListener, FocusListener {

	private JFrame frame;
	static JPanel mainPanel;

	static SpringLayout sl_mainPanel;

	// --------------------------- 左侧 --------------------------------------
	static JLabel lblMenu, lblPleaseOrdering;
	static MenuScrollPane menuScrollPane;
	static JButton btnSubmit, btnReset, btnAskNumAdd, btnAskNumMinus;
	static AskNumPanel askNumPanel;
	static JComboBox<String> comboBoxCalStrategy;

	static SpringLayout.Constraints mainPanelCons, lblMenuCons, lblPleaseOrderingCons, menuScrollPaneCons,
			btnSubmitCons, btnResetCons, askNumPanelCons, comboBoxCalStrategyCons;

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

	static String[] calStrategy = { "nonVIP", "VIP" };
	static String[][] tableContainers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// 导入 BeautyEye 皮肤
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
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

		// 左上标签
		lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("微软雅黑", Font.BOLD, 32));
		mainPanel.add(lblMenu);

		lblPleaseOrdering = new JLabel("Please ordering.");
		lblPleaseOrdering.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblPleaseOrdering);

		// 计算策略下拉菜单
		comboBoxCalStrategy = new JComboBox<>(calStrategy);
		comboBoxCalStrategy.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		comboBoxCalStrategy.setSelectedIndex(0);
		mainPanel.add(comboBoxCalStrategy);

		// 菜单滚动面板
		menuScrollPane = new MenuScrollPane();
		mainPanel.add(menuScrollPane);

		// 提交与重置按钮
		btnSubmit = new JButton("提交");
		btnSubmit.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		mainPanel.add(btnSubmit);
		btnSubmit.setActionCommand("Submit");

		btnReset = new JButton("重置");
		btnReset.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnReset.setForeground(Color.WHITE);
		btnReset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		mainPanel.add(btnReset);
		btnReset.setActionCommand("Reset");

		comboBoxCalStrategy.addItemListener(this);
		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);

		// 人数面板
		askNumPanel = new AskNumPanel();
		mainPanel.add(askNumPanel);

		// ----------------------- 面板右侧内容 ----------------------------------

		// 上部标签
		lblYourChoice = new JLabel("Your Choice");
		lblYourChoice.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblYourChoice);

		// 表格面板，以滚动面板为容器
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
		orderTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		orderTableScrollPane.setViewportView(orderTable);

		// 备注标签
		lblRemarks = new JLabel("Remarks");
		lblRemarks.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mainPanel.add(lblRemarks);

		// 备注文本框，以滚动面板为容器
		textAreaRemarksScrollPane = new JScrollPane();
		mainPanel.add(textAreaRemarksScrollPane);

		textAreaRemarks = new JTextArea();
		textAreaRemarks.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textAreaRemarksScrollPane.setViewportView(textAreaRemarks);

		// 总额标签
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("微软雅黑", Font.BOLD, 18));
		mainPanel.add(lblTotal);

		// 总额文本框
		textFieldTotal = new JTextField();
		textFieldTotal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldTotal.setHorizontalAlignment(JTextField.CENTER);
		textFieldTotal.setEditable(false);
		mainPanel.add(textFieldTotal);

		// 确认订单按钮
		btnConfirm = new JButton("确认订单");
		btnConfirm.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		mainPanel.add(btnConfirm);
		setBtnConfirmVisible(false);

		textAreaRemarks.getDocument().addDocumentListener(this);
		textAreaRemarks.addFocusListener(this);
		btnConfirm.addActionListener(this);

		// ---------------------------- 面板布局 --------------------------------
		sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);

		// 定义各个 Constraints
		lblMenuCons = sl_mainPanel.getConstraints(lblMenu);
		lblPleaseOrderingCons = sl_mainPanel.getConstraints(lblPleaseOrdering);
		menuScrollPaneCons = sl_mainPanel.getConstraints(menuScrollPane);
		mainPanelCons = sl_mainPanel.getConstraints(mainPanel);
		btnSubmitCons = sl_mainPanel.getConstraints(btnSubmit);
		btnResetCons = sl_mainPanel.getConstraints(btnReset);
		askNumPanelCons = sl_mainPanel.getConstraints(askNumPanel);
		comboBoxCalStrategyCons = sl_mainPanel.getConstraints(comboBoxCalStrategy);

		lblYourChoiceCons = sl_mainPanel.getConstraints(lblYourChoice);
		orderTableScrollPaneCons = sl_mainPanel.getConstraints(orderTableScrollPane);
		lblRemarksCons = sl_mainPanel.getConstraints(lblRemarks);
		textAreaRemarksScrollPaneCons = sl_mainPanel.getConstraints(textAreaRemarksScrollPane);
		lblTotalCons = sl_mainPanel.getConstraints(lblTotal);
		textFieldTotalCons = sl_mainPanel.getConstraints(textFieldTotal);
		btnConfirmCons = sl_mainPanel.getConstraints(btnConfirm);

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

		// 计算策略下拉菜单布局
		comboBoxCalStrategyCons.setConstraint(SpringLayout.SOUTH, lblMenuCons.getConstraint(SpringLayout.SOUTH));
		comboBoxCalStrategyCons.setConstraint(SpringLayout.EAST,
				Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(-10)));

		// -------------------------- 右侧面板布局 -------------------------------

		// 上部标签布局
		lblYourChoiceCons.setX(Spring.sum(menuScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));
		lblYourChoiceCons.setY(lblPleaseOrderingCons.getY());

		// 表格面板布局
		orderTableScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblYourChoiceCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		orderTableScrollPaneCons.setConstraint(SpringLayout.WEST,
				Spring.sum(lblYourChoiceCons.getConstraint(SpringLayout.WEST), Spring.constant(0)));

		// 备注标签布局
		lblRemarksCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(6)));
		lblRemarksCons.setConstraint(SpringLayout.WEST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.WEST), Spring.constant(0)));

		// 备注面板布局
		textAreaRemarksScrollPaneCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(lblRemarksCons.getConstraint(SpringLayout.SOUTH), Spring.constant(6)));
		textAreaRemarksScrollPaneCons.setConstraint(SpringLayout.WEST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.WEST), Spring.constant(0)));
		textAreaRemarksScrollPaneCons.setConstraint(SpringLayout.EAST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(0)));
		textAreaRemarksScrollPaneCons.setHeight(Spring.constant(100));

		// 确认订单按钮布局
		btnConfirmCons.setConstraint(SpringLayout.EAST,
				Spring.sum(orderTableScrollPaneCons.getConstraint(SpringLayout.EAST), Spring.constant(0)));
		btnConfirmCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(textAreaRemarksScrollPaneCons.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));

		// 总额文本框布局
		textFieldTotalCons.setConstraint(SpringLayout.EAST,
				Spring.sum(btnConfirmCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));
		textFieldTotalCons.setWidth(Spring.constant(100));
		textFieldTotalCons.setConstraint(SpringLayout.NORTH,
				Spring.sum(btnConfirmCons.getConstraint(SpringLayout.NORTH), Spring.constant(2)));

		// 总额标签布局
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

	// 重置、提交与确认订单按钮的监听响应
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Reset")) {
			menuScrollPane.resetAllNumTextField();
			askNumPanel.setOne();
			totalClear();
			fillTable();
			remarksClear();
			setBtnConfirmVisible(false);
		} else if (e.getActionCommand().equals("Submit")) {
			if (Order.size() == 0) {
				JOptionPane.showMessageDialog(frame, "请先点餐，再提交", "消息", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			setTotal();
			fillTable();
			setBtnConfirmVisible(true);
		} else if (e.getActionCommand().equals("确认订单")) {
			int result = JOptionPane.showConfirmDialog(frame, Order.toResultString(getIsVIPFromComboBox()), "订单确认",
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				Order.buildList();
				// 写入到记录中
				DB_Order_Process.addOrder(Order.getNumOfCustomer(), Order.getItemListJsonString(), Order.getRemarks(),
						(float) Order.getTotalCost(getIsVIPFromComboBox()));
				// System.out.println(Order.getItemListJsonString());
				JOptionPane.showMessageDialog(frame, "点餐成功", "消息", JOptionPane.INFORMATION_MESSAGE);
				btnReset.doClick();
				break;
			case JOptionPane.NO_OPTION:
				btnReset.doClick();
				JOptionPane.showMessageDialog(frame, "取消订单", "消息", JOptionPane.INFORMATION_MESSAGE);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			case JOptionPane.CLOSED_OPTION:
				break;
			}
		}
	}

	// 刷新表格内容
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

	// 获取计算策略
	public static boolean getIsVIPFromComboBox() {
		if (comboBoxCalStrategy.getSelectedItem().toString().equals("VIP"))
			return true;
		else
			return false;
	}

	// 设置总额文本框的内容
	static void setTotal() {
		textFieldTotal.setText("" + Order.getTotalCost(getIsVIPFromComboBox()));
	}

	// 清除总额文本框的内容
	public static void totalClear() {
		textFieldTotal.setText("");
	}

	// 设置确认订单按钮是否可见
	public static void setBtnConfirmVisible(boolean isVisible) {
		fillTable();
		btnConfirm.setVisible(isVisible);
	}

	// 监听计算策略下拉菜单的行为
	@Override
	public void itemStateChanged(ItemEvent e) {
		totalClear();
		fillTable();
		setBtnConfirmVisible(false);
	}

	private static void setRemarksText() {
		Order.setRemarks(textAreaRemarks.getText());
	}

	public static void remarksClear() {
		textAreaRemarks.setText("");
	}

	@Override
	public void focusGained(FocusEvent e) {
		textAreaRemarks.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		setRemarksText();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		totalClear();
		setBtnConfirmVisible(false);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		totalClear();
		setBtnConfirmVisible(false);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		totalClear();
		setBtnConfirmVisible(false);
	}

}
