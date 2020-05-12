1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/model/lotlot/Role.java
package com.mybatis.model.lotlot;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String roleId;
    private String roleName;
    private String roleDesc;
    private List<User> users;
}
