2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/handler/ScanHandler.java
package com.chad.wechatdev.commons.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ScanHandler extends AbstractHandler {
	 @Override
	  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                      Map<String, Object> context, WxMpService weixinService,
                                      WxSessionManager sessionManager) throws WxErrorException {
		
		this.logger.info("ScanHandler");
		this.logger.info("ScanHandler");
		this.logger.info("ScanHandler");


	    return null;
	  }

}
