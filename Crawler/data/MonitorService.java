3
https://raw.githubusercontent.com/cyl2cyl/nginx_service_discovery/master/src/main/java/com/xm/service/discovery/service/MonitorService.java
package com.xm.service.discovery.service;

import com.alibaba.nacos.api.exception.NacosException;

import java.io.IOException;

public interface MonitorService {

    void updateNginxFromNacos() throws IOException, InterruptedException, NacosException;
}
