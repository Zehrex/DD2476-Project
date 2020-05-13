1
https://raw.githubusercontent.com/rubywooJ/beyond/master/src/main/java/cn/tsxygfy/beyond/mapper/CommentsMapper.java
package cn.tsxygfy.beyond.mapper;

import cn.tsxygfy.beyond.model.po.Comments;

import java.util.List;

public interface CommentsMapper {
    Long deleteByPrimaryKey(Long id);

    Long insert(Comments record);

    Comments selectByPrimaryKey(Long id);

    List<Comments> selectAll();

    Long updateByPrimaryKey(Comments record);

    Long getCount();
}
