4
https://raw.githubusercontent.com/zzl520/etcd-config-spring-boot-starter/master/src/main/java/com/zzl/etcd/config/model/ConfigType.java
package com.zzl.etcd.config.model;

/**
 * @author zzl on 2020-03-20.
 * @description
 */
public enum ConfigType {

    PROPERTIES("properties"),

    YAML("yaml");

    String type;

    ConfigType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
