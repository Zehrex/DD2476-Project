2
https://raw.githubusercontent.com/okhurley/oauth2/master/oauth2_uaa/src/main/java/com/okhurley/uaa/config/MyUserDetailsService.java
package com.okhurley.uaa.config;

import com.okhurley.uaa.dao.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //将来连接数据库根据账号查询用户信息
        com.okhurley.uaa.entity.User user = userMapper.selectByUsername(username);
        System.out.println(user);
        if(user == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }

        UserDetails userDetails = User.withUsername(username).password(user.getPassword()).authorities("p1").build();
        return userDetails;
    }
}
