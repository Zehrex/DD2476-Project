10
https://raw.githubusercontent.com/373675032/Molihub/master/src/utils/ActiveCodeUtils.java
package utils;

import java.util.UUID;
/**
 * 生成注册激活码的工具类
 */
public class ActiveCodeUtils {
	public static String createActiveCode() {
		return UUID.randomUUID().toString();
	}
}
