1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/mapper/TeacherMapper.java
package com.mybatis.mapper;

import com.mybatis.model.single.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherMapper {
    /*查询全部*/
    public List<Teacher> findAll();

    /*根据id */
    Teacher findOneById(String teacherId);

    /*添加数据*/
    void insertOne(Teacher teacher);
}
