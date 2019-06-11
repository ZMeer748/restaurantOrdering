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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import menu.MenuItem;

/**
 * MenuItemContainPanel
 */
public class MenuItemContainPanel extends JPanel {

    MenuItem item;
    ImageIcon imageIcon;
    JLabel itemNameLabel;
    JButton plusButton, minusButton;
    JTextField numInputTextField;

    MenuItemContainPanel(MenuItem item) {
        this.setLayout(null);
        // imageIcon.getImage();
        itemNameLabel = new JLabel(item.getName());
		itemNameLabel.setFont(new Font("黑体", Font.PLAIN, 18));
		itemNameLabel.setBounds(102, 13, 271, 25);
        this.add(itemNameLabel);

		JLabel itemPriceLabel = new JLabel("￥" + item.getPrice());
		itemPriceLabel.setForeground(Color.RED);
		itemPriceLabel.setFont(new Font("黑体", Font.PLAIN, 20));
		itemPriceLabel.setBounds(102, 62, 72, 25);
        this.add(itemPriceLabel);

        minusButton = new JButton("-");
        minusButton.setFont(new Font("黑体", Font.PLAIN, 30));
        minusButton.setBounds(377, 24, 50, 50);
        this.add(minusButton);
        minusButton.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                zeroCheck();
            }
        });


        plusButton = new JButton("+");
        plusButton.setFont(new Font("黑体", Font.PLAIN, 30));
        plusButton.setBounds(490, 24, 50, 50);
        this.add(plusButton);

        numInputTextField = new JTextField("0");
		numInputTextField.setFont(new Font("黑体", Font.PLAIN, 20));
        numInputTextField.setBounds(441, 26, 35, 46);
        numInputTextField.setHorizontalAlignment(JTextField.CENTER);
        this.add(numInputTextField);
        // numInputTextField.setColumns(10);
        numInputTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("remove.");
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insert.");
                inputCheck();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changed.");
            }
        });
        
        numInputTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                numInputTextField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                emptyCheck();
            }
        });

        // minusButton.setVisible(false);
        // numInputTextField.setVisible(false);

    }

    void inputCheck() {
        String text = numInputTextField.getText();
        // System.out.println(text);
        if (text.matches("0[\\d]")) {
            System.out.println("0*");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    numInputTextField.setText(text.substring(1, text.length()));
                }
            }).start();
        } else if (!text.matches("[1-9]?[\\d]")) {
            System.out.println("input Error.");
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
            System.out.println("empty");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    numInputTextField.setText("0");
                }
            }).start();
        }
    }

    void zeroCheck() {
        String text = numInputTextField.getText();
        if(Integer.parseInt(text) == 0) {
            minusButton.setVisible(false);
            numInputTextField.setVisible(false);
        }
    }

}