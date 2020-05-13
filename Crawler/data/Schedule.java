1
https://raw.githubusercontent.com/wanzicong/mybatis-study/master/src/main/java/com/mybatis/schedule/Schedule.java
package com.mybatis.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*定时器任务*/
@Component
public class Schedule {
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void test() {
        System.out.println("两个小时过去了啊");
    }
}
