4
https://raw.githubusercontent.com/zzl520/etcd-config-spring-boot-starter/master/src/main/java/com/zzl/etcd/config/annotation/EtcdPropertySources.java
package com.zzl.etcd.config.annotation;

import java.lang.annotation.*;

/**
 * @author zzl on 2020-03-20.
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EtcdPropertySources {

    EtcdPropertySource[] value();
}