11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/enums/MtccRoleEnum.java
package com.yf.mtcc.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Getter
@RequiredArgsConstructor
public enum MtccRoleEnum {
    INITIATOR(1, "发起者"),


    CONSUMER(2, "消费者"),


    PROVIDER(3, "提供者"),


    NESTER(4, "嵌套者");

    private final int code;

    private final String desc;
}
