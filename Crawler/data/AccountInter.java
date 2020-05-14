11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-order/src/main/java/com/hyf/mtcc/demo/order/inter/AccountInter.java
package com.hyf.mtcc.demo.order.inter;

import com.hyf.mtcc.demo.order.request.DecreaseAccountReq;
import com.yf.mtcc.core.annotation.Mtcc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Descrtption AccountInter
 * @Author Elvis
 * @Date 2019/9/25
 **/
@FeignClient(value = "cloud-account",fallback = AccountInterHystrix.class)
public interface AccountInter {

    @GetMapping("/account/findAccountByName")
    String findAccountByName(@RequestParam("username") String username);

    @Mtcc
    @PostMapping("/account/decrease")
    String decreaseAccount(@RequestBody @Validated DecreaseAccountReq req);

}
