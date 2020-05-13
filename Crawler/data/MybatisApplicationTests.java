1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/test/java/com/mybatis/MybatisApplicationTests.java
package com.mybatis;

import com.mybatis.mapper.AccountMapper;
import com.mybatis.mapper.RolesMapper;
import com.mybatis.model.lotlot.Role;
import com.mybatis.model.lotlot.User;
import com.mybatis.model.single.Account;
import com.mybatis.model.single.Teacher;
import com.mybatis.service.StudentService;
import com.mybatis.service.TeacherService;
import com.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisApplicationTests {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    AccountMapper accountMapper;

    @Test
    void contextLoads() {

    }
}
