1
https://raw.githubusercontent.com/rubywooJ/beyond/master/src/main/java/cn/tsxygfy/beyond/mapper/LoginLogMapper.java
package cn.tsxygfy.beyond.mapper;

import cn.tsxygfy.beyond.model.po.LoginLog;

import java.util.List;

public interface LoginLogMapper {
    Long deleteByPrimaryKey(Long id);

    Long insert(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    List<LoginLog> selectAll();

    Long updateByPrimaryKey(LoginLog record);
}