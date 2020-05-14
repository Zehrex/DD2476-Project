34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_search/src/main/java/com/tensquare/search/dao/ArticleDao.java
package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 李聪
 * @date 2020/2/17 21:43
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
