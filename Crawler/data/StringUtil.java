23
https://raw.githubusercontent.com/mrchengwenlong/NettyIM/master/im_lib/src/main/java/com/takiku/im_lib/util/StringUtil.java
package com.takiku.im_lib.util;

/**
 * author:chengwl
 * Description:
 * Date:2020/4/11
 */
public class StringUtil {
    public static boolean isEmpty(Object o) {
        return (null == o || o.toString().trim().equals("")) ? true : false;
    }
}
