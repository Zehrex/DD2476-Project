2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/service/CategoryService.java
package com.example.lx.service;

import com.example.lx.dao.CategoryDao;
import com.example.lx.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lixiang
 * @Date 2020/5/10 13:35
 * @Version 1.0
 */

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDAO;

    public List<Category> list() {

        Sort sort = null;
        //sort=new Sort(Sort.Direction.DESC, "id");
        sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int id) {
        Category c= categoryDAO.findById(id).orElse(null);
        return c;
    }
}


