// 数据库通用类 放通用的操作函数 连接 增删改查
package dao;

import java.util.*;
import java.sql.*;
import service.Config;

public class DatabaseUtil {
    // 依次获取config配置文件中的配置参数
    private String url = Config.getConfig("url");
    private String user = Config.getConfig("user");
    private String password = Config.getConfig("password");
    private String driverClass = Config.getConfig("driverClass");
    private Connection con = null;

    // 构造函数 调用连接数据库的函数（封成函数比较好）
    public DatabaseUtil() {
        con = getConnection();
    }

    // 数据库连接函数 获取connection
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 赠、删、改等调用这个函数 用来执行sql数据库命令
    public int executeUpdate(String sql, Object... params) {
        int rlt = 0;
        try {
            PreparedStatement pstmt = null;
            pstmt = con.prepareStatement(sql);
            putParams(pstmt, params);
            rlt = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rlt;
    }

    // 放置参数
    private void putParams(PreparedStatement pstmt, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; ++i) {
                // pstmt.setObject(i+1,params);
                if (params[i] instanceof String)
                    pstmt.setString(i + 1, (String) params[i]);
                else if (params[i] instanceof Integer)
                    pstmt.setInt(i + 1, (Integer) params[i]);
                else if (params[i] == null)
                    pstmt.setNull(i + 1, Types.INTEGER);
            }
        }
    }

    // 查询函数
    public List<Map<String, Object>> query(String sql, Object... params) {
        PreparedStatement pstmt = null;
        List<Map<String, Object>> list = null;
        try {
            pstmt = con.prepareStatement(sql); // 准备查询
            putParams(pstmt, params); // 准备参数
            ResultSet rs = pstmt.executeQuery(); // 执行查询
            ResultSetMetaData rsmd = rs.getMetaData(); // 返回结果
            String[] keys = new String[rsmd.getColumnCount()]; // 创建返回结果的数组
            for (int i = 0; i < rsmd.getColumnCount(); ++i) { // 将返回结果的列名字放到数组里
                keys[i] = rsmd.getColumnLabel(i + 1);
            }
            list = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                // 将返回结果变为 list[列名]=这一列的数据 的形式
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < keys.length; ++i) {
                    map.put(keys[i], rs.getObject(keys[i]));
                }
                list.add(map);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void close() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("已关闭接口..");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
