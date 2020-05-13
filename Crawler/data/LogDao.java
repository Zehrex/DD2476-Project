2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dao/LogDao.java
package cn.blog.dao;

import cn.blog.Domin.LogDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface LogDao {

    /**
     * 添加日志
     */
    int addLog(LogDomain logDomain);

    /**
     * 删除日志
     */
    int deleteLogById(@Param("id") Integer id);

    /**
     * 获取日志
     */
    List<LogDomain> getLogs();
}
