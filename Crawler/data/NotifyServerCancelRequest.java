16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/ios/struct/kernel/NotifyServerCancelRequest.java
package com.github.unidbg.ios.struct.kernel;

import com.github.unidbg.pointer.UnicornStructure;
import com.sun.jna.Pointer;

import java.util.Arrays;
import java.util.List;

public class NotifyServerCancelRequest extends UnicornStructure {

    public NotifyServerCancelRequest(Pointer p) {
        super(p);
    }

    public NDR_record NDR;
    public int clientId;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("NDR", "clientId");
    }

}
