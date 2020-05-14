25
https://raw.githubusercontent.com/griabcrh/vue-activiti-demo/master/vue-activiti-service-demo/src/main/java/com/activiti/service/ProcessDesignService.java
package com.activiti.service;

import org.activiti.engine.repository.Model;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author yiyoung
 * @date 2020/4/21
 */
public interface ProcessDesignService {
    
    void createModel(String key,String name, String category, String descp) throws UnsupportedEncodingException;
    
    List<Model> listModel();
    
    void deleteModel(String modelId);
    
    String deployModel(String modelId) throws Exception;
}
