3
https://raw.githubusercontent.com/harry-xqb/rent-house/master/src/main/java/com/harry/renthouse/web/dto/AuthenticationDTO.java
package com.harry.renthouse.web.dto;

import lombok.Data;

/**
 *  认证信息dto
 * @author Harry Xu
 * @date 2020/5/11 11:47
 */
@Data
public class AuthenticationDTO{

    private String token;

    private UserDTO user;
}
