package order;

import java.util.Map.Entry;

import menu.Menu;
import menu.MenuItem;

/**
 * Order
 */
public class Order {

    private static int numOfCustomer = 1;
    private static String remarks;

    public static double getTotalCost(boolean isVIP) {
        double cusCost = numOfCustomer * 2;
        double orderCost = 0;
        double total;
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            orderCost += entry.getValue().getPrice() * entry.getValue().getNum();
        }
        if (isVIP) {
            total = (cusCost + orderCost) * 0.9;
        } else {
            total = cusCost + orderCost;
        }
        return (double) (Math.round(total * 100) / 100.0);
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

    public static String toResultString(boolean isVIP) {
        String tempString = new String();
        tempString += "客人共 " + numOfCustomer + " 位\n订单内容：\n";
        for (Entry<Integer, MenuItem> entry : Menu.getEntrySet()) {
            if (entry.getValue().getNum() == 0)
                continue;
            tempString += entry.getValue().getName() + " * " + entry.getValue().getNum() + "\n";
        }
        if (remarks.length() != 0) {
            tempString += "备注：" + remarks + "\n";
        }
        tempString += "需支付：" + getTotalCost(isVIP);
        return tempString;
    }

    public static void setNumOfCustomer(int num) {
        numOfCustomer = num;
    }

    public static int getNumOfCustomer() {
        return numOfCustomer;
    }

    public static void setRemarks(String str) {
        remarks = str;
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