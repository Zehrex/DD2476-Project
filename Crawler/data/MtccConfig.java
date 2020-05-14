11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/config/MtccConfig.java
package com.yf.mtcc.common.config;

import lombok.Data;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Data
public class MtccConfig {

    private String serializer = "kryo";

    private String serviceName;

    private int retryMax = 5;

    private MtccDbConfig dbConfig;

    private Long scheduleInitDelay = 180L;

    private Long scheduleDelay = 90L;

}
