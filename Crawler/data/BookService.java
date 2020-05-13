2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/service/BookService.java
package com.example.lx.service;
/**
 * @Author lixiang
 * @Date 2020/5/10 13:37
 * @Version 1.0
 */

import com.example.lx.dao.BookDao;
import com.example.lx.entity.Book;
import com.example.lx.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDAO ;
    @Autowired
    CategoryService categoryService;

    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }


    public List<Book> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return bookDAO.findAll(sort);
    }

    public void addOrUpdate(Book book) {
        bookDAO.save(book);
    }

    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }
}
