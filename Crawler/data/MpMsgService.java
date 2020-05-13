6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/mp/handler/MpMsgService.java
package com.github.taoroot.taoiot.mp.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.github.taoroot.taoiot.security.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author : zhiyi
 * Date: 2020/5/7
 */
@Log4j2
@Component
@AllArgsConstructor
public class MpMsgService {

    private final WxMpService wxMpService;
    private final SecurityProperties securityProperties;

    /**
     * 客服消息
     */
    void sendMsg(String msg) {

    }

    /**
     * 模板消息
     */
    public void sendTemplateMsg1(String openId, String deviceName, String deviceInfo, String msgType, String createTime, String remark) {
        WxMpTemplateMessage tsMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId("HjUvN0HogQAPVfvN9b-Yyq_rC6SbxzTM7f7Eq9p746s")
                .build();

        // 测试账号
        if (StringUtils.isBlank(wxMpService.getWxMpConfigStorage().getAesKey())) {
            tsMessage.setTemplateId(securityProperties.getWx().getTemplateId());
        }

        tsMessage.addData(new WxMpTemplateData("keyword1", deviceName));
        tsMessage.addData(new WxMpTemplateData("keyword2", deviceInfo));
        tsMessage.addData(new WxMpTemplateData("keyword3", msgType));
        tsMessage.addData(new WxMpTemplateData("keyword4", createTime));
        tsMessage.addData(new WxMpTemplateData("remark", remark));
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(tsMessage);
        } catch (WxErrorException e) {
            log.error(e);
        }
    }

    public void sendTextMsg(String openId, String deviceName, String msg) {
        WxMpKefuMessage build = WxMpKefuMessage
                .TEXT()
                .toUser(openId)
                .content(deviceName + ": " + msg)
                .build();

        if (StringUtils.isBlank(deviceName)) {
            build.setContent(msg);
        }
        try {

            wxMpService.getKefuService().sendKefuMessage(build);
        } catch (WxErrorException e) {
            log.error(e);
        }
    }

    /**
     * 发送图片
     */
    public void sendImgMsg(String openId, String deviceName, byte[] img) {
        File file = null;
        try {
            // 上传
            file = new File(IdUtil.fastSimpleUUID());
            FileUtil.writeBytes(img, file);
            WxMediaUploadResult wxMediaUploadResult = wxMpService
                    .getMaterialService()
                    .mediaUpload(WxConsts.KefuMsgType.IMAGE, file);

            // 发送给用户
            WxMpKefuMessage build = WxMpKefuMessage
                    .IMAGE()
                    .toUser(openId)
                    .mediaId(wxMediaUploadResult.getMediaId())
                    .build();
            wxMpService.getKefuService().sendKefuMessage(build);
        } catch (WxErrorException e) {
            e.printStackTrace();
            file.deleteOnExit();
            log.error(e);
        }
    }
}
