package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    JLabel itemNameLabel;
    JButton plusButton, minusButton;
    JTextField numInputTextField;

    MenuItemContainPanel(MenuItem item) {

        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

        this.setLayout(null);
        // imageIcon.getImage();
        itemNameLabel = new JLabel(item.getName());
        itemNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        itemNameLabel.setBounds(102, 13, 271, 25);
        this.add(itemNameLabel);

        JLabel itemPriceLabel = new JLabel("￥" + item.getPrice());
        itemPriceLabel.setForeground(Color.RED);
        itemPriceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        itemPriceLabel.setBounds(102, 62, 72, 25);
        this.add(itemPriceLabel);

        minusButton = new JButton("-");
        minusButton.setFont(new Font("黑体", Font.PLAIN, 30));
        minusButton.setBounds(377, 24, 50, 50);
        this.add(minusButton);
        minusButton.addActionListener(this);

        plusButton = new JButton("+");
        plusButton.setFont(new Font("黑体", Font.PLAIN, 30));
        plusButton.setBounds(490, 24, 50, 50);
        this.add(plusButton);
        plusButton.addActionListener(this);

        numInputTextField = new JTextField("0");
        numInputTextField.setFont(new Font("黑体", Font.PLAIN, 20));
        numInputTextField.setBounds(441, 26, 35, 46);
        numInputTextField.setHorizontalAlignment(JTextField.CENTER);
        this.add(numInputTextField);
        // numInputTextField.setColumns(10);
        numInputTextField.getDocument().addDocumentListener(this);
        numInputTextField.addFocusListener(this);

        minusButton.setVisible(false);
        numInputTextField.setVisible(false);

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
        numInputTextField.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
        emptyCheck();
        zeroCheck();
    }

    void inputCheck() {
        String text = numInputTextField.getText();
        // System.out.println(text);
        if (text.matches("0[\\d]")) {
            System.out.println("numText matches 0*");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    numInputTextField.setText(text.substring(1, text.length()));
                }
            }).start();
        } else if (!text.matches("[1-9]?[\\d]")) {
            System.out.println("numText input Error.");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    numInputTextField.setText(text.substring(0, text.length() - 1));
                }
            }).start();
        }
    }

    void emptyCheck() {
        String text = numInputTextField.getText();
        // System.out.println(text);
        if (text.equals("")) {
            System.out.println("numText empty");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    numInputTextField.setText("0");
                }
            }).start();
            minusButton.setVisible(false);
            numInputTextField.setVisible(false);
        }
    }

    void zeroCheck() {
        String text = numInputTextField.getText();
        if (Integer.parseInt(text) == 0) {
            minusButton.setVisible(false);
            numInputTextField.setVisible(false);
            this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        } else {
            minusButton.setVisible(true);
            numInputTextField.setVisible(true);
            this.setBorder(new LineBorder(new Color(255, 165, 0), 2, true));
        }
    }

    void addOne() {
        String text = numInputTextField.getText();
        int beforeNum = Integer.parseInt(text);
        numInputTextField.setText("" + (beforeNum + 1));
    }

    void minusOne() {
        String text = numInputTextField.getText();
        int beforeNum = Integer.parseInt(text);
        if (beforeNum == 0) {
            zeroCheck();
            return;
        }
        numInputTextField.setText("" + (beforeNum - 1));
    }

}