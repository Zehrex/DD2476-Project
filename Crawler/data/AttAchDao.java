2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dao/AttAchDao.java
package cn.blog.dao;

import cn.blog.dto.AttAchDto;
import cn.blog.Domin.AttAchDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AttAchDao {


    /**
     * 添加单个附件信息
     */
    int addAttAch(AttAchDomain attAchDomain);

    /**
     * 批量添加附件信息
     */
    int batchAddAttAch(List<AttAchDomain> list);

    /**
     * 根据主键编号删除附件信息
     */
    int deleteAttAch(int id);

    /**
     * 更新附件信息
     */
    int updateAttAch(AttAchDomain attAchDomain);

    /**
     * 根据主键获取附件信息
     */
    AttAchDto getAttAchById(@Param("id") int id);

    /**
     * 获取所有的附件信息
     */
    List<AttAchDto> getAtts();

    /**
     * 查找附件的数量
     */
    Long getAttsCount();
}
