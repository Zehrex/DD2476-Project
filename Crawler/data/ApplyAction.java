34
https://raw.githubusercontent.com/lzj-github/registry/master/naming/src/main/java/cn/lzj/nacos/naming/consistency/ApplyAction.java
package cn.lzj.nacos.naming.consistency;

/**
 * 标记实例的动作类
 */
public enum ApplyAction {

    //service数据更改(添加实例)
    CHANGE,

    //删除实例
    DELETE
}
