1
https://raw.githubusercontent.com/rubywooJ/beyond/master/src/test/java/cn/tsxygfy/beyond/IpUtilTest.java
package cn.tsxygfy.beyond;

import cn.tsxygfy.beyond.util.IpUtil;
import org.junit.Test;

public class IpUtilTest {
    @Test
    public void getIpTest() {
        String machineIp = IpUtil.getMachineIp();
        System.out.println(machineIp);
    }
}
