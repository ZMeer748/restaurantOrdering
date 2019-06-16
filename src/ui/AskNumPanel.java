package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import order.Order;

/**
 * AskNumPanel
 */
public class AskNumPanel extends JPanel implements ActionListener, DocumentListener, FocusListener {

    private static final long serialVersionUID = -4919314229585569132L;
    JLabel lblAskNum;
    JButton btnAdd, btnMinus;
    JTextField textFieldNumInput;

    SpringLayout sl_AskNumPanel;

    SpringLayout.Constraints askNumPanelCons, lblAskNumCons, btnMinusCons, textFieldNumInputCons, btnAddCons;

    AskNumPanel() {
        lblAskNum = new JLabel("Please input the customer's number ");
        lblAskNum.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        this.add(lblAskNum);

        btnMinus = new JButton("<");
        btnMinus.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(btnMinus);

        textFieldNumInput = new JTextField("1");
        textFieldNumInput.setFont(new Font("黑体", Font.BOLD, 20));
        textFieldNumInput.setHorizontalAlignment(JTextField.CENTER);
        this.add(textFieldNumInput);

        btnAdd = new JButton(">");
        btnAdd.setFont(new Font("黑体", Font.PLAIN, 12));
        this.add(btnAdd);

        sl_AskNumPanel = new SpringLayout();
        this.setLayout(sl_AskNumPanel);

        btnMinus.addActionListener(this);
        btnAdd.addActionListener(this);
        textFieldNumInput.getDocument().addDocumentListener(this);
        textFieldNumInput.addFocusListener(this);

        askNumPanelCons = sl_AskNumPanel.getConstraints(this);
        lblAskNumCons = sl_AskNumPanel.getConstraints(lblAskNum);
        btnMinusCons = sl_AskNumPanel.getConstraints(btnMinus);
        textFieldNumInputCons = sl_AskNumPanel.getConstraints(textFieldNumInput);
        btnAddCons = sl_AskNumPanel.getConstraints(btnAdd);

        btnMinusCons.setHeight(Spring.sum(lblAskNumCons.getHeight(), Spring.constant(6)));
        textFieldNumInputCons.setHeight(Spring.sum(lblAskNumCons.getHeight(), Spring.constant(4)));
        btnAddCons.setHeight(Spring.sum(lblAskNumCons.getHeight(), Spring.constant(6)));

        btnMinusCons.setWidth(Spring.constant(30));
        textFieldNumInputCons.setWidth(Spring.constant(36));
        btnAddCons.setWidth(Spring.constant(30));

        lblAskNumCons.setConstraint(SpringLayout.WEST,
                Spring.sum(askNumPanelCons.getConstraint(SpringLayout.WEST), Spring.constant(4)));
        btnMinusCons.setConstraint(SpringLayout.WEST,
                Spring.sum(lblAskNumCons.getConstraint(SpringLayout.EAST), Spring.constant(4)));
        textFieldNumInputCons.setConstraint(SpringLayout.WEST,
                Spring.sum(btnMinusCons.getConstraint(SpringLayout.EAST), Spring.constant(4)));
        btnAddCons.setConstraint(SpringLayout.WEST,
                Spring.sum(textFieldNumInputCons.getConstraint(SpringLayout.EAST), Spring.constant(4)));

        textFieldNumInputCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(textFieldNumInputCons.getConstraint(SpringLayout.NORTH), Spring.constant(2)));

        askNumPanelCons.setConstraint(SpringLayout.EAST, btnAddCons.getConstraint(SpringLayout.EAST));
        askNumPanelCons.setConstraint(SpringLayout.SOUTH, btnAddCons.getConstraint(SpringLayout.SOUTH));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "<") {
            minusOne();
            setCustomersNum();
        } else if (e.getActionCommand() == ">") {
            addOne();
            setCustomersNum();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        textFieldNumInput.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
        emptyCheck();
        setCustomersNum();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        inputCheck();
        setCustomersNum();

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        emptyCheck();
        setCustomersNum();
    }

    void inputCheck() {
        String text = textFieldNumInput.getText();
        if (text.matches("0[\\d]")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText(text.substring(1, text.length()));
                }
            }).start();
        } else if (!text.matches("[1-9]?[\\d]")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText(text.substring(0, text.length() - 1));
                }
            }).start();
        } else if (text.matches("0")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText("1");
                }
            }).start();
        } else if (text.matches("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText("1");
                }
            }).start();
        }
    }

    void emptyCheck() {
        String text = textFieldNumInput.getText();
        if (text.equals("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    textFieldNumInput.setText("1");
                }
            }).start();
        }
    }

    private void addOne() {
        String text = textFieldNumInput.getText();
        int beforeNum = Integer.parseInt(text);
        if (beforeNum == 99) {
            return;
        }
        textFieldNumInput.setText("" + (beforeNum + 1));
    }

    private void minusOne() {
        String text = textFieldNumInput.getText();
        if (text.equals("")) {
            emptyCheck();
            return;
        }
        int beforeNum = Integer.parseInt(text);
        if (beforeNum == 1) {
            return;
        }
        textFieldNumInput.setText("" + (beforeNum - 1));
    }

    private void setCustomersNum() {
        Order.setNumOfCustomer(getNumFromText());
    }

    int getNumFromText() {
        if (textFieldNumInput.getText().equals("")) {
            emptyCheck();
            return 1;
        }
        return Integer.parseInt(textFieldNumInput.getText());
    }

    void setOne() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                textFieldNumInput.setText("1");
            }
        }).start();
    }

}