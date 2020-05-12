2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/Domain/Emp.java
package jdbc.Domain;

import java.util.Date;

/**
 *  封装Emp表数据的JavaBean
 */
public class Emp {
    private int id;
    private String admin_login;
    private int last_login_time;
    private int status;
    private Date deleted_at;
    private String last_login_ip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdmin_login() {
        return admin_login;
    }

    public void setAdmin_login(String admin_login) {
        this.admin_login = admin_login;
    }

    public int getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(int last_login_time) {
        this.last_login_time = last_login_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", admin_login='" + admin_login + '\'' +
                ", last_login_time=" + last_login_time +
                ", status=" + status +
                ", deleted_at=" + deleted_at +
                ", last_login_ip='" + last_login_ip + '\'' +
                '}';
    }
}
