12
https://raw.githubusercontent.com/Col-E/SimAnalyzer/master/src/main/java/me/coley/analysis/StaticGetFactory.java
package me.coley.analysis;

import me.coley.analysis.value.AbstractValue;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.FieldInsnNode;

/**
 * Factory for generating {@link AbstractValue} from static field references.
 *
 * @author Matt
 */
public class StaticGetFactory {
	/**
	 * @param insn
	 * 		Field instruction.
	 *
	 * @return Value of {@link Opcodes#GETSTATIC}. {@link Unresolved} for unknown values.
	 */
	public AbstractValue getStatic(FieldInsnNode insn) {
		return null;
	}
}
