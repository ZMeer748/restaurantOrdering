package ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import menu.Menu;
import menu.MenuItem;

/**
 * MenuScrollPane
 */
public class MenuScrollPane extends JScrollPane {

    private static final long serialVersionUID = -5329789333805523046L;

    ArrayList<MenuItemContainPanel> MICPList;

    JPanel itemsContainPanel;

    public MenuScrollPane() {

        int itemCount = 0;
        int marginTop = 6;
        MICPList = new ArrayList<>();
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            MenuItemContainPanel micp = new MenuItemContainPanel(entry.getValue());
            System.out.println(entry.getValue().getName());
            if (itemCount != 0) {
                marginTop += 112;
            }
            System.out.println(marginTop);
            micp.setBounds(6, marginTop, 553, 100);
            MICPList.add(micp);
            itemCount++;
        }

        itemsContainPanel = new JPanel();
        itemsContainPanel.setPreferredSize(new Dimension(566, Menu.getNum() * 112));
        this.setViewportView(itemsContainPanel);
        itemsContainPanel.setLayout(null);

        for (MenuItemContainPanel micp : MICPList) {
            this.add(micp);
        }
    }

    public void add(MenuItemContainPanel micp) {
        itemsContainPanel.add(micp);
    }

}