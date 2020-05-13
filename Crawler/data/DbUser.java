6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/service/DbUser.java
package com.github.taoroot.taoiot.security.service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : zhiyi
 * Date: 2020/2/10
 */
@Data
@ApiModel("用户")
@NoArgsConstructor
@AllArgsConstructor
public class DbUser {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "微信公众号openId")
    private String wxMpOpenid;

    @ApiModelProperty(value = "支付宝公众号openId")
    private String aliMpOpenid;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "角色列表")
    private String roles;
}
