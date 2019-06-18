package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ui.UserInterface;

/**
 * DB_Order_Process
 */
public class DB_Order_Process {

    public static boolean addOrder(int cus_num, String orderListJson, String remarks, float total) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/OrderSystemDB?serverTimezone=UTC&characterEncoding=UTF-8", "root",
                "pw2018"); Statement s = c.createStatement();) {

            int user_id;
            if (UserInterface.getIsVIPFromComboBox()) {
                user_id = 3;
            } else
                user_id = 2;

            String sql = "insert into order_list values( null, " + user_id + ", " + cus_num + ", '" + orderListJson
                    + "', '" + remarks + "', " + total + ", now())";

            System.out.println(sql.toString());
            s.execute(sql);

            System.out.println("插入订单记录成功。");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}