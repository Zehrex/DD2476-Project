11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-demo/mtcc-demo-account/src/main/java/com/hyf/mtcc/demo/account/service/impl/AccountServiceImpl.java
package com.hyf.mtcc.demo.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyf.mtcc.demo.account.bean.Account;
import com.hyf.mtcc.demo.account.bean.AccountFreeze;
import com.hyf.mtcc.demo.account.dao.AccountFreezeMapper;
import com.hyf.mtcc.demo.account.dao.AccountMapper;
import com.hyf.mtcc.demo.account.request.DecreaseAccountReq;
import com.hyf.mtcc.demo.account.service.AccountService;
import com.yf.mtcc.common.exception.MtccException;
import com.yf.mtcc.core.annotation.Mtcc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Descrtption AccountServiceImpl
 * @Author Elvis
 * @Date 2019/9/25
 **/
@Slf4j
@Service
@Transactional(readOnly = true)
@SuppressWarnings("all")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountFreezeMapper accountFreezeMapper;

    @Transactional(rollbackFor = Exception.class, readOnly = false)
    @Mtcc(confirmMethod = "decreaseAccountConfirm", cancelMethod = "decreaseAccountCancel")
    @Override
    public Integer decrease(DecreaseAccountReq req) {
        log.info("==================decrease开始=====================req:{}" + req);
        String username = req.getUsername();
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Account dbAccount = accountMapper.selectOne(wrapper);
        int result = 0;
        if (dbAccount != null) {
            result = accountMapper.freezeAmount(dbAccount.getId(), req.getAmount(), new Date());
            if (result > 0) {
                //模拟try阶段处理超时
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                AccountFreeze accountFreeze = new AccountFreeze();
                accountFreeze.setOrderId(req.getOrderId());
                accountFreeze.setFreezeAmount(req.getAmount());
                accountFreeze.setUserId(dbAccount.getId());
                accountFreeze.setCreateTime(new Date());
                result = accountFreezeMapper.insert(accountFreeze);
            }
            //模拟try阶段处理出现异常
//            throw new MtccException("扣款出现异常");
        }
        log.info("==================decrease结束=====================req:{}" + req);
        return result;
    }

    @Override
    public String findAccountByName(String username) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Account dbAccount = accountMapper.selectOne(wrapper);
        if (dbAccount != null) {
            return dbAccount.getId();
        }
        return null;
    }


    /**
     * 幂等性
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public Integer decreaseAccountConfirm(DecreaseAccountReq req) {
        log.info("==================decreaseAccountConfirm开始=====================req:{}" + req);
        QueryWrapper<AccountFreeze> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", req.getOrderId());
        AccountFreeze accountFreeze = accountFreezeMapper.selectOne(wrapper);
        int result = 0;
        if (accountFreeze != null) {
            result = accountFreezeMapper.deleteById(accountFreeze.getId());
            if (result > 0) {
                result = accountMapper.freezeAmountConfirm(req.getUsername(), req.getAmount(), new Date());
            }
        }
        //int result = accountFreezeMapper.deleteFreezeRecord(req.getOrderId());//参考 https://www.jianshu.com/p/11c46dbfd6e7 https://www.jianshu.com/p/f8495015dace
        log.info("==================decreaseAccountConfirm结束=====================req:{}" + req);
        return result;
    }

    /**
     * 幂等性
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public Integer decreaseAccountCancel(DecreaseAccountReq req) {
        log.info("==================decreaseAccountCancel开始=====================req:{}" + req);
        QueryWrapper<AccountFreeze> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", req.getOrderId());
        AccountFreeze accountFreeze = accountFreezeMapper.selectOne(wrapper);
        int result = 0;
        if (accountFreeze != null) {
            result = accountFreezeMapper.deleteById(accountFreeze.getId());
            if (result > 0) {
                result = accountMapper.freezeAmountCancel(req.getUsername(), req.getAmount(), new Date());
            }
        }
        log.info("==================decreaseAccountCancel结束=====================req:{}" + req);
        return result;
    }

}
