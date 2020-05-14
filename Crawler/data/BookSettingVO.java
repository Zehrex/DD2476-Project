137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-front/src/main/java/com/java2nb/novel/vo/BookSettingVO.java
package com.java2nb.novel.vo;

import com.java2nb.novel.entity.BookSetting;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 11797
 */
@Data
public class BookSettingVO extends BookSetting implements Serializable {

    private String bookName;

    private String picUrl;

    private String authorName;

    private String bookDesc;

    private Float score;


    @Override
    public String toString() {
        return super.toString();
    }
}
