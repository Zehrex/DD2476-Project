34
https://raw.githubusercontent.com/lzj-github/registry/master/naming/src/main/java/cn/lzj/nacos/naming/core/Instances.java
package cn.lzj.nacos.naming.core;

import cn.lzj.nacos.api.pojo.Instance;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Instances {

    private List<Instance> instanceList = new ArrayList<>();
}
