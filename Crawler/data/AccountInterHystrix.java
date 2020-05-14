11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/inter/AccountInterHystrix.java
package com.hyf.mtcc.demo.order.inter;

import com.hyf.mtcc.demo.order.request.DecreaseAccountReq;
import com.yf.mtcc.common.exception.MtccHystrixException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AccountInterHystrix implements AccountInter {

    @Override
    public String findAccountByName(String username) {
        log.error("AccountInterHystrix findAccountByName,username:{}", username);
        //熔断需要抛出mtccHystrixException异常
        throw new MtccHystrixException("远程调用AccountInter超时");
    }

    @Override
    public String decreaseAccount(DecreaseAccountReq req) {
        log.error("AccountInterHystrix decreaseAccount,req:{}", req);
        //熔断需要跑出mtccHystrixException异常
        throw new MtccHystrixException("远程调用AccountInter超时");
    }

}
