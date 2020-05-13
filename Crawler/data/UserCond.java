2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dto/cond/UserCond.java
package cn.blog.dto.cond;

/**
 * 用户查找条件
 */
public class UserCond {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
