1
https://raw.githubusercontent.com/miaoo92/xxl-job-mongo/master/src/main/java/com/avon/rga/core/route/strategy/ExecutorRouteLast.java
package com.avon.rga.core.route.strategy;

import com.avon.rga.core.route.ExecutorRouter;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
