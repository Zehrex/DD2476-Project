1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/mapper/StudentMapper.java
package com.mybatis.mapper;

import com.mybatis.model.single.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper {

    /*查询全部学生*/
    List<Student> findAll();

    /*根据id查询*/
    Student findOneById(String id);

    /*根据id删除数据*/
    void deleteOneById(String id);

    /*添加数据*/
    void insertOne(Student student);

    /*跟新数据*/
    void updateOne(Student student);

    /*动态查询*/
    List<Student> findSome(Student student);

    /*模糊查询*/
    List<Student> findSomeLike(String name);

    /*查询几条数据=======>使用注解*/
    List<Student> findSomeTop(@Param("count") Integer count);
}
