140
https://raw.githubusercontent.com/GraxCode/threadtear/master/src/me/nov/threadtear/analysis/stack/IConstantReferenceHandler.java
package me.nov.threadtear.analysis.stack;

import java.util.List;

import org.objectweb.asm.tree.analysis.BasicValue;

public interface IConstantReferenceHandler {

  Object getFieldValueOrNull(BasicValue v, String owner, String name, String desc);

  Object getMethodReturnOrNull(BasicValue v, String owner, String name, String desc, List<? extends ConstantValue> values);

}
