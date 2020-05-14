18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/common/src/main/java/com/openjfx/database/common/Handler.java
package com.openjfx.database.common;

/**
 * <p>
 * can pass param and return value function interface
 * </p>
 *
 * @param <R> return data type
 * @param <D> pass function param type
 * @author yangkui
 * @since 1.0
 */
@FunctionalInterface
public interface Handler<R, D> {
    /**
     * that method use have return value and pass param
     *
     *
     * @param d pass param
     * @return return fix type result
     */
    R handler(D d);
}
