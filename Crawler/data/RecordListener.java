34
https://raw.githubusercontent.com/lzj-github/registry/master/naming/src/main/java/cn/lzj/nacos/naming/consistency/RecordListener.java
package cn.lzj.nacos.naming.consistency;

import cn.lzj.nacos.naming.core.Instances;

public interface RecordListener {

    void onChange(String key, Instances value);

    void onDelete(String key);
}
