137
https://raw.githubusercontent.com/201206030/novel-plus/master/novel-front/src/main/java/com/java2nb/novel/vo/BookVO.java
package com.java2nb.novel.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java2nb.novel.entity.Book;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class BookVO extends Book implements Serializable {

    @JsonFormat(timezone = "GMT+8", pattern = "MM/dd HH:mm")
    private Date lastIndexUpdateTime;


}
