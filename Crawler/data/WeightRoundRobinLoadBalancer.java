3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/schedule/lb/WeightRoundRobinLoadBalancer.java
package org.coral.jroutine.schedule.lb;

/**
 * round robin for load balance.
 * 
 * @author lihao
 * @date 2020-05-12
 */
public class WeightRoundRobinLoadBalancer implements LoadBalancer {

    @Override
    public <T extends Instance> T select(T[] instances) {
        return null;
    }

}