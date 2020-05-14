34
https://raw.githubusercontent.com/lzj-github/registry/master/client/src/main/java/cn/lzj/nacos/client/api/NamingService.java
package cn.lzj.nacos.client.api;

import cn.lzj.nacos.api.pojo.Instance;

import java.util.List;

public interface NamingService {

    public void registerInstance(String serviceName, String groupName , String ip, int port,String nameSpaceId);

    public void serviceFound(String namespaceId);

    public List<Instance> selectInstances(String serviceName);
}
