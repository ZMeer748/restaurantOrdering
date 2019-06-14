package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import menu.MenuItem;

/**
 * MenuItemContainPanel
 */
public class MenuItemContainPanel extends JPanel implements ActionListener, DocumentListener, FocusListener {

    private static final long serialVersionUID = 6578743630052115083L;
    MenuItem item;
    ImageIcon imageIcon;
    JLabel lblItemName, lblItemPrice;
    JButton btnAdd, btnMinus;
    JTextField textFieldNumInput;

    SpringLayout sl_micp;
    SpringLayout.Constraints micpCons, lblItemNameCons, lblItemPriceCons, btnMinusCons, btnAddCons,
            textFieldNumInputCons;

    public MenuItemContainPanel(MenuItem item) {

        // imageIcon.getImage();

        lblItemName = new JLabel(item.getName());
        lblItemName.setFont(new Font("微软雅黑", Font.BOLD, 18));
        this.add(lblItemName);

        lblItemPrice = new JLabel("￥" + item.getPrice());
        lblItemPrice.setForeground(Color.RED);
        lblItemPrice.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.add(lblItemPrice);

        btnMinus = new JButton("-");
        btnMinus.setFont(new Font("黑体", Font.BOLD, 30));
        this.add(btnMinus);

        btnAdd = new JButton("+");
        btnAdd.setFont(new Font("黑体", Font.BOLD, 30));
        this.add(btnAdd);

        textFieldNumInput = new JTextField("0");
        textFieldNumInput.setFont(new Font("黑体", Font.BOLD, 20));
        textFieldNumInput.setHorizontalAlignment(JTextField.CENTER);
        this.add(textFieldNumInput);

        btnMinus.setVisible(false);
        textFieldNumInput.setVisible(false);

        sl_micp = new SpringLayout();
        this.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY, 2, 6, 2, true));
        this.setLayout(sl_micp);

        btnMinus.addActionListener(this);
        btnAdd.addActionListener(this);
        textFieldNumInput.getDocument().addDocumentListener(this);
        textFieldNumInput.addFocusListener(this);

        micpCons = sl_micp.getConstraints(this);
        lblItemNameCons = sl_micp.getConstraints(lblItemName);
        lblItemPriceCons = sl_micp.getConstraints(lblItemPrice);
        btnAddCons = sl_micp.getConstraints(btnAdd);
        btnMinusCons = sl_micp.getConstraints(btnMinus);
        textFieldNumInputCons = sl_micp.getConstraints(textFieldNumInput);

        micpCons.setHeight(Spring.constant(104));

    }

    public void setLayouts() {

        lblItemNameCons.setX(Spring.constant(6));
        lblItemNameCons.setY(Spring.constant(6));

        lblItemPriceCons.setConstraint(SpringLayout.WEST, lblItemNameCons.getConstraint(SpringLayout.WEST));
        lblItemPriceCons.setConstraint(SpringLayout.SOUTH,
                Spring.sum(micpCons.getConstraint(SpringLayout.SOUTH), Spring.constant(-6)));

        btnAddCons.setY(Spring.constant(25));
        btnAddCons.setWidth(Spring.constant(50));
        btnAddCons.setHeight(Spring.constant(50));
        micpCons.getConstraint(SpringLayout.EAST).getValue();
        btnAddCons.setConstraint(SpringLayout.EAST,
                Spring.sum(micpCons.getConstraint(SpringLayout.EAST), Spring.constant(-50)));

        textFieldNumInputCons.setY(Spring.constant(30));
        textFieldNumInputCons.setWidth(Spring.constant(40));
        textFieldNumInputCons.setHeight(Spring.constant(40));
        textFieldNumInputCons.setConstraint(SpringLayout.EAST,
                Spring.sum(btnAddCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));

        btnMinusCons.setY(Spring.constant(25));
        btnMinusCons.setWidth(Spring.constant(50));
        btnMinusCons.setHeight(Spring.constant(50));
        btnMinusCons.setConstraint(SpringLayout.EAST,
                Spring.sum(textFieldNumInputCons.getConstraint(SpringLayout.WEST), Spring.constant(-10)));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "-") {
            minusOne();
            zeroCheck();
        } else if (e.getActionCommand() == "+") {
            addOne();
            zeroCheck();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("numText remove.");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("numText insert.");
        inputCheck();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("numText changed.");
        emptyCheck();
    }

    @Override
    public void focusGained(FocusEvent e) {
        textFieldNumInput.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
        emptyCheck();
        zeroCheck();
    }

    void inputCheck() {
        String text = textFieldNumInput.getText();
        // System.out.println(text);
        if (text.matches("0[\\d]")) {
            System.out.println("numText matches 0*");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText(text.substring(1, text.length()));
                }
            }).start();
        } else if (!text.matches("[1-9]?[\\d]")) {
            System.out.println("numText input Error.");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText(text.substring(0, text.length() - 1));
                }
            }).start();
        }
    }

    void emptyCheck() {
        String text = textFieldNumInput.getText();
        // System.out.println(text);
        if (text.equals("")) {
            System.out.println("numText empty");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText("0");
                }
            }).start();
            btnMinus.setVisible(false);
            textFieldNumInput.setVisible(false);
        }
    }

    void zeroCheck() {
        String text = textFieldNumInput.getText();
        if (Integer.parseInt(text) == 0) {
            btnMinus.setVisible(false);
            textFieldNumInput.setVisible(false);
            this.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY, 2, 6, 2, true));
        } else {
            btnMinus.setVisible(true);
            textFieldNumInput.setVisible(true);
            this.setBorder(new LineBorder(new Color(255, 165, 0), 2, true));
        }
    }

    void addOne() {
        String text = textFieldNumInput.getText();
        int beforeNum = Integer.parseInt(text);
        textFieldNumInput.setText("" + (beforeNum + 1));
    }

    void minusOne() {
        String text = textFieldNumInput.getText();
        int beforeNum = Integer.parseInt(text);
        if (beforeNum == 0) {
            zeroCheck();
            return;
        }
        textFieldNumInput.setText("" + (beforeNum - 1));
    }

}