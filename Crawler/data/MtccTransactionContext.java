11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/context/MtccTransactionContext.java
package com.yf.mtcc.common.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: 事务上下文在各个微服务中传递
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MtccTransactionContext implements Serializable {
    private String transactionId;
    private int phase;
    private int role;

}
