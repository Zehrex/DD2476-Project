140
https://raw.githubusercontent.com/GraxCode/threadtear/master/src/me/nov/threadtear/analysis/rewriter/value/values/NumberValue.java
package me.nov.threadtear.analysis.rewriter.value.values;

import java.util.Objects;

import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.BasicValue;

import me.nov.threadtear.analysis.rewriter.value.CodeReferenceValue;
import me.nov.threadtear.util.asm.Instructions;

public class NumberValue extends CodeReferenceValue {

  private Object value;

  public NumberValue(BasicValue type, AbstractInsnNode node, Object value) {
    super(type, node);
    if (value instanceof Number || value instanceof Character || value instanceof Boolean) {
      this.value = Objects.requireNonNull(value);
    } else {
      throw new IllegalArgumentException("not a number");
    }
  }

  @Override
  public boolean isKnownValue() {
    return true;
  }

  @Override
  public boolean isRequiredInCode() {
    return false;
  }

  @Override
  public CodeReferenceValue combine() {
    return this;
  }

  @Override
  public boolean equalsWith(CodeReferenceValue obj) {
    if (this == obj)
      return true;
    if (obj instanceof NumberValue) {
      return ((NumberValue) obj).value.equals(value);
    }
    return false;
  }

  @Override
  public InsnList cloneInstructions() {
    return Instructions.singleton(new LdcInsnNode(getStackValueOrNull()));
  }

  @Override
  public InsnList getInstructions() {
    return Instructions.singleton(node);
  }

  @Override
  public Object getStackValueOrNull() {
    if (value instanceof Character) {
      return (int) ((Character) value).charValue();
    }
    if (value instanceof Boolean) {
      return ((Boolean) value).booleanValue() ? 1 : 0;
    }
    return value;
  }
}