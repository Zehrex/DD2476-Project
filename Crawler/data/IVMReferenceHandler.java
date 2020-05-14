140
https://raw.githubusercontent.com/GraxCode/threadtear/master/src/me/nov/threadtear/vm/IVMReferenceHandler.java
package me.nov.threadtear.vm;

import org.objectweb.asm.tree.ClassNode;

public interface IVMReferenceHandler {
  public ClassNode tryClassLoad(String name);
}
