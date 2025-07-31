package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static final String CONFIG_FILE = "db.properties";
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    
    // 线程局部变量存储当前线程的数据库连接
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    
    static {
        try {
            // 加载配置文件
            loadConfig();
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    
    // 加载数据库配置
    private static void loadConfig() throws IOException {
        Properties props = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        if (in != null) {
            props.load(in);
            URL = props.getProperty("db.url");
            USERNAME = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");
            in.close();
        } else {
            throw new IOException("无法加载数据库配置文件: " + CONFIG_FILE);
        }
    }
    
    // 获取数据库连接
    public static Connection getConnection() {
        Connection conn = threadLocal.get();
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                threadLocal.set(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    // 开启事务
    public static void beginTransaction() {
        try {
            Connection conn = getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 提交事务
    public static void commitTransaction() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            try {
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }
    
    // 回滚事务
    public static void rollbackTransaction() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    }
    
    // 关闭数据库连接
    public static void closeConnection() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                threadLocal.remove();
            }
        }
    }
}    