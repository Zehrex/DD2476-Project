137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-crawl/src/main/java/com/java2nb/novel/mapper/CrawlBookIndexMapper.java
package com.java2nb.novel.mapper;

import com.java2nb.novel.entity.BookIndex;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 */
public interface CrawlBookIndexMapper extends BookIndexMapper {


    /**
     * 查询最后的章节
     * */
    BookIndex queryLastIndex(@Param("bookId") Long bookId);
}
