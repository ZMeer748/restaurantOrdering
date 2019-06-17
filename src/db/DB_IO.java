package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import menu.Menu;
import menu.MenuItem;

/**
 * DB_IO
 */
public class DB_IO {

    public static void main(String[] args) {
        for (MenuItem item : Menu.getList()) {
            insertInto_menu_item(item.getCode(), item.getName(), (float) item.getPrice(), item.getSort());
        }
    }

    static void insertInto_menu_item(int id, String name, float price, String sort) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/OrderSystemDB?serverTimezone=UTC&characterEncoding=UTF-8", "root",
                "pw2018"); Statement s = c.createStatement();) {

            File f = new File("img/" + name + ".png");
            if (!f.exists()) {
                f = new File("img/no picture.png");
            }
            FileInputStream fis = new FileInputStream(f);
            String sql = "insert into menu_item values(" + id + ", '" + name + "', " + price + ", '" + sort + "', ?)";
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setBinaryStream(1, fis, (int) f.length());
            preparedStatement.executeUpdate();
            fis.close();
            preparedStatement.close();

            System.out.println("插入菜品 " + name + " 成功");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<MenuItem> getItemList() {
        ArrayList<MenuItem> tempList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/OrderSystemDB?serverTimezone=UTC&characterEncoding=UTF-8", "root",
                "pw2018"); Statement s = c.createStatement();) {
            String sql = "select * from menu_item";

            // 执行查询语句，并把结果集返回给ResultSet
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("item_id");// 可以使用字段名
                String name = rs.getString("item_name");// 也可以使用字段的顺序
                float price = rs.getFloat("item_price");
                String sort = rs.getString("item_sort");
                Blob image = rs.getBlob("item_image");
                System.out.printf("%d\t%s\t%f\t%s%n", id, name, price, sort);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
