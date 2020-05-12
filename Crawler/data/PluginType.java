23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/common/constant/PluginType.java
package com.alibaba.datax.common.constant;

/**
 * Created by jingxing on 14-8-31.
 */
public enum PluginType {
    //pluginType还代表了资源目录，很难扩展,或者说需要足够必要才扩展。先mark Handler（其实和transformer一样），再讨论
    READER("reader"), TRANSFORMER("transformer"), WRITER("writer"), HANDLER("handler");

    private String pluginType;

    private PluginType(String pluginType) {
        this.pluginType = pluginType;
    }

    @Override
    public String toString() {
        return this.pluginType;
    }
}
