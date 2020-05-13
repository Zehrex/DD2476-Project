2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/dao/CategoryDao.java
package com.example.lx.dao;
/**
 * @Author lixiang
 * @Date 2020/5/10 13:34
 * @Version 1.0
 */

import com.example.lx.entity.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author     ：lx
 * @date       ：Created in 2020/5/10 13:34
 * @description：
 * @modified By：
 * @version: 1.0
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
