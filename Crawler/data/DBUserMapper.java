6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/service/DBUserMapper.java
package com.github.taoroot.taoiot.security.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author : zhiyi
 * Date: 2020/2/11
 */
public interface DBUserMapper {

    @Select("select * from ${table} where id = #{id}")
    DbUser getByUserId(@Param("table") String table, @Param("id") Integer id);

    @Select("select * from ${table} where username = #{username}")
    DbUser getByUsername(@Param("table") String table, @Param("username") String username);

    @Select("select * from ${table} where ali_mp_openid = #{openId}")
    DbUser getByAliMpOpenid(@Param("table") String table, @Param("openId") String openId);

    @Select("select * from ${table} where wx_mp_openid = #{openId}")
    DbUser getByWxMpOpenid(@Param("table") String table, @Param("openId") String openId);

    @Update("insert into ${table} (username, password,ali_mp_openid,wx_mp_openid, token, roles) " +
            "values (#{username}, #{password}, #{aliMpOpenid}, #{wxMpOpenid}, #{token}, #{roles})")
    void insert(@Param("table") String table,
                @Param("username") String username,
                @Param("password") String password,
                @Param("aliMpOpenid") String aliMpOpenid,
                @Param("wxMpOpenid") String wxMpOpenid,
                @Param("token") String token,
                @Param("roles") String roles
    );
}
