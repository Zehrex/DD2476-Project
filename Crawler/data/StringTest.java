2
https://raw.githubusercontent.com/wechaturl/wechat_url_api_java_sdk/master/src/test/java/StringTest.java
import com.fasterxml.jackson.core.JsonProcessingException;
import www.wechaturl.us.fangfeng.sdk.exception.DefaultException;
import www.wechaturl.us.fangfeng.sdk.service.DomainUrlProtectService;
import www.wechaturl.us.fangfeng.sdk.vo.UrlParam;
import org.junit.Assert;
import org.junit.Test;

public class StringTest {
  private static final int VALID_APPID = 12345678;
  private static final String VALID_APPKEY = "GetAppKeyAtThe:https://www.wechaturl.us";
  private static final String TESTING_URL = "https://www.google.com";

  @Test
  public void test() {
    UrlParam urlParam = new UrlParam(123, "tester");
    Assert.assertEquals("", urlParam.getStatus());
  }

  @Test
  public void test2() throws JsonProcessingException, DefaultException {
    UrlParam urlParam = new UrlParam(VALID_APPID, VALID_APPKEY);
    urlParam.setGroupId(4).setRequestUri("http://www.google.com");
    DomainUrlProtectService domainUrlProtectService = new DomainUrlProtectService();
    String res = domainUrlProtectService.dynamicSetURIParameters(urlParam);
    System.out.println(res);
  }

}
