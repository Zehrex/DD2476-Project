21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/test/java/com/southwind/phone_store_demo/repository/PhoneCategoryRepositoryTest.java
package com.southwind.phone_store_demo.repository;

import com.southwind.phone_store_demo.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneCategoryRepositoryTest {

    @Autowired
    private PhoneCategoryRepository repository;

    @Test
    void findAll(){
        List<PhoneCategory> list = repository.findAll();
        for (PhoneCategory phoneCategory : list) {
            System.out.println(phoneCategory);
        }
    }

    @Test
    void findByCategoryType(){
        PhoneCategory phoneCategory = repository.findByCategoryType(1);
        System.out.println(phoneCategory);
    }

}