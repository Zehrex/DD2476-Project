16
https://raw.githubusercontent.com/wmm1996528/unidbg_douyin10/master/src/test/java/com/sun/jna/JniDispatch32.java
package com.sun.jna;

import com.github.unidbg.*;
import com.github.unidbg.arm.HookStatus;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.HookContext;
import com.github.unidbg.hook.ReplaceCallback;
import com.github.unidbg.hook.hookzz.HookEntryInfo;
import com.github.unidbg.hook.hookzz.HookZz;
import com.github.unidbg.hook.hookzz.IHookZz;
import com.github.unidbg.hook.hookzz.WrapCallback;
import com.github.unidbg.hook.whale.IWhale;
import com.github.unidbg.hook.whale.Whale;
import com.github.unidbg.hook.xhook.IxHook;
import com.github.unidbg.linux.android.AndroidARMEmulator;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.XHookImpl;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.MemoryBlock;
import com.github.unidbg.pointer.UnicornPointer;
import com.github.unidbg.utils.Inspector;

import java.io.File;
import java.io.IOException;

public class JniDispatch32 extends AbstractJni {

    private static LibraryResolver createLibraryResolver() {
        return new AndroidResolver(23);
    }

    private static AndroidEmulator createARMEmulator() {
        return new AndroidARMEmulator("com.sun.jna");
    }

    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    private final DvmClass Native;

    private JniDispatch32() throws IOException {
        emulator = createARMEmulator();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(createLibraryResolver());
        memory.setCallInitFunction();

        vm = emulator.createDalvikVM(null);
        vm.setJni(this);
        vm.setVerbose(true);
        DalvikModule dm = vm.loadLibrary(new File("src/test/resources/example_binaries/armeabi-v7a/libcms.so"), false);
        dm.callJNI_OnLoad(emulator);
        module = dm.getModule();

        Native = vm.resolveClass("com/sun/jna/Native");

        Symbol __system_property_get = module.findSymbolByName("__system_property_get", true);
        MemoryBlock block = memory.malloc(0x10);
        Number ret = __system_property_get.call(emulator, "ro.build.version.sdk", block.getPointer())[0];
        System.out.println("sdk=" + new String(block.getPointer().getByteArray(0, ret.intValue())) + ", libc=" + memory.findModule("libc.so"));
    }

    private void destroy() throws IOException {
        emulator.close();
        System.out.println("destroy");
    }

    public static void main(String[] args) throws Exception {
        JniDispatch32 test = new JniDispatch32();

        test.test();

        test.destroy();
    }

    private void test() {
        IxHook xHook = XHookImpl.getInstance(emulator);
        xHook.register("libjnidispatch.so", "malloc", new ReplaceCallback() {
            @Override
            public HookStatus onCall(Emulator<?> emulator, HookContext context, long originFunction) {
                int size = context.getIntArg(0);
                context.push(size);
                System.out.println("malloc=" + size);
                return HookStatus.RET(emulator, originFunction);
            }

            @Override
            public void postCall(Emulator<?> emulator, HookContext context) {
                int size = context.pop();
                System.out.println("malloc=" + size + ", ret=" + context.getPointerArg(0));
            }
        }, true);
        xHook.refresh();

        IWhale whale = Whale.getInstance(emulator);
        Symbol free = emulator.getMemory().findModule("libc.so").findSymbolByName("free");
        whale.inlineHookFunction(free, new ReplaceCallback() {
            @Override
            public HookStatus onCall(Emulator<?> emulator, long originFunction) {
                System.out.println("WInlineHookFunction free=" + emulator.getContext().getPointerArg(0));
                return HookStatus.RET(emulator, originFunction);
            }
        });

        long start = System.currentTimeMillis();
        final int size = 0x20;
        Number ret = Native.callStaticJniMethod(emulator, "malloc(J)J", size);
        Pointer pointer = UnicornPointer.pointer(emulator, ret.intValue() & 0xffffffffL);
        assert pointer != null;
        pointer.setString(0, getClass().getName());
        vm.deleteLocalRefs();
        Inspector.inspect(pointer.getByteArray(0, size), "malloc ret=" + ret + ", offset=" + (System.currentTimeMillis() - start) + "ms");

        IHookZz hookZz = HookZz.getInstance(emulator);
        Symbol newJavaString = module.findSymbolByName("newJavaString");
        hookZz.wrap(newJavaString, new WrapCallback<RegisterContext>() {
            @Override
            public void preCall(Emulator<?> emulator, RegisterContext ctx, HookEntryInfo info) {
                Pointer value = ctx.getPointerArg(1);
                Pointer encoding = ctx.getPointerArg(2);
                System.out.println("newJavaString value=" + value.getString(0) + ", encoding=" + encoding.getString(0));
            }
        });

        ret = Native.callStaticJniMethod(emulator, "getNativeVersion()Ljava/lang/String;");
        long hash = ret.intValue() & 0xffffffffL;
        StringObject version = vm.getObject(hash);
        vm.deleteLocalRefs();
        System.out.println("getNativeVersion version=" + version.getValue() + ", offset=" + (System.currentTimeMillis() - start) + "ms");

        ret = Native.callStaticJniMethod(emulator, "getAPIChecksum()Ljava/lang/String;");
        hash = ret.intValue() & 0xffffffffL;
        StringObject checksum = vm.getObject(hash);
        vm.deleteLocalRefs();
        System.out.println("getAPIChecksum checksum=" + checksum.getValue() + ", offset=" + (System.currentTimeMillis() - start) + "ms");

        ret = Native.callStaticJniMethod(emulator, "sizeof(I)I", 0);
        vm.deleteLocalRefs();
        System.out.println("sizeof POINTER_SIZE=" + ret.intValue() + ", offset=" + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        if ("java/lang/System->getProperty(Ljava/lang/String;)Ljava/lang/String;".equals(signature)) {
            StringObject string = varArg.getObject(0);
            String string2;
            if (string.getValue().equals("http.proxyHost")) {
                string2 = "80";
            } else {
                string2 = System.getProperty(string.getValue());
            }
            return new StringObject(vm, string2);
        }

        return super.callStaticObjectMethod(vm, dvmClass, signature, varArg);
    }

}
