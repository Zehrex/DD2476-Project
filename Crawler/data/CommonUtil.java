2
https://raw.githubusercontent.com/jiangvin/webtank/master/websocket/src/main/java/com/integration/socket/util/CommonUtil.java
package com.integration.socket.util;

/**
 * @author 蒋文龙(Vin)
 * @description
 * @date 2020/5/9
 */
public class CommonUtil {
    private static long id = 0;

    public static String getId() {
        return "generatedServerId=" + id++;
    }
}
