11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/enums/MtccPhaseEnum.java
package com.yf.mtcc.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@RequiredArgsConstructor
@Getter
public enum MtccPhaseEnum {
    READY(0, "READY准备阶段"),


    TRYING(1, "try阶段"),


    CONFIRMING(2, "confirm阶段"),


    CANCELING(3, "cancel阶段");

    private final int code;

    private final String desc;

    public static MtccPhaseEnum acquireByCode(final int code) {
        return Arrays.stream(MtccPhaseEnum.values())
                .filter(v -> Objects.equals(v.getCode(), code))
                .findFirst().orElse(MtccPhaseEnum.TRYING);
    }
}
