23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/common/plugin/AbstractJobPlugin.java
package com.alibaba.datax.common.plugin;

import com.webank.wedatasphere.exchangis.datax.common.constant.TransportType;

/**
 * Created by jingxing on 14-8-24.
 */
public abstract class AbstractJobPlugin extends AbstractPlugin {
    private TransportType transportType;

    public void setTransportType(TransportType transportType){
        this.transportType = transportType;
    }

    public TransportType getTransportType(){
        return this.transportType;
    }
    /**
     * @return the jobPluginCollector
     */
    public JobPluginCollector getJobPluginCollector() {
        return jobPluginCollector;
    }

    /**
     * @param jobPluginCollector the jobPluginCollector to set
     */
    public void setJobPluginCollector(
            JobPluginCollector jobPluginCollector) {
        this.jobPluginCollector = jobPluginCollector;
    }

    private JobPluginCollector jobPluginCollector;

}
