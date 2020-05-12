6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/SecurityUser.java
package com.github.taoroot.taoiot.security;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author : zhiyi
 * Date: 2020/2/13
 */
@Getter
@Setter
public class SecurityUser extends User {

    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 登录方式
     */
    private LoginType loginType;
    /**
     * 微信公众号openId
     */
    private String wxMpOpenid;
    /**
     * 支付宝公众号openId
     */
    private String aliMpOpenid;
    /**
     * 对外提供数据的使用,作用和密码差不多,但是password能登录平台,toke不能
     */
    private String token;

    public SecurityUser(Integer id, LoginType loginType, String wxMpOpenid, String aliMpOpenid, String username, String password, String token, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.loginType = loginType;
        this.wxMpOpenid = wxMpOpenid;
        this.aliMpOpenid = aliMpOpenid;
        this.token = token;
    }
}
