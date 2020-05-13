2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/service/ContentService.java
package cn.blog.service;

import cn.blog.dto.cond.ContentCond;
import cn.blog.Domin.ContentDomain;
import com.github.pagehelper.PageInfo;

/**
 * 文章服务层
 */
public interface ContentService {

    /**
     * 添加文章
     */
    void addArticle(ContentDomain contentDomain);

    /**
     * 根据编号删除文章
     */
    void deleteArticleById(Integer cid);

    /**
     * 更新文章
     */
    void updateArticleById(ContentDomain contentDomain);

    /**
     * 更新分类
     */
    void updateCategory(String ordinal, String newCatefory);



    /**
     * 添加文章点击量
     */
    void updateContentByCid(ContentDomain content);

    /**
     * 根据编号获取文章
     */
    ContentDomain getAtricleById(Integer cid);

    /**
     * 根据条件获取文章列表
     */
    PageInfo<ContentDomain> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize);

    /**
     * 获取最近的文章（只包含id和title）
     */
    PageInfo<ContentDomain> getRecentlyArticle(int pageNum, int pageSize);

    /**
     * 搜索文章
     */
    PageInfo<ContentDomain> searchArticle(String param, int pageNun, int pageSize);
}
