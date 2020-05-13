12
https://raw.githubusercontent.com/Col-E/SimAnalyzer/master/src/main/java/me/coley/analysis/exception/Validator.java
package me.coley.analysis.exception;

import me.coley.analysis.value.AbstractValue;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.Frame;

import java.util.function.BiPredicate;

/**
 * Validator for {@link ResolvableAnalyzerException}.
 *
 * @author Matt
 */
public interface Validator extends BiPredicate<MethodNode, Frame<AbstractValue>[]> {}
