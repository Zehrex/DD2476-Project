2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dao/OptionDao.java
package cn.blog.dao;

import cn.blog.Domin.OptionsDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站配置dao
 */
@Mapper
public interface OptionDao {

    /**
     * 删除网站配置
     */
    int deleteOptionByName(@Param("name") String name);

    /**
     * 更新网站配置
     */
    int updateOptionByName(OptionsDomain options);

    /***
     * 根据名称获取网站配置
     */
    OptionsDomain getOptionByName(@Param("name") String name);

    /**
     * 获取全部网站配置
     */
    List<OptionsDomain> getOptions();
}
