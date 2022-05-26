package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    private static Connection conn = null;
    private static Statement stmt = null;

    static {
        Properties pro = new Properties();
        try {
            pro.load(new FileReader("C:\\Users\\zy\\Desktop\\studentManager\\src\\jdbc.properties"));
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url,user,password);
    }
    // 查询方法
    public static ResultSet runQuery(String sql) throws Exception {
        if (conn == null){
            conn = getConnection();
        }
        if ( stmt == null){
            stmt = conn.createStatement();
        }
        return stmt.executeQuery(sql);
    }

    // 更新方法
    public static int runUpdate(String sql) throws Exception {
        int count = 0;
        if (conn == null) {
            conn = getConnection();
        }
        if (stmt == null) {
            stmt = conn.createStatement();
        }

        count = stmt.executeUpdate(sql);

        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
        return count;
    }

    public static void close(Statement stmt,Connection conn){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void close(ResultSet re, Statement stmt, Connection conn){
        if (re != null){
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
