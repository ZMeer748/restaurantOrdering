package order;

import java.util.Map.Entry;

import menu.Menu;
import menu.MenuItem;

/**
 * Order
 */
public class Order {

    static int numOfCustomer = 1;

    static double getTotalCost(boolean isVIP) {
        double cusCost = numOfCustomer * 2;
        double orderCost = 0;
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            orderCost += entry.getValue().getPrice() * entry.getValue().getNum();
        }
        if (isVIP) {
            return (double) Math.round(((cusCost + orderCost) * 0.9 * 100) / 100);
        } else {
            return (double) Math.round(((cusCost + orderCost) * 100) / 100);
        }
    }

    public static String[][] toTableString() {
        String[][] tempString = new String[size()][3];
        int i = 0;
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            if (entry.getValue().getNum() == 0)
                continue;
            tempString[i++] = new String[] { entry.getValue().getName(), "" + entry.getValue().getPrice(),
                    "" + entry.getValue().getNum() };
        }

        return tempString;
    }

    public static void setNumOfCustomer(int num) {
        numOfCustomer = num;
    }

    public static int getNumOfCustomer() {
        return numOfCustomer;
    }

    public static int size() {
        int i = 0;
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            if (entry.getValue().getNum() == 0)
                continue;
            i++;
        }
        return i;
    }

}