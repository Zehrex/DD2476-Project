1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/service/StudentService.java
package com.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.mapper.StudentMapper;
import com.mybatis.model.single.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    /*查询全部数据*/
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    /*查询全部并分页 =========>分页操作*/
    public List<Student> findAllPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage - 1, pageSize);
        PageInfo<Student> pageInfo = new PageInfo<>(studentMapper.findAll());
        return pageInfo.getList();
    }

    /*根据id查询*/
    public Student findOneById(String id) {
        return studentMapper.findOneById(id);
    }

    /*根据id删除*/
    public void deleteOneById(String id) {
        studentMapper.deleteOneById(id);
    }

    /*添加数据*/
    public void insertOne(Student student) {
        studentMapper.insertOne(student);
    }

    /*更新数据*/
    public void updateOne(Student student) {
        studentMapper.updateOne(student);
    }

    /*动态查询语句*/
    public List<Student> findSome(Student student) {
        return studentMapper.findSome(student);
    }

    /*模糊查询*/
    public List<Student> findSomeLike(String name) {
        return studentMapper.findSomeLike(name);
    }

    /*查询前几条数据*/
    public List<Student> findSomeTop(Integer count) {
        return studentMapper.findSomeTop(count);
    }
}
