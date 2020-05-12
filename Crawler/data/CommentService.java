2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/service/CommentService.java
package cn.blog.service;

import cn.blog.dto.cond.CommentCond;
import cn.blog.Domin.CommentDomain;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 评论服务层
 */
public interface CommentService {

    /**
     * 新增评论
     */
    void addComment(CommentDomain commentDomain);

    /**
     * 删除评论
     */
    void deleteComment(Integer coid);

    /**
     * 更新评论的状态
     */
    void updateCommentStatus(Integer coid, String status);


    /**
     * 查找单条评论
     */
    CommentDomain getCommentById(Integer coid);

    /**
     * 根据文章编号获取评论列表--只显示通过审核的评论-正常状态的
     */
    List<CommentDomain> getCommentsByCId(Integer cid);

    /**
     * 根据条件获取评论列表
     */
    PageInfo<CommentDomain> getCommentsByCond(CommentCond commentCond, int pageNum, int pageSize);
}
