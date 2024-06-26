16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/main/java/com/github/unidbg/hook/hookzz/IHookZz.java
package com.github.unidbg.hook.hookzz;

import com.github.unidbg.Symbol;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.IHook;
import com.github.unidbg.hook.ReplaceCallback;

public interface IHookZz extends IHook {

    int RS_SUCCESS = 1;

    void enable_arm_arm64_b_branch();
    void disable_arm_arm64_b_branch();

    <T extends RegisterContext> void wrap(long functionAddress, WrapCallback<T> callback);
    <T extends RegisterContext> void wrap(Symbol symbol, WrapCallback<T> callback);

    void replace(long functionAddress, ReplaceCallback callback);
    void replace(Symbol symbol, ReplaceCallback callback);

    void replace(long functionAddress, ReplaceCallback callback, boolean enablePostCall);
    void replace(Symbol symbol, ReplaceCallback callback, boolean enablePostCall);

}
