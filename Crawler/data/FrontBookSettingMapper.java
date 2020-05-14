137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-front/src/main/java/com/java2nb/novel/mapper/FrontBookSettingMapper.java
package com.java2nb.novel.mapper;

import com.java2nb.novel.vo.BookSettingVO;

import java.util.List;

/**
 * @author Administrator
 */
public interface FrontBookSettingMapper extends BookSettingMapper {

    List<BookSettingVO> listVO();
}
