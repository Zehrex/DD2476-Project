23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/core/util/ExceptionTracker.java
package com.alibaba.datax.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionTracker {
    public static final int STRING_BUFFER = 4096;

    public static String trace(Throwable ex) {
        StringWriter sw = new StringWriter(STRING_BUFFER);
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}