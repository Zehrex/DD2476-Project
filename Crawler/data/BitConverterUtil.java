1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/core/util/BitConverterUtil.java
package com.game.core.util;

/**
 * @Author: wx
 * @Date: 上午 11:00 2019/12/27 0027
 * @Desc: 字节数组的转换
 * @version:
 */
public class BitConverterUtil {

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 11:00 2019/12/27 0027
     * @Param
     */
    public static byte[] intToBytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
        }
        return b;
    }

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 11:00 2019/12/27 0027
     * @Param
     */
    public static int BytesToInt(byte[] b, int start) {
        return b[3 + start] & 0xFF |
                (b[2 + start] & 0xFF) << 8 |
                (b[1 + start] & 0xFF) << 16 |
                (b[start] & 0xFF) << 24;
    }

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 11:00 2019/12/27 0027
     * @Param
     */
    public static byte[] FloatToBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return intToBytes(intBits);
    }

    /**
     * @Author: @
     * @Desc:
     * @Date: 上午 11:00 2019/12/27 0027
     * @Param
     */
    public static float BytesToFloat(byte[] bytes, int start) {
        int i = BytesToInt(bytes, start);
        return Float.intBitsToFloat(i);
    }

}
