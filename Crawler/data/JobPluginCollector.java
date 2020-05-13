23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/common/plugin/JobPluginCollector.java
package com.alibaba.datax.common.plugin;

import java.util.List;
import java.util.Map;

/**
 * Created by jingxing on 14-9-9.
 */
public interface JobPluginCollector extends PluginCollector {

    /**
     * 从Task获取自定义收集信息
     */
    Map<String, List<String>> getMessage();

    /**
     * 从Task获取自定义收集信息
     */
    List<String> getMessage(String key);
}
