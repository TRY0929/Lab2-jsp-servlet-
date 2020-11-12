// 专门用来对users表进行独有操作的类
package service;

import java.util.*;

import dao.DatabaseUtil;
import utils.UserTable;

public class UserUtil {
    // 创建users表
    public void createTable(DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "create table users" + "( " + "username varchar(10) not null," + "pass varchar(8) not null,"
                + "primary key (username)" + ")" + "DEFAULT CHARSET=utf8;";
        // 这里要记得设计编码为utf-8 和整个数据库一致
        DatabaseUtil.executeUpdate(sql);
    }

    public void addUserTable(UserTable u, DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "insert into users(username,pass) values(?, ?)";
        Object[] obj = { u.getUsername(), u.getPassword() };
        DatabaseUtil.executeUpdate(sql, obj);
    }

    public int delUserTableOnUsername(String username, DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "delete from users where username like ?";
        int result = DatabaseUtil.executeUpdate(sql, username);
        return result > 0 ? 1 : 0;
    }

    // 删除users表
    public void dropTable(DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "drop table users";
        DatabaseUtil.executeUpdate(sql);
    }

    // 将数据库中的数据都一条条放到数组中
    public List<UserTable> queAll(DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "select * from users";
        List<Map<String, Object>> list = DatabaseUtil.query(sql);
        List<UserTable> userList = new ArrayList<>();
        UserTable user = null;
        for (Map<String, Object> map : list) {
            user = new UserTable((String) map.get("username"), (String) map.get("pass"));
            userList.add(user);
        }
        return userList;
    }
}
