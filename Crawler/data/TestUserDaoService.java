2
https://raw.githubusercontent.com/joneconsulting/springbootproject/master/src/test/java/com/example/demo/dao/TestUserDaoService.java
package com.example.demo.dao;

import com.example.demo.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.List;

public class TestUserDaoService {
    UserDaoService service = new UserDaoService();

    @Test
    public void test_사용자목록조회() {
        List<User> list = service.getUserList();
        assertEquals(3, list.size(),
                "초기 사용자는 3명이어야 합니다.");
    }

    @Test
    public void test_사용자정보확인() {
        User user = service.getUserList().get(0);
        assertTrue(user.getId() == 1);
    }

    @Test
    public void test_사용자조회() {
        User user = service.getUser(Integer.valueOf(1));
        assertNotNull(user);
        assertEquals(1, user.getId(),
                "사용자 ID가 잘못되었습니다.");
    }
}
