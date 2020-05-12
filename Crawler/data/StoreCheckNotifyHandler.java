2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/handler/StoreCheckNotifyHandler.java
package com.chad.wechatdev.commons.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 门店审核事件处理
 *

 */
@Component
public class StoreCheckNotifyHandler extends AbstractHandler {

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
    // TODO 处理门店审核事件
    return null;
  }

}
