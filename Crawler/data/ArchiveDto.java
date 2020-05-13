2
https://raw.githubusercontent.com/chenval/my-site/master/src/main/java/cn/blog/dto/ArchiveDto.java
package cn.blog.dto;

import cn.blog.Domin.ContentDomain;

import java.util.List;

/**
 * 文章归档类
 */
public class ArchiveDto {

    private String date;
    private String count;
    private List<ContentDomain> articles;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ContentDomain> getArticles() {
        return articles;
    }

    public void setArticles(List<ContentDomain> articles) {
        this.articles = articles;
    }
}
