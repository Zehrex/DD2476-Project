2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/service/MetaService.java
package cn.blog.service;

import cn.blog.dto.MetaDto;
import cn.blog.dto.cond.MetaCond;
import cn.blog.Domin.MetaDomain;

import java.util.List;

/**
 * 项目服务层
 */
public interface MetaService {
    /**
     * 添加项目
     */
    void addMeta(MetaDomain meta);

    /**
     * 添加
     */
    void saveMeta(String type, String name, Integer mid);



    /**
     * 批量添加
     */
    void addMetas(Integer cid, String names, String type);



    /**
     * 添加或者更新
     */
    void saveOrUpdate(Integer cid, String name, String type);

    /**
     * 删除项目
     */
    void deleteMetaById(Integer mid);

    /**
     * 更新项目
     */
    void updateMeta(MetaDomain meta);

    /**
     * 根据编号获取项目
     */
    MetaDomain getMetaById(Integer mid);

    /**
     * 获取所有的项目
     */
    List<MetaDomain> getMetas(MetaCond metaCond);

    /**
     * 根据类型查询项目列表，带项目下面的文章数
     */
    List<MetaDto> getMetaList(String type, String orderby, int limit);
}
