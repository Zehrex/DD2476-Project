11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/serializer/ObjectSerializer.java
package com.yf.mtcc.common.serializer;

import com.yf.mtcc.common.exception.MtccException;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: 序列化器接口
 */
public interface ObjectSerializer {

    byte[] serialize(Object obj) throws MtccException;


    <T> T deSerialize(byte[] param, Class<T> clazz) throws MtccException;

}
