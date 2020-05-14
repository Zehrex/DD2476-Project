137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-front/src/main/java/com/java2nb/novel/mapper/FrontUserBookshelfMapper.java
package com.java2nb.novel.mapper;

import com.java2nb.novel.vo.BookShelfVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontUserBookshelfMapper extends UserBookshelfMapper {

    List<BookShelfVO> listBookShelf(@Param("userId") Long userId);

}
