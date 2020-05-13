1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/test/java/com/loststars/dynamic/datasource/QuickbuyApplicationTests.java
package com.loststars.dynamic.datasource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.loststars.quickbuy.QuickbuyApplication;
import com.loststars.quickbuy.service.impl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuickbuyApplication.class)
public class QuickbuyApplicationTests {
    
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    
    @Test
	public void test() {
	    Integer a = new Integer(3);
	    Integer b = new Integer(3);
	    System.out.println(a.intValue() == b.intValue());
	}
}
