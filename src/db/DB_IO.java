package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import menu.MenuItem;

/**
 * DB_IO
 */
public class DB_IO {

    public static void main(String[] args) {
        // for (MenuItem item : Menu.getList()) {
        // insertInto_menu_item(item.getCode(), item.getName(), (float) item.getPrice(),
        // item.getSort());
        // }

        getItemList();
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

    public static ArrayList<MenuItem> getItemList() {
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

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                float price = rs.getFloat("item_price");
                String sort = rs.getString("item_sort");
                Blob imageBlob = rs.getBlob("item_image");
                File file;
                if (imageBlob != null) {
                    OutputStream out = null;
                    file = File.createTempFile(name, ".png");
                    out = new FileOutputStream(file);
                    out.write(imageBlob.getBytes(1, (int) imageBlob.length()));
                    out.close();
                } else {
                    file = new File("img/no picture.png");
                }
                tempList.add(new MenuItem(id, name, price, sort, file.getAbsolutePath()));
                // System.out.printf("%d\t%s\t%f\t%s\t%s%n", id, name, price, sort,
                // file.getAbsolutePath());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

}
