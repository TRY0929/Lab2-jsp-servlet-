package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.DatabaseUtil;
import utils.PersonTable;

public class PersonUtil {
    public void createTable(DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "create table person" + "(" + "username varchar(10) not null," + "name varchar(20) not null,"
                + "age int default 18," + "teleno char(11) default '18570253175'," + "primary key (name)" + ")"
                + "DEFAULT CHARSET=utf8;";
        DatabaseUtil.executeUpdate(sql);
    }

    public void addPerson(PersonTable p, DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "insert into person(username,name,age,teleno) values(?, ?, ?, ?)";
        Object[] obj = { p.getUsername(), p.getName(), p.getAge(), p.getTeleno() };
        DatabaseUtil.executeUpdate(sql, obj);
    }

    public int addOrModifyPerson(PersonTable p, DatabaseUtil DatabaseUtil) throws Exception {
        String sql_1 = "select * from person where username=?";
        List<Map<String, Object>> userList, personList;
        personList = DatabaseUtil.query(sql_1, p.getUsername());
        if (personList.isEmpty()) {// person表中不存在该username
            String sql_2 = "select * from users where username=?";
            userList = DatabaseUtil.query(sql_2, p.getUsername());
            if (userList.isEmpty()) {// user表中也不存在该username,
                String sql_3 = "insert into users(username,pass) values(?,?)";
                Object[] obj = { p.getUsername(), "888888" };// 默认的passwaod为888888
                DatabaseUtil.executeUpdate(sql_3, obj);
            }
            String sql_4 = "insert into person(username,name,age,teleno) values(?, ?, ?, ?)";
            Object[] obj = { p.getUsername(), p.getName(), p.getAge(), p.getTeleno() };
            int result = DatabaseUtil.executeUpdate(sql_4, obj);
            return result > 0 ? 1 : 0;
        } else {
            String sql_5 = "update person set name=?,age=?,teleno=? where username=?";
            Object[] obj = { p.getName(), p.getAge(), p.getTeleno(), p.getUsername() };
            int result = DatabaseUtil.executeUpdate(sql_5, obj);
            return result > 0 ? 3 : 2;
        }
    }

    public void delPersonOnUsername(String username, DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "delete from person where username like ?";
        DatabaseUtil.executeUpdate(sql, username);
    }

    // 删除person表
    public void dropTable(DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "drop table person";
        DatabaseUtil.executeUpdate(sql);
    }

    // 将数据库中的数据都一条条放到数组中
    public List<PersonTable> queAll(DatabaseUtil DatabaseUtil) throws Exception {
        String sql = "select * from person";
        List<Map<String, Object>> list = DatabaseUtil.query(sql);
        List<PersonTable> personList = new ArrayList<>();
        PersonTable person = null;
        for (Map<String, Object> map : list) {
            person = new PersonTable((String) map.get("username"), (String) map.get("name"), (Integer) map.get("age"),
                    (String) map.get("teleno"));
            personList.add(person);
        }
        return personList;
    }
}
