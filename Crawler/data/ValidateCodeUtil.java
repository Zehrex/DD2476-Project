6
https://raw.githubusercontent.com/taoroot/taoiot/master/src/main/java/com/github/taoroot/taoiot/security/filter/ValidateCodeUtil.java
package com.github.taoroot.taoiot.security.filter;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;

/**
 * @author : zhiyi
 * Date: 2020/5/5
 */
public class ValidateCodeUtil {

    private static TimedCache<String, ICaptcha> cache = new TimedCache<>(1000 * 60 * 5);

    public static ICaptcha getCaptcha(String key) {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        captcha.setGenerator(randomGenerator);
        // 重新生成code
        captcha.createCode();
        cache.put(key, captcha);
        return captcha;
    }

    public static boolean verifyCaptcha(String key, String value) {
        ICaptcha iCaptcha = cache.get(key);
        return iCaptcha != null && iCaptcha.verify(value);
    }
}
