1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/model/lotlot/User.java
package com.mybatis.model.lotlot;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private String userId;
    private String userName;
    private String userAddress;
    private String userSex;
    private Date userBirthday;
    private List<Role> roles;
}
