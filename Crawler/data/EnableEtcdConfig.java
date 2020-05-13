4
https://raw.githubusercontent.com/zzl520/etcd-config-spring-boot-starter/master/src/main/java/com/zzl/etcd/config/annotation/EnableEtcdConfig.java
package com.zzl.etcd.config.annotation;

import com.zzl.etcd.config.component.EtcdConfigBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zzl on 2020-03-24.
 * @description
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EtcdConfigBeanDefinitionRegistrar.class)
public @interface EnableEtcdConfig {

}
