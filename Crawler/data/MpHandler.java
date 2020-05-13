6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/mp/MpHandler.java
package com.github.taoroot.taoiot.mp;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * @author : zhiyi
 * Date: 2020/5/7
 */
public interface MpHandler {

    /**
     * 处理消息
     *
     * @param inMessage
     * @return
     */
    String process(WxMpXmlMessage inMessage) throws Exception;
}
