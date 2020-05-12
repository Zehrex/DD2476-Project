6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/mp/MpEndpoint.java
package com.github.taoroot.taoiot.mp;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.IdUtil;
import com.github.taoroot.taoiot.security.SecurityUser;
import com.github.taoroot.taoiot.security.SecurityUserDetailsService;
import com.github.taoroot.taoiot.security.annotation.NotAuth;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;


/**
 * @author zhiyi
 */
@Log4j2
@RestController
@RequestMapping("/mp")
@NotAuth
@AllArgsConstructor
public class MpEndpoint {

    private final WxMpService wxMpService;

    private final SecurityUserDetailsService userDetailsService;

    private final Map<String, MpHandler> handlerMap;

    private static final TimedCache<Long, WxMpXmlMessage> CACHE = new TimedCache<>(1000 * 60 * 24);

    @SneakyThrows
    @PostMapping("/{userId}/{token}/img")
    public String upload(@RequestParam("file") MultipartFile file,
                         @PathVariable("userId") Integer userId,
                         @PathVariable("token") String token) {

        log.info("user: {} upload img: {}", userId, file.getOriginalFilename());
        SecurityUser userDetails = (SecurityUser) userDetailsService.loadUserByUserId(userId);
        if (userDetails == null || !userDetails.getToken().equals(token)) {
            return "token error";
        }

        try {
            // 文件名
            String originalFilename = file.getOriginalFilename();
            // 扩展名
            assert originalFilename != null;
            String fileExtName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
            File tempFile = File.createTempFile(IdUtil.fastSimpleUUID(), "." + fileExtName);
            file.transferTo(tempFile);
            WxMediaUploadResult wxMediaUploadResult = wxMpService
                    .getMaterialService()
                    .mediaUpload(WxConsts.KefuMsgType.IMAGE, tempFile);

            // 发送给用户
            WxMpKefuMessage build = WxMpKefuMessage
                    .IMAGE()
                    .toUser(userDetails.getWxMpOpenid())
                    .mediaId(wxMediaUploadResult.getMediaId())
                    .build();
            wxMpService.getKefuService().sendKefuMessage(build);
            return wxMediaUploadResult.getMediaId();
        } catch (Exception e) {
            log.error("upload error: ", e);
        }
        return "-1";
    }

    @GetMapping(produces = "text/plain;charset=utf-8")
    public String auth(@RequestParam(name = "signature", required = false) String signature,
                       @RequestParam(name = "timestamp", required = false) String timestamp,
                       @RequestParam(name = "nonce", required = false) String nonce,
                       @RequestParam(name = "echostr", required = false) String echostr) {

        log.info("mp auth：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "-1";
    }

    /**
     * 新的消息
     */
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String newMsg(@RequestBody String requestBody,
                         @RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("openid") String openid,
                         @RequestParam(name = "encrypt_type", required = false) String encryptType,
                         @RequestParam(name = "msg_signature", required = false) String msgSignature) {

        // 验证 测试账号不进行验证
        if (StringUtils.isNoneBlank(wxMpService.getWxMpConfigStorage().getAesKey())
                && !wxMpService.checkSignature(timestamp, nonce, signature)) {
            return "-1";
        }

        WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
        log.info("new msg: {}", inMessage);

        // 过滤重复消息
        if (CACHE.get(inMessage.getMsgId()) != null) {
            return "-1";
        }
        CACHE.put(inMessage.getMsgId(), inMessage);

        // 用户登录
        SecurityUser userDetails = (SecurityUser) userDetailsService.loadUserByWechat(openid);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 消息处理
        MpHandler mpHandler = handlerMap.get(inMessage.getMsgType());
        String result = "消息类型不支持";
        if (mpHandler != null) {
            try {
                result = mpHandler.process(inMessage);
            } catch (Exception e) {
                result = "系统异常";
                log.error(e);
                e.printStackTrace();
            }
        }

        return WxMpXmlOutMessage.TEXT().content(result)
                .fromUser(inMessage.getToUser()).toUser(inMessage.getFromUser())
                .build().toXml();
    }
}
