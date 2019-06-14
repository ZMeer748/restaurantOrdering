package ui;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import menu.Menu;
import menu.MenuItem;

/**
 * MenuScrollPane
 */
public class MenuScrollPane extends JScrollPane {

    private static final long serialVersionUID = -5329789333805523046L;

    ArrayList<MenuItemContainPanel> MICPList;

    JPanel itemsContainPanel;

    SpringLayout sl_itemsContainPanel;

    SpringLayout.Constraints menuScrollPaneCons, itemsContainPanelCons;

    Spring beforeSOUTH, defaultWEST = Spring.constant(6);

    public MenuScrollPane() {

        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        itemsContainPanel = new JPanel();

        this.setViewportView(itemsContainPanel);

        MICPList = new ArrayList<>();
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            MenuItemContainPanel micp = new MenuItemContainPanel(entry.getValue());
            System.out.println(entry.getValue().getName());
            MICPList.add(micp);
            itemsContainPanel.add(micp);
        }

        sl_itemsContainPanel = new SpringLayout();
        itemsContainPanel.setLayout(sl_itemsContainPanel);

        itemsContainPanelCons = sl_itemsContainPanel.getConstraints(itemsContainPanel);

    }

    public void setLayouts(SpringLayout.Constraints menuScrollPaneCons) {

        this.menuScrollPaneCons = menuScrollPaneCons;

        itemsContainPanelCons.setConstraint(SpringLayout.EAST, menuScrollPaneCons.getConstraint(SpringLayout.EAST));

        Spring beforeSOUTH = itemsContainPanelCons.getConstraint(SpringLayout.NORTH);
        Spring defaultWEST = Spring.sum(itemsContainPanelCons.getConstraint(SpringLayout.WEST), Spring.constant(6));
        for (MenuItemContainPanel micp : this.MICPList) {
            SpringLayout.Constraints tempMicpCons = sl_itemsContainPanel.getConstraints(micp);
            tempMicpCons.setConstraint(SpringLayout.NORTH, Spring.sum(beforeSOUTH, Spring.constant(6)));
            tempMicpCons.setConstraint(SpringLayout.WEST, defaultWEST);
            tempMicpCons.setConstraint(SpringLayout.EAST, itemsContainPanelCons.getConstraint(SpringLayout.EAST));
            beforeSOUTH = tempMicpCons.getConstraint(SpringLayout.SOUTH);
            micp.setLayouts();
        }

        itemsContainPanelCons.setConstraint(SpringLayout.SOUTH, Spring.sum(beforeSOUTH, Spring.constant(6)));
        System.out.println(itemsContainPanelCons.getConstraint(SpringLayout.SOUTH).getValue());

    }

}