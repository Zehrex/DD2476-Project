16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/hook/InvocationContext.java
package com.github.unidbg.hook;

public interface InvocationContext {

    void push(Object... objs);

    <T> T pop();

}
