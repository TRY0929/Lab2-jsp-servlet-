// 设置users表数据相关
package utils;
public class UserTable {
    private String username;//primary key
    private String password;
    public UserTable(String username,String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
