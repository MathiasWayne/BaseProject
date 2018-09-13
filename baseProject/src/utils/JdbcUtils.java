package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: AloysMack
 * jdbc连接的工具类
 **/
public class JdbcUtils {
    private static  DataSource dataSource;

    static {
        //创建properties集合用于读取数据库信息
        Properties properties=new Properties();
        InputStream in=JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getCon() throws SQLException {
        return dataSource.getConnection();
    }

    public static  DataSource getDataSource() {
        return dataSource;
    }
}
