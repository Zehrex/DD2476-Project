5
https://raw.githubusercontent.com/sstewartgallus/jsystemf/master/src/com/sstewartgallus/runtime/Infotable.java
package com.sstewartgallus.runtime;

import java.lang.invoke.MethodHandle;
import java.util.List;

// fixme... eventually eliminate ...
record Infotable(
        List<Class<?>>arguments,
        MethodHandle entryPoint) {
}
