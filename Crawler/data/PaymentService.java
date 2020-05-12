1
https://raw.githubusercontent.com/iMine141/springcloud-test/master/cloud-provider-paymeny8001/src/main/java/com/atguigu/springcloud/service/PaymentService.java
package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther zzyy
 * @create 2020-02-18 10:40
 */
public interface PaymentService
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
