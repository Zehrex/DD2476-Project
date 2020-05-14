16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/spi/ValuePair.java
package com.github.unidbg.spi;

public interface ValuePair {

    void set(String key, Object value);
    <T> T get(String key);

}
