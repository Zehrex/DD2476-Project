2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dao/RelationShipDao.java
package cn.blog.dao;

import cn.blog.Domin.RelationShipDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 中间表
 */
@Mapper
public interface RelationShipDao {

    /**
     * 添加
     */
    int addRelationShip(RelationShipDomain relationShip);

    /**
     * 根据文章编号和meta编号删除关联
     */
    int deleteRelationShipById(@Param("cid") Integer cid, @Param("mid") Integer mid);

    /**
     * 根据文章编号删除关联
     */
    int deleteRelationShipByCid(@Param("cid") Integer cid);

    /**
     * 根据meta编号删除关联
     */
    int deleteRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 更新
     */
    int updateRelationShip(RelationShipDomain relationShip);

    /**
     * 根据文章主键获取关联
     */
    List<RelationShipDomain> getRelationShipByCid(@Param("cid") Integer cid);

    /**
     * 根据meta编号获取关联
     */
    List<RelationShipDomain> getRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 获取数量
     */
    Long getCountById(@Param("cid") Integer cid, @Param("mid") Integer mid);
}
