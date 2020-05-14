21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/test/java/com/southwind/phone_store_demo/repository/PhoneInfoRepositoryTest.java
package com.southwind.phone_store_demo.repository;

import com.southwind.phone_store_demo.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneInfoRepositoryTest {

    @Autowired
    private PhoneInfoRepository repository;

    @Test
    void findAll(){
        List<PhoneInfo> list = repository.findAll();
        for (PhoneInfo phoneInfo : list) {
            System.out.println(phoneInfo);
        }
    }

    @Test
    void findAllByCategoryType(){
        List<PhoneInfo> list = repository.findAllByCategoryType(1);
        for (PhoneInfo phoneInfo : list) {
            System.out.println(phoneInfo);
        }
    }

}