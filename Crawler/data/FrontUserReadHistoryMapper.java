137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-front/src/main/java/com/java2nb/novel/mapper/FrontUserReadHistoryMapper.java
package com.java2nb.novel.mapper;

import com.java2nb.novel.vo.BookReadHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontUserReadHistoryMapper extends UserReadHistoryMapper {

    List<BookReadHistoryVO> listReadHistory(@Param("userId") Long userId);

}
