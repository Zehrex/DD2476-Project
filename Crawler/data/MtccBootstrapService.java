11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/MtccBootstrapService.java
package com.yf.mtcc.core.service;

import com.yf.mtcc.common.config.MtccConfig;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@FunctionalInterface
public interface MtccBootstrapService {

    void initMtccTransaction(MtccConfig mtccConfig);
}
