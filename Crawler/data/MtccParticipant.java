11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/domain/MtccParticipant.java
package com.yf.mtcc.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: 记录事务参与者的bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MtccParticipant implements Serializable {
    private String transactionId;
    private MtccInvocation confirmInvocation;
    private MtccInvocation cancelInvocation;
}
