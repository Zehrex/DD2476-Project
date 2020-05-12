3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/schedule/lb/LoadBalancer.java
package org.coral.jroutine.schedule.lb;

/**
 * load balancer.
 * 
 * @author lihao
 * @date 2020-05-12
 */
public interface LoadBalancer {

    public <T extends Instance> T select(T[] instances);
}
