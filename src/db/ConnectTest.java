package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import menu.Menu;
import menu.MenuItem;

/**
 * ConnectTest
 */
public class ConnectTest {

    public static void main(String[] args) {
        Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            c = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/OrderSystemDB?serverTimezone=UTC&characterEncoding=UTF-8", "root",
                    "pw2018");

            s = c.createStatement();

            int i = 0;
            for (MenuItem item : Menu.getList()) {
                if (i < 9) {
                    i++;
                    continue;
                }
                File f = new File("img/" + item.getName() + ".png");
                FileInputStream fis = new FileInputStream(f);
                String sql = "insert into menu_item values(" + item.getCode() + ", '" + item.getName() + "', "
                        + (float) item.getPrice() + ", 'Food', ?)";
                // String sql_2 = "delete from menu_item where item_id = 1";
                PreparedStatement preparedStatement = c.prepareStatement(sql);
                preparedStatement.setBinaryStream(1, fis, (int) f.length());
                preparedStatement.executeUpdate();
                s.execute(sql);
                fis.close();
                preparedStatement.close();
                i++;
            }

            System.out.println("执行语句成功");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 数据库的连接时有限资源，相关操作结束后，养成关闭数据库的好习惯
            // 先关闭Statement
            if (s != null)
                try {
                    s.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            // 后关闭Connection
            if (c != null)
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }
}