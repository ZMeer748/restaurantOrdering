package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSON;

import menu.MenuItem;
import order.OrderRecord;
import property.PropertiesProcess;
import ui.UserOrderingSystem;

/**
 * DBInteraction_Order
 */
public class DBInteraction_Order {

    static String dbName, dbUserName, dbPassword;

    static {
        dbName = PropertiesProcess.getProperty("Database.name");
        dbUserName = PropertiesProcess.getProperty("Database.user_name");
        dbPassword = PropertiesProcess.getProperty("Database.user_password");
    }

    public static void main(String[] args) {
        ArrayList<OrderRecord> orderRecordList = getAllOrderRecord();
        for (OrderRecord or : orderRecordList) {
            System.out.println("id: "+or.getId() + "\tnum: " + or.getNumOfCustomer() + "\ttotal: " + or.getTotal());
            for(MenuItem item : or.getItemList()) {
                System.out.println("item_name: " + item.getName() + "\titem_num: " + item.getNum());
            }
            System.out.println("-----------------------------------------------");
        }
    }

    public static boolean addOrderRecord(int cus_num, String orderListJson, String remarks, float total) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {

            int user_id;
            if (UserOrderingSystem.getIsVIPFromComboBox()) {
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
            System.out.println("插入订单记录失败。");
        }
        return false;
    }

    public static ArrayList<OrderRecord> getAllOrderRecord() {
        ArrayList<OrderRecord> orderRecordList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "select * from order_list";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("order_id");
                int user_id = rs.getInt("order_user_id");
                int cus_num = rs.getInt("order_customer_num");
                String content_jsonStr = rs.getString("order_content");
                String remarks = rs.getString("order_remarks");
                float total = rs.getFloat("order_total");
                Date order_time = rs.getTime("order_datetime");
                Date order_date = rs.getDate("order_datetime");
                Date dateTime = new Date();

                // Construct date and time objects
                Calendar dateCal = Calendar.getInstance();
                dateCal.setTime(order_date);
                Calendar timeCal = Calendar.getInstance();
                timeCal.setTime(order_time);
                // Extract the time of the "time" object to the "date"
                dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
                dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
                dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
                // Get the time value!
                dateTime = dateCal.getTime();

                ArrayList<MenuItem> itemArr = (ArrayList<MenuItem>) JSON.parseArray(content_jsonStr, MenuItem.class);

                orderRecordList.add(new OrderRecord(id, user_id, itemArr, cus_num, remarks, total, dateTime));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderRecordList;
    }

    public static OrderRecord getOrderRecord(int order_id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "select * from order_list where order_id = " + order_id;

            ResultSet rs = s.executeQuery(sql);
            rs.next();
            int id = rs.getInt("order_id");
            int user_id = rs.getInt("order_user_id");
            int cus_num = rs.getInt("order_customer_num");
            String content_jsonStr = rs.getString("order_content");
            String remarks = rs.getString("order_remarks");
            float total = rs.getFloat("order_total");
            Date order_time = rs.getTime("order_datetime");
            Date order_date = rs.getDate("order_datetime");
            Date dateTime = new Date();

            // Construct date and time objects
            Calendar dateCal = Calendar.getInstance();
            dateCal.setTime(order_date);
            Calendar timeCal = Calendar.getInstance();
            timeCal.setTime(order_time);
            // Extract the time of the "time" object to the "date"
            dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
            dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
            dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
            // Get the time value!
            dateTime = dateCal.getTime();

            ArrayList<MenuItem> itemArr = (ArrayList<MenuItem>) JSON.parseArray(content_jsonStr, MenuItem.class);

            return new OrderRecord(id, user_id, itemArr, cus_num, remarks, total, dateTime);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}