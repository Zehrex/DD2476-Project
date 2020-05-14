34
https://raw.githubusercontent.com/lzj-github/registry/master/naming/src/main/java/cn/lzj/nacos/naming/netty/MessageProtocol.java
package cn.lzj.nacos.naming.netty;

import lombok.Data;

/**
 * 自定义协议包
 */
@Data
public class MessageProtocol {
    //定义一次发送包体长度
    private int len;
    //一次发送包体内容
    private byte[] content;

}
