2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/config/WechatMpProperties.java
package com.chad.wechatdev.commons.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "wechat.mp")
public class WechatMpProperties {
  /**
   * 设置微信公众号的appid
   */
  private String appId;

  /**
   * 是否显示日志
   */
  private boolean show;

  public boolean isShow() {
    return show;
  }

  public void setShow(boolean show) {
    this.show = show;
  }

  /**
   *
   */
  public final static String DOMAN_URL = "https://cloudpos2-scanorderqa.topbole.com";

;  /**
   * 微信开放平台二维码连接
   */
  public final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

  /**
   * access_token 地址 有效期2個小時
   */
  public final static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  /**
   * jssdk 地址 有效期2個小時
   */
  public final static String JSAPI_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

  /**
   * 开放平台用戶授權获取access_token地址
   */
  public final static String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

  /**
   * 授权access_token校验 是否有效
   */
  public final static String OPEN_ACCESS_TOKEN_ISEFFECTIVE_URL="https://api.weixin.qq.com/sns/auth?access_token=%s&openid=%s";

  /**
   * 失效token刷新access_token
   */
  public final static  String OPEN_ACCESS_TOKEN_REFRESH_URL ="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

  public final static String SEARCHER_GOODSDATA ="http://cloudposapi2qa.topbole.com/cloudpos-cloudposapi/scannerorder/selectByProdname.htm?companyid=%s&shopid=%s&prodname=%s";

  /**
   * 获取用户信息
   */
  public final static String OPEN_USER_INFO_URL ="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
  /**
   * 设置微信公众号的app secret
   */
  private String secret;

  /**
   * 设置微信公众号的token
   */
  private String token;

  /**
   * 设置微信公众号的EncodingAESKey
   */
  private String aesKey;

  public String getAesKey() {
    return aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  public String getAppId() {
    return this.appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }




  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }
}
