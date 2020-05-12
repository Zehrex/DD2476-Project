2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dto/MetaDto.java
package cn.blog.dto;

import cn.blog.Domin.MetaDomain;

/**
 * 标签、分类列表
 */
public class MetaDto extends MetaDomain {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
