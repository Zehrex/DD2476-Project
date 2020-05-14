11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/domain/MtccInvocation.java
package com.yf.mtcc.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MtccInvocation implements Serializable {
    private Class targetClass;
    private String methodName;
    private Object[] args;
    private Class[] parameterTypes;

}
