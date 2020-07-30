package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC 操作的工具类
 */
public class JdbcUtils {

    private static DataSource dataSource = null;

    static {
        dataSource = new ComboPooledDataSource("mvcapp");
    }

    /**
     * 释放Connection连接
     *
     * @param connection
     */
    public static void releaseConnection(Connection connection) {
        try {
            if (connection!=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 返回数据源的一个Connection对象
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
