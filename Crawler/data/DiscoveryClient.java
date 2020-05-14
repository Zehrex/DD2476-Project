34
https://raw.githubusercontent.com/lzj-github/registry/master/client/src/main/java/cn/lzj/nacos/client/api/DiscoveryClient.java
package cn.lzj.nacos.client.api;

import cn.lzj.nacos.api.pojo.Instance;

import java.util.List;

public interface DiscoveryClient {

    List<Instance> getInstances(String serviceId);
}
