11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-account/src/main/java/com/hyf/mtcc/demo/account/service/AccountService.java
package com.hyf.mtcc.demo.account.service;

import com.hyf.mtcc.demo.account.request.DecreaseAccountReq;

public interface AccountService {

    Integer decrease(DecreaseAccountReq req);

    String findAccountByName(String username);

}
