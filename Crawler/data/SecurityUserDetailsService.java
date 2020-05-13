6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/SecurityUserDetailsService.java
package com.github.taoroot.taoiot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author : zhiyi
 * Date: 2020/3/21
 */
public interface SecurityUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUserId(Integer userId) throws UsernameNotFoundException;

    UserDetails loadUserByWechat(String code) throws UsernameNotFoundException;

    UserDetails loadUserByAlipay(String code) throws UsernameNotFoundException;
}
