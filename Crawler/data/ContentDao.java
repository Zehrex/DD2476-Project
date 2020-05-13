2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dao/ContentDao.java
package cn.blog.dao;

import cn.blog.dto.ArchiveDto;
import cn.blog.dto.cond.ContentCond;
import cn.blog.Domin.ContentDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章持久层
 */
@Mapper
public interface ContentDao {

    /**
     * 添加文章
     */
    int addArticle(ContentDomain contentDomain);

    /**
     * 根据编号删除文章
     */
    int deleteArticleById(@Param("cid") Integer cid);

    /**
     * 更新文章
     */
    int updateArticleById(ContentDomain contentDomain);

    /**
     * 更新文章的评论数
     */
    int updateArticleCommentCountById(@Param("cid") Integer cid, @Param("commentsNum") Integer commentsNum);



    /**
     * 根据编号获取文章
     */
    ContentDomain getArticleById(@Param("cid") Integer cid);

    /**
     * 根据条件获取文章列表
     */
    List<ContentDomain> getArticlesByCond(ContentCond contentCond);

    /**
     * 获取文章总数量
     */
    Long getArticleCount();

    /**
     * 获取归档数据
     */
    List<ArchiveDto> getArchive(ContentCond contentCond);

    /**
     * 获取最近的文章（只包含id和title）
     */
    List<ContentDomain> getRecentlyArticle();

    /**
     * 搜索文章-根据标题 或 内容匹配
     */
    List<ContentDomain> searchArticle(@Param("param") String param);

}
