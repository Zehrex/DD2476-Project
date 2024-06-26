7
https://raw.githubusercontent.com/zeoio/fabric-toolkit/master/bcp-install-common/src/main/java/com/cgb/bcpinstall/common/annotation/InvokeLog.java
/*
 *  Copyright CGB Corp All Rights Reserved.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.cgb.bcpinstall.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InvokeLog {

    /**
     * 是否写入日志 默认不写入
     *
     * @return
     */
    boolean persistence() default false;

    /**
     * 调用方法名称
     *
     * @return
     */
    String name() default "";

    /**
     * 描述
     *
     * @return
     */
    String description() default "";

    /**
     * 是否打印返回值
     *
     * @return
     */
    boolean printReturn() default true;
}
