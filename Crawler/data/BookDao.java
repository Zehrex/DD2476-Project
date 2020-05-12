2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/dao/BookDao.java
package com.example.lx.dao;
/**
 * @Author lixiang
 * @Date 2020/5/10 13:33
 * @Version 1.0
 */

import com.example.lx.entity.Book;
import com.example.lx.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author     ：lx
 * @date       ：Created in 2020/5/10 13:33
 * @description：
 * @modified By：
 * @version: 1.0
 */
public interface BookDao extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}

