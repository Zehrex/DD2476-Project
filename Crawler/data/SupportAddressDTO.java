3
https://raw.githubusercontent.com/harry-xqb/rent-house/master/src/main/java/com/harry/renthouse/web/dto/SupportAddressDTO.java
package com.harry.renthouse.web.dto;

import lombok.Data;

/**
 *  支持区域dto
 * @author Harry Xu
 * @date 2020/5/8 17:36
 */
@Data
public class SupportAddressDTO {

    private Long id;

    private String enName;

    private String cnName;

    private String level;
}
