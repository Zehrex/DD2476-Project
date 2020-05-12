7
https://raw.githubusercontent.com/chenjk-520/keventbus/master/keventbus/src/main/java/com/util/keventbus/key/ExceptionKey.java
package com.util.keventbus.key;

/**
 * 文件描述：
 * 作者：chenjingkun708
 * 创建时间：2020/5/6
 * 更改时间：2020/5/6
 */
public interface ExceptionKey {
    //没有绑定异常
    String NOSUBSCRIBER = "NoSubscriber";
    //方法异常
    String METHOD_EXCEPTION = "method_exception";
}
