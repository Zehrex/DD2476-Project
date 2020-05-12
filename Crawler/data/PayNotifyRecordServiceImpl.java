6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/pay/service/impl/PayNotifyRecordServiceImpl.java
package com.github.taoroot.taoiot.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.taoroot.taoiot.pay.entity.PayNotifyRecord;
import com.github.taoroot.taoiot.pay.mapper.PayNotifyRecordMapper;
import com.github.taoroot.taoiot.pay.service.PayNotifyRecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步通知记录
 *
 * @author flizi
 * @date 2019-05-28 23:57:23
 */
@Slf4j
@AllArgsConstructor
public class PayNotifyRecordServiceImpl extends ServiceImpl<PayNotifyRecordMapper, PayNotifyRecord> implements PayNotifyRecordService {

}
