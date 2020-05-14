137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-front/src/main/java/com/java2nb/novel/mapper/FrontBookCommentMapper.java
package com.java2nb.novel.mapper;

import com.java2nb.novel.vo.BookCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontBookCommentMapper extends BookCommentMapper {

    List<BookCommentVO> listCommentByPage(@Param("userId") Long userId, @Param("bookId") Long bookId);

}
