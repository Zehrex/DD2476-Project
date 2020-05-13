23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/common/plugin/Pluginable.java
package com.alibaba.datax.common.plugin;

import com.alibaba.datax.common.util.Configuration;

import java.util.List;

public interface Pluginable {
    String getDeveloper();

    String getDescription();

    void setPluginConf(Configuration pluginConf);

    void init();

    void destroy();

    String getPluginName();

    Configuration getPluginJobConf();

    List<Configuration> getPeerPluginJobConfList();

    List<String> getPeerPluginNameList();

    void setPluginJobConf(Configuration jobConf);

    void addPeerPluginJobConf(Configuration peerPluginJobConf);

    void addPeerPluginName(String peerPluginName);

}
