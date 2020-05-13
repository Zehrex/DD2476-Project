9
https://raw.githubusercontent.com/guozaizai/myHttpSdkDemo/master/app/src/main/java/myapp/com/xm/myapplication/Model/PwdLoginRequest.java
package myapp.com.xm.myapplication.Model;

import lombok.Data;

/**
 *
 */
@Data
public class PwdLoginRequest {
    private String username;
    private String password;

    public PwdLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
