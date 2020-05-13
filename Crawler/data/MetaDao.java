2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dao/MetaDao.java
package cn.blog.dao;

import cn.blog.dto.MetaDto;
import cn.blog.dto.cond.MetaCond;
import cn.blog.Domin.MetaDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface MetaDao {

    /**
     * 添加项目
     */
    int addMeta(MetaDomain meta);

    /**
     * 删除项目
     */
    int deleteMetaById(@Param("mid") Integer mid);

    /**
     * 更新项目
     */
    int updateMeta(MetaDomain meta);

    /**
     * 根据编号获取项目
     */
    MetaDomain getMetaById(@Param("mid") Integer mid);


    /**
     * 根据条件查询
     */
    List<MetaDomain> getMetasByCond(MetaCond metaCond);

    /**
     * 根据类型获取meta数量
     */
    Long getMetasCountByType(@Param("type") String type);

    /**
     * 根据sql查询
     */
    List<MetaDto> selectFromSql(Map<String, Object> paraMap);

}
