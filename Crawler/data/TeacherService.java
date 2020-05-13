1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/service/TeacherService.java
package com.mybatis.service;

import com.mybatis.mapper.TeacherMapper;
import com.mybatis.model.single.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    /*查询全部*/
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    /*根据id查询*/
    public Teacher findOneById(String teacherId) {
        return teacherMapper.findOneById(teacherId);
    }

    /*添加数据*/
    public void insertOne(Teacher teacher) {
        teacherMapper.insertOne(teacher);
    }
}
