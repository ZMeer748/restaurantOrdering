package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import menu.Menu;
import menu.MenuItem;
import resources.PropertiesProcess;

/**
 * DB_IO
 */
public class DB_Menu_Process {

    static String dbName, dbUserName, dbPassword;

    static {
        dbName = PropertiesProcess.getProperty("Database.name");
        dbUserName = PropertiesProcess.getProperty("Database.user_name");
        dbPassword = PropertiesProcess.getProperty("Database.user_password");
    }

    public static void main(String[] args) {
        for (MenuItem item : Menu.getList()) {
            addItem(item.getCode(), item.getName(), (float) item.getPrice(), item.getSort());
        }
    }

    public static boolean addItem(int id, String name, float price, String sort) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {

            File f = new File("img/" + name + ".png");
            if (!f.exists()) {
                f = new File("img/no picture.png");
            }
            String sql = "insert into menu_item values(" + id + ", '" + name + "', " + price + ", '" + sort + "', 'img/"
                    + name + ".png')";
            s.execute(sql);

            System.out.println("插入菜品 " + name + " 成功");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<MenuItem> getMenuItemList() {
        ArrayList<MenuItem> tempList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "select * from menu_item";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                float price = rs.getFloat("item_price");
                String sort = rs.getString("item_sort");
                String imageURL = rs.getString("item_image_URL");
                File file;
                if (imageURL != null) {
                    file = new File(imageURL);
                    if (!file.exists())
                        file = new File("img/no picture.png");
                } else {
                    file = new File("img/no picture.png");
                }
                tempList.add(new MenuItem(id, name, price, sort, file.getAbsolutePath()));
                // System.out.printf("%d\t%s\t%f\t%s\t%s%n", id, name, price, sort,
                // file.getAbsolutePath());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public static boolean isItemExistInMenu(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "select * from menu_item where item_id = " + id;

            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                System.out.println(id + " exists in the menu.");
                return true;
            } else {
                System.out.println(id + " doesn't exist in the menu.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateItemPrice(int id, float price) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "update menu_item set item_price = " + price + " where item_id = " + id;

            s.execute(sql);

            System.out.println("update " + id + "'s price to " + price);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("update failed");
        return false;
    }

    public static boolean updateItemName(int id, String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "update menu_item set item_name = '" + name + "' where item_id = " + id;

            s.execute(sql);

            System.out.println("update " + id + "'s name to " + name);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("update failed");
        return false;
    }

    public static boolean updateItemSort(int id, String sort) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&characterEncoding=UTF-8", dbUserName,
                dbPassword); Statement s = c.createStatement();) {
            String sql = "update menu_item set item_sort = '" + sort + "' where item_id = " + id;

            s.execute(sql);

            System.out.println("update " + id + "'s sort to " + sort);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("update failed");
        return false;
    }
}
